package com.muradismayilov.easy_english.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.muradismayilov.easy_english.R;
import com.muradismayilov.easy_english.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private final Context mContext;
    private final List<User> mList;
    private final FirebaseAuth mAuth;

    public UserAdapter(Context context, List<User> mList) {
        this.mContext = context;
        this.mList = mList;
        mAuth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.recycler_item_user, viewGroup, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, final int i) {
        userViewHolder.recycler_item_user_itemCV.setStrokeColor(mContext.getResources().getColor(R.color.colorTwo));
        userViewHolder.recycler_item_user_itemCV.setStrokeWidth(0);

        if (mAuth.getCurrentUser() != null) {
            if (mList.get(i).getId().equals(mAuth.getCurrentUser().getUid())) {
                userViewHolder.recycler_item_user_itemCV.setStrokeColor(mContext.getResources().getColor(R.color.colorTwo));
                userViewHolder.recycler_item_user_itemCV.setStrokeWidth(5);
            }
        }

        userViewHolder.recycler_item_user_usernameTV.setText(mList.get(i).getUsername());
        userViewHolder.recycler_item_user_scoreTV.setText(mList.get(i).getScore());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}