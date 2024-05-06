package `in`.knightcoder.hiltapp.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `in`.knightcoder.hiltapp.remote.api.DaggerHiltApi
import `in`.knightcoder.hiltapp.repository.DaggerHiltRepository
import `in`.knightcoder.hiltapp.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDaggerHiltRepository(gaggerHiltApi: DaggerHiltApi) = DaggerHiltRepository(gaggerHiltApi)


    @Singleton
    @Provides
    fun provideDaggerHiltApi(): DaggerHiltApi {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        httpClient.addInterceptor { chain ->
            var builders = chain.request()
            try {
                builders = chain.request().newBuilder().header("Accept", "application/json")
                    .header("Accept", "application/x-www-form-urlencoded").build()
            } catch (e: UnknownHostException) {
                Log.e("RetrofitCall", "UnknownHostException", e)
            } catch (e: SocketTimeoutException) {
                Log.e("RetrofitCall", "Socket Timeout", e)
            } catch (e: SocketException) {
                Log.e("RetrofitCall", "Socket Exception", e)
            } catch (e: IOException) {
                Log.e("RetrofitCall", "IOException", e)
            } catch (e: Exception) {
                Log.e("RetrofitCall", "Exception", e)
            }
            chain.proceed(builders)
        }

        return Retrofit.Builder().client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(Constants.BASE_URL).build()
            .create(DaggerHiltApi::class.java)
    }


}
