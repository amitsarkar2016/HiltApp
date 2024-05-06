package `in`.knightcoder.hiltapp.remote.api

import `in`.knightcoder.hiltapp.remote.model.BaseResponse
import `in`.knightcoder.hiltapp.utils.UrlHelper
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DaggerHiltApi {

    @POST(UrlHelper.welcome)
    suspend fun fetchHomeData(): Response<BaseResponse>
}