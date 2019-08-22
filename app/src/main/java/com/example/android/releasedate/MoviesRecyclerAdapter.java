package com.example.android.releasedate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MoviesRecyclerAdapter.MovieViewHolder> {

    private List<Movie> moviesList = new ArrayList<>();


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Movie movie = moviesList.get(i);

        movieViewHolder.titleView.setText(movie.title);
        movieViewHolder.genreView.setText(movie.genreIds.toString());


        Date releaseDate = movie.releaseDate;
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.US);
        movieViewHolder.dateView.setText(formatter.format(releaseDate));
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        final ImageView posterView;

        final TextView titleView;
        final TextView genreView;
        final TextView dateView;

        MovieViewHolder(View view) {
            super(view);

            posterView = view.findViewById(R.id.item_poster);
            titleView = view.findViewById(R.id.item_title);
            genreView = view.findViewById(R.id.item_genre);
            dateView = view.findViewById(R.id.item_release_date);

        }
    }
}
