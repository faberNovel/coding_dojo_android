package com.fabernovel.codingdojo.data;

import android.content.Context;

import com.fabernovel.codingdojo.entity.Movie;
import com.fabernovel.codingdojo.utils.AppExecutors;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

public class MovieRepository {

    private final AppExecutors appExecutors;
    private final Network network;

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w300/";

    public MovieRepository(AppExecutors appExecutors, Context context) {
        this.appExecutors = appExecutors;
        this.network = Network.getInstance(context);
    }

    public void getMovie(GetMovieCallback callback) {
        appExecutors.onNetworkIO(() -> {
            try {
                Movie movie = fetchMovie();
                dispatchSuccess(movie, callback);
            } catch (ParseException e) {
                Timber.w(e);
                dispatchError(e, callback);
            }
        });
    }

    @NotNull
    private Movie fetchMovie() throws ParseException {
        String dateString = "2019-10-02";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
        Date releaseDate = format.parse(dateString);
        return new Movie(
            "Joker",
            "https://image.tmdb.org/t/p/w300/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
            3.4F,
            releaseDate,
            "Drame"
        );
    }

    @NotNull
    private List<Movie> fetchMovies() throws ParseException {
        String dateString = "2019-10-02";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
        Date releaseDate = format.parse(dateString);
        ArrayList<Movie> list = new ArrayList<>();
        list.add(new Movie(
            "Joker",
            IMAGE_BASE_URL + "udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
            3.4F,
            releaseDate,
            "Drame"
        ));
        list.add(new Movie(
            "Ford v Ferrari",
            IMAGE_BASE_URL + "6ApDtO7xaWAfPqfi2IARXIzj8QS.jpg",
            3.9F,
            releaseDate,
            "Action"
        ));
        list.add(new Movie(
            "Proxima",
            IMAGE_BASE_URL + "41YWD0M32uo6ekBEvVrQgidyemB.jpg",
            3.5F,
            releaseDate,
            "Drame"
        ));
        return list;
    }

    private void dispatchError(Throwable e, GetMovieCallback callback) {
        appExecutors.onMainThread(() -> callback.onError(e));
    }

    private void dispatchSuccess(Movie movie, GetMovieCallback callback) {
        appExecutors.onMainThread(() -> callback.onGetMovie(movie));
    }
}
