package com.muradismayilov.easy_english.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.muradismayilov.easy_english.R;
import com.muradismayilov.easy_english.model.Word;
import com.muradismayilov.easy_english.recyclerview.MyAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WordActivity extends AppCompatActivity {

    // UI Components
    // RecyclerView
    @BindView(R.id.word_wordRV)
    RecyclerView word_wordRV;
    // FrameLayout
    @BindView(R.id.word_bannerAdPlaceholderFL)
    FrameLayout word_bannerAdPlaceholderFL;

    // String
    @BindString(R.string.easyenglish_words_banner)
    String easyenglish_words_banner;

    // Variables
    private List<Word> mList;
    // AdView
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_word);
        ButterKnife.bind(this);
        initialFunctions();
    }

    private void initialFunctions() {
        declareVariables();
        fillTheList();
        setRecyclerView();
        boolean isPremium = HomeActivity.isPremium;
        if (!isPremium) {
            setAd();
        }
    }

    private void declareVariables() {
        mList = new ArrayList<>();
        adView = new AdView(this);
    }

    private void fillTheList() {
        List<String> wordList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.word)));
        List<String> meaningList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.word_meaning)));
        List<String> transcriptionList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.word_transcription)));

        for (int i = 0; i < wordList.size(); i++) {
            mList.add(new Word(wordList.get(i), meaningList.get(i), transcriptionList.get(i)));
        }
    }

    private void setRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter adapter = new MyAdapter(this, mList, getLayoutInflater());

        word_wordRV.setLayoutManager(layoutManager);
        word_wordRV.setAdapter(adapter);
    }

    private void setAd() {
        MobileAds.initialize(this);
        adView.setAdUnitId(easyenglish_words_banner);
        word_bannerAdPlaceholderFL.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();

        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

    @OnClick(R.id.word_startBTN)
    public void word_startBTNClicked() {
        Intent intent = new Intent(WordActivity.this, LearnWordActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
