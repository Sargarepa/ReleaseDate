package com.example.android.releasedate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    private MoviesRetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createRetrofitService();

        retrofitService.getAllMovies(API_KEY).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                Log.e("MainActivity", response.body().results.toString());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e("MainActivity", "Network failed because of " + t);
            }
        });
    }

    private void createRetrofitService() {
        Gson gson = createGsonForRetrofit();
        Retrofit retrofit = createRetrofitInstance(gson);

        // Create an instance of MoviesRetrofitService that actually knows how to perform the request
        retrofitService = retrofit.create(MoviesRetrofitService.class);
    }

    private Gson createGsonForRetrofit() {
        return new GsonBuilder()
                    // give Gson parser for converting String to Date
                    .registerTypeAdapter(Date.class, new DateJsonParser())
                    .create();
    }

    private Retrofit createRetrofitInstance(Gson gson) {
        return new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private static final String API_KEY = "459e450632dfc0e39bfcc76eff02e6c4";

    private interface MoviesRetrofitService {
        @GET("/3/discover/movie")
        Call<MoviesResponse> getAllMovies(@Query("api_key") String apiKey);
    }
}
