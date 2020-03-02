package com.fabernovel.codingdojo.app.main;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.fabernovel.codingdojo.R;
import com.fabernovel.codingdojo.entity.Movie;
import com.google.android.material.tabs.TabLayout;
import java.util.List;

public class DiscoverActivity extends AppCompatActivity implements DiscoverViewContract {
    private DiscoverPresenter presenter;
    MoviesAdapter adapter = new MoviesAdapter();

    private TextView discoverMessage;
    private RecyclerView movieRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = DiscoverPresenter.getInstance(this);
        setContentView(R.layout.activity_discover);
        setupTabLayout();

        discoverMessage = findViewById(R.id.discoverMessage);
        movieRecycler = findViewById(R.id.movie_recycler);
        setupRecycler();
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

    private void setupTabLayout() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabLayout.Tab topYearTab = tabLayout.getTabAt(1);
        if (topYearTab != null) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            topYearTab.setText(getString(R.string.tab_top_year_title, currentYear));
        }
    }
    private void setupRecycler() {
        movieRecycler.setAdapter(adapter);
    }

    @Override
    public void showLoading() {
        discoverMessage.setText(R.string.loading);
        discoverMessage.setVisibility(View.VISIBLE);
        movieRecycler.setVisibility(View.GONE);
    }

    @Override
    public void showContent(List<Movie> movies) {
        adapter.submitList(movies);
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
