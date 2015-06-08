package com.moreno.weatherapp.db.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.moreno.weatherapp.db.WeatherDBHelper;
import com.moreno.weatherapp.util.DBConstants;

/**
 * Custom cursor loader for retrieving data from DB
 * Created on 08.06.2015.
 */
public class WeatherCursorLoader extends AsyncTaskLoader<Cursor> {
    private final SQLiteDatabase mDb;
    private Cursor mCursor;

    public WeatherCursorLoader(Context context) {
        super(context);
        mDb = new WeatherDBHelper(context).getReadableDatabase();
    }

    @Override
    public Cursor loadInBackground() {
        String[] columns = {
                DBConstants.COLUMN_NAME_ID,
                DBConstants.MainTable.COLUMN_NAME_DATE,
                DBConstants.MainTable.COLUMN_NAME_WEATHER_TYPE,
                DBConstants.MainTable.COLUMN_NAME_WEATHER_ICON_TYPE,
                DBConstants.MainTable.COLUMN_NAME_MIN_TEMPERATURE,
                DBConstants.MainTable.COLUMN_NAME_MAX_TEMPERATURE,
                DBConstants.MainTable.COLUMN_NAME_SRT,
                DBConstants.MainTable.COLUMN_NAME_SST,
        };

        Cursor cursor = mDb.query(DBConstants.MainTable.MAIN_TABLE_NAME, columns, null, null, null, null, DBConstants.MainTable.COLUMN_NAME_DATE + " ASC");
        cursor.registerContentObserver(new ContentObserver(null) {
            @Override
            public void onChange(boolean selfChange) {
                onContentChanged();
            }
        });
        cursor.moveToFirst();
        return cursor;
    }

    @Override
    public void onCanceled(Cursor data) {
        if (data != null && !data.isClosed()) {
            data.close();
        }
    }

    @Override
    public void deliverResult(Cursor data) {
        super.deliverResult(data);
        if (isReset() && data != null && !data.isClosed()) {
            data.close();
            return;
        }
        Cursor oldCursor = mCursor;
        mCursor = data;

        if (isStarted()) {
            super.deliverResult(data);
        }

        if (oldCursor != null && mCursor != oldCursor) {
            oldCursor.close();
        }
    }

    @Override
    protected void onStartLoading() {
        if (mCursor != null) {
            deliverResult(mCursor);
        }
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();
        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
            mCursor = null;
        }
    }
}
