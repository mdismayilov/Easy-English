package com.muradismayilov.easy_english.activities;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.textview.MaterialTextView;
import com.muradismayilov.easy_english.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreWordActivity extends AppCompatActivity {

    // UI Components
    // TextView
    @BindView(R.id.more_word_wordTV)
    MaterialTextView more_word_wordTV;
    @BindView(R.id.more_word_meaningTV)
    MaterialTextView more_word_meaningTV;
    @BindView(R.id.more_word_transcriptionTV)
    MaterialTextView more_word_transcriptionTV;
    @BindView(R.id.more_word_definitionTV)
    MaterialTextView more_word_definitionTV;
    @BindView(R.id.more_word_exampleTV)
    MaterialTextView more_word_exampleTV;
    @BindView(R.id.more_word_synonymTV)
    MaterialTextView more_word_synonymTV;
    @BindView(R.id.more_word_antonymTV)
    MaterialTextView more_word_antonymTV;
    // FrameLayout
    @BindView(R.id.more_word_bannerAdPlaceholderFL)
    FrameLayout more_word_bannerAdPlaceholderFL;

    // String
    @BindString(R.string.went_wrong)
    String went_wrong;
    @BindString(R.string.easyenglish_more_word_banner)
    String easyenglish_more_word_banner;

    // Variables
    // String
    private String word;
    private String word_meaning;
    private String word_transcription;
    private String word_definition;
    private String word_example;
    private String word_synonym;
    private String word_antonym;
    // AdView
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_more_word);
        ButterKnife.bind(this);
        initialFunctions();
    }

    private void initialFunctions() {
        declareVariables();
        setText();
        boolean isPremium = HomeActivity.isPremium;
        if (!isPremium) {
            setAd();
        }
    }

    private void declareVariables() {
        try {
            word = getIntent().getStringExtra("word") + "";
            word_meaning = getIntent().getStringExtra("word_meaning") + "";
            word_transcription = getIntent().getStringExtra("word_transcription") + "";
            word_definition = getIntent().getStringExtra("word_definition") + "";
            word_example = getIntent().getStringExtra("word_example") + "";
            word_synonym = getIntent().getStringExtra("word_synonym") + "";
            word_antonym = getIntent().getStringExtra("word_antonym") + "";
        } catch (Exception e) {
            Toast.makeText(this, went_wrong, Toast.LENGTH_SHORT).show();
        }

        adView = new AdView(this);
    }

    private void setText() {
        try {
            more_word_wordTV.setText(word);
            more_word_meaningTV.setText(word_meaning);
            more_word_transcriptionTV.setText(word_transcription);
            more_word_definitionTV.setText(word_definition);
            more_word_exampleTV.setText(word_example);
            more_word_synonymTV.setText(word_synonym);
            more_word_antonymTV.setText(word_antonym);
        } catch (Exception e) {
            Toast.makeText(this, went_wrong, Toast.LENGTH_SHORT).show();
        }
    }

    private void setAd() {
        MobileAds.initialize(this);
        adView.setAdUnitId(easyenglish_more_word_banner);
        more_word_bannerAdPlaceholderFL.addView(adView);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
