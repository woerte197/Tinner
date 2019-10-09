package com.wangyang.tinner

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteCallbackList

class IBookService : Service() {

    var mBookList: MutableList<Book> = ArrayList()
    var mRemoteCallbackList: RemoteCallbackList<IBookAddListener> = RemoteCallbackList()

    override fun onCreate() {
        super.onCreate()
    }


    val iBook = object : IBook.Stub() {
        override fun register(listener: IBookAddListener?) {
            mRemoteCallbackList.register(listener)
        }

        override fun unRegister(listener: IBookAddListener?) {
            mRemoteCallbackList.unregister(listener)
        }

        override fun getBookList(): MutableList<Book> {
            return mBookList
        }

        override fun addBook(book: Book?) {
            mBookList.add(book!!)
            notificationBook(book)
        }

    }

    private fun notificationBook(book: Book) {
        val count = mRemoteCallbackList.beginBroadcast()
        for (i in 0..count){
            val listener = mRemoteCallbackList.getBroadcastItem(i)
            listener.NotificationRefresh(book)
        }
        mRemoteCallbackList.finishBroadcast()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return iBook
    }


}