package com.muradismayilov.easy_english.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muradismayilov.easy_english.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestWordActivity extends AppCompatActivity {

    // UI Components
    // MaterialTextView
    @BindView(R.id.test_word_scoreTV)
    MaterialTextView test_word_scoreTV;
    @BindView(R.id.test_word_questionTV)
    MaterialTextView test_word_questionTV;
    @BindView(R.id.test_word_firstAnswerTV)
    MaterialTextView test_word_firstAnswerTV;
    @BindView(R.id.test_word_secondAnswerTV)
    MaterialTextView test_word_secondAnswerTV;
    @BindView(R.id.test_word_questionTranscriptionTV)
    MaterialTextView test_word_questionTranscriptionTV;
    // CardView
    @BindView(R.id.test_word_firstAnswerCV)
    MaterialCardView test_word_firstAnswerCV;
    @BindView(R.id.test_word_secondAnswerCV)
    MaterialCardView test_word_secondAnswerCV;
    // FrameLayout
    @BindView(R.id.test_word_bannerAdPlaceholderFL)
    FrameLayout test_word_bannerAdPlaceholderFL;
    // ImageView
    @BindView(R.id.test_word_awardIV)
    ImageView test_word_awardIV;

    // String
    @BindString(R.string.right_answer)
    String right_answer;
    @BindString(R.string.wrong_answer)
    String wrong_answer;
    @BindString(R.string.continue_test)
    String continue_test;
    @BindString(R.string.your_score)
    String your_score;
    @BindString(R.string.no_internet_connection)
    String no_internet_connection;
    @BindString(R.string.didnt_save_score)
    String didnt_save_score;
    @BindString(R.string.easyenglish_test_banner)
    String easyenglish_test_banner;
    @BindString(R.string.easyenglish_test_interstitial)
    String easyenglish_test_interstitial;
    @BindString(R.string.thank_you_for_watching_ad)
    String thank_you_for_watching_ad;

    // Variables
    // String
    private String mUid, username, score;
    // List
    private List<String> questionList, rightAnswerList, wrongAnswerList, transcriptionList, wordList, wordMeaningList, wordTranscriptionsList;
    private List<Integer> randomNumberListForRightAnswer, randomNumberListForWrongAnswer;
    private List<MaterialTextView> textViewList;
    // Integer
    private int index, userScore, numberOfRightAnswer;
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
        setContentView(R.layout.activity_test_word);
        ButterKnife.bind(this);
        initialFunctions();
    }

    private void initialFunctions() {
        declareVariables();
        getUserInfoFromDB();
        fillTheList();
        setTenRandomQuestion();
        setTenRightAnswers();
        setTenWrongAnswers();
        setTest();
        setAwardImage();
        boolean isPremium = HomeActivity.isPremium;
        if (!isPremium) {
            setAd();
        }
        setInterstitialAd();
    }

    private void setAwardImage() {
        Glide.with(this)
                .load(R.drawable.award)
                .priority(Priority.IMMEDIATE)
                .into(test_word_awardIV);
    }

    private void declareVariables() {
        // Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            mUid = mUser.getUid();
        }
        // String
        username = "";
        score = "";
        // List
        questionList = new ArrayList<>();
        randomNumberListForRightAnswer = new ArrayList<>();
        randomNumberListForWrongAnswer = new ArrayList<>();
        rightAnswerList = new ArrayList<>();
        wrongAnswerList = new ArrayList<>();
        transcriptionList = new ArrayList<>();
        textViewList = new ArrayList<>();
        // Integer
        index = 0;
        userScore = 0;
        numberOfRightAnswer = 0;
        // AdView
        adView = new AdView(this);
    }

    private void setAd() {
        // BannerAd
        MobileAds.initialize(this);
        adView.setAdUnitId(easyenglish_test_banner);
        test_word_bannerAdPlaceholderFL.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();

        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);
    }

    private void setInterstitialAd(){
        // InterstitialAd
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(easyenglish_test_interstitial);
        AdRequest interstitialAdRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(interstitialAdRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();

                final Handler handler = new Handler();
                handler.postDelayed(() -> Toast.makeText(TestWordActivity.this, thank_you_for_watching_ad, Toast.LENGTH_SHORT).show(), 300);

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

    private void getUserInfoFromDB() {
        if (mUid != null) {
            DatabaseReference mDatabaseReference = FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("Users")
                    .child(mUid);

            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    try {
                        username = dataSnapshot.child("username").getValue().toString();
                        score = dataSnapshot.child("score").getValue().toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    setScoreText();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void setScoreText() {
        String scoreText = username + ": " + score;
        test_word_scoreTV.setText(scoreText);
    }

    private void fillTheList() {
        wordList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.word)));
        wordMeaningList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.word_meaning)));
        wordTranscriptionsList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.word_transcription)));

        textViewList.add(test_word_firstAnswerTV);
        textViewList.add(test_word_secondAnswerTV);
    }

    private void setTenRandomQuestion() {
        questionList.clear();
        transcriptionList.clear();
        randomNumberListForRightAnswer.clear();

        Random random = new Random();

        while (randomNumberListForRightAnswer.size() != 10) {
            int randomNumber = random.nextInt(wordList.size() - 2);

            if (!randomNumberListForRightAnswer.contains(randomNumber)) {
                randomNumberListForRightAnswer.add(randomNumber);
            }
        }

        for (int i = 0; i < 10; i++) {
            questionList.add(wordList.get(randomNumberListForRightAnswer.get(i)));
        }

        for (int i = 0; i < 10; i++) {
            transcriptionList.add(wordTranscriptionsList.get(randomNumberListForRightAnswer.get(i)));
        }

    }

    private void setTenRightAnswers() {
        rightAnswerList.clear();

        for (int i = 0; i < 10; i++) {
            rightAnswerList.add(wordMeaningList.get(randomNumberListForRightAnswer.get(i)));
        }
    }

    private void setTenWrongAnswers() {
        wrongAnswerList.clear();
        randomNumberListForWrongAnswer.clear();

        Random random = new Random();

        while (randomNumberListForWrongAnswer.size() != 10) {
            int randomNumber = random.nextInt(wordList.size() - 2);

            if (!randomNumberListForRightAnswer.contains(randomNumber)) {
                randomNumberListForWrongAnswer.add(randomNumber);
            }
        }

        for (int i = 0; i < 10; i++) {
            wrongAnswerList.add(wordMeaningList.get(randomNumberListForWrongAnswer.get(i)));
        }
    }

    private void setTest() {
        test_word_firstAnswerCV.setClickable(true);
        test_word_secondAnswerCV.setClickable(true);

        test_word_questionTV.setText(questionList.get(index));
        test_word_questionTranscriptionTV.setText(transcriptionList.get(index));

        Random random = new Random();
        int randomPosition = random.nextInt(2);

        textViewList.get(randomPosition).setText(rightAnswerList.get(index));
        if (randomPosition == 0) {
            textViewList.get(1).setText(wrongAnswerList.get(index));
        } else {
            textViewList.get(0).setText(wrongAnswerList.get(index));
        }
    }

    @OnClick(R.id.test_word_firstAnswerCV)
    public void test_firstAnswerCVClicked() {
        if (test_word_firstAnswerTV.getText().toString().equals(rightAnswerList.get(index))) {
            test_word_firstAnswerCV.setClickable(true);
            test_word_secondAnswerCV.setClickable(true);

            numberOfRightAnswer += 1;
            userScore += 5;
            showRightAnswerDialog();
        } else {
            test_word_firstAnswerCV.setClickable(true);
            test_word_secondAnswerCV.setClickable(true);

            if (userScore >= 10) {
                userScore -= 10;
            }

            showWrongAnswerDialog();
        }
    }

    @OnClick(R.id.test_word_secondAnswerCV)
    public void test_secondAnswerCVClicked() {
        if (test_word_secondAnswerTV.getText().toString().equals(rightAnswerList.get(index))) {
            test_word_firstAnswerCV.setClickable(true);
            test_word_secondAnswerCV.setClickable(true);

            numberOfRightAnswer += 1;
            userScore += 5;
            showRightAnswerDialog();
        } else {
            test_word_firstAnswerCV.setClickable(true);
            test_word_secondAnswerCV.setClickable(true);

            if (userScore >= 10) {
                userScore -= 10;
            }

            showWrongAnswerDialog();
        }
    }

    private void showRightAnswerDialog() {
        final AlertDialog dialog_right_answer = new AlertDialog.Builder(this).create();
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_right_answer, null);

        MaterialButton dialog_right_answer_continueBTN = view.findViewById(R.id.dialog_right_answer_continueBTN);

        dialog_right_answer_continueBTN.setOnClickListener(v -> {
            dialog_right_answer.dismiss();

            if (index == 9) {
                showResultDialog();
            } else {
                index++;
                setTest();
            }
        });

        dialog_right_answer.setView(view);
        dialog_right_answer.setCanceledOnTouchOutside(false);
        dialog_right_answer.show();
    }

    private void showWrongAnswerDialog() {
        final AlertDialog dialog_wrong_answer = new AlertDialog.Builder(this).create();
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_wrong_answer, null);

        MaterialButton dialog_wrong_answer_continueBTN = view.findViewById(R.id.dialog_wrong_answer_continueBTN);

        dialog_wrong_answer_continueBTN.setOnClickListener(v -> {
            dialog_wrong_answer.dismiss();

            if (index == 9) {
                showResultDialog();
            } else {
                index++;
                setTest();
            }
        });

        dialog_wrong_answer.setView(view);
        dialog_wrong_answer.setCanceledOnTouchOutside(false);
        dialog_wrong_answer.show();
    }

    private void showResultDialog() {
        final AlertDialog dialog_result = new AlertDialog.Builder(this).create();
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_result, null);

        MaterialTextView dialog_result_numberOfRightAnswersTV = view.findViewById(R.id.dialog_result_numberOfRightAnswersTV);
        MaterialTextView dialog_result_scoreTV = view.findViewById(R.id.dialog_result_scoreTV);
        MaterialButton dialog_result_homeBTN = view.findViewById(R.id.dialog_result_homeBTN);
        MaterialButton dialog_result_testAgainBTN = view.findViewById(R.id.dialog_result_testAgainBTN);
        MaterialButton dialog_result_showAdBTN = view.findViewById(R.id.dialog_result_showAdBTN);

        String text1 = "10/" + numberOfRightAnswer;
        dialog_result_numberOfRightAnswersTV.setText(text1);

        String text2 = userScore + " " + your_score;
        dialog_result_scoreTV.setText(text2);

        dialog_result_homeBTN.setOnClickListener(v -> {
            dialog_result.dismiss();
            saveUserScore();
            onBackPressed();
        });

        dialog_result_testAgainBTN.setOnClickListener(v -> {
            dialog_result.dismiss();
            saveUserScore();
            Intent intent = getIntent();
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            TestWordActivity.this.finish();
        });

        dialog_result_showAdBTN.setOnClickListener(v -> {
            if (interstitialAd != null) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
            }
        });

        dialog_result.setView(view);
        dialog_result.setCanceledOnTouchOutside(false);
        dialog_result.show();
    }

    @SuppressWarnings("ConstantConditions")
    private void saveUserScore() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            saveIt();

        } else {
            Toast.makeText(this, didnt_save_score, Toast.LENGTH_SHORT).show();
        }
    }

    private void saveIt() {
        int totalScore = Integer.parseInt(score) + userScore;

        DatabaseReference mDatabaseReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Users")
                .child(mUid);

        mDatabaseReference.child("score").setValue(totalScore);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @OnClick(R.id.test_word_awardIV)
    public void test_word_awardIVClicked() {
        Intent intent = new Intent(TestWordActivity.this, UserActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
