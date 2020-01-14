package com.fabernovel.codingdojo.app.main;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.fabernovel.codingdojo.R;

// TODO: implement DiscoverViewContract
public class DiscoverActivity extends AppCompatActivity{

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
        // TODO: initialize views
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
