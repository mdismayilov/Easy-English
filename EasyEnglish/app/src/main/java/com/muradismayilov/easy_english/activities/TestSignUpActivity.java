package com.muradismayilov.easy_english.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muradismayilov.easy_english.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestSignUpActivity extends AppCompatActivity {

    // UI Components
    // TextInputEditText
    @BindView(R.id.test_sign_up_usernameET)
    TextInputEditText test_sign_up_usernameET;
    @BindView(R.id.test_sign_up_emailET)
    TextInputEditText test_sign_up_emailET;
    @BindView(R.id.test_sign_up_passwordET)
    TextInputEditText test_sign_up_passwordET;
    // ConstraintLayout
    @BindView(R.id.test_sign_up_mainCL)
    ConstraintLayout test_sign_up_mainCL;
    // ProgressBar
    @BindView(R.id.test_sign_up_progressBar)
    ProgressBar test_sign_up_progressBar;

    // String
    @BindString(R.string.no_internet_connection)
    String no_internet_connection;
    @BindString(R.string.fill_empty_fields)
    String fill_empty_fields;
    @BindString(R.string.username_too_long)
    String username_too_long;
    @BindString(R.string.went_wrong)
    String went_wrong;
    @BindString(R.string.we_sent_verification_code)
    String we_sent_verification_code;

    // Variables
    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    //String
    private String username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_test_sign_up);
        ButterKnife.bind(this);
        initialFunctions();
    }

    private void initialFunctions() {
        declareVariables();
        test_sign_up_progressBar.setVisibility(View.INVISIBLE);
    }

    private void declareVariables() {
        // Firebase
        mAuth = FirebaseAuth.getInstance();
        // String
        username = "";
        email = "";
        password = "";
    }

    @SuppressWarnings("ConstantConditions")
    @OnClick(R.id.test_sign_up_signUpBTN)
    public void test_sign_up_signUpBTNClicked() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            signUp();

        } else {
            Toast.makeText(this, no_internet_connection, Toast.LENGTH_SHORT).show();
        }
    }

    private void signUp() {
        showProgress();
        getTexts();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            hideProgress();
            Toast.makeText(this, fill_empty_fields, Toast.LENGTH_SHORT).show();
        } else if (username.length() > 15) {
            hideProgress();
            Toast.makeText(this, username_too_long, Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
                mUser = authResult.getUser();

                if (mUser != null) {

                    mUser.sendEmailVerification().addOnSuccessListener(aVoid -> saveUserData()).addOnFailureListener(e -> {
                        hideProgress();
                        Toast.makeText(TestSignUpActivity.this, went_wrong, Toast.LENGTH_SHORT).show();
                    });

                } else {
                    hideProgress();
                    Toast.makeText(TestSignUpActivity.this, went_wrong, Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(e -> {
                hideProgress();
                Toast.makeText(TestSignUpActivity.this, went_wrong, Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void saveUserData() {
        DatabaseReference mDatabaseReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Users")
                .child(mUser.getUid());

        mDatabaseReference.child("username").setValue(username);
        mDatabaseReference.child("score").setValue("0").addOnSuccessListener(aVoid -> {
            hideProgress();
            Toast.makeText(TestSignUpActivity.this, we_sent_verification_code, Toast.LENGTH_LONG).show();
            final Handler handler = new Handler();
            handler.postDelayed(this::go, 400);

        }).addOnFailureListener(e -> {
            hideProgress();
            Toast.makeText(TestSignUpActivity.this, went_wrong, Toast.LENGTH_SHORT).show();
        });
    }

    private void getTexts() {
        if (test_sign_up_usernameET.getText() != null) {
            username = test_sign_up_usernameET.getText().toString().trim();
        }
        if (test_sign_up_emailET.getText() != null) {
            email = test_sign_up_emailET.getText().toString().trim();
        }
        if (test_sign_up_passwordET.getText() != null) {
            password = test_sign_up_passwordET.getText().toString().trim();
        }
    }

    @OnClick(R.id.test_sign_up_logInTV)
    public void test_sign_up_logInTVClicked() {
        go();
    }

    private void go() {
        Intent intent = new Intent(TestSignUpActivity.this, TestLogInActivity.class);
        startActivity(intent);
        TestSignUpActivity.this.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void showProgress() {
        test_sign_up_mainCL.setAlpha(0.5f);
        test_sign_up_progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        test_sign_up_mainCL.setAlpha(1.0f);
        test_sign_up_progressBar.setVisibility(View.INVISIBLE);
    }
}
