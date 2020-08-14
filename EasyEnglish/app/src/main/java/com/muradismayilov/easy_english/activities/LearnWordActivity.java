package com.muradismayilov.easy_english.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.button.MaterialButton;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.muradismayilov.easy_english.R;
import com.muradismayilov.easy_english.database.DatabaseHelper;
import com.muradismayilov.easy_english.model.Word;
import com.muradismayilov.easy_english.tools.MyArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LearnWordActivity extends AppCompatActivity {

    // UI Components
    // SwipeFlingAdapterView
    @BindView(R.id.learn_word_swipeFLingAdapterView)
    SwipeFlingAdapterView learn_word_swipeFLingAdapterView;
    // TextView
    @BindView(R.id.learn_word_wordMeaningTV)
    TextView learn_word_wordMeaningTV;
    // FrameLayout
    @BindView(R.id.learn_word_bannerAdPlaceholderFL)
    FrameLayout learn_word_bannerAdPlaceholderFL;

    // String
    @BindString(R.string.went_wrong)
    String went_wrong;
    @BindString(R.string.copied)
    String copied;
    @BindString(R.string.share)
    String share;
    @BindString(R.string.download_the_app)
    String download_the_app;
    @BindString(R.string.app_link)
    String app_link;
    @BindString(R.string.app_full_name)
    String app_full_name;
    @BindString(R.string.no_more_detail)
    String no_more_detail;
    @BindString(R.string.easyenglish_learn_word_banner)
    String easyenglish_learn_word_banner;
    @BindString(R.string.easyenglish_learn_word_interstitial)
    String easyenglish_learn_word_interstitial;
    @BindString(R.string.we_need_ads)
    String we_need_ads;

    // Variables
    // ArrayAdapter
    private MyArrayAdapter arrayAdapter;
    // List
    private List<Word> mList;
    // Integer
    private int index, swipeIndex;
    // Final
    private static final String SHARED_PREFERENCES = "word_index";
    // TextToSpeech
    private TextToSpeech textToSpeech;
    // String
    private String voiceWord, definition, example, synonym, antonym, wordForMore, word_meaningForMore, word_transcriptionForMore;
    // AdView
    private AdView adView;
    // InterstitialAd
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_learn_word);
        ButterKnife.bind(this);
        initialFunctions();
    }

    private void initialFunctions() {
        declareVariables();
        getIndex();
        fillTheList();
        setTheCard();
        learn_word_wordMeaningTV.setText(mList.get(0).getMeaning());
        voiceWord = mList.get(0).getWord();
        wordForMore = mList.get(0).getWord();
        word_meaningForMore = mList.get(0).getMeaning();
        word_transcriptionForMore = mList.get(0).getTranscription();
        boolean isPremium = HomeActivity.isPremium;
        if (!isPremium) {
            setAd();
        }
    }

    private void declareVariables() {
        // List
        mList = new ArrayList<>();
        // String
        voiceWord = "";
        wordForMore = "";
        word_meaningForMore = "";
        word_transcriptionForMore = "";
        definition = "";
        example = "";
        synonym = "";
        antonym = "";
        // AdView
        adView = new AdView(this);
    }

    private void getIndex() {
        SharedPreferences sharedPreferences =
                getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        index = sharedPreferences.getInt("word_index", 0);
    }

    private void fillTheList() {
        mList.clear();

        List<String> wordList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.word)));
        List<String> wordMeaningList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.word_meaning)));
        List<String> wordTranscriptionList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.word_transcription)));

        for (int i = 0; i < wordList.size(); i++) {
            mList.add(new Word(wordList.get(i), wordMeaningList.get(i), wordTranscriptionList.get(i)));
        }
    }

    private void setTheCard() {
        mList.subList(0, index).clear();

        arrayAdapter = new MyArrayAdapter(this, mList);

        learn_word_swipeFLingAdapterView.setAdapter(arrayAdapter);

        learn_word_swipeFLingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                swipeIndex++;
                if (swipeIndex == 16) {
                    if (interstitialAd != null) {
                        if (interstitialAd.isLoaded()) {
                            interstitialAd.show();
                        }
                    }
                    swipeIndex = 0;
                }

                index++;

                mList.remove(0);
                arrayAdapter.notifyDataSetChanged();

                learn_word_wordMeaningTV.setText(mList.get(0).getMeaning());
                voiceWord = mList.get(0).getWord();
                wordForMore = mList.get(0).getWord();
                word_meaningForMore = mList.get(0).getMeaning();
                word_transcriptionForMore = mList.get(0).getTranscription();

                if (mList.size() < 3) {
                    showFinishDialog();
                }
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
            }

            @Override
            public void onRightCardExit(Object dataObject) {
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float v) {
            }
        });
    }

    private void setAd() {
        // BannerAd
        MobileAds.initialize(this);
        adView.setAdUnitId(easyenglish_learn_word_banner);
        learn_word_bannerAdPlaceholderFL.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();

        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);

        // InterstitialAd
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(easyenglish_learn_word_interstitial);
        AdRequest interstitialAdRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(interstitialAdRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();

                final Handler handler = new Handler();
                handler.postDelayed(() -> Toast.makeText(LearnWordActivity.this, we_need_ads, Toast.LENGTH_SHORT).show(), 300);

                interstitialAd.loadAd(interstitialAdRequest);
            }
        });
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

    @Override
    protected void onStop() {
        saveIndex();
        super.onStop();
    }

    private void saveIndex() {
        SharedPreferences.Editor editor =
                getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE).edit();
        editor.putInt("word_index", index);
        editor.apply();
    }

    @OnClick(R.id.learn_word_soundIV)
    public void learn_word_soundIVClicked() {
        textToSpeech = new TextToSpeech(LearnWordActivity.this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.getDefault());
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, went_wrong, Toast.LENGTH_SHORT).show();
                } else {
                    textToSpeech.speak(voiceWord, TextToSpeech.QUEUE_FLUSH, null);
                }
            } else
                Toast.makeText(this, went_wrong, Toast.LENGTH_SHORT).show();
        });
    }

    @OnClick(R.id.learn_word_moreIV)
    public void learn_word_moreIVClicked() {
        final AlertDialog dialog_more = new AlertDialog.Builder(this).create();
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_more, null);

        LinearLayout dialog_more_restartLL = view.findViewById(R.id.dialog_more_restartLL);
        LinearLayout dialog_more_copyLL = view.findViewById(R.id.dialog_more_copyLL);
        LinearLayout dialog_more_shareLL = view.findViewById(R.id.dialog_more_shareLL);
        LinearLayout dialog_more_suggestionLL = view.findViewById(R.id.dialog_more_suggestionLL);

        dialog_more_restartLL.setOnClickListener(v -> {
            dialog_more.dismiss();
            restart();
        });

        dialog_more_copyLL.setOnClickListener(v -> {
            dialog_more.dismiss();
            copy();
        });

        dialog_more_shareLL.setOnClickListener(v -> {
            dialog_more.dismiss();
            share();
        });

        dialog_more_suggestionLL.setOnClickListener(v -> {
            dialog_more.dismiss();
            suggestion();
        });

        dialog_more.setView(view);
        dialog_more.setCanceledOnTouchOutside(true);
        dialog_more.show();
    }

    private void restart() {
        if (index != 0) {
            index = 0;
            saveIndex();
            Intent intent = getIntent();
            LearnWordActivity.this.finish();
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    private void copy() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("text", voiceWord);
        assert clipboard != null;
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, copied, Toast.LENGTH_SHORT).show();
    }

    private void share() {
        String word = voiceWord;
        String wordMeaning = learn_word_wordMeaningTV.getText().toString();
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = word + ": " + wordMeaning + "\n\n" + download_the_app + "\n" + app_link;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, app_full_name);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, share));
    }

    private void suggestion() {
        Intent intent = new Intent(LearnWordActivity.this, SuggestionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @OnClick(R.id.learn_word_previousIV)
    public void learn_word_previousIVClicked() {
        if (index != 0) {
            index -= 1;
            saveIndex();
            Intent intent = getIntent();
            LearnWordActivity.this.finish();
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    private void showFinishDialog() {
        final AlertDialog dialog_finish = new AlertDialog.Builder(LearnWordActivity.this).create();

        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_finish, null);

        MaterialButton dialog_finish_startAgainBTN = view.findViewById(R.id.dialog_finish_startAgainBTN);
        MaterialButton dialog_finish_homeBTN = view.findViewById(R.id.dialog_finish_homeBTN);

        dialog_finish_startAgainBTN.setOnClickListener(v -> restart());

        dialog_finish_homeBTN.setOnClickListener(v -> {
            index = 0;
            saveIndex();
            Intent intent = new Intent(LearnWordActivity.this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            LearnWordActivity.this.finish();
        });

        dialog_finish.setView(view);
        dialog_finish.setCanceledOnTouchOutside(false);
        dialog_finish.show();
    }

    @OnClick(R.id.learn_word_moreWordTV)
    public void learn_word_moreWordTVClicked() {
        getFromDB();
    }

    private void getFromDB() {
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(LearnWordActivity.this);
            databaseHelper.openDataBase();

            Cursor cursor = databaseHelper.getMeaning(wordForMore);
            if (cursor.moveToFirst()) {

                definition = cursor.getString(cursor.getColumnIndex("en_definition"));
                example = cursor.getString(cursor.getColumnIndex("example"));
                synonym = cursor.getString(cursor.getColumnIndex("synonyms"));
                antonym = cursor.getString(cursor.getColumnIndex("antonyms"));
            }

            Intent intent = new Intent(LearnWordActivity.this, MoreWordActivity.class);
            intent.putExtra("word", wordForMore);
            intent.putExtra("word_meaning", word_meaningForMore);
            intent.putExtra("word_transcription", word_transcriptionForMore);
            intent.putExtra("word_definition", definition);
            intent.putExtra("word_example", example);
            intent.putExtra("word_synonym", synonym);
            intent.putExtra("word_antonym", antonym);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        } catch (Exception e) {
            Toast.makeText(this, no_more_detail, Toast.LENGTH_SHORT).show();
        }
    }
}
