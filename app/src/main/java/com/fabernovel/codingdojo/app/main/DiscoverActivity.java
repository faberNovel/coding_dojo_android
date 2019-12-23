package com.fabernovel.codingdojo.app.main;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;
import com.fabernovel.codingdojo.R;

public class DiscoverActivity extends AppCompatActivity implements DiscoverViewContract {
    private DiscoverPresenter presenter = DiscoverPresenter.getInstance();

    private TextView messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        messageView = findViewById(R.id.main_message);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void showLoading() {
        messageView.setText(R.string.loading);
    }

    @Override
    public void showContent(CharSequence message) {
        messageView.setText(message);
    }

    @Override
    public void showError(CharSequence message) {
        messageView.setText(getString(R.string.error, message));
    }
}
