package com.moreno.weatherapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moreno.weatherapp.R;

/**
 * View holder for weather list items
 * Created on 08.06.2015.
 */
public class WeatherListItemHolder extends RecyclerView.ViewHolder{
    public TextView mDateView;
    public ImageView mIconView;
    public TextView mForecastView;
    public TextView mMinTempView;
    public TextView mMaxTempView;

    public WeatherListItemHolder(View itemView) {
        super(itemView);
        mDateView = (TextView) itemView.findViewById(R.id.weather_date);
        mIconView = (ImageView) itemView.findViewById(R.id.weather_icon);
        mForecastView = (TextView) itemView.findViewById(R.id.weather_forecast);
        mMinTempView = (TextView) itemView.findViewById(R.id.weather_min_temp);
        mMaxTempView = (TextView) itemView.findViewById(R.id.weather_max_temp);
    }
}
