package com.fabernovel.codingdojo.data;

import androidx.annotation.Nullable;
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
    Call<RestDiscoverResponse> discoverMovies(
        @Query("api_key") String apiKey,
        @Nullable @Query("primary_release_date.gte") String fromDate,
        @Nullable @Query("primary_release_date.gte") String toDate,
        @Nullable @Query("primary_release_year") Integer year,
        @Nullable @Query("sort_by") String sortBy,
        @Query("language") String language
    );
}
