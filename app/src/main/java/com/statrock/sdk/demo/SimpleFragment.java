package com.statrock.sdk.demo;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

public abstract class SimpleFragment extends BaseFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId, container, false);

        TextView textView1 = view.findViewById(R.id.text1);
        if (textView1 != null) {
            Spanned info;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                info = Html.fromHtml(getString(R.string.text1), Html.FROM_HTML_MODE_LEGACY);
            } else {
                info = Html.fromHtml(getString(R.string.text1));
            }
            textView1.setText(info);
        }
        TextView textView2 = view.findViewById(R.id.text2);
        if (textView2 != null) {
            Spanned info;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                info = Html.fromHtml(getString(R.string.text2), Html.FROM_HTML_MODE_LEGACY);
            } else {
                info = Html.fromHtml(getString(R.string.text2));
            }
            textView2.setText(info);
        }
        TextView textView3 = view.findViewById(R.id.text3);
        if (textView3 != null) {
            Spanned info;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                info = Html.fromHtml(getString(R.string.text3), Html.FROM_HTML_MODE_LEGACY);
            } else {
                info = Html.fromHtml(getString(R.string.text3));
            }
            textView3.setText(info);
        }
        return view;
    }
}
