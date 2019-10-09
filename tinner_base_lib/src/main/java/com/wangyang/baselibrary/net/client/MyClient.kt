package com.wangyang.baselibrary.net.client

import com.wangyang.baselibrary.net.MyApiFactory
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import java.io.File
import java.util.*

class MyClient constructor(
    private val url: String,
    private val params: WeakHashMap<String, Any>?,
    private val body: RequestBody?,
    val file: File?,
    private val files: List<File>?
) {

    companion object {
        fun builder(): MyClientBuilder {
            return MyClientBuilder()
        }

    }

    fun get(): Observable<String> {
        return requestMethod(RequestType.GET)
    }

    fun post(): Observable<String> {
        return requestMethod(RequestType.POST)
    }

    fun postBody(): Observable<String> {
        return requestMethod(RequestType.POST_BODY)
    }

    fun put(): Observable<String> {
        return requestMethod(RequestType.PUT)
    }

    fun putBody(): Observable<String> {
        return requestMethod(RequestType.PUT_BODY)
    }

    fun uploads(): Observable<String> {
        return requestMethod(RequestType.UPLOADS)
    }

    fun upload(): Observable<String> {
        return requestMethod(RequestType.UPLOAD)
    }

    fun downLoad(): @JvmSuppressWildcards Observable<ResponseBody> {
        return MyApiFactory.INS.service.download(url)
    }

    fun downLoadWithParams(): @JvmSuppressWildcards Observable<ResponseBody> {
        return MyApiFactory.INS.service.download(url, params)
    }


    private fun requestMethod(type: RequestType): Observable<String> {
        val apiService = MyApiFactory.INS.service
        return when (type) {
            RequestType.GET -> {
                apiService.get(url, params)
            }
            RequestType.POST -> {
                apiService.post(url, params)

            }
            RequestType.POST_BODY -> {
                apiService.postBody(url, body)

            }
            RequestType.PUT -> {
                apiService.put(url, params)

            }
            RequestType.PUT_BODY -> {
                apiService.putBody(url, body)

            }
            RequestType.UPLOAD -> {
                val requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), file)
                val part = MultipartBody.Part.createFormData("file", file!!.name, requestBody)
                apiService.upload(url, initUploadParams(), part)

            }
            RequestType.UPLOADS -> {
                apiService.uploads(url, initUploads())

            }
            RequestType.DOWNLOAD -> {
                apiService.get(url, params)

            }
            RequestType.DELETE -> {
                apiService.get(url, params)
            }
        }

    }

    private fun initUploadParams(): Map<String, RequestBody> {
        val partMap = HashMap<String, RequestBody>()
        if (params!!.isNotEmpty()) {
            for (key in params.keys) {
                val requestBody = RequestBody.create(MediaType.parse("text/plain"), params[key].toString())
                partMap[key] = requestBody
            }
        }
        return partMap
    }

    private fun initUploads(): Map<String, MultipartBody.Part> {
        val partMap = HashMap<String, MultipartBody.Part>()
        for (i in files!!) {
            val body = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), i)
            val multipartBody = MultipartBody.Part.createFormData("file", i.name, body)
            partMap["image$i"] = multipartBody
        }
        return partMap
    }
}