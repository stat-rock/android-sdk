package com.statrock.sdk.demo;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;

import com.statrock.sdk.StatRockListener;
import com.statrock.sdk.StatRockType;
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

    private boolean isPlayerLoaded = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        ScrollView scrollView = view.findViewById(R.id.scroll_view);
        StatRockView statRockView = view.findViewById(R.id.statrock);
        FrameLayout stickyContainer = view.findViewById(R.id.sticky_container);
        statRockView.setStickyContainer(stickyContainer);

        Rect rect = new Rect();
        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            boolean isVisible = statRockView.getGlobalVisibleRect(rect);

            if (!isPlayerLoaded && isVisible
                    && rect.top >= 0
                    && rect.bottom <= scrollView.getHeight()) {
                statRockView.load("Hr5pC_SLH6PV", StatRockType.IN_PAGE);
                isPlayerLoaded = true;
            }
        });
        return view;
    }
}
