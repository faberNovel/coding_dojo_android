package com.fabernovel.codingdojo.app.main;

public interface DiscoverViewContract {
    void showLoading();
    void showContent(CharSequence message);
    void showError(CharSequence message);
}
