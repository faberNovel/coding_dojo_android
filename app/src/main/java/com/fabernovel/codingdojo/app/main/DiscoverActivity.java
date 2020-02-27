package com.fabernovel.codingdojo.app.main;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.fabernovel.codingdojo.R;
import com.fabernovel.codingdojo.entity.Movie;

public class DiscoverActivity extends AppCompatActivity implements DiscoverViewContract {

    private DiscoverPresenter presenter;

    private TextView discoverMessage;
    private RecyclerView movieRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = DiscoverPresenter.getInstance(this);
        setContentView(R.layout.activity_discover);

        discoverMessage = findViewById(R.id.discoverMessage);
        movieRecycler = findViewById(R.id.movie_recycler);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start(this);
    }

    @Override
    protected void onStop() {
        presenter.stop();
        super.onStop();
    }

    @Override
    public void showLoading() {
        discoverMessage.setText(R.string.loading);
        discoverMessage.setVisibility(View.VISIBLE);
        movieRecycler.setVisibility(View.GONE);
    }

    @Override
    public void showContent(Movie model) {
        discoverMessage.setVisibility(View.GONE);
        movieRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(CharSequence message) {
        discoverMessage.setText(message);
        discoverMessage.setVisibility(View.VISIBLE);
        movieRecycler.setVisibility(View.GONE);
    }
}
