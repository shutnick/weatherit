package com.moreno.weatherapp.db.dao;

/**
 * Holder class to store daily weather data
 * Created on 08.06.2015.
 */
public class DailyWeatherItem {
    private long mDate;
    private String mWeatherType;
    private String mWeatherIconId;
    private double mMinTemp;
    private double mMaxTemp;
    private long mSrt;
    private long mSst;

    public DailyWeatherItem(long date, String weatherType, String wetherIconType
            , double minTemp, double maxTemp, long srt, long sst) {
        mDate = date;
        mWeatherType = weatherType;
        mWeatherIconId = wetherIconType;
        mMinTemp = minTemp;
        mMaxTemp = maxTemp;
        mSrt = srt;
        mSst = sst;
    }

    public long date() {
        return mDate;
    }

    public String weatherType() {
        return mWeatherType;
    }

    public String weatherIconId() {
        return mWeatherIconId;
    }

    public double minTemp() {
        return mMinTemp;
    }

    public double maxTemp() {
        return mMaxTemp;
    }

    public long srt() {
        return mSrt;
    }

    public long sst() {
        return mSst;
    }
}
