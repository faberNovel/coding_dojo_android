package com.fabernovel.codingdojo.app.main;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.fabernovel.codingdojo.data.GetMovieCallback;
import com.fabernovel.codingdojo.data.MovieRepository;
import com.fabernovel.codingdojo.data.RestMovieMapper;
import com.fabernovel.codingdojo.entity.Movie;
import com.fabernovel.codingdojo.utils.AppExecutors;

public class DiscoverPresenter {

    public static DiscoverPresenter getInstance(Context context) {
        final MovieRepository movieRepository = new MovieRepository(
            AppExecutors.getInstance(),
            new RestMovieMapper(),
            context
        );
        return new DiscoverPresenter(movieRepository);
    }

    @NonNull
    private final MovieRepository movieRepository;

    @Nullable
    private DiscoverViewContract view;

    @NonNull
    private GetMovieCallback getMovieCallback = new GetMovieCallback() {
        @Override
        public void onGetMovie(Movie movie) {
            // TODO: show content in view
        }

        @Override
        public void onError(@NonNull Throwable error) {
            // TODO: 20/08/2020 (sjcqs) show error in view
        }
    };

    private DiscoverPresenter(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void start(@NonNull DiscoverViewContract view) {
        this.view = view;
        // TODO: show loading and load content
    }

    public void stop() {
        this.view = null;
    }
}
