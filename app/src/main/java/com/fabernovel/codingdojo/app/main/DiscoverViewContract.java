package com.fabernovel.codingdojo.app.main;

import com.fabernovel.codingdojo.entity.Movie;

public interface DiscoverViewContract {
    void showLoading();
    void showContent(Movie model);
    void showError(CharSequence message);
}
