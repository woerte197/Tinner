package com.wangyang.news_moudle.ui


import android.content.ContentValues
import android.database.ContentObserver
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.router_lib.provider.moudle.news.INewsProvider
import com.tencent.mm.opensdk.utils.Log
import com.wangyang.baselibrary.ui.fragment.BaseMvpFragment
import com.wangyang.news_moudle.presenter.NewsPresenter
import com.wangyang.news_moudle.presenter.view.NewsView


/**
 * A simple [Fragment] subclass.
 *
 */
@Route(path = INewsProvider.NEWS_FRAGMENT)
class NewsFragment : BaseMvpFragment<NewsPresenter>(), NewsView {
    companion object {
        val P_HANDLER = object : Handler() {
            override fun handleMessage(msg: Message?) {

            }
        }
    }

    override fun setLayout(): Int {
        return com.wangyang.news_moudle.R.layout.fragment_news
    }

    private val localClassName = "NewsFragment"


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var mUri = Uri.parse("content://com.wangyang.mybasemodule.test.studentProvider/student")
        val contentValues = ContentValues()
        contentValues.put("number", 6)
        contentValues.put("name", "wang")
        activity?.contentResolver?.insert(mUri, contentValues)
        val course = activity?.contentResolver?.query(mUri, arrayOf("id", "name"), null, null, null)
        while (course!!.moveToNext()) {
            Log.e(localClassName, course.getInt(0).toString())
            Log.e(localClassName, course.getString(1))
        }

        activity?.contentResolver?.registerContentObserver(mUri, true, MyContentObserver(P_HANDLER))
    }


    class MyContentObserver(val handler: Handler?) : ContentObserver(handler) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
        }

        override fun onChange(selfChange: Boolean, uri: Uri?) {
            super.onChange(selfChange, uri)
            val message = Message.obtain()
            message.obj = uri
            handler?.sendMessage(message)
        }
    }
}
