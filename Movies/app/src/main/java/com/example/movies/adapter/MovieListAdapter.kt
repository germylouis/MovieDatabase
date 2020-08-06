package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.model.Result
import com.example.movies.util.Constants.IMG_BASE_URL

class MovieListAdapter(private var myClickListener: MyClickListener) :
    RecyclerView.Adapter<MovieListAdapter.MovieListHolder>() {


    var movieList: List<Result> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_view, parent, false)

        return MovieListHolder(view, movieList, myClickListener)
    }


    override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
        holder.movieName.text = movieList[position].originalTitle
        holder.movieScore.text = movieList[position].popularity.toString()
        holder.releaseDate.text = movieList[position].releaseDate

        Glide.with(holder.itemView.context)
            .load(IMG_BASE_URL + movieList[position].posterPath)
            .into(holder.movieImg)
    }

    override fun getItemCount(): Int = movieList.size

    interface MyClickListener {
        fun onClick(result: Result)
    }

    inner class MovieListHolder(
        itemView: View,
        private var list: List<Result>,
        private var myClickListener: MyClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var movieName: TextView = itemView.findViewById(R.id.movieName)
        var movieImg: ImageView = itemView.findViewById(R.id.movieImg)
        var movieScore: TextView = itemView.findViewById(R.id.movieScore)
        var releaseDate: TextView = itemView.findViewById(R.id.releaseDate)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            myClickListener.onClick(list[adapterPosition])
        }
    }
}
