package com.example.movies.data

import com.example.movies.model.MovieResults
import com.example.movies.util.Constants.API_KEY
import com.example.movies.util.Constants.BASE_URL
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {

    private var api: Api

    init {
        api = createApiInstance(getInstance())
    }

    private fun createApiInstance(mInstance: Retrofit): Api {
        return mInstance.create<Api>(Api::class.java)
    }

    private fun getInstance(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient =
            OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getMovies(): Observable<MovieResults> {
        return api.getPopularMovies(API_KEY)
    }
}