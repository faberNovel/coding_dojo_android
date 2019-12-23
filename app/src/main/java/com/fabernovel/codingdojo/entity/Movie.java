package com.fabernovel.codingdojo.entity;

import java.util.Date;

public class Movie {

    private String title;
    private Float rating;
    private Date releaseDate;
    private String genre;
    private String posterPath;

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
