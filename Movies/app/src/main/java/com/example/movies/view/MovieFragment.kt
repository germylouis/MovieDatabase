package com.example.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.model.Result
import com.example.movies.util.Constants.IMG_BASE_URL
import kotlinx.android.synthetic.main.movie_fragment.view.*

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val movie = bundle?.getParcelable<Result>("movie")

        view.fragment_title.text = movie?.title
        view.fragment_score.text = movie?.voteAverage.toString()
        view.fragment_date.text = movie?.releaseDate
        view.fragment_description.text = movie?.overview

        Glide.with(view.context.applicationContext)
            .load(IMG_BASE_URL + movie?.posterPath)
            .into(view.fragment_poster)

    }


}