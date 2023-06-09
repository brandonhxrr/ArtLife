package com.brandonhxrr.artlife.data.Painting;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.brandonhxrr.artlife.R;
import com.google.android.material.textview.MaterialTextView;

public class PaintingViewHolder extends RecyclerView.ViewHolder {

    private final MaterialTextView paintingTitle;
    private final MaterialTextView paintingAuthor;
    private final ImageView paintingImage;
    private final CardView card;

    public PaintingViewHolder(@NonNull View itemView) {
        super(itemView);

        paintingTitle = itemView.findViewById(R.id.painting_title);
        paintingAuthor = itemView.findViewById(R.id.painting_author);
        paintingImage = itemView.findViewById(R.id.painting_image);
        card = itemView.findViewById(R.id.card);
    }

    public ImageView getPaintingImage() {
        return paintingImage;
    }

    public MaterialTextView getPaintingTitle() {
        return paintingTitle;
    }

    public MaterialTextView getPaintingAuthor() {
        return paintingAuthor;
    }

    public CardView getCard() {
        return card;
    }
}
