package com.muradismayilov.easy_english.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.muradismayilov.easy_english.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuggestionActivity extends AppCompatActivity {

    // UI Components
    // FrameLayout
    @BindView(R.id.suggestion_bannerAdPlaceholderFL)
    FrameLayout suggestion_bannerAdPlaceholderFL;

    // String
    @BindString(R.string.easyenglish_suggestions_banner)
    String easyenglish_suggestions_banner;

    // Variables
    // AdView
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_suggestion);
        ButterKnife.bind(this);
        initialFunctions();
    }

    private void initialFunctions(){
        declareVariables();
        boolean isPremium = HomeActivity.isPremium;
        if (!isPremium) {
            setAd();
        }
    }

    private void declareVariables(){
        adView = new AdView(this);
    }

    private void setAd() {
        MobileAds.initialize(this);
        adView.setAdUnitId(easyenglish_suggestions_banner);
        suggestion_bannerAdPlaceholderFL.addView(adView);

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

    @OnClick(R.id.bbc_learning_englishLL)
    public void bbc_learning_englishLLClicked() {
        go("https://www.youtube.com/channel/UCHaHD477h-FeBbVh9Sh7syA");
    }

    @OnClick(R.id.rachels_englishLL)
    public void rachels_englishLLClicked() {
        go("https://www.youtube.com/channel/UCvn_XCl_mgQmt3sD753zdJA");
    }

    @OnClick(R.id.go_natural_english_with_gabbyLL)
    public void go_natural_english_with_gabbyLLClicked() {
        go("https://www.youtube.com/channel/UC9Pbt3q-ihROg1lmmmQdU2w");
    }

    @OnClick(R.id.learn_english_with_englishclass101_comLL)
    public void learn_english_with_englishclass101_comLLClicked() {
        go("https://www.youtube.com/user/ENGLISHCLASS101");
    }

    @OnClick(R.id.learn_english_with_lets_talk_free_english_lessonsLL)
    public void learn_english_with_lets_talk_free_english_lessonsLLClicked() {
        go("https://www.youtube.com/channel/UCicjynhfFw2LiIQFnoS1JTw");
    }

    @OnClick(R.id.to_fluencyLL)
    public void to_fluencyLLClicked() {
        go("https://www.youtube.com/channel/UC32mYgIHS-e3C3Eyd2tRw6g");
    }

    @OnClick(R.id.learn_english_with_tv_seriesLL)
    public void learn_english_with_tv_seriesLLClicked() {
        go("https://www.youtube.com/channel/UCKgpamMlm872zkGDcBJHYDg");
    }

    @OnClick(R.id.english_speaking_practiceLL)
    public void english_speaking_practiceLLClicked() {
        go("https://www.youtube.com/channel/UCHh5i83CFCk8DZyeEEIfZdA");
    }

    @OnClick(R.id.mmmEnglishLL)
    public void mmmEnglishLLClicked() {
        go("https://www.youtube.com/channel/UCrRiVfHqBIIvSgKmgnSY66g");
    }

    @OnClick(R.id.jamesesl_english_lessonsLL)
    public void jamesesl_english_lessonsLLClicked() {
        go("https://www.youtube.com/channel/UCwA7Aepp7nRUJNa8roQ-6Bw");
    }

    @OnClick(R.id.english_practice_testLL)
    public void english_practice_testLLClicked() {
        go("https://www.youtube.com/channel/UCgBfckiwjde0_C-ORnfL64g");
    }

    @OnClick(R.id.the_daily_dropoutLL)
    public void the_daily_dropoutLLClicked() {
        go("https://www.youtube.com/channel/UCklSaeqb0wONcyEv44PV-iQ");
    }

    @OnClick(R.id.bigdawstvLL)
    public void bigdawstvLLClicked() {
        go("https://www.youtube.com/user/BigDawsTv");
    }

    @OnClick(R.id.steven_schapiroLL)
    public void steven_schapiroLLClicked() {
        go("https://www.youtube.com/channel/UCJv5T2W-D3K3fYO0prgv5uw");
    }

    @OnClick(R.id.thatwasepicLL)
    public void thatwasepicLLClicked() {
        go("https://www.youtube.com/channel/UCSrnmu3W6YXWU_85DKT5arg");
    }

    @OnClick(R.id.lahwfLL)
    public void lahwfLLClicked() {
        go("https://www.youtube.com/channel/UCQlVOYJyQp64rA12ac0mv6g");
    }

    @OnClick(R.id.rudy_mancusoLL)
    public void rudy_mancusoLLClicked() {
        go("https://www.youtube.com/channel/UC5jkXpfnBhlDjqh0ir5FsIQ");
    }

    @OnClick(R.id.angrypicnicLL)
    public void angrypicnicLLClicked() {
        go("https://www.youtube.com/channel/UCdOpBLrrnrYA-q4dfNfY8Sw");
    }

    @OnClick(R.id.anwar_jibawiLL)
    public void anwar_jibawiLLClicked() {
        go("https://www.youtube.com/channel/UCEr55381WIqO1w_IzgcI5DQ");
    }

    @OnClick(R.id.lele_ponsLL)
    public void lele_ponsLLClicked() {
        go("https://www.youtube.com/channel/UCi9cDo6239RAzPpBZO9y5SA");
    }

    @OnClick(R.id.amelia_gethingLL)
    public void amelia_gethingLLClicked() {
        go("https://www.youtube.com/channel/UCxZrj5lEksv_I7r9L854LeA");
    }

    @OnClick(R.id.adrian_geeLL)
    public void adrian_geeLLClicked() {
        go("https://www.youtube.com/channel/UCvmpOQfPsIrxO0NI5slLDwQ");
    }

    @OnClick(R.id.josh_paler_linLL)
    public void josh_paler_linLLClicked() {
        go("https://www.youtube.com/user/JoshPalerLin/");
    }

    @OnClick(R.id.the_tonight_show_starring_jimmy_fallonLL)
    public void the_tonight_show_starring_jimmy_fallonLLClicked() {
        go("https://www.youtube.com/channel/UC8-Th83bH_thdKZDJCrn88g");
    }

    @OnClick(R.id.the_ellen_showLL)
    public void the_ellen_showLLClicked() {
        go("https://www.youtube.com/user/TheEllenShow");
    }

    @OnClick(R.id.marques_brownleeLL)
    public void marques_brownleeLLClicked() {
        go("https://www.youtube.com/channel/UCBJycsmduvYEL83R_U4JriQ");
    }

    @OnClick(R.id.vogueLL)
    public void vogueLLClicked() {
        go("https://www.youtube.com/channel/UCRXiA3h1no_PFkb1JCP0yMA");
    }

    @OnClick(R.id.team_fearlessLL)
    public void team_fearlessLLClicked() {
        go("https://www.youtube.com/channel/UCf9_s9ii6BZ-klpgmtIi3WQ");
    }

    @OnClick(R.id.motivation_studyLL)
    public void motivation_studyLLClicked() {
        go("https://www.youtube.com/channel/UC8PICQUP0a_HsrA9S4IIgWw");
    }

    private void go(String url) {
        Uri uri = Uri.parse(url);
        Intent go_intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(go_intent);
    }
}
