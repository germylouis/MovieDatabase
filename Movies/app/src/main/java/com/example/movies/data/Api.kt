package com.example.movies.data

import com.example.movies.model.MovieResults
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("popular")
    fun getPopularMovies(
        @Query("api_key") key: String
    ): Observable<MovieResults>
}