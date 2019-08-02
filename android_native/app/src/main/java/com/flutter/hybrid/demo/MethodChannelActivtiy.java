package com.flutter.hybrid.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.flutter.hybrid.demo.channel.EventChannelPlugin;
import com.flutter.hybrid.demo.channel.MethodChannelPlugin;
import com.flutter.hybrid.demo.channel.MethodInterface;
import com.flutter.hybrid.demo.model.NativeObject;
import com.flutter.hybrid.demo.utils.GsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.flutter.facade.Flutter;
import io.flutter.plugin.common.JSONMethodCodec;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

public class MethodChannelActivtiy extends AppCompatActivity implements MethodInterface {
    private ViewGroup mFlutterContainer;
    private MethodChannelPlugin mMethodChannelPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_method_channel);

        mFlutterContainer = findViewById(R.id.flutter_container);


        FlutterView flutterView = Flutter.createView(this, getLifecycle(), "MethodChannel");
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mFlutterContainer.addView(flutterView, layoutParams);

        mMethodChannelPlugin = MethodChannelPlugin.registerWith(flutterView);
    }

    @Override
    public void getDeviceInfoByString(MethodCall methodCall, MethodChannel.Result result) {
        if(methodCall.arguments instanceof String) {
            String data = (String) methodCall.arguments;
            //给flutter端的返回数据
            result.success(data + " " + getDeviceBrand() + " " + getSystemModel());
        }
    }

    @Override
    public void getDeviceInfoByMap(MethodCall methodCall, MethodChannel.Result result) {
        if(methodCall.arguments instanceof Map) {
            Map<String, String> data = (Map<String, String>) methodCall.arguments;
            data.put("brand", getDeviceBrand());
            data.put("model", getSystemModel());
            //给flutter端的返回数据
            result.success(data);
        }else if(methodCall.arguments instanceof JSONObject){
            JSONObject jsonObject = (JSONObject) methodCall.arguments;
            try {
                jsonObject.put("brand", getDeviceBrand());
                jsonObject.put("model", getSystemModel());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            result.success(jsonObject);
        }
    }

    @Override
    public void getDeviceInfoByList(MethodCall methodCall, MethodChannel.Result result) {
        if(methodCall.arguments instanceof List) {
            List<String> data = (List<String>) methodCall.arguments;
            data.add(getDeviceBrand());
            data.add(getSystemModel());
            //给flutter端的返回数据
            result.success(data);
        }else if(methodCall.arguments instanceof JSONArray){
            JSONArray jsonArray = (JSONArray) methodCall.arguments;
            jsonArray.put(getDeviceBrand());
            jsonArray.put(getSystemModel());
            result.success(jsonArray);
        }
    }

    /**
     * 接收到复杂数据类型
     * @param methodCall
     * @param result
     */
    @Override
    public void getDeviceInfoByObject(MethodCall methodCall, MethodChannel.Result result) {
        //方式1：似乎不支持直接传递Object,所以转为json回传
        NativeObject object = new NativeObject();
        object.brand = getDeviceBrand();
        object.model = getSystemModel();
        Log.d("xiao1", GsonUtils.toJson(object));
        result.success(GsonUtils.toJson(object));

        //方式2：回传JSONObject对象
//        if(methodCall.arguments instanceof JSONObject){
//            JSONObject jsonObject = (JSONObject) methodCall.arguments;
//            try {
//                jsonObject.put("brand", getDeviceBrand());
//                jsonObject.put("model", getSystemModel());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            result.success(jsonObject);
//        }
    }


    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    public void onInvokeFlutterMethod(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("invoke", "native调用flutter方法");
        map.put("brand", getDeviceBrand());
        map.put("model", getSystemModel());
        mMethodChannelPlugin.invokeMethod("flutterMethod", map);
    }
}
