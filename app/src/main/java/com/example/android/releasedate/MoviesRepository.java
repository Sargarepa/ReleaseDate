package com.example.android.releasedate;

import com.example.android.releasedate.Exceptions.TMdBApiException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {

    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final String API_KEY = "459e450632dfc0e39bfcc76eff02e6c4";

    private static volatile MoviesRepository moviesRepository;

    private MoviesRetrofitService api;

    private MoviesRepository(MoviesRetrofitService api) {
        this.api = api;
    }


    //Use singleton pattern as the application won't need more than one instance of MoviesRepository
    public static MoviesRepository getInstance() {
        if (moviesRepository == null) {
            synchronized (MoviesRepository.class) {
                if (moviesRepository == null) {
                    Gson gson = createGsonForRetrofit();
                    Retrofit retrofit = createRetrofitInstance(gson);

                    moviesRepository = new MoviesRepository(retrofit.create(MoviesRetrofitService.class));
                }
            }
        }

        return moviesRepository;
    }

    private static Gson createGsonForRetrofit() {
        return new GsonBuilder()
                // give Gson parser for converting String to Date
                .registerTypeAdapter(Date.class, new DateJsonParser())
                .create();
    }

    private static Retrofit createRetrofitInstance(Gson gson) {
        return new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public void getMovies(final MoviesCallback callback) {
        api.getAllMovies(API_KEY).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    MoviesResponse moviesResponse = response.body();
                    if (moviesResponse != null && moviesResponse.results != null) {
                        callback.onSuccess(moviesResponse.results);
                    } else {
                        callback.onError(new TMdBApiException("Could not retrieve movie data"));
                    }
                } else {
                    callback.onError(new TMdBApiException("Could not retrieve movie data"));
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                callback.onError(new TMdBApiException("Could not retrieve movie data"));
            }
        });
    }

    public void getGenres(final GenresCallback callback) {
        api.getAllGenres(API_KEY).enqueue(new Callback<GenresResponse>() {
            @Override
            public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                if (response.isSuccessful()) {
                    GenresResponse genresResponse = response.body();
                    if (genresResponse != null && genresResponse.genres != null) {
                        callback.onSuccess(genresResponse.genres);
                    } else {
                        callback.onError(new TMdBApiException("Could not retrieve genre data"));
                    }
                } else {
                    callback.onError(new TMdBApiException("Could not retrieve genre data"));
                }
            }

            @Override
            public void onFailure(Call<GenresResponse> call, Throwable t) {
                callback.onError(new TMdBApiException("Could not retrieve genre data"));
            }
        });
    }
}
