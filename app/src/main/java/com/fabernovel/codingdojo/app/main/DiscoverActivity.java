package com.fabernovel.codingdojo.app.main;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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
        // TODO: set content view

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
