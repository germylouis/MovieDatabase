package com.example.movies.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.data.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import com.example.movies.model.Result

class MovieViewModel(private val mRepository: MovieRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var myMoviesReturned = MutableLiveData<Boolean>()
    var returnMovies: List<Result> = ArrayList()

    fun getPopularMovies() {

        compositeDisposable.add(
            mRepository.getPopularMovies().subscribe(
                { schoolResults ->
                    returnMovies = schoolResults.results
                    myMoviesReturned.setValue(true)
                },
                {
                    Log.d("TAG_X", "Popular Movies Function in View Model:${it.message} ")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}
