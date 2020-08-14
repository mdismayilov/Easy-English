package com.muradismayilov.easy_english.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.google.android.material.textview.MaterialTextView;
import com.muradismayilov.easy_english.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends AppCompatActivity {

    // UI Components
    // ImageView
    @BindView(R.id.about_us_facebookIV)
    ImageView about_us_facebookIV;
    @BindView(R.id.about_us_instagramIV)
    ImageView about_us_instagramIV;
    @BindView(R.id.about_us_twitterIV)
    ImageView about_us_twitterIV;
    @BindView(R.id.about_us_whatsappIV)
    ImageView about_us_whatsappIV;
    @BindView(R.id.about_us_websiteIV)
    ImageView about_us_websiteIV;
    @BindView(R.id.about_us_emailIV)
    ImageView about_us_emailIV;
    // TextView
    @BindView(R.id.about_us_copyrightTV)
    MaterialTextView about_us_copyrightTV;

    // String
    @BindString(R.string.send_via)
    String send_via;
    @BindString(R.string.copyright_2)
    String copyright_2;
    @BindString(R.string.copyright_3)
    String copyright_3;
    @BindString(R.string.facebook)
    String facebook;
    @BindString(R.string.instagram)
    String instagram;
    @BindString(R.string.twitter)
    String twitter;
    @BindString(R.string.whatsapp)
    String whatsapp;
    @BindString(R.string.website)
    String website;
    @BindString(R.string.email)
    String email;
    @BindString(R.string.google_play)
    String google_play;
    @BindString(R.string.app_full_name)
    String app_full_name;
    @BindString(R.string.privacy_policy_link)
    String privacy_policy_link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        initialFunctions();
    }

    private void initialFunctions() {
        setImage();
        setText();
    }

    private void setImage() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.facebook);
        images.add(R.drawable.instagram);
        images.add(R.drawable.twitter);
        images.add(R.drawable.whatsapp);
        images.add(R.drawable.website);
        images.add(R.drawable.email);

        List<ImageView> imageViews = new ArrayList<>();
        imageViews.add(about_us_facebookIV);
        imageViews.add(about_us_instagramIV);
        imageViews.add(about_us_twitterIV);
        imageViews.add(about_us_whatsappIV);
        imageViews.add(about_us_websiteIV);
        imageViews.add(about_us_emailIV);

        for (int i = 0; i < images.size(); i++) {
            Glide.with(this)
                    .load(images.get(i))
                    .priority(Priority.IMMEDIATE)
                    .into(imageViews.get(i));
        }
    }

    @OnClick(R.id.about_us_facebookIV)
    public void contact_us_facebookIVCLicked() {
        open(facebook);
    }

    @OnClick(R.id.about_us_instagramIV)
    public void contact_us_instagramIVCLicked() {
        open(instagram);
    }

    @OnClick(R.id.about_us_twitterIV)
    public void contact_us_twitterIVCLicked() {
        open(twitter);
    }

    @OnClick(R.id.about_us_whatsappIV)
    public void contact_us_whatsappIVCLicked() {
        call();
    }

    @OnClick(R.id.about_us_websiteIV)
    public void contact_us_websiteIVCLicked() {
        open(website);
    }

    @OnClick(R.id.about_us_emailIV)
    public void contact_us_emailIVCLicked() {
        openEmail();
    }

    @OnClick(R.id.about_us_otherAppsTV)
    public void about_us_otherAppsTVClicked() {
        open(google_play);
    }

    private void open(String url) {
        Uri open_uri = Uri.parse(url);
        Intent open_intent = new Intent(Intent.ACTION_VIEW, open_uri);
        startActivity(open_intent);
    }

    private void call() {
        Uri call_uri = Uri.parse(whatsapp);
        Intent call_intent = new Intent(Intent.ACTION_DIAL, call_uri);
        startActivity(call_intent);
    }

    private void openEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, app_full_name);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(emailIntent, send_via));
    }

    @OnClick(R.id.about_us_privacyPolicyTV)
    public void contact_us_privacyPolicyTVClicked() {
        open(privacy_policy_link);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void setText() {
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String copyright = copyright_2 + " " + year + " " + copyright_3;
        about_us_copyrightTV.setText(copyright);
    }
}
