package com.wangyang.baselibrary.net


import com.wangyang.baselibrary.config.ConfigManager
import com.wangyang.baselibrary.config.ConfigType
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by mac on 2018/9/25.
 */

class MyApiFactory {

    val service: MyApiService
        get() {
            if (null == MAP[MY_API_SERVICE]) {
                MAP[MY_API_SERVICE] = MyApiServiceHolder.MY_API_SERVICE
            }
            return MAP[MY_API_SERVICE] as MyApiService
        }

    companion object {
        private val MAP = HashMap<String, Any>()
        private const val MY_API_SERVICE = "MyApiService"

        val INS: MyApiFactory  by lazy { MyApiFactory() }
    }
}


private object MyApiServiceHolder {
    val MY_API_SERVICE: MyApiService = RetrofitBuilderHolder.RETROFIT.create(MyApiService::class.java)
}

private object RetrofitBuilderHolder {

    val HOST_URL = ConfigManager.getConfig().getConfig(ConfigType.HOST_URL)
    val RETROFIT: Retrofit = Retrofit.Builder()
        .client(OkHttpClientHolder.OK_HTTP_CLIENT)
        .baseUrl(HOST_URL as String)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

}

private object OkHttpClientHolder {

    val INTERCEPTOR_LIST = ConfigManager.getConfig().getConfig(ConfigType.INTERCEPTOR)
    val BUILDER = OkHttpClient.Builder()

    val OK_HTTP_CLIENT: OkHttpClient = addInterceptor()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    private fun addInterceptor(): OkHttpClient.Builder {
        if (null != INTERCEPTOR_LIST) {
            for (interceptor in INTERCEPTOR_LIST as ArrayList<Interceptor>) {
                BUILDER.addInterceptor(interceptor)
            }
        }
        return BUILDER
    }
}


