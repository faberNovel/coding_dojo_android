package com.fabernovel.codingdojo.data;

import androidx.annotation.NonNull;

import com.fabernovel.codingdojo.entity.Movie;

public interface GetMovieCallback {
    void onGetMovie(Movie movie);
    void onError(@NonNull Throwable error);
}
