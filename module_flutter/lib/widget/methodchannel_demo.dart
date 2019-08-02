import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class MethodChannelWidget extends StatefulWidget {
  @override
  _MethodChannelWidgetState createState() => _MethodChannelWidgetState();
}

class _MethodChannelWidgetState extends State<MethodChannelWidget> {
  //MethodChannel _methodChannel = MethodChannel("MethodChannelPlugin", JSONMethodCodec());
  MethodChannel _methodChannel = MethodChannel("MethodChannelPlugin");
  String _msg = '';
  //native调用flutter方法处理器
  Future<Object> _handler(MethodCall call){
    switch(call.method){
      case 'flutterMethod':
        print(call.arguments);
        setState(() {
          //message是native返回的数据
          _msg = "调用参数：" + call.arguments.toString();
        });
        break;
    }
  }

  @override
  void initState() {
    // TODO: implement initState
    _methodChannel.setMethodCallHandler(_handler);
    super.initState();
  }

  //flutter调用native的对应方法,使用String传递数据
  void _getDeviceInfoByString() {
    Future<String> future =
        _methodChannel.invokeMethod("getDeviceInfoByString", 'hello flutter');
    future.then((message) {
      setState(() {
        //message是native返回的数据
        _msg = "返回值：" + message.toString();
      });
    });
  }

  //flutter调用native的对应方法,使用map传递数据
  void _getDeviceInfoByMap() {
    Future<Object> future = _methodChannel
        .invokeMethod("getDeviceInfoByMap", {'name': 'xiao', 'age': 30});
    future.then((message) {
      setState(() {
        //message是native返回的数据
        _msg = "返回值：" + message.toString();
      });
    });
  }

  //flutter调用native的对应方法,使用List传递数据
  void _getDeviceInfoByList() {
    Future<Object> future =
        _methodChannel.invokeMethod("getDeviceInfoByList", ['华为', '小米']);
    future.then((message) {
      setState(() {
        //message是native返回的数据
        _msg = "返回值：" + message.toString();
      });
    });
  }

  //flutter调用native的对应方法
  void _getDeviceInfoByObject() {
    Future<Object> future =
        _methodChannel.invokeMethod("getDeviceInfoByObject", {
      'name': 'flutter',
      'list': ['华为', '小米'],
      'map': {'name': 'xiao', 'age': 30}
    });
    future.then((message) {
      setState(() {
        //message是native返回的数据
        print(message);
        _msg = "返回值：" + message.toString();
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.grey[200],
      child: Column(
        children: <Widget>[
          RaisedButton(
            child: Text(
              '获取设备信息（String传递数据）',
              style: TextStyle(fontSize: 16),
            ),
            onPressed: _getDeviceInfoByString,
          ),
          RaisedButton(
            child: Text(
              '获取设备信息（Map传递数据）',
              style: TextStyle(fontSize: 16),
            ),
            onPressed: _getDeviceInfoByMap,
          ),
          RaisedButton(
            child: Text(
              '获取设备信息（List传递数据）',
              style: TextStyle(fontSize: 16),
            ),
            onPressed: _getDeviceInfoByList,
          ),
          RaisedButton(
            child: Text(
              '获取设备信息（传递数据复杂数据）',
              style: TextStyle(fontSize: 16),
            ),
            onPressed: _getDeviceInfoByObject,
          ),
          Text(
            _msg,
            style: TextStyle(
                decoration: TextDecoration.none,
                fontSize: 20,
                color: Theme.of(context).accentColor),
          )
        ],
      ),
    );
  }
}
