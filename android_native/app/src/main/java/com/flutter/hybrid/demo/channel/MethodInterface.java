package com.flutter.hybrid.demo.channel;

import android.support.annotation.NonNull;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public interface MethodInterface {
    void getDeviceInfoByString(MethodCall methodCall, MethodChannel.Result result);
    void getDeviceInfoByMap(MethodCall methodCall, MethodChannel.Result result);
    void getDeviceInfoByList(MethodCall methodCall, MethodChannel.Result result);
    void getDeviceInfoByObject(MethodCall methodCall, MethodChannel.Result result);
}
