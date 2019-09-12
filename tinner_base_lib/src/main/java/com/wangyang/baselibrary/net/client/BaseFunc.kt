package com.wangyang.baselibrary.net.client

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.functions.Function

class BaseFunc(var type: Any) : Function<String, Observable<Any>> {
    override fun apply(string: String): Observable<Any> {
        return Observable.just(Gson().fromJson(string, type.javaClass))
    }


}

