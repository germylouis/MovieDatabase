package com.example.movies.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.data.MovieRepository
import com.example.movies.viewmodel.MovieViewModel
import com.example.movies.viewmodel.VMFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mSearchText: SearchView
    private lateinit var viewModel: MovieViewModel
    private lateinit var rView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rView = rv
        mSearchText = search
        viewModel =
            ViewModelProvider(this, VMFactory(MovieRepository())).get(MovieViewModel::class.java)

        populateList()
    }

    private fun populateList() {
        viewModel.getPopularMovies()
        setRView()
    }

    private fun setRView() {
        rView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rView.adapter = viewModel.mAdapter

    }


}