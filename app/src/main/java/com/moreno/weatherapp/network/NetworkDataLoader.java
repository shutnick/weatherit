package com.moreno.weatherapp.network;

import android.app.Activity;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.moreno.weatherapp.R;
import com.moreno.weatherapp.ui.warning.WarningDialog;
import com.moreno.weatherapp.json.JsonHandler;
import com.moreno.weatherapp.util.CheckInternetUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class manages loading JSON data
 * Created by Moreno on 27.05.2015.
 */
public class NetworkDataLoader {
    public final String TAG = getClass().getSimpleName();
    private Activity mActivity;

    public NetworkDataLoader(Activity activity) {
        mActivity = activity;
    }

    /**
     * Call to get data from server
     * @param url   URL of request
     */
    public void load (String url) {
        if (!CheckInternetUtil.isInternetEnabled(mActivity)) {
            WarningDialog.createDialog(R.string.warning_dialog_title_internet
                    , R.string.warning_dialog_message_no_internet)
                    .show(mActivity.getFragmentManager(), WarningDialog.TAG);
            return;
        }

        RequestQueue requestQueue = Volley.newRequestQueue(mActivity);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    handleJSON(response);
                } catch (JSONException e) {
                    WarningDialog.createDialog(R.string.warning_dialog_error_title
                            , R.string.warning_dialog_error_parsing_json_message)
                            .show(mActivity.getFragmentManager(), WarningDialog.TAG);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                WarningDialog.createDialog(R.string.warning_dialog_connection_error_title
                        , R.string.warning_dialog_connection_error_message)
                        .show(mActivity.getFragmentManager(), WarningDialog.TAG);
                Log.e(TAG, "Error: \n" + error.toString());
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS
                , DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(request);
    }

    private void handleJSON(JSONObject response) throws JSONException {
        Log.i(TAG, "response obtained");
        new JsonHandler(mActivity).parse(response);
    }
}
