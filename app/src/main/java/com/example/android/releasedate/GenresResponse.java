package com.example.android.releasedate;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenresResponse {

    @SerializedName("genres")
    List<Genre> genres;
}
