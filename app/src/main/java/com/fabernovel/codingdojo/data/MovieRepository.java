package com.fabernovel.codingdojo.data;

import android.content.Context;
import android.icu.util.Calendar;
import com.fabernovel.codingdojo.entity.Movie;
import com.fabernovel.codingdojo.utils.AppExecutors;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Response;

public class MovieRepository {

    private final AppExecutors appExecutors;
    private final RestMovieMapper mapper;
    private final Network network;

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
    private static final String API_KEY = "2f2b8c1d8b356448ef10151490944133";
    private static final String FRENCH = "fr";
    private static final String SORT_BY_VOTE = "vote_average.desc";

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w300/";

    public MovieRepository(
        AppExecutors appExecutors,
        RestMovieMapper mapper,
        Context context
    ) {
        this.appExecutors = appExecutors;
        this.mapper = mapper;
        this.network = Network.getInstance(context);
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

    public void getUpcomingMovies(GetMoviesCallback callback) {
        appExecutors.onNetworkIO(() -> {
            Call<RestDiscoverResponse> call = network.movieApi().discoverMovies(
                API_KEY,
                today(),
                null,
                null,
                null,
                FRENCH
            );

            fetchMovieList(callback, call);
        });
    }

    public void getTopMoviesOfTheYear(GetMoviesCallback callback) {
        appExecutors.onNetworkIO(() -> {
            Call<RestDiscoverResponse> call = network.movieApi().discoverMovies(
                API_KEY,
                null,
                null,
                currentYear(),
                SORT_BY_VOTE,
                FRENCH
            );
            fetchMovieList(callback, call);
        });
    }

    public void getMoviesInTheatre(GetMoviesCallback callback) {
        appExecutors.onNetworkIO(() -> {
            Call<RestDiscoverResponse> call = network.movieApi().discoverMovies(
                API_KEY,
                inCinemaStartDate(),
                today(),
                null,
                null,
                FRENCH
            );

            fetchMovieList(callback, call);
        });
    }

    private void fetchMovieList(GetMoviesCallback callback, Call<RestDiscoverResponse> call) {
        try {
            Response<RestDiscoverResponse> response = call.execute();
            List<Movie> movies = new ArrayList<>();

            if (response.isSuccessful() && response.body() != null) {
                List<RestMovie> restMovies = response.body().getResults();
                if (restMovies != null) {
                    restMovies.forEach(restMovie -> {
                        List<RestGenre> restGenres = getMovieGenres(
                            restMovie, callback
                        );
                        movies.add(mapper.map(restMovie, restGenres));
                    });
                }
                dispatchSuccess(movies, callback);
            }
        } catch (IOException e) {
            e.printStackTrace();
            dispatchError(e, callback);
        }
    }

    @NotNull
    private List<RestGenre> getMovieGenres(RestMovie restMovie, GetMoviesCallback callback) {
        List<RestGenre> restGenres = new ArrayList<>();
        restMovie.getGenreIds().forEach(genreId ->
            restGenres.add(getGenre(genreId, callback))
        );
        return restGenres;
    }

    private int currentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    private String today() {
        Date date = new Date();
        return FORMAT.format(date);
    }

    private String inCinemaStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date fromDate = calendar.getTime();
        return FORMAT.format(fromDate);
    }

    private RestGenre getGenre(int id, GetMoviesCallback callback) {
        Call<RestGenre> genreCall = network.movieApi().getGenre(id, API_KEY, FRENCH);
        try {
            Response<RestGenre> response = genreCall.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
            dispatchError(e, callback);
        }
        return null;
    }

    private void dispatchError(Throwable e, GetMovieCallback callback) {
        appExecutors.onMainThread(() -> callback.onError(e));
    }

    private void dispatchError(Throwable e, GetMoviesCallback callback) {
        appExecutors.onMainThread(() -> callback.onError(e));
    }

    private void dispatchSuccess(Movie movie, GetMovieCallback callback) {
        appExecutors.onMainThread(() -> callback.onGetMovie(movie));
    }

    private void dispatchSuccess(List<Movie> movies, GetMoviesCallback callback) {
        appExecutors.onMainThread(() -> callback.onGetMovies(movies));
    }
}
