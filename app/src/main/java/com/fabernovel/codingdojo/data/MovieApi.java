package com.fabernovel.codingdojo.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface MovieApi {

    @GET("/3/genre/{id}")
    Call<RestGenre> getGenre(
        @Path("id") Integer id,
        @Query("api_key") String apiKey,
        @Query("language") String language
    );

    @GET("/3/discover/movie")
    Call<RestDiscoverResponse> getMoviesInTheatre(
        @Query("api_key") String apiKey,
        @Query("primary_release_date.gte") String fromDate,
        @Query("primary_release_date.lte") String toDate,
        @Query("language") String language
    );

    @GET("/3/discover/movie?sort_by=vote_average.desc")
    Call<RestDiscoverResponse> getTopMovies(
        @Query("api_key") String apiKey,
        @Query("primary_release_year") Integer year
    );

    @GET("/3/discover/movie")
    Call<RestDiscoverResponse> getUpcomingMovies(
        @Query("api_key") String apiKey,
        @Query("primary_release_date.gte") String fromDate,
        @Query("language") String language
    );
}
