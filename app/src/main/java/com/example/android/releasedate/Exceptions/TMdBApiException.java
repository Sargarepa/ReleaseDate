package com.example.android.releasedate.Exceptions;

import com.google.gson.annotations.Expose;

public class TMdBApiException extends Exception {

    public TMdBApiException(String message) {
        super(message);
    }

}
