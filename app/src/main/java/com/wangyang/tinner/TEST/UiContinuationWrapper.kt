package com.wangyang.tinner.TEST

import android.os.Handler
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class UiContinuationWrapper<T>(private val handler: Handler, private val continuation: Continuation<T>) :
    Continuation<T> {
    override val context: CoroutineContext = EmptyCoroutineContext

    override fun resumeWith(result: Result<T>) {
        handler.post { continuation.resumeWith(result) }
    }

}