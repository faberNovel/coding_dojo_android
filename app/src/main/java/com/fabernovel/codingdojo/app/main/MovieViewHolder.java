package com.fabernovel.codingdojo.app.main;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fabernovel.codingdojo.R;
import com.fabernovel.codingdojo.entity.Movie;
import com.fabernovel.codingdojo.utils.DateUtils;
import com.squareup.picasso.Picasso;

class MovieViewHolder extends RecyclerView.ViewHolder {

    private final Context context;

    private TextView movieTitle;
    private TextView movieRating;
    private RatingBar movieRatingBar;
    private TextView movieReleaseDateValue;
    private TextView movieGenreValue;
    private ImageView moviePoster;

    MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();

        movieTitle = itemView.findViewById(R.id.movie_card_title);
        movieReleaseDateValue = itemView.findViewById(R.id.movie_card_release_date_value);
        movieGenreValue = itemView.findViewById(R.id.movie_card_genre_value);
        moviePoster = itemView.findViewById(R.id.movie_card_poster);
        movieRatingBar = itemView.findViewById(R.id.movie_card_stars);
        movieRating = itemView.findViewById(R.id.move_card_rating);
    }

    void bind(Movie model) {
        populateMovieCard(model);
    }

    private void populateMovieCard(Movie model) {
        movieTitle.setText(model.getTitle());
        movieRatingBar.setRating(model.getRating());
        movieRating.setText(context.getString(R.string.movie_rating_format, model.getRating()));
        movieReleaseDateValue.setText(DateUtils.formatDate(model.getReleaseDate()));
        movieGenreValue.setText(model.getGenre());
        Picasso.get().load(model.getPosterPath()).into(moviePoster);
    }
}
