package com.test.bookmyseat.webcalls

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.bookmyseat.Constants.BASE_URL
import com.test.bookmyseat.models.MovieDetailsModel
import com.test.bookmyseat.models.MoviesListResponse
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

class RestClientManager {
    companion object {
        private var mInstance: RestClientManager? = null
        lateinit var context: Context

        fun getInstance(context: Context): RestClientManager {
            this.context = context
            if (mInstance == null) {
                mInstance = RestClientManager()
            }
            return mInstance!!
        }
    }

    private lateinit var retrofit: Retrofit
    private lateinit var restService: RestService
    private lateinit var okHttpClient: OkHttpClient

    init {
        initClient()
    }

    private fun initClient() {
        val builder = OkHttpClient.Builder()

        val interceptor = HttpLoggingInterceptor()
        builder.addNetworkInterceptor(interceptor).build()
        builder.addNetworkInterceptor { chain ->
            chain.proceed(chain.request())
        }
        builder.addInterceptor(RequestInterceptor())
        okHttpClient = builder.build()
        okHttpClient.dispatcher.maxRequestsPerHost = 20
        val gson: Gson = GsonBuilder().setLenient().create();
        val gsonFactory = GsonConverterFactory.create(gson)
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl(BASE_URL)
        retrofitBuilder.client(okHttpClient)
        retrofitBuilder.addConverterFactory(gsonFactory)
        retrofit = retrofitBuilder.build()
        restService = retrofit.create(RestService::class.java)
    }

    class RequestInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            val requestBuilder = request.newBuilder()
            val httpBuilder: HttpUrl.Builder = request.url.newBuilder().addQueryParameter("api_key", "ec5a5e7c478c3a01167e2f8fc77bab8a")
            requestBuilder.url(httpBuilder.build())
            request = requestBuilder.build()
            return chain.proceed(request)
        }
    }

    fun getMovieList(callback: Callback<MoviesListResponse>) {
        val call = restService.getMovies()
        call.enqueue(callback)
    }

    fun getMovieDetails(movieId: Int, callback: Callback<MovieDetailsModel>) {
        val call = restService.getMovieDetails(movieId)
        call.enqueue(callback)
    }

}