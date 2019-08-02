package com.flutter.hybrid.demo.channel;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import io.flutter.plugin.common.EventChannel;
import io.flutter.view.FlutterView;

/**
 * EventChannel通信场景：
 * 用于数据流的持续性通信，通常用于native向Dart通信，例如：手机电量变化、网络变化等
 *
 */
public class EventChannelPlugin implements EventChannel.StreamHandler {
    private static final String TAG = EventChannelPlugin.class.getSimpleName();
    private EventChannel.EventSink eventSink;


    public static EventChannelPlugin registerWith(FlutterView flutterView) {
        EventChannelPlugin plugin = new EventChannelPlugin(flutterView);
        new EventChannel(flutterView, "EventChannelPlugin").setStreamHandler(plugin);
        return plugin;

    }

    private EventChannelPlugin(FlutterView flutterView) {
    }

    public void send(Object params) {
        if (eventSink != null) {
            eventSink.success(params);
        }
    }

    public void sendError(String str1, String str2, Object params) {
        if (eventSink != null) {
            eventSink.error(str1, str2, params);
        }
    }

    public void cancel() {
        if (eventSink != null) {
            eventSink.endOfStream();
        }
    }


    /**
     * dart初始化监听时调用
     * @param o //flutter初始化EventChannel时返回的值，仅此一次
     * @param eventSink
     */
    @Override
    public void onListen(Object o, EventChannel.EventSink eventSink) {
        this.eventSink = eventSink;
        Log.i(TAG, "Object：" + o.toString());
    }

    /**
     *
     * @param o
     */
    @Override
    public void onCancel(Object o) {
        Log.i(TAG, "onCancel：" + o.toString());
        this.eventSink = null;
    }
}
