package com.moreno.weatherapp.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moreno.weatherapp.R;


/**
 * A placeholder fragment containing Weather info.
 */
public class WheatherListFragment extends Fragment {

    public WheatherListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wheather_list, container, false);
    }
}
