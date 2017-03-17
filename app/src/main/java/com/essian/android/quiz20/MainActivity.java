package com.essian.android.quiz20;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment leaderBoard = new LeaderBoardFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container, leaderBoard).commit();

    }
}
