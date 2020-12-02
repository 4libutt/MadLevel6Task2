package com.example.madlevel6task2.api

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {
    companion object {
        private const val baseUrl = "https://api.themoviedb.org/"

        fun createApi(): MovieApiService {
            // Create logging client
            val okHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(ApiInterceptor())
                .build()

            val triviaApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return triviaApi.create(MovieApiService::class.java)
        }
    }
}

class ApiInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl = request.url.newBuilder().addQueryParameter("api_key", "eeb09676fc362df20a5e511f1844ada6").build()
        request = request.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}