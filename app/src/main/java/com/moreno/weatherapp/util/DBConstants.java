package com.moreno.weatherapp.util;

import android.provider.BaseColumns;

/**
 * Class stores constants for DB
 * Created on 08.06.2015.
 */
public class DBConstants {

    public static final String DATABASE_NAME = "WeatherIt.db";
    public static final int DATABASE_VERSION = 1;
    public static final String COLUMN_NAME_ID = BaseColumns._ID;

    public static class MainTable {
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_WEATHER_TYPE = "weather_type";
        public static final String COLUMN_NAME_WEATHER_ICON_TYPE = "weather_icon_type";
        public static final String COLUMN_NAME_MIN_TEMPERATURE = "min_temperature";
        public static final String COLUMN_NAME_MAX_TEMPERATURE = "max_temperature";
        public static final String COLUMN_NAME_SRT = "srt";
        public static final String COLUMN_NAME_SST = "sst";
        public static final String MAIN_TABLE_NAME = "main_weather_data";
    }

    public static class WeatherTypeTable {
        public static final String WEATHER_TYPE_TABLE_NAME = "weather_type";
        public static final String COLUMN_NAME_TYPE = "type";
    }

    public static class WeatherIconTable {
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String WEATHER_ICON_TYPE_TABLE_NAME = "weather_icon_type";
    }

    public static class GeoDataTable {
        public static final String GEO_DATA_TABLE_NAME = "geo_data";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_TIME_ZONE = "time_zone";
        public static final String COLUMN_NAME_OS = "os";
    }
}
