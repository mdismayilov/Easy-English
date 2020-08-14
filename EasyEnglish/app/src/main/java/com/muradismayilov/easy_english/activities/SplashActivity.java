package com.muradismayilov.easy_english.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.muradismayilov.easy_english.R;
import com.muradismayilov.easy_english.database.DatabaseHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    // UI Components
    // ImageView
    @BindView(R.id.splash_easyenglishIV)
    ImageView splash_easyenglishIV;

    // Variables
    private DatabaseHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initialFunctions();
    }

    private void initialFunctions() {
        setImage();
        loadDatabase();
    }

    private void setImage() {
        Glide.with(this)
                .load(R.drawable.easyenglish_logo)
                .priority(Priority.IMMEDIATE)
                .into(splash_easyenglishIV);
    }

    private void loadDatabase() {
        myDbHelper = new DatabaseHelper(this);

        if (myDbHelper.checkDataBase()) {
            openDatabase();
            go();
        } else {
            DatabaseAsync task = new DatabaseAsync(SplashActivity.this);
            task.execute();
        }
    }

    private void openDatabase() {
        try {
            myDbHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void go() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            SplashActivity.this.finish();
        }, 2000);
    }

    @SuppressLint("StaticFieldLeak")
    class DatabaseAsync extends AsyncTask<Void, Void, Boolean> {

        private final Context context;

        DatabaseAsync(Context context) {
            this.context = context;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            DatabaseHelper myDbHelper = new DatabaseHelper(context);

            try {
                myDbHelper.createDataBase();

            } catch (Exception e) {

                Toast.makeText(context, "" + R.string.went_wrong, Toast.LENGTH_SHORT).show();
            }
            myDbHelper.close();
            return null;
        }

        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            go();
        }
    }
}
