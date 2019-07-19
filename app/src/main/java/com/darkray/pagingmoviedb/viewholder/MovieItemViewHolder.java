package com.darkray.pagingmoviedb.viewholder;

import androidx.recyclerview.widget.RecyclerView;

import com.darkray.pagingmoviedb.databinding.RowMovieBinding;

public class MovieItemViewHolder extends RecyclerView.ViewHolder {

    public RowMovieBinding binding;

    public MovieItemViewHolder(RowMovieBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
