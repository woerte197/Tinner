package com.wangyang.tinner

import android.app.Service
import android.content.Intent
import android.os.IBinder

class IBookService : Service() {

    var mBookList: MutableList<Book> = ArrayList()

    override fun onCreate() {
        super.onCreate()
    }


    val iBook = object : IBook.Stub() {
        override fun getBookList(): MutableList<Book> {
                return mBookList
        }

        override fun addBook(book: Book?) {
            mBookList.add(book!!)
        }

    }

    override fun onBind(intent: Intent?): IBinder? {
        return iBook
    }


}