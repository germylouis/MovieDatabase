package com.example.movies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.movies.adapter.MovieListAdapter
import com.example.movies.data.MovieRepository
import com.example.movies.model.MovieResults
import io.reactivex.disposables.CompositeDisposable


class MovieViewModel(private val mRepository: MovieRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private var movieList: List<MovieResults> = ArrayList()
    val mAdapter = MovieListAdapter()


    fun getPopularMovies() {
        mRepository
            .getPopularMovies()
            .subscribe({ response ->
                mAdapter.movieList = response.results
                movieList = listOf(response)
                mAdapter.notifyDataSetChanged()
            }, {
                Log.d("TAG", "Error: ${it.message}")
            })?.let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
