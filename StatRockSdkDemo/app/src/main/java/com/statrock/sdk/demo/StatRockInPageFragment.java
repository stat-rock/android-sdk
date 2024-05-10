package com.statrock.sdk.demo;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import androidx.annotation.NonNull;

import com.statrock.sdk.StatRockView;

public class StatRockInPageFragment extends SimpleFragment {
    @SuppressWarnings("unused")
    public static StatRockInPageFragment newInstance(int layoutId) {
        StatRockInPageFragment fragment = new StatRockInPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_LAYOUT_ID, layoutId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        StatRockView statRock = view.findViewById(R.id.statrock);

        Rect rect = new Rect();
        ScrollView scrollView = (ScrollView) view;
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                boolean result = statRock.getGlobalVisibleRect(rect);
                if (result
                        && statRock.getHeight() == rect.height()
                        && statRock.getWidth() == rect.width()) {
                    statRock.load("Hr5pC_SLH6PV");
                    scrollView.getViewTreeObserver().removeOnScrollChangedListener(this);
                }
            }
        });
        return view;
    }
}
