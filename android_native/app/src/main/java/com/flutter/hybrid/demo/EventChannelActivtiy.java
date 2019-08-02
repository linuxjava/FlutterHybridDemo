package com.flutter.hybrid.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.flutter.hybrid.demo.channel.EventChannelPlugin;

import io.flutter.facade.Flutter;
import io.flutter.view.FlutterView;

public class EventChannelActivtiy extends AppCompatActivity {
    private ViewGroup mFlutterContainer;
    private EventChannelPlugin mEventChannelPlugin;
    private EditText mInputEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_event_channel);

        mFlutterContainer = findViewById(R.id.flutter_container);
        mInputEdit = findViewById(R.id.edit_input);


        FlutterView flutterView = Flutter.createView(this, getLifecycle(), "EventChannel");
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mFlutterContainer.addView(flutterView, layoutParams);

        mEventChannelPlugin = EventChannelPlugin.registerWith(flutterView);
    }

    public void onSend(View view) {
        String msg = mInputEdit.getText().toString();
        if(!TextUtils.isEmpty(msg)){
            mEventChannelPlugin.send(msg);
        }
    }
}
