package com.example.android.releasedate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.releasedate.Exceptions.MoviesCallbackException;

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

        adapter = new MoviesRecyclerAdapter();

        moviesRepository = MoviesRepository.getInstance();

        moviesRepository.getMovies(new MoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                adapter.setMoviesList(movies);
                moviesList.setAdapter(adapter);
            }

            @Override
            public void onError(MoviesCallbackException e) {
                e.printStackTrace();
            }
        });
    }




}
