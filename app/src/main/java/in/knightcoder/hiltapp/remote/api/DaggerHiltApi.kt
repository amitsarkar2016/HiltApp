package `in`.knightcoder.hiltapp.remote.api

import `in`.knightcoder.hiltapp.remote.model.BaseResponse
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DaggerHiltApi {

    @FormUrlEncoded
    @POST("test")
    suspend fun fetchHomeData(): Response<BaseResponse>
}