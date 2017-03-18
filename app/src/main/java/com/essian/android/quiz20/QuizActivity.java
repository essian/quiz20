package com.essian.android.quiz20;

import android.support.v4.app.Fragment;

public class QuizActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new QuizFragment();
    }

}
