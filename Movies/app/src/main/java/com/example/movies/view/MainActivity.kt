package com.example.movies.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.adapter.MovieListAdapter
import com.example.movies.data.MovieRepository
import com.example.movies.model.Result
import com.example.movies.viewmodel.MovieViewModel
import com.example.movies.viewmodel.VMFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MovieListAdapter.MyClickListener {

    private var movieList: List<Result> = ArrayList()
    private var movieFragment: MovieFragment = MovieFragment()

    private lateinit var mSearchText: SearchView
    private lateinit var viewModel: MovieViewModel
    private lateinit var rView: RecyclerView
    private lateinit var mAdapter: MovieListAdapter
    private lateinit var resultObserver: Observer<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rView = rv
        mSearchText = search
        viewModel =
            ViewModelProvider(this, VMFactory(MovieRepository())).get(MovieViewModel::class.java)
        mAdapter = MovieListAdapter(this)
        populateList()

    }

    private fun populateList() {
        viewModel.getPopularMovies()
        movieList = viewModel.returnMovies

        resultObserver = Observer {
            movieList = viewModel.returnMovies
            setRView()
        }
        viewModel.myMoviesReturned.observe(this, resultObserver)
    }

    private fun setRView() {
        rView.layoutManager = LinearLayoutManager(this@MainActivity)
        rView.adapter = mAdapter

        mAdapter.movieList = movieList
        mAdapter.notifyDataSetChanged()

    }

    override fun onClick(result: Result) {
        val movieBun = Bundle()
        val fm: FragmentManager = supportFragmentManager
        movieBun.putParcelable("movie", result)
        movieFragment.arguments = movieBun

        if (movieFragment.isAdded) {
            fm.beginTransaction().remove(movieFragment).commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frame_layout, movieFragment)
                .addToBackStack(movieFragment.tag)
                .commit()
        }
    }


}







