package com.flutter.hybrid.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import io.flutter.facade.Flutter;
import io.flutter.view.FlutterView;

public class HybridViewActivtiy extends AppCompatActivity {
    private ViewGroup mFlutterContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_hybrid_view);

        mFlutterContainer = findViewById(R.id.flutter_container);

        FlutterView flutterView = Flutter.createView(this, getLifecycle(), "flutter_view");
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mFlutterContainer.addView(flutterView, layoutParams);
    }
}
