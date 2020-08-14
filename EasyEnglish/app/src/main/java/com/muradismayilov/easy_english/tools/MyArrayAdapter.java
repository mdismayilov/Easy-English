package com.muradismayilov.easy_english.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textview.MaterialTextView;
import com.muradismayilov.easy_english.R;
import com.muradismayilov.easy_english.model.Word;

import java.util.List;


public class MyArrayAdapter extends ArrayAdapter<Word> {

    private final Context mContext;
    private final List<Word> mList;

    public MyArrayAdapter(@NonNull Context context, List<Word> list) {
        super(context, 0, list);
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.layout_card, parent, false);

        MaterialTextView layout_card_wordTV = listItem.findViewById(R.id.layout_card_wordTV);
        MaterialTextView layout_card_transcriptionTV = listItem.findViewById(R.id.layout_card_transcriptionTV);

        layout_card_wordTV.setText(mList.get(position).getWord());
        layout_card_transcriptionTV.setText(mList.get(position).getTranscription());

        return listItem;
    }
}
