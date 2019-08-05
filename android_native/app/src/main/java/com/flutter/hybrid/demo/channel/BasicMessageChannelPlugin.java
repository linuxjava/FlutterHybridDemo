package com.flutter.hybrid.demo.channel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.common.StringCodec;
import io.flutter.view.FlutterView;

public class BasicMessageChannelPlugin implements BasicMessageChannel.MessageHandler<String> {
    private BasicMessageChannel<String> mMessageChannel;

    public static BasicMessageChannelPlugin registerWith(FlutterView flutterView) {
        return new BasicMessageChannelPlugin(flutterView);
    }

    private BasicMessageChannelPlugin(FlutterView flutterView) {
        mMessageChannel = new BasicMessageChannel<String>(flutterView, "BasicMessageChannelPlugin", StringCodec.INSTANCE);
        mMessageChannel.setMessageHandler(this);
    }

    /**
     * flutter调用native消息处理
     * @param s
     * @param reply
     */
    @Override
    public void onMessage(@Nullable String s, @NonNull BasicMessageChannel.Reply<String> reply) {
        Log.d("xiao1", "native收到消息: " + s);
        //返回消息给flutter
        reply.reply("hello Flutter");
    }

    public void send(String msg){
        mMessageChannel.send(msg);
    }

    public void send(String msg, BasicMessageChannel.Reply<String> callback){
        mMessageChannel.send(msg);
    }
}
