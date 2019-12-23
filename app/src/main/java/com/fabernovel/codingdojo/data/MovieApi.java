package com.fabernovel.codingdojo.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface MovieApi {

    @GET("/3/discover/movie")
    Call<RestDiscoverResponse> getUpcomingMovies(
        @Query("api_key") String apiKey,
        @Query("primary_release_date.gte") String fromDate,
        @Query("language") String language
    );
}
