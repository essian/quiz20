package com.essian.android.quiz20;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import database.ScoreDbHelper;

/**
 * Created by Essian on 17/03/2017.
 */

public class DisplayQuizResultFragment extends DialogFragment {

    private static final String ARG_SCORE = "score";
    private static final String ARG_NAME = "name";

    public static DisplayQuizResultFragment newInstance(int score, String name) {
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putInt(ARG_SCORE, score);

        DisplayQuizResultFragment fragment = new DisplayQuizResultFragment();
        fragment.setArguments(args);
        return fragment;
    }




    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final int score = getArguments().getInt(ARG_SCORE);
        final String name = getArguments().getString(ARG_NAME);
        String message = "Well done " + name
                + "! You scored " + score + " " + getResources().getQuantityString(R.plurals.point, score) +"."
                + "\n\nDo you want to save your score or retry and change your answers?";
        return new AlertDialog.Builder(getActivity())
                .setTitle("Your score")
                .setMessage(message)
                .setPositiveButton(R.string.save,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteOpenHelper scoreDbHelper = new ScoreDbHelper(getActivity());
                                SQLiteDatabase db = scoreDbHelper.getReadableDatabase();
                                ContentValues values = new ContentValues();
                                values.put("NAME", name);
                                values.put("SCORE", score);
                                db.insert("SCORE", null, values);
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton(R.string.retry, null)
                .create();
    }
}
