package com.example.android.releasedate;

import com.example.android.releasedate.Exceptions.MoviesCallbackException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {

    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final String API_KEY = "459e450632dfc0e39bfcc76eff02e6c4";

    private static MoviesRepository moviesRepository;

    private MoviesRetrofitService api;

    private MoviesRepository(MoviesRetrofitService api) {
        this.api = api;
    }


    //Use singleton pattern as the application won't need more than one instance of MoviesRepository
    public static MoviesRepository getInstance() {
        if (moviesRepository == null) {
            synchronized (MoviesRepository.class) {
                Gson gson = createGsonForRetrofit();
                Retrofit retrofit = createRetrofitInstance(gson);

                moviesRepository = new MoviesRepository(retrofit.create(MoviesRetrofitService.class));
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
                    MoviesResponse movies = response.body();
                    if (movies != null && movies.results != null) {
                        callback.onSuccess(movies.results);
                    } else {
                        callback.onError(new MoviesCallbackException("Could not retrieve movie data"));
                    }
                } else {
                    callback.onError(new MoviesCallbackException("Could not retrieve movie data"));
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                callback.onError(new MoviesCallbackException("Could not retrieve movie data"));
            }
        });
    }
}
