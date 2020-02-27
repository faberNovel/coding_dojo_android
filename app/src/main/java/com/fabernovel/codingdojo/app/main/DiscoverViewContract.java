package com.fabernovel.codingdojo.app.main;

import com.fabernovel.codingdojo.entity.Movie;

import java.util.List;

public interface DiscoverViewContract {
    void showLoading();
    void showContent(List<Movie> model);
    void showError(CharSequence message);
}
