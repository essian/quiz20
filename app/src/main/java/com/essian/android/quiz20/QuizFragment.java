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
    private static final String TIM_GUNN_CORRECT_ANSWER = "make it work";
    private static final String TREADLE_CORRECT_ANSWER = "treadle";
    private static final String OVERLOCKER_THREADS_CORRECT_ANSWER = "2";
    private static final String ELECTRIC_MACHINE_CORRECT_ANSWER = "singer";
    private final String STATE_NEEDLE_COUNT = "needleCount";
    private int needle_count = 0;
    private int score = 0;

    private EditText mNameEditText;
    private String mName;

    private TextView mTimGunnQuestionView;
    private EditText mTimGunnEditText;

    private TextView mTreadleQuestionView;
    private EditText mTreadleEditText;

    private LinearLayout mBig4CheckBoxes;
    private TextView mBig4QuestionView;
    private CheckBox mSimplicity;
    private CheckBox mVogue;
    private CheckBox mButterick;
    private CheckBox mMccalls;
    private CheckBox mBurdastyle;
    private CheckBox mKwiksew;
    private CheckBox mBurda;
    private CheckBox mNewlook;

    private TextView mNeedleEyeQuestion;
    private RadioButton mNeedleEyeAnswer;
    private RadioGroup mNeedleEyeRg;

    private TextView mOverlockerThreadsQuestionView;
    private TextView mOverlockerThreadsAnswerView;

    private TextView mBastingQuestionView;
    private RadioButton mBastingCorrectAnswer;
    private RadioGroup mBastingRg;

    private TextView mElectricMachineQuestionView;
    private EditText mElectricMachineEditText;

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

    /**
     * Sets up options menu and creates fragment
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    /**
     * Creates view for fragment and finds necessary views, sets onClickListeners
     *
     * @param inflater           LayoutInflater that inflates view
     * @param container          ViewGroup for fragment
     * @param savedInstanceState
     * @return inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);

        mOverlockerThreadsQuestionView = (TextView) v.findViewById(R.id.questionOverlockerThreads);
        mOverlockerThreadsAnswerView = (TextView) v.findViewById(R.id.needle_count);
        if (savedInstanceState != null) {
            needle_count = savedInstanceState.getInt(STATE_NEEDLE_COUNT);
        } else {
            needle_count = 0;
        }

        displayNeedleCount();

        mNameEditText = (EditText) v.findViewById(R.id.nameEditText);
        mNeedleEyeAnswer = (RadioButton) v.findViewById(R.id.needle_eye_tip);
        mNeedleEyeQuestion = (TextView) v.findViewById(R.id.needleEye);
        mNeedleEyeRg = (RadioGroup) v.findViewById(R.id.radio_group_needle_eye);
        mBastingQuestionView = (TextView) v.findViewById(R.id.questionBasting);
        mBastingCorrectAnswer = (RadioButton) v.findViewById(R.id.basting_true);
        mBastingRg = (RadioGroup) v.findViewById(R.id.radio_group_basting);
        mElectricMachineQuestionView = (TextView) v.findViewById(R.id.electricMachine);
        mElectricMachineEditText = (EditText) v.findViewById(R.id.electric_machine_answer);
        mTimGunnQuestionView = (TextView) v.findViewById(R.id.timGunn);
        mTimGunnEditText = (EditText) v.findViewById(R.id.tim_gunn_answer);
        mTreadleQuestionView = (TextView) v.findViewById(treadle);
        mTreadleEditText = (EditText) v.findViewById(R.id.treadle_answer);
        mBig4CheckBoxes = (LinearLayout) v.findViewById(R.id.big4CheckBoxes);
        mBig4QuestionView = (TextView) v.findViewById(R.id.big4);
        mSimplicity = (CheckBox) v.findViewById(R.id.simplicity);
        mVogue = (CheckBox) v.findViewById(R.id.vogue);
        mButterick = (CheckBox) v.findViewById(R.id.butterick);
        mMccalls = (CheckBox) v.findViewById(R.id.mccalls);
        mBurdastyle = (CheckBox) v.findViewById(R.id.burdastyle);
        mKwiksew = (CheckBox) v.findViewById(R.id.kwiksew);
        mBurda = (CheckBox) v.findViewById(R.id.burda);
        mNewlook = (CheckBox) v.findViewById(R.id.newlook);
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

    /**
     * Sets up the options menu
     *
     * @param menu     is the menu
     * @param inflater is an MenuInflater object that is passed in
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_quiz, menu);
    }

    /**
     * Controls what happens when a menu item is selected
     *
     * @param item is the selected item
     * @return true indicates success
     */
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
        if (!checkName(getName())) { return; };
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

    /**
     * Gets the user's name
     *
     * @return the user's name without unnecessary white space
     */
    private String getName() {
        mName = mNameEditText.getText().toString().trim();
        return mName;
    }

    /**
     * Checks for valid name
     * @param name is the user's name
     * @return boolean indicating if name is valid
     */
    private boolean checkName(String name) {
        if (name == null || name.isEmpty()) {
            mNameEditText.requestFocus();
            Toast.makeText(getActivity(), "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionOverlockerThreads() {
        boolean result = mOverlockerThreadsAnswerView.getText().equals(OVERLOCKER_THREADS_CORRECT_ANSWER);
        grade(result, mOverlockerThreadsQuestionView);
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionBasting() {
        boolean result = mBastingCorrectAnswer.isChecked();
        grade(result, mBastingQuestionView);
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionElectricMachine() {
        boolean result = mElectricMachineEditText.getText().toString().toLowerCase().contains(ELECTRIC_MACHINE_CORRECT_ANSWER);
        grade(result, mElectricMachineQuestionView);
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionNeedleEye() {
        boolean result = mNeedleEyeAnswer.isChecked();
        grade(result, mNeedleEyeQuestion);
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionTimGunn() {
        boolean result = mTimGunnEditText.getText().toString().toLowerCase().contains(TIM_GUNN_CORRECT_ANSWER);
        grade(result, mTimGunnQuestionView);
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionTreadle() {
        String treadleTextAnswer = mTreadleEditText.getText().toString().toLowerCase();
        boolean result = treadleTextAnswer.contains(TREADLE_CORRECT_ANSWER) || treadleTextAnswer.contains("treddle");
        grade(result, mTreadleQuestionView);
    }

    /**
     * This method increments the score if the answer to the question is correct
     */
    private void scoreQuestionBig4() {
        Boolean allRightAnswers = mSimplicity.isChecked() &&
                mVogue.isChecked() &&
                mButterick.isChecked() &&
                mMccalls.isChecked();
        Boolean anyWrongAnswers = mBurdastyle.isChecked() ||
                mKwiksew.isChecked() ||
                mBurda.isChecked() ||
                mNewlook.isChecked();
        boolean result = allRightAnswers && !anyWrongAnswers;
        grade(result, mBig4QuestionView);
    }

    /**
     * This method updates the displayed needle count
     */
    private void displayNeedleCount() {
        mOverlockerThreadsAnswerView.setText(String.valueOf(needle_count));
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


