package com.muradismayilov.easy_english.recyclerview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.muradismayilov.easy_english.R;

class MyViewHolder extends RecyclerView.ViewHolder {

    final MaterialCardView recycler_item_itemCV;
    final MaterialTextView recycler_item_wordTV;

    MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recycler_item_itemCV = itemView.findViewById(R.id.recycler_item_itemCV);
        recycler_item_wordTV = itemView.findViewById(R.id.recycler_item_wordTV);
    }
}
