package com.example.android.releasedate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView moviesList;
    private MoviesRecyclerAdapter adapter;

    private MoviesRepository moviesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesList = findViewById(R.id.movies_list);
        moviesList.setLayoutManager(new LinearLayoutManager(this));

        moviesRepository = MoviesRepository.getInstance();

        moviesRepository.getMovies(new MoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                adapter = new MoviesRecyclerAdapter(movies);
                moviesList.setAdapter(adapter);
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "Failed retrieving movie data", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
