package com.muradismayilov.easy_english.recyclerview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.muradismayilov.easy_english.R;

class UserViewHolder extends RecyclerView.ViewHolder {

    final MaterialTextView recycler_item_user_usernameTV;
    final MaterialTextView recycler_item_user_scoreTV;
    final MaterialCardView recycler_item_user_itemCV;

    UserViewHolder(@NonNull View itemView) {
        super(itemView);

        recycler_item_user_usernameTV = itemView.findViewById(R.id.recycler_item_user_usernameTV);
        recycler_item_user_scoreTV = itemView.findViewById(R.id.recycler_item_user_scoreTV);
        recycler_item_user_itemCV = itemView.findViewById(R.id.recycler_item_user_itemCV);
    }
}
