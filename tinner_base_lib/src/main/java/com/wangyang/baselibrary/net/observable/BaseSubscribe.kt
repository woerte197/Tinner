package com.wangyang.baselibrary.net.observable


import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class BaseSubscribe<T> : Observer<T> {


    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {

    }


    override fun onError(e: Throwable) {

    }

    override fun onComplete() {

    }

}
