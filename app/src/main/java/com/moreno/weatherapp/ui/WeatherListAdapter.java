package com.moreno.weatherapp.ui;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moreno.weatherapp.R;
import com.moreno.weatherapp.util.DBConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Custom cursor adapter for RecyclerView
 * Created on 08.06.2015.
 */
public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListItemHolder> {

    private Cursor mCursor;
    private int mIconIndex;
    private int mDateIndex;
    private int mWeatherIndex;
    private int mMinTempIndex;
    private int mMaxTempIndex;
    private int mSrtIndex;
    private int mSstIndex;

    @Override
    public WeatherListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        WeatherListItemHolder vh = new WeatherListItemHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(WeatherListItemHolder holder, int position) {
        mCursor.moveToPosition(position);
        DateFormat format = SimpleDateFormat.getDateTimeInstance();
        String date = format.format(new Date(mCursor.getLong(mDateIndex)));
        holder.mDateView.setText(date);
        holder.mForecastView.setText(mCursor.getString(mWeatherIndex));
        holder.mIconView.setImageResource(R.drawable.default_icon);
        double minTemp = mCursor.getDouble(mMinTempIndex);
        holder.mMinTempView.setText("Min: " + minTemp);
        double maxTemp = mCursor.getDouble(mMaxTempIndex);
        holder.mMaxTempView.setText("Max: " + maxTemp);
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    /**
     * Set cursor with new content and refresh data list
     * @param newCursor New cursor data
     */
    public void changeCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        } else {
            if (newCursor != null) {
                mIconIndex = newCursor.getColumnIndex(DBConstants.MainTable.COLUMN_NAME_WEATHER_ICON_TYPE);
                mDateIndex = newCursor.getColumnIndex(DBConstants.MainTable.COLUMN_NAME_DATE);
                mWeatherIndex = newCursor.getColumnIndex(DBConstants.MainTable.COLUMN_NAME_WEATHER_TYPE);
                mMinTempIndex = newCursor.getColumnIndex(DBConstants.MainTable.COLUMN_NAME_MIN_TEMPERATURE);
                mMaxTempIndex = newCursor.getColumnIndex(DBConstants.MainTable.COLUMN_NAME_MAX_TEMPERATURE);
                mSrtIndex = newCursor.getColumnIndex(DBConstants.MainTable.COLUMN_NAME_SRT);
                mSstIndex = newCursor.getColumnIndex(DBConstants.MainTable.COLUMN_NAME_SST);
            }
        }
        mCursor = newCursor;
        notifyDataSetChanged();
    }
}
