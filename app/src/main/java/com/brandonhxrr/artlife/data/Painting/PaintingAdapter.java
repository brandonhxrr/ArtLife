package com.brandonhxrr.artlife.data.Painting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.brandonhxrr.artlife.R;
import com.bumptech.glide.Glide;

public class PaintingAdapter extends RecyclerView.Adapter<PaintingViewHolder> {

    private final Painting[] paintings;

    public PaintingAdapter(Painting[] paintings) {
        this.paintings = paintings;
    }

    @NonNull
    @Override
    public PaintingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blog, parent, false);
        return new PaintingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PaintingViewHolder holder, int position) {
        Glide.with(holder.itemView).load(paintings[position].imageUri).into(holder.getPaintingImage());
        holder.getPaintingTitle().setText(paintings[position].name);
        holder.getPaintingAuthor().setText(paintings[position].author);
    }

    @Override
    public int getItemCount() {
        return paintings.length;
    }
}
