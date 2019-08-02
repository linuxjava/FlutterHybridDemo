package com.flutter.hybrid.demo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import io.flutter.app.FlutterFragmentActivity;
import io.flutter.facade.Flutter;

public class FlutterExtendActivity extends FlutterFragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.flutter_container, Flutter.createFragment("home"));
        tx.commit();
    }
}
