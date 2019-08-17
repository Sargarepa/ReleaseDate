package com.example.android.releasedate;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {
    @SerializedName("results")
    public List<Movie> results;

}
