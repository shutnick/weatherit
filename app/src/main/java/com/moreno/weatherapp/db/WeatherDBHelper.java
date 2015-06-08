package com.moreno.weatherapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.moreno.weatherapp.util.DBConstants;

/**
 * Helper class to create DB
 * Created on 08.06.2015.
 */
public class WeatherDBHelper extends SQLiteOpenHelper {
    public static final String DB_INTEGER_TYPE = " INTEGER";
    public static final String DB_FLOAT_TYPE = " REAL";
    public static final String DB_STRING_TYPE = " TEXT";
    public static final String PRIMARY_KEY = " PRIMARY KEY";
    public static final String AUTOINCREMENT = " AUTOINCREMENT";
    public static final String CREATE_TABLE = "CREATE TABLE ";
    public static final String COMMA = ",";
    public static final String DROP_ALL_TABLES = "DROP TABLE IF EXIST *";

    private final String SQL_CREATE_MAIN_TABLE = CREATE_TABLE + DBConstants.MainTable.MAIN_TABLE_NAME + " (" +
            DBConstants.COLUMN_NAME_ID + DB_INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA +
            DBConstants.MainTable.COLUMN_NAME_DATE + DB_INTEGER_TYPE + COMMA +
            DBConstants.MainTable.COLUMN_NAME_WEATHER_TYPE + DB_INTEGER_TYPE + COMMA +
            DBConstants.MainTable.COLUMN_NAME_WEATHER_ICON_TYPE + DB_INTEGER_TYPE + COMMA +
            DBConstants.MainTable.COLUMN_NAME_MIN_TEMPERATURE + DB_FLOAT_TYPE + COMMA +
            DBConstants.MainTable.COLUMN_NAME_MAX_TEMPERATURE + DB_FLOAT_TYPE + COMMA +
            DBConstants.MainTable.COLUMN_NAME_SRT + DB_INTEGER_TYPE + COMMA +
            DBConstants.MainTable.COLUMN_NAME_SST + DB_INTEGER_TYPE + ")";

    private final String SQL_CREATE_WEATHER_TYPE_TABLE = CREATE_TABLE +
            DBConstants.WeatherTypeTable.WEATHER_TYPE_TABLE_NAME + " (" +
            DBConstants.COLUMN_NAME_ID + DB_INTEGER_TYPE + PRIMARY_KEY + COMMA +
            DBConstants.WeatherTypeTable.COLUMN_NAME_TYPE + DB_INTEGER_TYPE + ")";
    private final String SQL_CREATE_WEATHER_ICON_TABLE = CREATE_TABLE +
            DBConstants.WeatherIconTable.WEATHER_ICON_TYPE_TABLE_NAME + " (" +
            DBConstants.COLUMN_NAME_ID + DB_INTEGER_TYPE + PRIMARY_KEY + COMMA +
            DBConstants.WeatherIconTable.COLUMN_NAME_TYPE + DB_INTEGER_TYPE + ")";

    private final String SQL_CREATE_GEO_TABLE = CREATE_TABLE + DBConstants.GeoDataTable.GEO_DATA_TABLE_NAME + " (" +
            DBConstants.GeoDataTable.COLUMN_NAME_LATITUDE + DB_STRING_TYPE + COMMA +
            DBConstants.GeoDataTable.COLUMN_NAME_LONGITUDE + DB_STRING_TYPE + COMMA +
            DBConstants.GeoDataTable.COLUMN_NAME_TIME_ZONE + DB_STRING_TYPE + COMMA +
            DBConstants.GeoDataTable.COLUMN_NAME_OS + DB_FLOAT_TYPE + ")";

    public WeatherDBHelper(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MAIN_TABLE);
        db.execSQL(SQL_CREATE_WEATHER_TYPE_TABLE);
        db.execSQL(SQL_CREATE_WEATHER_ICON_TABLE);
        db.execSQL(SQL_CREATE_GEO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_ALL_TABLES);
        onCreate(db);
    }
}
