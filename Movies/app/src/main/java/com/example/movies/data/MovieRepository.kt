package com.example.movies.data

import com.example.movies.model.MovieResults
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository {

    fun getPopularMovies(): Observable<MovieResults> {
        return ApiInstance.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}