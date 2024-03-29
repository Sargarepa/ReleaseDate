package com.example.android.releasedate;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

class Movie {

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("genre_ids")
    public List<Integer> genreIds;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("release_date")
    public Date releaseDate;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genreIds=" + genreIds +
                ", posterPath='" + posterPath + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
