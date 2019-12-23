package com.fabernovel.codingdojo.entity;

import java.util.Date;

public class Movie {

    private final String title;
    private final Float rating;
    private final Date releaseDate;
    private final String genre;
    private final String posterPath;

    public Movie(String title, String posterPath, Float rating, Date releaseDate, String genre) {
        this.title = title;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Float getRating() {
        return rating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }
}
