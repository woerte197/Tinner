package com.wangyang.tinner.TEST

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.support.v7.app.AppCompatActivity
import com.wangyang.tinner.R

class CallActivity : AppCompatActivity() {

    private var mCallService: Messenger? = null
    private val mCallActivityHandler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg?.what){
                2->{

                }
            }
        }
    }


    private val mCallActivityMessenger = Messenger(mCallActivityHandler)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        val intent = Intent(this, CallService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mCallService = Messenger(service)
            val message = Message.obtain()
            message.what = 1
            message.replyTo = this@CallActivity.mCallActivityMessenger
            mCallService!!.send(message)
        }

    }
}
