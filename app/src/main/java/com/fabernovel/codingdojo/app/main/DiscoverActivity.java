package com.fabernovel.codingdojo.app.main;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.fabernovel.codingdojo.R;

// TODO: implement DiscoverViewContract
public class DiscoverActivity extends AppCompatActivity {

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
        // TODO: start the presenter
    }

    @Override
    protected void onStop() {
        // TODO: stop the presenter
        super.onStop();
    }
}
