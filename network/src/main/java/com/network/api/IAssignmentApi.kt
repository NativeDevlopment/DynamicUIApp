package com.network.api

import com.network.dto.DynamicUiData
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface IAssignmentApi {
    @GET
    suspend fun fetchCustomUi(@Url  url:String): DynamicUiData
    @GET
    suspend fun fetchImage(@Url  url:String):ResponseBody
}