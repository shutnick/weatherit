package com.moreno.weatherapp.ui.warning;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.moreno.weatherapp.R;

/**
 * Helper class to show warning if user gets empty cursor from db
 * Created on 08.06.2015.
 */
public class WarningHandler extends Handler{

    public static final int NO_DATA_TO_SHOW = 4;
    private final Activity mActivity;

    public WarningHandler(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void handleMessage(Message msg) {
        int what = msg.what;
        switch (what) {
            case NO_DATA_TO_SHOW:
                WarningDialog.createDialog(R.string.warning_dialog_title_no_cache
                        , R.string.warning_dialog_message_no_cache)
                .show(mActivity.getFragmentManager(), WarningDialog.WARNING_FRAGMENT_TAG);
                break;
        }
    }
}
