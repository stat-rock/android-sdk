package com.statrock.sdk.demo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    protected static final String ARG_PARAM_LAYOUT_ID = "param_layout_id";
    protected int layoutId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            layoutId = getArguments().getInt(ARG_PARAM_LAYOUT_ID);
        }
    }
}
