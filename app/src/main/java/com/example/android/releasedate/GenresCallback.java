package com.example.android.releasedate;

import com.example.android.releasedate.Exceptions.TMdBApiException;

import java.util.List;

public interface GenresCallback {

    void onSuccess(List<Genre> genres);

    void onError(TMdBApiException e);
}
