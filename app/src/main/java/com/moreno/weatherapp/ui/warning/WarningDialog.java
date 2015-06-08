package com.moreno.weatherapp.ui.warning;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Helper dialog to notify user working issues
 * Created by Moreno on 21.05.2015.
 */
public class WarningDialog extends DialogFragment {

    public static final String TITLE_KEY = "title";
    public static final String MESSAGE_KEY = "message";
    public static final String TAG = "warning dialog";
    public static final String WARNING_FRAGMENT_TAG = "warning";

    /**
     * Create dialog with appropriate data
     * @param titleId   Dialog title
     * @param messageId Dialog message
     * @return  Prepared dialog
     */
    public static WarningDialog createDialog(int titleId, int messageId) {
        WarningDialog dialog = new WarningDialog();
        Bundle args = new Bundle();
        args.putInt(TITLE_KEY, titleId);
        args.putInt(MESSAGE_KEY, messageId);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        AlertDialog dialog = new AlertDialog
                .Builder(getActivity())
                .setTitle(args.getInt(TITLE_KEY))
                .setMessage(args.getInt(MESSAGE_KEY))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                }).create();
        return dialog;
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        Fragment warning = getFragmentManager().findFragmentByTag(WARNING_FRAGMENT_TAG);
        if (warning != null) {
            transaction.remove(warning);
        }

        return super.show(transaction, tag);
    }
}
