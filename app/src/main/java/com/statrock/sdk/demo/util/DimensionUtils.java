package com.statrock.sdk.demo.util;

import android.content.Context;

import androidx.annotation.NonNull;

import com.statrock.sdk.util.Preconditions;

public class DimensionUtils {

    public static int px2dp(@NonNull Context context, int px) {
        Preconditions.checkNotNull(context);

        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }

    public static int dp2px(@NonNull Context context, int dp) {
        Preconditions.checkNotNull(context);

        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }
}
