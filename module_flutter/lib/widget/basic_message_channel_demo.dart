import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class BasicMessageChannelWidget extends StatefulWidget {
  @override
  _BasicMessageChannelWidgetState createState() =>
      _BasicMessageChannelWidgetState();
}

class _BasicMessageChannelWidgetState extends State<BasicMessageChannelWidget> {
  BasicMessageChannel<String> _BasicMessageChannel =
      BasicMessageChannel<String>('BasicMessageChannelPlugin', StringCodec());

  /**
   * native发送消息到flutter中的处理函数，并返回消息
   */
  Future<String> _handler(message) async {
    print('Flutter收到消息: $message');
    return 'hello native';
  }

  //向native发送消息
  void _sendToNative() {
    Future<String> future = _BasicMessageChannel.send('hello native');
    future.then((message) {
      print("Flutter收到回复：" + message);
    });
  }

  @override
  void initState() {
//    _BasicMessageChannel.setMessageHandler((message) => Future<dynamic>(() {
//          print('Flutter收到消息: $message');
//          return 'hello native';
//        }));
    _BasicMessageChannel.setMessageHandler(_handler);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(20),
      color: Colors.grey[200],
      child: Column(
        children: <Widget>[
          Row(
            children: <Widget>[
              Expanded(
                child: RaisedButton(
                  onPressed: _sendToNative,
                  child: Text(
                    'send',
                    style: TextStyle(fontSize: 16),
                  ),
                ),
              )
            ],
          ),
        ],
      ),
    );
  }
}
