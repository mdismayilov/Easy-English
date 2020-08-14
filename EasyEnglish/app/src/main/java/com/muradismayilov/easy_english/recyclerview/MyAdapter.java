package com.muradismayilov.easy_english.recyclerview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.muradismayilov.easy_english.R;
import com.muradismayilov.easy_english.model.Word;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private final Context mContext;
    private final List<Word> mList;
    private final LayoutInflater layoutInflater;

    public MyAdapter(Context context, List<Word> mList, LayoutInflater layoutInflater) {
        this.mContext = context;
        this.mList = mList;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.recycler_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.recycler_item_wordTV.setText(mList.get(i).getWord());
        myViewHolder.recycler_item_itemCV.setOnClickListener(v -> showDialog(i));
    }

    private void showDialog(int i) {
        final AlertDialog dialog_toast = new AlertDialog.Builder(mContext).create();
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.dialog_toast, null);

        MaterialTextView dialog_toast_wordTV = view.findViewById(R.id.dialog_toast_wordTV);
        MaterialTextView dialog_toast_meaningTV = view.findViewById(R.id.dialog_toast_meaningTV);
        MaterialTextView dialog_toast_transcriptionTV = view.findViewById(R.id.dialog_toast_transcriptionTV);
        MaterialButton dialog_toast_closeBTN = view.findViewById(R.id.dialog_toast_closeBTN);

        dialog_toast_wordTV.setText(mList.get(i).getWord());
        dialog_toast_meaningTV.setText(mList.get(i).getMeaning());
        dialog_toast_transcriptionTV.setText(mList.get(i).getTranscription());
        dialog_toast_closeBTN.setOnClickListener(v -> dialog_toast.dismiss());

        dialog_toast.setView(view);
        dialog_toast.setCanceledOnTouchOutside(false);
        dialog_toast.show();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

