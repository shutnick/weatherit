package com.moreno.weatherapp.ui;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.moreno.weatherapp.R;
import com.moreno.weatherapp.db.WeatherDBHelper;
import com.moreno.weatherapp.db.loader.WeatherCursorLoader;
import com.moreno.weatherapp.ui.warning.WarningDialog;
import com.moreno.weatherapp.geo.LocationLoader;
import com.moreno.weatherapp.ui.warning.WarningHandler;
import com.moreno.weatherapp.util.CheckInternetUtil;
import com.moreno.weatherapp.util.DBConstants;

/**
 * Main ui component to show weather data
 */

public class WeatherListActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final int DEFAULT_LOADER_ID = 0;
    public final String TAG = getClass().getSimpleName();
    private RecyclerView mListView;
    private LinearLayoutManager mLayoutManager;
    private WeatherListAdapter mAdapter;
    private Handler mWarningHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheather_list);
        mListView = (RecyclerView) findViewById(R.id.weather_list);
        mLayoutManager = new LinearLayoutManager(this);
        mListView.setLayoutManager(mLayoutManager);
        mAdapter = new WeatherListAdapter();
        mListView.setAdapter(mAdapter);

        getLoaderManager().initLoader(DEFAULT_LOADER_ID, null, this);

        mWarningHandler = new WarningHandler(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wheather_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!CheckInternetUtil.isInternetEnabled(this)) {
            WarningDialog.createDialog(R.string.warning_dialog_title_internet
                    , R.string.warning_dialog_message_internet)
            .show(getFragmentManager(), WarningDialog.WARNING_FRAGMENT_TAG);
        } else {
            refreshData(LocationManager.NETWORK_PROVIDER);
        }
    }

    private void refreshData(final String provider) {
        LocationLoader.getCurrentLocation(this);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "Loading created");
        return new WeatherCursorLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.i(TAG, "Loading finished");
        if (data.getCount() == 0) {
            mWarningHandler.sendEmptyMessage(WarningHandler.NO_DATA_TO_SHOW);
        }
        mAdapter.changeCursor(data);
        fillHeader();
    }

    private void fillHeader() {
        SQLiteDatabase db = new WeatherDBHelper(this).getReadableDatabase();
        Cursor headerCursor = db.query(DBConstants.GeoDataTable.GEO_DATA_TABLE_NAME, null, null, null
                , null, null, null);
        headerCursor.moveToFirst();
        if (headerCursor.getCount() != 0) {
            int latitudeIndex = headerCursor.getColumnIndex(DBConstants.GeoDataTable.COLUMN_NAME_LATITUDE);
            int longitudeIndex = headerCursor.getColumnIndex(DBConstants.GeoDataTable.COLUMN_NAME_LONGITUDE);
            int timeZoneIndex = headerCursor.getColumnIndex(DBConstants.GeoDataTable.COLUMN_NAME_TIME_ZONE);
            int osIndex = headerCursor.getColumnIndex(DBConstants.GeoDataTable.COLUMN_NAME_OS);
            TextView latitudeView = (TextView) findViewById(R.id.weather_latitude);
            TextView longitudeView = (TextView) findViewById(R.id.weather_longitude);
            TextView timeZoneView = (TextView) findViewById(R.id.weather_timezone);
            TextView osView = (TextView) findViewById(R.id.weather_os);
            latitudeView.setText("Lt: " + headerCursor.getDouble(latitudeIndex));
            longitudeView.setText("Lg: " + headerCursor.getDouble(longitudeIndex));
            timeZoneView.setText("Time zone: " + headerCursor.getString(timeZoneIndex));
            osView.setText("OS: " + headerCursor.getDouble(osIndex));
        }

        headerCursor.close();
    }

    @Override
    public void onLoaderReset(Loader loader) {
        Log.i(TAG, "Loading reset");
    }
}
