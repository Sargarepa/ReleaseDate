package com.example.android.releasedate;

import java.util.List;

public interface MoviesCallback {

    void onSuccess(List<Movie> movies);

    void onError();
}
