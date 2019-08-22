package com.example.android.releasedate.Exceptions;

import com.google.gson.annotations.Expose;

public class MoviesCallbackException extends Exception {

    public MoviesCallbackException(String message) {
        super(message);
    }

}
