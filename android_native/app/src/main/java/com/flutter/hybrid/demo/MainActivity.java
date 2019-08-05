package com.flutter.hybrid.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * native view局部嵌入flutter view
     *
     * @param view
     */
    public void onClick1(View view) {
        startActivity(new Intent(this, HybridViewActivtiy.class));
    }

    /**
     * Native加载整个Flutter view
     *
     * @param view
     */
    public void onClick2(View view) {
        startActivity(new Intent(this, FlutterExtendActivity.class));
    }

    /**
     * BasicMessageChannel
     *
     * @param view
     */
    public void onClick3(View view) {
        startActivity(new Intent(this, BasicMessageChannelActivity.class));
    }

    /**
     * MethodChannel
     *
     * @param view
     */
    public void onClick4(View view) {
        startActivity(new Intent(this, MethodChannelActivtiy.class));
    }

    /**
     * EventChannel
     *
     * @param view
     */
    public void onClick5(View view) {
        startActivity(new Intent(this, EventChannelActivtiy.class));
    }
}
