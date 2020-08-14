package com.muradismayilov.easy_english.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muradismayilov.easy_english.R;
import com.muradismayilov.easy_english.receivers.InternetReceiver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements PurchasesUpdatedListener {

    // UI Components
    // ImageView
    @BindView(R.id.home_wordsIV)
    ImageView home_wordsIV;
    @BindView(R.id.home_phrasesIV)
    ImageView home_phrasesIV;
    @BindView(R.id.home_testIV)
    ImageView home_testIV;
    @BindView(R.id.home_supportIV)
    ImageView home_supportIV;
    // FrameLayout
    @BindView(R.id.home_bannerAdPlaceholderFL)
    FrameLayout home_bannerAdPlaceholderFL;
    // MaterialCardView
    @BindView(R.id.home_goPremiumCV)
    MaterialCardView home_goPremiumCV;

    // Strings
    @BindString(R.string.press_back_again)
    String press_back_again;
    @BindString(R.string.app_link)
    String app_link;
    @BindString(R.string.thank_you_for_watching_ad)
    String thank_you_for_watching_ad;
    @BindString(R.string.easyenglish_home_banner)
    String easyenglish_home_banner;
    @BindString(R.string.easyenglish_home_interstitial)
    String easyenglish_home_interstitial;
    @BindString(R.string.payment_canceled)
    String payment_canceled;
    @BindString(R.string.no_internet_connection)
    String no_internet_connection;
    @BindString(R.string.went_wrong)
    String went_wrong;

    // Variables
    // Boolean
    private boolean doubleBackToExitPressedOnce;
    // AdView
    private AdView adView;
    // InterstitialAd
    private InterstitialAd interstitialAd;
    // Firebase
    private FirebaseUser mUser;
    // BillingClient
    private BillingClient billingClient;
    // List
    private List<SkuDetails> inAPPList;
    // Static
    public static boolean isPremium;
    // BroadcastReceiver
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initialFunctions();
    }

    private void initialFunctions() {
        declareVariables();
        setImages();
        setBilling();
        //checkIfTheUserIsPremium();
        isPremium = true;
        registerInternetReceiver();
        if (!isPremium) {
            setAd();
        }else{
            home_goPremiumCV.setVisibility(View.GONE);
        }
        setInterstitialAd();
    }

    private void declareVariables() {
        // Boolean
        doubleBackToExitPressedOnce = false;
        // AdView
        adView = new AdView(this);
        // Billing
        billingClient = BillingClient
                .newBuilder(this)
                .enablePendingPurchases()
                .setListener(this)
                .build();
        // BroadcastReceiver
        broadcastReceiver = new InternetReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    private void setImages() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.words);
        images.add(R.drawable.phrases);
        images.add(R.drawable.test);
        images.add(R.drawable.support_us);

        List<ImageView> imageViews = new ArrayList<>();
        imageViews.add(home_wordsIV);
        imageViews.add(home_phrasesIV);
        imageViews.add(home_testIV);
        imageViews.add(home_supportIV);

        for (int i = 0; i < images.size(); i++) {
            Glide.with(this)
                    .load(images.get(i))
                    .priority(Priority.IMMEDIATE)
                    .into(imageViews.get(i));
        }
    }

    private void setAd() {
        // BannerAd
        MobileAds.initialize(this);
        adView.setAdUnitId(easyenglish_home_banner);
        home_bannerAdPlaceholderFL.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();

        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);
    }

    private void setInterstitialAd(){
        // InterstitialAd
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(easyenglish_home_interstitial);
        AdRequest interstitialAdRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(interstitialAdRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();

                final Handler handler = new Handler();
                handler.postDelayed(() -> Toast.makeText(HomeActivity.this, thank_you_for_watching_ad, Toast.LENGTH_SHORT).show(), 300);

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

    private void setBilling() {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {

                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {

                    List<String> inAppListString = new ArrayList<>();
                    inAppListString.add("no_ads");

                    SkuDetailsParams.Builder inAppParams = SkuDetailsParams.newBuilder();
                    inAppParams.setSkusList(inAppListString).setType(BillingClient.SkuType.INAPP);

                    billingClient.querySkuDetailsAsync(inAppParams.build(), (billingResult1, list) -> inAPPList = list);

                } else {
                    Toast.makeText(HomeActivity.this, went_wrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                Toast.makeText(HomeActivity.this, went_wrong, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkIfTheUserIsPremium() {
        Purchase.PurchasesResult purchasesResult = billingClient.queryPurchases(BillingClient.SkuType.INAPP);
        List<Purchase> purchaseList = purchasesResult.getPurchasesList();
        isPremium = purchaseList != null;
    }

    @OnClick(R.id.home_wordsLL)
    public void home_wordsLLClicked() {
        go(WordActivity.class);
    }

    @OnClick(R.id.home_phrasesLL)
    public void home_phrasesLLClicked() {
        go(PhraseActivity.class);
    }

    @OnClick(R.id.home_testLL)
    public void home_testLLCLicked() {
        if (mUser != null) {
            showChooseDialog();
        } else {
            go(TestLogInActivity.class);
        }
    }

    @OnClick(R.id.home_supportLL)
    public void home_supportLLClicked() {
        showSupportDialog();
    }

    private void go(Class activity) {
        Intent intent = new Intent(HomeActivity.this, activity);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void showSupportDialog() {
        final AlertDialog dialog_support = new AlertDialog.Builder(this).create();
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_support, null);

        ImageView dialog_support_supportUsIV = view.findViewById(R.id.dialog_support_supportUsIV);
        ImageView dialog_support_closeIV = view.findViewById(R.id.dialog_support_closeIV);
        MaterialButton dialog_support_rateItBTN = view.findViewById(R.id.dialog_support_rateItBTN);
        MaterialButton dialog_support_showAdBTN = view.findViewById(R.id.dialog_support_showAdBTN);

        Glide.with(this)
                .load(R.drawable.support_us_2)
                .priority(Priority.IMMEDIATE)
                .into(dialog_support_supportUsIV);

        dialog_support_closeIV.setOnClickListener(v -> dialog_support.dismiss());

        dialog_support_rateItBTN.setOnClickListener(v -> {
            Uri uri = Uri.parse(app_link);
            Intent puan_ver_intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(puan_ver_intent);
        });

        dialog_support_showAdBTN.setOnClickListener(v -> {
            if (interstitialAd != null) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
            }
        });

        dialog_support.setView(view);
        dialog_support.setCanceledOnTouchOutside(false);
        dialog_support.show();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
        } else {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, press_back_again, Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        }
    }

    @OnClick(R.id.home_aboutUsTV)
    public void home_aboutUsTVClicked() {
        go(AboutUsActivity.class);
    }

    private void showChooseDialog() {
        final AlertDialog dialog_choose = new AlertDialog.Builder(this).create();
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_choose_for_home, null);

        MaterialButton dialog_choose_for_home_wordsBTN = view.findViewById(R.id.dialog_choose_for_home_wordsBTN);
        MaterialButton dialog_choose_for_home_phrasesBTN = view.findViewById(R.id.dialog_choose_for_home_phrasesBTN);
        ImageView dialog_choose_for_home_closeIV = view.findViewById(R.id.dialog_choose_for_home_closeIV);

        dialog_choose_for_home_wordsBTN.setOnClickListener(v -> {
            dialog_choose.dismiss();
            go(TestWordActivity.class);
        });

        dialog_choose_for_home_phrasesBTN.setOnClickListener(v -> {
            dialog_choose.dismiss();
            go(TestPhraseActivity.class);
        });

        dialog_choose_for_home_closeIV.setOnClickListener(v -> dialog_choose.dismiss());

        dialog_choose.setView(view);
        dialog_choose.setCanceledOnTouchOutside(false);
        dialog_choose.setCancelable(false);
        dialog_choose.show();
    }

    private void showBillingDialog() {
        if (inAPPList.size() != 0) {
            BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                    .setSkuDetails(inAPPList.get(0))
                    .build();

            billingClient.launchBillingFlow(HomeActivity.this, flowParams);
        }
    }

    @OnClick(R.id.home_goPremiumCV)
    public void home_goPremiumCVClicked() {
        final AlertDialog dialog_premium = new AlertDialog.Builder(this).create();
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_premium, null);

        ImageView dialog_premium_closeIV = view.findViewById(R.id.dialog_premium_closeIV);
        ImageView dialog_premium_premiumIV = view.findViewById(R.id.dialog_premium_premiumIV);
        MaterialButton dialog_premium_premiumBTN = view.findViewById(R.id.dialog_premium_premiumBTN);

        dialog_premium_closeIV.setOnClickListener(v -> dialog_premium.dismiss());

        Glide.with(this)
                .load(R.drawable.easyenglish_logo)
                .priority(Priority.IMMEDIATE)
                .into(dialog_premium_premiumIV);

        dialog_premium_premiumBTN.setOnClickListener(v -> showBillingDialog());

        dialog_premium.setView(view);
        dialog_premium.setCanceledOnTouchOutside(false);
        dialog_premium.show();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> list) {
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {

            for (Purchase purchase : list) {

                if (!purchase.isAcknowledged()) {
                    AcknowledgePurchaseParams.newBuilder()
                            .setPurchaseToken(purchase.getPurchaseToken())
                            .build();
                }

                restartTheApp();
            }
        }

        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {

            Toast.makeText(HomeActivity.this, payment_canceled, Toast.LENGTH_SHORT).show();
        }
    }

    private void restartTheApp() {
        Intent intent = getIntent();
        HomeActivity.this.finish();
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void registerInternetReceiver() {
        if (!isPremium) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    private void unregisterInternetReceiver() {
        if (!isPremium) {
            try {
                unregisterReceiver(broadcastReceiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterInternetReceiver();
    }
}
