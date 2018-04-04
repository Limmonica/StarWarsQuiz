package com.example.android.starwarsquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class QuizActivity extends AppCompatActivity {

    // Tracks the score of the player
    int playerScore;
    // Tracks the name of the player
    String playerName;
    // Tracks the correct answers to the 2nd question
    CheckBox getAnswer2A;
    CheckBox getAnswer2B;
    CheckBox getAnswer2C;
    // Tracks the incorrect answer to the 2nd question
    CheckBox getAnswer2D;
    // Tracks the right answers to the questions with a single correct answer
    EditText getAnswer1;
    RadioButton getAnswer3D;
    RadioButton getAnswer4B;
    RadioButton getAnswer5D;
    RadioButton getAnswer6A;
    RadioButton getAnswer7A;
    RadioButton getAnswer8D;
    RadioButton getAnswer9B;
    RadioButton getAnswer10D;

    /**
     * This method hides the keyboard when clicking outside the EditText area
     * Needs the parent view to be made clickable and focusable
     */
    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
            }
        }
    }

    /**
     * This method is called when clicking outside the EditText area
     * and hides the keyboard
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();
        if (v != null
                && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE)
                && v instanceof EditText
                && !v.getClass().getName().startsWith("android.webkit.")) {
            int screenCoordinates[] = new int[2];
            v.getLocationOnScreen(screenCoordinates);
            float x = ev.getRawX() + v.getLeft() - screenCoordinates[0];
            float y = ev.getRawY() + v.getTop() - screenCoordinates[1];
            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sets the content of the activity to use the activity_quiz.xml layout file
        setContentView(R.layout.activity_quiz);
        // Initializes the Bundle which holds the data passed between Activities
        Bundle bundle = getIntent().getExtras();
        // Gets the data stored in the bundle and sent from Main Activity
        if (bundle != null) {
            // Gets the name of the player
            playerName = bundle.getString("name");
        }
        // Initializes the right answers to the questions with one correct answer
        getAnswer1 = findViewById(R.id.q1_a);
        getAnswer3D = findViewById(R.id.q3_d1);
        getAnswer4B = findViewById(R.id.q4_b1);
        getAnswer5D = findViewById(R.id.q5_d1);
        getAnswer6A = findViewById(R.id.q6_a1);
        getAnswer7A = findViewById(R.id.q7_a1);
        getAnswer8D = findViewById(R.id.q8_d1);
        getAnswer9B = findViewById(R.id.q9_b1);
        getAnswer10D = findViewById(R.id.q10_d1);
        // Initializes all the answers for the 2nd question - multiple correct answers
        getAnswer2A = findViewById(R.id.q2_a1);
        getAnswer2B = findViewById(R.id.q2_b1);
        getAnswer2C = findViewById(R.id.q2_c1);
        getAnswer2D = findViewById(R.id.q2_d0);
        // Initializes the View Score button View
        Button result = findViewById(R.id.view_score);
        // Set a click listener on the View Score button View
        result.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the View Score View is clicked
            @Override
            public void onClick(View v) {
                // Checks if the player has selected the correct answers
                // boolean question1AnswerD = getAnswer1D.isChecked();
                String userAnswer = getAnswer1.getText().toString();
                boolean question2AnswerA = getAnswer2A.isChecked();
                boolean question2AnswerB = getAnswer2B.isChecked();
                boolean question2AnswerC = getAnswer2C.isChecked();
                // Checks if the player has also selected the wrong answer for question 2
                boolean question2AnswerD = getAnswer2D.isChecked();
                boolean question3AnswerD = getAnswer3D.isChecked();
                boolean question4AnswerB = getAnswer4B.isChecked();
                boolean question5AnswerD = getAnswer5D.isChecked();
                boolean question6AnswerA = getAnswer6A.isChecked();
                boolean question7AnswerA = getAnswer7A.isChecked();
                boolean question8AnswerD = getAnswer8D.isChecked();
                boolean question9AnswerB = getAnswer9B.isChecked();
                boolean question10AnswerD = getAnswer10D.isChecked();
                // Calculates the final score of the player, based on their responses, and stores it into a variable
                playerScore = checkScore(userAnswer, question2AnswerA, question2AnswerB, question2AnswerC,
                        question2AnswerD, question3AnswerD, question4AnswerB, question5AnswerD, question6AnswerA,
                        question7AnswerA, question8AnswerD, question9AnswerB, question10AnswerD);
                // Creates intent to pass data and send the Player to the Results screen when the button View Score is clicked
                Intent resultIntent = new Intent(QuizActivity.this, ResultsActivity.class);
                // Sends the player score to the Results screen
                resultIntent.putExtra("score", playerScore);
                // Sends the player name to the Results screen
                resultIntent.putExtra("name", playerName);
                startActivity(resultIntent);
            }
        });
    }

    /**
     * This method checks if the player has selected the right the answers and assigns 1 point for each.
     *
     * @param userAnswer        checks if the user has written the correct answer
     * @param question2AnswerA  checks if the user has selected the correct answer
     * @param question2AnswerB  checks if the user has selected the correct answer
     * @param question2AnswerC  checks if the user has selected the correct answer
     * @param question2AnswerD  checks if the user has selected the correct answer
     * @param question3AnswerD  checks if the user has selected the correct answer
     * @param question4AnswerB  checks if the user has selected the correct answer
     * @param question5AnswerD  checks if the user has selected the correct answer
     * @param question6AnswerA  checks if the user has selected the correct answer
     * @param question7AnswerA  checks if the user has selected the correct answer
     * @param question8AnswerD  checks if the user has selected the correct answer
     * @param question9AnswerB  checks if the user has selected the correct answer
     * @param question10AnswerD checks if the user has selected the correct answer
     * @return score;
     */
    public int checkScore(String userAnswer, boolean question2AnswerA, boolean question2AnswerB,
                          boolean question2AnswerC, boolean question2AnswerD, boolean question3AnswerD,
                          boolean question4AnswerB, boolean question5AnswerD, boolean question6AnswerA,
                          boolean question7AnswerA, boolean question8AnswerD, boolean question9AnswerB,
                          boolean question10AnswerD) {
        // Initializes the score at 0
        int score = 0;
        // Strings in the EditText to compare against
        String Red = "Red";
        String red = "red";
        // Checks if the correct answer is written in the EditText. If yes, adds 1 to the score. Otherwise does nothing.
        if (red.equalsIgnoreCase(userAnswer) || Red.equalsIgnoreCase(userAnswer)) score++;
        // Checks if the correct answer is selected. If yes, adds 1 to the score. Otherwise does nothing.
        if (question2AnswerA && question2AnswerB && question2AnswerC && !question2AnswerD) score++;
        if (question3AnswerD) score++;
        if (question4AnswerB) score++;
        if (question5AnswerD) score++;
        if (question6AnswerA) score++;
        if (question7AnswerA) score++;
        if (question8AnswerD) score++;
        if (question9AnswerB) score++;
        if (question10AnswerD) score++;
        // At the end, it returns the score
        return score;
    }
}