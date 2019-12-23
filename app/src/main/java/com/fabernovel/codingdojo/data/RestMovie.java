package com.fabernovel.codingdojo.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

class RestMovie {

    @SerializedName("id")
    private int id;
    @SerializedName("genre_ids")
    private List<Integer> genreIds;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private Date releaseDate;
    @SerializedName("title")
    private String title;
    @SerializedName("vote_average")
    private Float voteAverage;

    Float getVoteAverage() {
        return voteAverage;
    }

    List<Integer> getGenreIds() {
        return genreIds;
    }

    String getPosterPath() {
        return posterPath;
    }

    Date getReleaseDate() {
        return releaseDate;
    }

    String getTitle() {
        return title;
    }
}
