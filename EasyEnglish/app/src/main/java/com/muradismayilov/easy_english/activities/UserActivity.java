package com.muradismayilov.easy_english.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muradismayilov.easy_english.R;
import com.muradismayilov.easy_english.model.User;
import com.muradismayilov.easy_english.recyclerview.UserAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {

    // UI Components
    // RecyclerView
    @BindView(R.id.user_userRV)
    RecyclerView user_userRV;

    // String
    @BindString(R.string.no_internet_connection)
    String no_internet_connection;

    // Variables
    // List
    private List<User> mList;
    // String
    private String mUid;
    // Adapter
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        initialFunctions();
    }

    private void initialFunctions() {
        declareVariables();
        setRecyclerView();
        checkInternet();
    }

    private void declareVariables() {
        // List
        mList = new ArrayList<>();
        // Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            mUid = mUser.getUid();
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getUsers();

        } else {
            Toast.makeText(this, no_internet_connection, Toast.LENGTH_SHORT).show();
        }
    }

    private void getUsers() {
        if (mUid != null) {
            DatabaseReference mDatabaseReference = FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("Users");

            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    mList.clear();

                    try {

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            mList.add(new User(ds.getKey(), ds.child("username").getValue().toString(), ds.child("score").getValue().toString()));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Collections.sort(mList, User.comparator);

                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void setRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new UserAdapter(this, mList);
        user_userRV.setLayoutManager(mLayoutManager);
        user_userRV.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
