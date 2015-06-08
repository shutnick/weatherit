package com.moreno.weatherapp.geo;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.moreno.weatherapp.network.NetworkDataLoader;
import com.moreno.weatherapp.util.CheckInternetUtil;

/**
 * Util class to get last known location
 * Created on 08.06.2015.
 */
public class LocationLoader {

    private static final String TAG = "LocationLoader";
    public static final String JSON_PATH = "http://weatheritapi.ds.trustsourcing.com/GetData?lon=%f&lat=%f";
    public static final int THIRTY_MINUTES = 1000 * 60 * 30;
    public static final int MIN_DISTANCE = 1000;

    /**
     * Call to get last known location
     * @param activity  Activity
     */
    public static void getCurrentLocation(final Activity activity) {
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i(TAG, "Location changed");
                String url = String.format(JSON_PATH, location.getLatitude(), location.getLongitude());
//                    Log.i("tag", "Link: " + url);
                new NetworkDataLoader(activity).load(url);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.i(TAG, "Status changed");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.i(TAG, "Provider enabled");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.i(TAG, "Provider disabled");
            }
        };
        String provider;
        if (CheckInternetUtil.isInternetEnabled(activity)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            provider = LocationManager.GPS_PROVIDER;
        }
        locationManager.requestLocationUpdates(provider, THIRTY_MINUTES, MIN_DISTANCE, listener);
    }
}
