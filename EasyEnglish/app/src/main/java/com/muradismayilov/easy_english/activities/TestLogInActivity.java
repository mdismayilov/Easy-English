package com.muradismayilov.easy_english.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muradismayilov.easy_english.R;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestLogInActivity extends AppCompatActivity {

    // UI Components
    // AutoCompleteTextView
    @BindView(R.id.test_log_in_emailET)
    AutoCompleteTextView test_log_in_emailET;
    // TextInputEditText
    @BindView(R.id.test_log_in_passwordET)
    TextInputEditText test_log_in_passwordET;
    // ProgressBar
    @BindView(R.id.test_log_in_progressBar)
    ProgressBar test_log_in_progressBar;
    // ConstraintLayout
    @BindView(R.id.test_log_in_mainCL)
    ConstraintLayout test_log_in_mainCL;

    // String
    @BindString(R.string.no_internet_connection)
    String no_internet_connection;
    @BindString(R.string.fill_empty_fields)
    String fill_empty_fields;
    @BindString(R.string.went_wrong)
    String went_wrong;
    @BindString(R.string.verify_your_email_address)
    String verify_your_email_address;
    @BindString(R.string.enter_your_email)
    String enter_your_email;
    @BindString(R.string.check_your_email_to_reset_password)
    String check_your_email_to_reset_password;
    @BindString(R.string.you_are_not_premium)
    String you_are_not_premium;

    // Variables
    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    // String
    private String email, password;
    // Final
    private final String PREFS_NAME = "PingBusPrefs";
    private final String PREFS_SEARCH_HISTORY = "SearchHistory";
    // List
    private Set<String> history;
    // Boolean
    private boolean isPremium;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_test_log_in);
        ButterKnife.bind(this);
        initialFunctions();
    }

    private void initialFunctions() {
        declareVariables();
        getHistory();
        test_log_in_progressBar.setVisibility(View.INVISIBLE);
        isPremium = HomeActivity.isPremium;
    }

    private void declareVariables() {
        // Firebase
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        // String
        email = "";
        password = "";
    }

    private void getHistory() {
        try {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            history = new HashSet<>(settings.getStringSet(PREFS_SEARCH_HISTORY, new HashSet<>()));
            setAutoCompleteSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.test_log_in_forgotPasswordTV)
    public void test_log_in_forgotPasswordTVClicked() {

        final AlertDialog dialog_forgot_password = new AlertDialog.Builder(this).create();
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_forgot_password, null);

        TextInputEditText dialog_forgot_password_emailET = view.findViewById(R.id.dialog_forgot_password_emailET);
        MaterialButton dialog_forgot_password_sendBTN = view.findViewById(R.id.dialog_forgot_password_sendBTN);
        MaterialButton dialog_forgot_password_cancelBTN = view.findViewById(R.id.dialog_forgot_password_cancelBTN);

        dialog_forgot_password_sendBTN.setOnClickListener(v -> {
            String dialog_email = "";

            if (dialog_forgot_password_emailET.getText() != null) {
                dialog_email = dialog_forgot_password_emailET.getText().toString().trim();
            }

            if (TextUtils.isEmpty(dialog_email)) {
                Toast.makeText(TestLogInActivity.this, enter_your_email, Toast.LENGTH_SHORT).show();
            } else {
                mAuth.sendPasswordResetEmail(dialog_email).addOnSuccessListener(aVoid -> Toast.makeText(TestLogInActivity.this, check_your_email_to_reset_password, Toast.LENGTH_LONG).show()).addOnFailureListener(e -> Toast.makeText(TestLogInActivity.this, went_wrong, Toast.LENGTH_SHORT).show());
            }
        });

        dialog_forgot_password_cancelBTN.setOnClickListener(v -> dialog_forgot_password.dismiss());

        dialog_forgot_password.setView(view);
        dialog_forgot_password.setCanceledOnTouchOutside(false);
        dialog_forgot_password.show();
    }

    @SuppressWarnings("ConstantConditions")
    @OnClick(R.id.test_log_in_logInBTN)
    public void test_log_in_logInBTNClicked() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            logIn();

        } else {
            Toast.makeText(this, no_internet_connection, Toast.LENGTH_SHORT).show();
        }
    }

    private void logIn() {
        showProgress();
        getTexts();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            hideProgress();
            Toast.makeText(this, fill_empty_fields, Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
                mUser = authResult.getUser();

                if (mUser != null) {
                    if (mUser.isEmailVerified()) {
                        hideProgress();
                        addSearchInput(email);
                        showChooseDialog();
                    } else {
                        hideProgress();
                        Toast.makeText(TestLogInActivity.this, verify_your_email_address, Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(e -> {
                hideProgress();
                Toast.makeText(TestLogInActivity.this, went_wrong, Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void getTexts() {
        if (test_log_in_emailET.getText() != null) {
            email = test_log_in_emailET.getText().toString().trim();
        }
        if (test_log_in_passwordET.getText() != null) {
            password = test_log_in_passwordET.getText().toString().trim();
        }
    }

    @OnClick(R.id.test_log_in_signUpTV)
    public void test_log_in_signUpTVClicked() {
        if (isPremium) {
            go(TestSignUpActivity.class);
        } else {
            Toast.makeText(this, you_are_not_premium, Toast.LENGTH_SHORT).show();
        }
    }

    private void go(Class activity) {
        Intent intent = new Intent(TestLogInActivity.this, activity);
        startActivity(intent);
        TestLogInActivity.this.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void showProgress() {
        test_log_in_mainCL.setAlpha(0.5f);
        test_log_in_progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        test_log_in_mainCL.setAlpha(1.0f);
        test_log_in_progressBar.setVisibility(View.INVISIBLE);
    }

    private void setAutoCompleteSource() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, history.toArray(new String[0]));
        test_log_in_emailET.setAdapter(adapter);
    }

    private void addSearchInput(String input) {
        if (!history.contains(input)) {
            history.add(input);
            setAutoCompleteSource();
        }
    }

    private void savePrefs() {
        try {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putStringSet(PREFS_SEARCH_HISTORY, history);

            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        savePrefs();
    }

    private void showChooseDialog() {
        final AlertDialog dialog_choose = new AlertDialog.Builder(this).create();
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_choose_for_log_in, null);

        MaterialButton dialog_choose_for_log_in_wordsBTN = view.findViewById(R.id.dialog_choose_for_log_in_wordsBTN);
        MaterialButton dialog_choose_for_log_in_phrasesBTN = view.findViewById(R.id.dialog_choose_for_log_in_phrasesBTN);

        dialog_choose_for_log_in_wordsBTN.setOnClickListener(v -> {
            dialog_choose.dismiss();
            go(TestWordActivity.class);
        });

        dialog_choose_for_log_in_phrasesBTN.setOnClickListener(v -> {
            dialog_choose.dismiss();
            go(TestPhraseActivity.class);
        });

        dialog_choose.setView(view);
        dialog_choose.setCanceledOnTouchOutside(false);
        dialog_choose.show();
    }
}
