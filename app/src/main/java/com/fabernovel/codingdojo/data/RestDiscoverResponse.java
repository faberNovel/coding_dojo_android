package com.fabernovel.codingdojo.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestDiscoverResponse {
    @SerializedName("results")
    private List<RestMovie> results;

    List<RestMovie> getResults() {
        return results;
    }
}
