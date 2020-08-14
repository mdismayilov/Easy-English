package com.muradismayilov.easy_english.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.muradismayilov.easy_english.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoInternetDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_no_internet_dialog);
        ButterKnife.bind(this);
    }

    @SuppressWarnings("ConstantConditions")
    @OnClick(R.id.activity_no_internet_dialog_tryAgainBTN)
    public void activity_no_internet_dialog_tryAgainBTNClicked() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            NoInternetDialogActivity.this.finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        }
    }

    @Override
    public void onBackPressed() {
    }
}
