package com.fabernovel.codingdojo.data;

import androidx.annotation.NonNull;
import com.fabernovel.codingdojo.entity.Movie;
import java.util.List;

public interface GetMoviesCallback {
    void onGetMovies(List<Movie> movies);
    void onError(@NonNull Throwable error);
}
