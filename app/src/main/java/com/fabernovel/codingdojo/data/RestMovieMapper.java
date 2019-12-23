package com.fabernovel.codingdojo.data;

import androidx.annotation.NonNull;

import com.fabernovel.codingdojo.entity.Movie;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RestMovieMapper {

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w300/";

    @NotNull
    Movie map(@NonNull RestMovie toMap, List<RestGenre> restGenres) {

        StringBuilder genres = new StringBuilder();
        for (RestGenre genre : restGenres) {
            if (genre != null) {
                genres.append(genre.getGenreName());
                if (restGenres.indexOf(genre) < restGenres.size() - 1)
                    genres.append(", ");
            }
        }

        return new Movie(
            toMap.getTitle(),
            IMAGE_BASE_URL + toMap.getPosterPath(),
            toMap.getVoteAverage() / 2.0F,
            toMap.getReleaseDate(),
            genres.toString()
        );
    }
}
