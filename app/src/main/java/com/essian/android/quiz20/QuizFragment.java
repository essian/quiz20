package com.essian.android.quiz20;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.essian.android.quiz20.R.id.treadle;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {

    private static final String DIALOG_SCORE = "DialogScore";
    private final String STATE_NEEDLE_COUNT = "needleCount";
    int needle_count = 0;
    int score = 0;

    private EditText mNameEditText;
    private String mName;

    private TextView timGunnQuestionView;
    private String timGunnCorrectAnswer = "make it work";
    private EditText mTimGunnEditText;

    private TextView treadleQuestionView;
    private String treadleCorrectAnswer = "treadle";
    private EditText mTreadleEditText;

    private LinearLayout mBig4CheckBoxes;
    private TextView big4QuestionView;
    private CheckBox simplicity;
    private CheckBox vogue;
    private CheckBox butterick;
    private CheckBox mccalls;
    private CheckBox burdastyle;
    private CheckBox kwiksew;
    private CheckBox burda;
    private CheckBox newlook;

    private TextView needleEyeQuestion;
    private RadioButton needleEyeAnswer;
    private RadioGroup mNeedleEyeRg;

    private TextView overlockerThreadsQuestionView;
    private TextView overlockerThreadsAnswerView;
    private static final String overlockerThreadsCorrectAnswer = "2";

    private TextView bastingQuestionView;
    private RadioButton bastingCorrectAnswer;
    private RadioGroup mBastingRg;

    private TextView electricMachineQuestionView;
    private static final String electricMachineCorrectAnswer = "singer";
    private EditText mElectricMachineEditText;

    private TextView needle_quantity_view;
    private Button mScoreButton;
    private Button mIncrementNeedles;
    private Button mDecrementNeedles;
    private Button mResetButton;


    public QuizFragment() {
        // Required empty public constructor
    }

    private int getColorWrapper(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(id);
        } else {
            //noinspection deprecation
            return context.getResources().getColor(id);
        }
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
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);

        overlockerThreadsQuestionView = (TextView) v.findViewById(R.id.questionOverlockerThreads);
        overlockerThreadsAnswerView = (TextView) v.findViewById(R.id.needle_count);

        if (savedInstanceState != null) {
            needle_count = savedInstanceState.getInt(STATE_NEEDLE_COUNT);
        } else {
            needle_count = 0;
        }

        displayNeedleCount();

        mNameEditText = (EditText) v.findViewById(R.id.nameEditText);

        needleEyeAnswer = (RadioButton) v.findViewById(R.id.needle_eye_tip);
        needleEyeQuestion = (TextView) v.findViewById(R.id.needleEye);
        mNeedleEyeRg = (RadioGroup) v.findViewById(R.id.radio_group_needle_eye);

        bastingQuestionView = (TextView) v.findViewById(R.id.questionBasting);
        bastingCorrectAnswer = (RadioButton) v.findViewById(R.id.basting_true);
        mBastingRg = (RadioGroup) v.findViewById(R.id.radio_group_basting);


        electricMachineQuestionView = (TextView) v.findViewById(R.id.electricMachine);
        mElectricMachineEditText = (EditText) v.findViewById(R.id.electric_machine_answer);

        timGunnQuestionView = (TextView) v.findViewById(R.id.timGunn);
        timGunnCorrectAnswer = getString(R.string.tim_gunn_answer);
        mTimGunnEditText = (EditText) v.findViewById(R.id.tim_gunn_answer);

        treadleQuestionView = (TextView) v.findViewById(treadle);
        treadleCorrectAnswer = "treadle";
        mTreadleEditText = (EditText) v.findViewById(R.id.treadle_answer);

        mBig4CheckBoxes = (LinearLayout) v.findViewById(R.id.big4CheckBoxes);
        big4QuestionView = (TextView) v.findViewById(R.id.big4);
        simplicity = (CheckBox) v.findViewById(R.id.simplicity);
        vogue = (CheckBox) v.findViewById(R.id.vogue);
        butterick = (CheckBox) v.findViewById(R.id.butterick);
        mccalls = (CheckBox) v.findViewById(R.id.mccalls);
        burdastyle = (CheckBox) v.findViewById(R.id.burdastyle);
        kwiksew = (CheckBox) v.findViewById(R.id.kwiksew);
        burda = (CheckBox) v.findViewById(R.id.burda);
        newlook = (CheckBox) v.findViewById(R.id.newlook);


        mIncrementNeedles = (Button) v.findViewById(R.id.increment_needles);
        mIncrementNeedles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                needle_count += 1;
                displayNeedleCount();
            }
        });

        mDecrementNeedles = (Button) v.findViewById(R.id.decrement_needles);
        mDecrementNeedles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (needle_count > 0) {
                    needle_count -= 1;
                    displayNeedleCount();
                } else {
                    Toast.makeText(getActivity(), "You can not have negative needles", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mScoreButton = (Button) v.findViewById(R.id.score_button);
        mScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score();
            }
        });
        mResetButton = (Button) v.findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        mNameEditText.requestFocus();
        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_quiz, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.submit_quiz:
                score();
                return true;
            case R.id.reset_quiz:
                reset();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Saves the value of needle_count
     *
     * @param savedInstanceState is passed to onCreateView when the fragment is recreated
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current state
        savedInstanceState.putInt(STATE_NEEDLE_COUNT, needle_count);

        // call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * This method restarts the fragment resulting in the answers and scores being reset
     */
    public void reset() {
        mNameEditText.requestFocus();
        mNameEditText.setText("");
        mElectricMachineEditText.setText("");
        mTimGunnEditText.setText("");
        mTreadleEditText.setText("");

        mBastingRg.clearCheck();
        mNeedleEyeRg.clearCheck();

        needle_count = 0;
        displayNeedleCount();

        for (int i = 0; i < mBig4CheckBoxes.getChildCount(); i++) {

            LinearLayout ll = (LinearLayout) mBig4CheckBoxes.getChildAt(i);
            for (int j = 0; j < ll.getChildCount(); j++) {
                View v = ll.getChildAt(j);
                if (v instanceof CheckBox) {
                    CheckBox cb = (CheckBox) v;
                    cb.setChecked(false);
                }
            }
        }

    }


    /**
     * This method calculates the user's score, displays it then resets it ready for the app to be rescored.
     */
    public void score() {
        if (getName() == null || getName().isEmpty()) {
            mNameEditText.requestFocus();
            Toast.makeText(getActivity(), "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }
        scoreQuestionOverlockerThreads();
        scoreQuestionBasting();
        scoreQuestionElectricMachine();
        scoreQuestionNeedleEye();
        scoreQuestionTimGunn();
        scoreQuestionTreadle();
        scoreQuestionBig4();
        displayScore();
        score = 0;
    }

    public String getName() {
        mName = mNameEditText.getText().toString().trim();
        return mName;
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionOverlockerThreads() {
        boolean result = overlockerThreadsAnswerView.getText().equals(overlockerThreadsCorrectAnswer);
        grade(result, overlockerThreadsQuestionView);
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionBasting() {
        boolean result = bastingCorrectAnswer.isChecked();
        grade(result, bastingQuestionView);
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionElectricMachine() {
        boolean result = mElectricMachineEditText.getText().toString().toLowerCase().contains(electricMachineCorrectAnswer);
        grade(result, electricMachineQuestionView);
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionNeedleEye() {
        boolean result = needleEyeAnswer.isChecked();
        grade(result, needleEyeQuestion);
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionTimGunn() {
        boolean result = mTimGunnEditText.getText().toString().toLowerCase().contains(timGunnCorrectAnswer);
        grade(result, timGunnQuestionView);
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionTreadle() {
        String treadleTextAnswer = mTreadleEditText.getText().toString().toLowerCase();
        boolean result = treadleTextAnswer.contains(treadleCorrectAnswer) || treadleTextAnswer.contains("treddle");
        grade(result, treadleQuestionView);
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionBig4() {
        Boolean allRightAnswers = simplicity.isChecked() &&
                vogue.isChecked() &&
                butterick.isChecked() &&
                mccalls.isChecked();
        Boolean anyWrongAnswers = burdastyle.isChecked() ||
                kwiksew.isChecked() ||
                burda.isChecked() ||
                newlook.isChecked();
        boolean result = allRightAnswers && !anyWrongAnswers;
        grade(result, big4QuestionView);
    }

    /**
     * This method updates the displayed needle count
     */
    private void displayNeedleCount() {
        overlockerThreadsAnswerView.setText(String.valueOf(needle_count));
    }

    /**
     * This method displays the users score in a toast message
     */
    private void displayScore() {
//        String message = "You scored " + score;
//        message += " out of 7";
//        message += "\nWell done " + mName + "!";
//        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.show();
        FragmentManager fm = getFragmentManager();
        DisplayQuizResultFragment scoreDialog = DisplayQuizResultFragment.newInstance(score, mName);
        scoreDialog.show(fm, DIALOG_SCORE);
    }

    /**
     * This method increments score and changes question text color depending on the result of the question
     *
     * @param result       is a boolean indicating if the answer was correct (true)
     * @param questionView is the textView that holds the question text
     */
    private void grade(boolean result, TextView questionView) {
        if (result) {
            score += 1;
        } else {
            incorrectColor(questionView);
        }
    }


    /**
     * This method sets the text color of the passed in view to colorCorrectAnswer
     *
     * @param view is the current view
     */
    private void incorrectColor(TextView view) {
        view.setTextColor(getColorWrapper(getContext(), R.color.colorAccent));

    }

}


