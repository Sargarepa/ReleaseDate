package com.example.android.releasedate;

import com.example.android.releasedate.Exceptions.TMdBApiException;

import java.util.List;

public interface MoviesCallback {

    void onSuccess(List<Movie> movies);

    void onError(TMdBApiException e);
}
