package com.wangyang.tinner.TEST

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger

class CallService : Service() {

      val mMessengerHandler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg?.what){
                1->{
                    var messanger = msg.replyTo
                    val message =Message.obtain()
                    message.what= 2
                    message.replyTo = messanger
                    messanger.send(message)
                }
            }
        }

    }

    private val messenger = Messenger(mMessengerHandler)


    override fun onBind(intent: Intent?): IBinder? {
        return messenger.binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

}