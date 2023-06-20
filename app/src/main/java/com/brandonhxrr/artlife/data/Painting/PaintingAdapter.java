package com.brandonhxrr.artlife.data.Painting;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.data.Blog.Blog;
import com.brandonhxrr.artlife.ui.PaintingDetail;
import com.bumptech.glide.Glide;

import java.util.List;

public class PaintingAdapter extends RecyclerView.Adapter<PaintingViewHolder> {

    List<Painting> paintings;

    public PaintingAdapter(List<Painting> paintings) {
        this.paintings = paintings;
    }

    @NonNull
    @Override
    public PaintingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.painting, parent, false);
        return new PaintingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PaintingViewHolder holder, int position) {
        Glide.with(holder.itemView).load(paintings.get(position).imageUri).into(holder.getPaintingImage());
        holder.getPaintingTitle().setText(paintings.get(position).name);
        holder.getPaintingAuthor().setText(paintings.get(position).author);
        holder.getCard().setOnClickListener(v -> {
            Intent startDetails = new Intent(holder.itemView.getContext(), PaintingDetail.class);
            startDetails.putExtra("Data", paintings.get(position));
            holder.itemView.getContext().startActivity(startDetails);
        });
    }

    @Override
    public int getItemCount() {
        return paintings.size();
    }
}
