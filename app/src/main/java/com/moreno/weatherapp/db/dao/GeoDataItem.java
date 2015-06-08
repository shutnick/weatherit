package com.moreno.weatherapp.db.dao;

/**
 * Holder class for geo data
 * Created on 08.06.2015.
 */
public class GeoDataItem {
    private double mLatitude;
    private double mLongitude;
    private String mTimeZone;
    private double mOs;

    public double latitude() {
        return mLatitude;
    }

    public double lonitude() {
        return mLongitude;
    }

    public String timeZone() {
        return mTimeZone;
    }

    public double os() {
        return mOs;
    }

    public GeoDataItem(double latitude, double longitude, String timeZone, double os) {
        mLatitude = latitude;
        mLongitude = longitude;
        mTimeZone = timeZone;
        mOs = os;
    }
}
