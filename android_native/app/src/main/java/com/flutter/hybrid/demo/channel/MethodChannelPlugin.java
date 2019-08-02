package com.flutter.hybrid.demo.channel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.flutter.plugin.common.JSONMethodCodec;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

public class MethodChannelPlugin implements MethodChannel.MethodCallHandler {
    private MethodChannel mMethodChannel;
    private MethodInterface mMethodInterface;

    public static MethodChannelPlugin registerWith(FlutterView flutterView) {
        MethodChannel methodChannel = new MethodChannel(flutterView, "MethodChannelPlugin");

        MethodChannelPlugin plugin = new MethodChannelPlugin((MethodInterface) flutterView.getContext(), methodChannel);
        methodChannel.setMethodCallHandler(plugin);

        return plugin;
    }

    private MethodChannelPlugin(MethodInterface methodInterface, MethodChannel channel) {
        mMethodChannel = channel;
        mMethodInterface = methodInterface;
    }

    //调用flutter端方法，无返回值
    public void invokeMethod(@Nullable String method, @Nullable Object arguments) {
        mMethodChannel.invokeMethod(method, arguments);
    }
    //调用flutter端方法，有返回值
    public void invokeMethod(@Nullable String method, @Nullable Object arguments, MethodChannel.Result result) {
        mMethodChannel.invokeMethod(method, arguments, result);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall methodCall, @NonNull MethodChannel.Result result) {
        switch (methodCall.method) {
            case "getDeviceInfoByString":
                mMethodInterface.getDeviceInfoByString(methodCall, result);
                break;
            case "getDeviceInfoByMap":
                mMethodInterface.getDeviceInfoByMap(methodCall, result);
                break;
            case "getDeviceInfoByList":
                mMethodInterface.getDeviceInfoByList(methodCall, result);
                break;
            case "getDeviceInfoByObject":
                mMethodInterface.getDeviceInfoByObject(methodCall, result);
                break;
            default:
                result.notImplemented();
                break;
        }
    }
}
