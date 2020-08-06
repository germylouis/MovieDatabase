package com.example.movies.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResults(
    @SerializedName("page")
    @Expose
    var page: Int,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int,
    @SerializedName("results")
    @Expose var results: List<Result>
)