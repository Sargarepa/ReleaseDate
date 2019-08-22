package com.example.android.releasedate;

import com.example.android.releasedate.Exceptions.MoviesCallbackException;

import java.util.List;

public interface MoviesCallback {

    void onSuccess(List<Movie> movies);

    void onError(MoviesCallbackException e);
}
