import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

/**
 * EventChannel通信
 */
class EventChannelWidget extends StatefulWidget {
  @override
  _EventChannelWidgetState createState() => _EventChannelWidgetState();
}

class _EventChannelWidgetState extends State<EventChannelWidget> {
  String _msg = 'Flutter';
  EventChannel _EventChannelPlugin = new EventChannel("EventChannelPlugin");
  StreamSubscription _StreamSubscription;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    //["abc", 123, "你好"]对应着Android端onListen方法的第一个参数，可不传值
    _StreamSubscription =
        _EventChannelPlugin.receiveBroadcastStream(["abc", 123, "你好"])
            .listen(_onData);
  }

  @override
  void dispose() {
    if (_StreamSubscription != null) {
      _StreamSubscription.cancel();
      _StreamSubscription = null;
    }
    // TODO: implement dispose
    super.dispose();
  }

  void _onData(message) {
    print(message);
    setState(() {
      _msg = message;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.grey[200],
      child: Center(
        child: Text(
          _msg,
          //textDirection: TextDirection.ltr,
          //textAlign: TextAlign.left,
          style: TextStyle(
              decoration: TextDecoration.none,
              fontSize: 20,
              color: Theme.of(context).accentColor),
        ),
      ),
    );
  }
}
