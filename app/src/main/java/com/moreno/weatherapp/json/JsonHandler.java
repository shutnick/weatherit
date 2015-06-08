package com.moreno.weatherapp.json;

import android.content.Context;
import android.util.Log;

import com.moreno.weatherapp.db.WeatherDBManager;
import com.moreno.weatherapp.db.dao.DailyWeatherItem;
import com.moreno.weatherapp.db.dao.GeoDataItem;
import com.moreno.weatherapp.util.JsonDataConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class parses obtained JSON data and write it to DB
 * Created on 08.06.2015.
 */
public class JsonHandler {

    private final WeatherDBManager mDbManager;

    public JsonHandler(Context context) {
        mDbManager = new WeatherDBManager(context);
    }

    /**
     * Call to parse appropriate server data
     * @see JsonDataConstants
     * @param data  JSON obtained from server
     */
    public void parse(JSONObject data) {
        try {
            double latitude = data.getDouble(JsonDataConstants.LATITUDE);
            double longitude = data.getDouble(JsonDataConstants.LONGITUDE);
            String timeZone = data.getString(JsonDataConstants.TIME_ZONE);
            double os = data.getDouble(JsonDataConstants.OS);
            Log.d("tag", "Lt: " + latitude + ", Lg: " + longitude + ", time zone: "
                    + timeZone + ", os: " + os);
            GeoDataItem geoDataItem = new GeoDataItem(latitude, longitude, timeZone, os);
            resetData();
            mDbManager.insertGeoData(geoDataItem);
            JSONArray hArray = data.getJSONArray(JsonDataConstants.HOUR);
            parseHourlyData(hArray);
            JSONArray dArray = data.getJSONArray(JsonDataConstants.DAILY);
            parseDailyData(dArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void resetData() {
        mDbManager.resetData();
    }

    private void parseDailyData(JSONArray dArray) {
        Log.d("tag", "D array: ");
        for (int j = 0; j < dArray.length(); j++) {
            try {
                JSONObject element = dArray.getJSONObject(j);
                long time = element.getLong(JsonDataConstants.DATE);
                String s = element.getString(JsonDataConstants.FORECAST);
                String i = element.getString(JsonDataConstants.ICON);
                double minTemp = element.getDouble(JsonDataConstants.TEMP_MIN);
                double maxTemp = element.getDouble(JsonDataConstants.TEMP_MAX);
                long srt = element.getLong(JsonDataConstants.SR_T);
                long sst = element.getLong(JsonDataConstants.SS_T);
//                Log.d("tag", "Time: " + time + ", S: " + s + ", I: " + i + ", min temp:" + minTemp
//                        + ", max temp:" + maxTemp + ", SrT: " + srt + ", Sst: " + sst);
                DailyWeatherItem item = new DailyWeatherItem(time, s, i, minTemp
                        , maxTemp, srt, sst);
                mDbManager.insertDailyWeather(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseHourlyData(JSONArray hArray) {
        Log.d("tag", "H array: ");
        for (int j = 0; j < hArray.length(); j++) {
            try {
                JSONObject element = hArray.getJSONObject(j);
                long time = element.getLong(JsonDataConstants.DATE);
                String s = element.getString(JsonDataConstants.FORECAST);
                String i = element.getString(JsonDataConstants.ICON);
                double temperature = element.getDouble(JsonDataConstants.TEMP);
                double aTemp = element.getDouble(JsonDataConstants.A_TEMP);
//                Log.d("tag", "Time: " + time + ", S: " + s + ", I: " + i + ", temp:" + temperature + ", atemp:" + aTemp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
