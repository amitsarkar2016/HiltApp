package `in`.knightcoder.hiltapp.repository

import `in`.knightcoder.hiltapp.remote.api.DaggerHiltApi
import `in`.knightcoder.hiltapp.remote.model.BaseResponse
import `in`.knightcoder.hiltapp.utils.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DaggerHiltRepository @Inject constructor(
    private val api: DaggerHiltApi,
) {

    private val _homeResponse = MutableSharedFlow<Resource<BaseResponse>?>()
    val homeResponse: SharedFlow<Resource<BaseResponse>?> get() = _homeResponse

    suspend fun fetchHomeData() {
        try {
            val result = api.fetchHomeData()
            result.body()?.let {
                _homeResponse.emit(Resource.Success(it))
            }
        } catch (e: IOException) {
            _homeResponse.emit(Resource.Error("Network error occurred."))
        } catch (e: HttpException) {
            _homeResponse.emit(Resource.Error("HTTP error: ${e.code()}"))
        } catch (e: Exception) {
            e.printStackTrace()
            _homeResponse.emit(Resource.Error("An unknown error occurred."))
        }
    }


}
