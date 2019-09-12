package com.wangyang.baselibrary.ext

import android.os.Handler
import com.wangyang.baselibrary.net.client.BaseFunc
import com.wangyang.baselibrary.net.observable.BaseSubscribe
import com.wangyang.baselibrary.utils.FileUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.File

fun <T> Observable<T>.execute(subscribe: BaseSubscribe<T>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscribe)

}

fun Observable<ResponseBody>.downLoad(handler: Handler, dir: String, fileName: String): Observable<File> {
    return this.map {
        FileUtil.writeToDisk(it.byteStream(), dir, fileName, handler, it.contentLength())
    }
}

fun Observable<String>.convert(t: Any): Observable<Any> {
    return this.flatMap(BaseFunc(t))
}



