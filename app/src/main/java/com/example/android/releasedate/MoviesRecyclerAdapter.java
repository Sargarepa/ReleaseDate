package com.example.android.releasedate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MoviesRecyclerAdapter.MovieViewHolder> {

    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185";

    private List<Genre> genres = new ArrayList<>();
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
        movieViewHolder.genreView.setText(getGenres(movie.genreIds));


        Date releaseDate = movie.releaseDate;
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.US);
        movieViewHolder.dateView.setText(formatter.format(releaseDate));

        Glide.with(movieViewHolder.itemView)
                .load(IMAGE_BASE_URL + movie.posterPath)
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(movieViewHolder.posterView);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    private String getGenres(List<Integer> genreIds) {
        List<String> genreList = new ArrayList<>();
        for (Integer genreId : genreIds) {
            for (Genre genre : genres) {
                if (genreId == genre.id) {
                    genreList.add(genre.name);
                    break;
                }
            }
        }
        return TextUtils.join(", ", genreList);
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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
