package com.fabernovel.codingdojo.app.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.fabernovel.codingdojo.R;
import com.fabernovel.codingdojo.entity.Movie;
import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private final List<Movie> dataSet = new ArrayList<>();

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    void submitList(List<Movie> models) {
        dataSet.clear();
        dataSet.addAll(models);
        notifyDataSetChanged();
    }
}
