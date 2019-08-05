package com.flutter.hybrid.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.flutter.hybrid.demo.channel.BasicMessageChannelPlugin;

import io.flutter.facade.Flutter;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.view.FlutterView;

public class BasicMessageChannelActivity extends AppCompatActivity {
    private ViewGroup mFlutterContainer;
    private BasicMessageChannelPlugin mPlugin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicmessagechannel);

        mFlutterContainer = findViewById(R.id.flutter_container);

        FlutterView flutterView = Flutter.createView(this, getLifecycle(), "BasicMessageChannel");
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mFlutterContainer.addView(flutterView, layoutParams);

        mPlugin = BasicMessageChannelPlugin.registerWith(flutterView);

    }

    public void onInvokeFlutterMethod(View v){
        mPlugin.send("hello Flutter", new BasicMessageChannel.Reply<String>() {
            @Override
            public void reply(@Nullable String s) {
                Log.d("xiao1", "native接收回复: " + s);
            }
        });
    }
}
