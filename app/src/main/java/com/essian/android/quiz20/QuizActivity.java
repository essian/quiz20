package com.essian.android.quiz20;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        FragmentManager fm = getSupportFragmentManager();
        Fragment quiz = fm.findFragmentById(R.id.fragment_container);

        if (quiz == null) {
            quiz = new QuizFragment();
            fm.beginTransaction().add(R.id.fragment_container, quiz).commit();
        }

    }

}
