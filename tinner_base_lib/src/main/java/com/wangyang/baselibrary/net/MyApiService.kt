package com.wangyang.baselibrary.net

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * Created by mac on 2018/9/25.
 * //单文件上传 Multipart 允许多个Part。 多个@Part，每个Part对应一个RequestBody。
 * //多文件上传 @PartMap有点类似@Part的封装版本。
 */
interface MyApiService {

    @GET
    fun get(@Url url: String, @QueryMap map: Map<String, Any>?): @JvmSuppressWildcards Observable<String>


    @FormUrlEncoded
    @POST
    fun post(@Url url: String, @FieldMap map: Map<String, Any>?): @JvmSuppressWildcards Observable<String>


    @POST
    fun postBody(@Url url: String, @Body body: RequestBody?): @JvmSuppressWildcards Observable<String>


    @FormUrlEncoded
    @PUT
    fun put(@Url url: String, @FieldMap map: Map<String, Any>?): @JvmSuppressWildcards Observable<String>


    @PUT
    fun putBody(@Url url: String, @Body body: RequestBody?): Observable<String>


    @DELETE
    fun delete(@Url url: String, @QueryMap map: Map<String, Any>?): @JvmSuppressWildcards Observable<String>

    @Multipart
    @POST
    fun upload(@Url url: String, @PartMap map: Map<String, RequestBody>?, @Part file: MultipartBody.Part?): Observable<String>


    @Multipart
    @POST
    fun uploads(@Url url: String, @PartMap map: Map<String, MultipartBody.Part>?): Observable<String>


    @Streaming
    @GET
    fun download(@Url url: String, @QueryMap map: MutableMap<String, Any>? ):@JvmSuppressWildcards  Observable<ResponseBody>

    @Streaming
    @GET
    fun download(@Url url: String):@JvmSuppressWildcards  Observable<ResponseBody>



}