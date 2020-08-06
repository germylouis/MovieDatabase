package com.example.movies.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("popularity")
    @Expose
    var popularity: Double,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int,
    @SerializedName("video")
    @Expose
    var video: Boolean,
    @SerializedName("poster_path")
    @Expose
    var posterPath: String?,
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("adult")
    @Expose
    var adult: Boolean,
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String?,
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String?,
    @SerializedName("original_title")
    @Expose
    var originalTitle: String?,
    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>,
    @SerializedName("title")
    @Expose
    var title: String?,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double,
    @SerializedName("overview")
    @Expose
    var overview: String?,
    @SerializedName("release_date")
    @Expose
    var releaseDate: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("genreIds"),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(popularity)
        parcel.writeInt(voteCount)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeString(posterPath)
        parcel.writeInt(id)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(backdropPath)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(title)
        parcel.writeDouble(voteAverage)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}