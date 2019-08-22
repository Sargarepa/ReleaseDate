package com.example.android.releasedate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface MoviesRetrofitService {
    @GET("/3/discover/movie")
    Call<MoviesResponse> getAllMovies(@Query("api_key") String apiKey);

    @GET("/3/genre/movie/list")
    Call<GenresResponse> getAllGenres(@Query("api_key") String apiKey);
}