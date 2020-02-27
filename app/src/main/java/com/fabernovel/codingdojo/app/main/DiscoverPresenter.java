package com.fabernovel.codingdojo.app.main;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.fabernovel.codingdojo.data.GetMoviesCallback;
import com.fabernovel.codingdojo.data.MovieRepository;
import com.fabernovel.codingdojo.data.RestMovieMapper;
import com.fabernovel.codingdojo.entity.Movie;
import com.fabernovel.codingdojo.utils.AppExecutors;

import java.util.List;

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
    private GetMoviesCallback getMoviesCallback = new GetMoviesCallback() {
        @Override
        public void onGetMovies(List<Movie> movies) {
            if (view != null) {
                view.showContent(movies);
            }
        }

        @Override
        public void onError(@NonNull Throwable error) {
            if (view != null) {
                view.showError(error.getMessage());
            }
        }
    };

    private DiscoverPresenter(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void start(@NonNull DiscoverViewContract view) {
        this.view = view;
        view.showLoading();
        getMovies();
    }

    private void getMovies() {
        movieRepository.getMovies(getMoviesCallback);
    }

    public void stop() {
        this.view = null;
    }
}
