package com.wangyang.tinner.TEST

import android.os.Handler
import com.tencent.mm.opensdk.utils.Log
import com.wangyang.baselibrary.utils.FileUtil
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.lang.Exception
import kotlin.coroutines.*

fun doCoroutines(handler: Handler, block: suspend () -> Unit) {
    block.startCoroutine(object : Continuation<Unit> {
        override val context: CoroutineContext = AsyncContext(handler)

        override fun resumeWith(result: Result<Unit>) {

        }
    })
}


suspend fun <T>doAsync(block:  () -> T) = suspendCoroutine<T> { continuation ->
    Log.e("doCoroutines", "异步任务开始前")
    AsyncTask {
        try {
            continuation.resume(block())
        }
        catch (exception:Exception){
            continuation.resumeWithException(exception)
        }
    }.excute()
}


fun writeFile(inputStream: InputStream):File{
  return  FileUtil.writeToDisk(inputStream,"tinner","tinner.txt")
}
