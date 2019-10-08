package com.wangyang.baselibrary.net.client

import android.text.TextUtils
import okhttp3.RequestBody
import java.io.File
import java.lang.NullPointerException
import java.util.*

class MyClientBuilder {
    private var mUrl: String? = null
    private var mBody: RequestBody? = null
    private var mParams: WeakHashMap<String, Any> = WeakHashMap()
    private var mFile: File? = null
    private var mFiles: List<File>? = null

    fun addUrl(url: String): MyClientBuilder {
        mUrl = url
        return this
    }

    fun addBody(body: RequestBody): MyClientBuilder {
        mBody = body
        return this
    }

    fun addParams(params: WeakHashMap<String, Any>): MyClientBuilder {
        mParams.putAll(params)
        return this
    }

    fun addFile(file: File): MyClientBuilder {
        mFile = file
        return this
    }

    fun addFiles(files: List<File>): MyClientBuilder {
        mFiles = files
        return this
    }

    fun build(): MyClient {
        if (!TextUtils.isEmpty(mUrl)) {
            return MyClient(mUrl!!, mParams, mBody, mFile, mFiles)
        }
        throw NullPointerException("URL NOT IS NULL!")
    }
}