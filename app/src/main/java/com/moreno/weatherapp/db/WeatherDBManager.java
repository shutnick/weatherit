package com.moreno.weatherapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.moreno.weatherapp.db.dao.DailyWeatherItem;
import com.moreno.weatherapp.db.dao.GeoDataItem;
import com.moreno.weatherapp.util.DBConstants;

/**
 * Helper class to manage DB content
 * Created on 08.06.2015.
 */
public class WeatherDBManager {

    private final SQLiteDatabase mDb;

    public WeatherDBManager(Context context) {
        WeatherDBHelper helper = new WeatherDBHelper(context);
        mDb = helper.getWritableDatabase();
    }

    /**
     * Insert new row to DB with daily weather data
     * @param data Holder object for required data
     */
    public void insertDailyWeather(DailyWeatherItem data) {
        ContentValues values = new ContentValues();
        values.put(DBConstants.MainTable.COLUMN_NAME_DATE, data.date());
        values.put(DBConstants.MainTable.COLUMN_NAME_WEATHER_TYPE, data.weatherType());
        values.put(DBConstants.MainTable.COLUMN_NAME_WEATHER_ICON_TYPE, data.weatherIconId());
        values.put(DBConstants.MainTable.COLUMN_NAME_MIN_TEMPERATURE, data.minTemp());
        values.put(DBConstants.MainTable.COLUMN_NAME_MAX_TEMPERATURE, data.maxTemp());
        values.put(DBConstants.MainTable.COLUMN_NAME_SRT, data.srt());
        values.put(DBConstants.MainTable.COLUMN_NAME_SST, data.sst());

        mDb.insert(DBConstants.MainTable.MAIN_TABLE_NAME, null, values);
    }

    /**
     * Replace content of geo data in DB
     * @param data  Holder object for required data
     */
    public void insertGeoData(GeoDataItem data) {
        mDb.delete(DBConstants.GeoDataTable.GEO_DATA_TABLE_NAME, null, null);
        ContentValues values = new ContentValues();
        values.put(DBConstants.GeoDataTable.COLUMN_NAME_LATITUDE, data.latitude());
        values.put(DBConstants.GeoDataTable.COLUMN_NAME_LONGITUDE, data.lonitude());
        values.put(DBConstants.GeoDataTable.COLUMN_NAME_TIME_ZONE, data.timeZone());
        values.put(DBConstants.GeoDataTable.COLUMN_NAME_OS, data.os());
        mDb.insert(DBConstants.GeoDataTable.GEO_DATA_TABLE_NAME, null, values);
    }

    /**
     * Clear content of weather data table
     */
    public void resetData() {
        mDb.delete(DBConstants.MainTable.MAIN_TABLE_NAME, null, null);
    }
}
