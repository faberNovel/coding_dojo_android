package com.fabernovel.codingdojo.app.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.fabernovel.codingdojo.R;
import com.fabernovel.codingdojo.entity.Movie;
import com.fabernovel.codingdojo.utils.DateUtils;
import com.squareup.picasso.Picasso;

public class DiscoverActivity extends AppCompatActivity implements DiscoverViewContract {

    private DiscoverPresenter presenter;

    private CardView movieCard;
    private TextView discoverMessage;
    private TextView movieTitle;
    private ImageView moviePoster;
    private RatingBar movieRatingBar;
    private TextView movieRating;
    private TextView movieReleaseDate;
    private TextView movieGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = DiscoverPresenter.getInstance(this);
        setContentView(R.layout.activity_discover);

        movieCard = findViewById(R.id.movie_card);
        discoverMessage = findViewById(R.id.discoverMessage);
        movieTitle = findViewById(R.id.movie_card_title);
        moviePoster = findViewById(R.id.movie_card_poster);
        movieRatingBar = findViewById(R.id.movie_card_stars);
        movieRating = findViewById(R.id.move_card_rating);
        movieReleaseDate = findViewById(R.id.movie_card_release_date_value);
        movieGenres = findViewById(R.id.movie_card_genre_value);
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
        movieCard.setVisibility(View.GONE);
    }

    @Override
    public void showContent(Movie model) {
        movieTitle.setText(model.getTitle());
        movieRatingBar.setRating(model.getRating());
        movieRating.setText(getString(R.string.movie_rating_format, model.getRating()));
        movieReleaseDate.setText(DateUtils.formatDate(model.getReleaseDate()));
        movieGenres.setText(model.getGenre());
        Picasso.get()
            .load(model.getPosterPath())
            .into(moviePoster);

        discoverMessage.setVisibility(View.GONE);
        movieCard.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(CharSequence message) {
        discoverMessage.setText(message);

        discoverMessage.setVisibility(View.VISIBLE);
        movieCard.setVisibility(View.GONE);
    }
}
