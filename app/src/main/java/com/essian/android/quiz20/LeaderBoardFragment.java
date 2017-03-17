package com.essian.android.quiz20;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import database.ScoreDbHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderBoardFragment extends Fragment {

    ViewGroup parent;

    public LeaderBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_leader_board, container, false);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), QuizActivity.class);
                startActivity(i);
            }
        });

        parent = (ViewGroup) v.findViewById(R.id.score_table);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        View v = getView();
        drawTable(v);
    }

    @Override
    public void onStop() {
        super.onStop();
        View v = getView();
        parent.removeAllViews();

    }

    private void drawTable(View v) {

        TableLayout tl = new TableLayout(getContext());
        try {
            SQLiteOpenHelper scoreDbHelper = new ScoreDbHelper(getActivity());
            SQLiteDatabase db = scoreDbHelper.getReadableDatabase();
            Cursor cursor = db.query(
                    "SCORE",
                    new String[]{"NAME", "SCORE"},
                    null,
                    null,
                    null,
                    null,
                    "SCORE DESC LIMIT 3");
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                TableRow tr = new TableRow(getContext());
                TextView nameView = new TextView(getContext(), null, R.attr.tableRow);
                nameView.setText(cursor.getString(0));
                tr.addView(nameView);

                TextView scoreView = new TextView(getContext(), null, R.attr.tableRow);
                scoreView.setText("" + cursor.getInt(1));
                tr.addView(scoreView);

                tl.addView(tr);
                cursor.moveToNext();
            }
            cursor.close();

        } catch (SQLiteException e) {
            Toast.makeText(getActivity(), R.string.database_unavailable, Toast.LENGTH_SHORT).show();
        }

        if (tl.getChildCount() > 0) {
            parent.addView(tl);
        } else {
            TextView noScores = new TextView(getActivity(), null, R.attr.infoTextStyle);
            noScores.setText(R.string.no_high_scores);
            parent.addView(noScores);
        }
    }

}
