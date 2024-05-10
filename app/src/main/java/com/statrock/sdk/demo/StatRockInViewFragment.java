package com.statrock.sdk.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.statrock.sdk.StatRockView;

public class StatRockInViewFragment extends SimpleFragment {
    @SuppressWarnings("unused")
    public static StatRockInViewFragment newInstance(int layoutId) {
        StatRockInViewFragment fragment = new StatRockInViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_LAYOUT_ID, layoutId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        StatRockView statRock = view.findViewById(R.id.statrock);
        statRock.load("Hr5pC_SLH6PV");
        return view;
    }
}
