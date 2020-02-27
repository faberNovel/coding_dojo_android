package com.fabernovel.codingdojo.app.main;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.fabernovel.codingdojo.data.GetMovieCallback;
import com.fabernovel.codingdojo.data.MovieRepository;
import com.fabernovel.codingdojo.entity.Movie;
import com.fabernovel.codingdojo.utils.AppExecutors;

import java.util.List;

public class DiscoverPresenter {

    public static DiscoverPresenter getInstance(Context context) {
        final MovieRepository movieRepository = new MovieRepository(
            AppExecutors.getInstance(),
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
        public void onGetMovies(@NonNull List<Movie> movies) {
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
        movieRepository.getMovies(getMovieCallback);
    }

    public void stop() {
        this.view = null;
    }
}
