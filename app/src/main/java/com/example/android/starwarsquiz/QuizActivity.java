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

    // Tracks the name of the player
    String playerName;

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

        // Tracks the correct answers to the 2nd question
        final CheckBox answer2a = findViewById(R.id.q2_a1);
        final CheckBox answer2b = findViewById(R.id.q2_b1);
        final CheckBox answer2c = findViewById(R.id.q2_c1);
        // Tracks the incorrect answer to the 2nd question
        final CheckBox answer2d = findViewById(R.id.q2_d0);
        // Tracks the right answers to the questions with a single correct answer
        final EditText answer1 = findViewById(R.id.q1_a);
        final RadioButton answer3 = findViewById(R.id.q3_d1);
        final RadioButton answer4 = findViewById(R.id.q4_b1);
        final RadioButton answer5 = findViewById(R.id.q5_d1);
        final RadioButton answer6 = findViewById(R.id.q6_a1);
        final RadioButton answer7 = findViewById(R.id.q7_a1);
        final RadioButton answer8 = findViewById(R.id.q8_d1);
        final RadioButton answer9 = findViewById(R.id.q9_b1);
        final RadioButton answer10 = findViewById(R.id.q10_d1);

        // Initializes the View Score button View
        Button result = findViewById(R.id.view_score);
        // Set a click listener on the View Score button View
        result.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the View Score View is clicked
            @Override
            public void onClick(View v) {
                // Tracks the score of the player
                int score = 0;

                // Gets the user input
                String userAnswer = answer1.getText().toString();

                // Strings in the EditText to compare against the user input
                String Red = "Red";
                String red = "red";

                // Checks if the user has introduced and/or checked the correct answers
                if (red.equalsIgnoreCase(userAnswer) || Red.equalsIgnoreCase(userAnswer)) score++;
                if (answer2a.isChecked() && answer2b.isChecked() && answer2c.isChecked() && !answer2d.isChecked())
                    score++;
                if (answer3.isChecked()) score++;
                if (answer4.isChecked()) score++;
                if (answer5.isChecked()) score++;
                if (answer6.isChecked()) score++;
                if (answer7.isChecked()) score++;
                if (answer8.isChecked()) score++;
                if (answer9.isChecked()) score++;
                if (answer10.isChecked()) score++;

                // Creates intent to pass data and send the Player to the Results screen when the button View Score is clicked
                Intent resultIntent = new Intent(QuizActivity.this, ResultsActivity.class);
                // Sends the player score to the Results screen
                resultIntent.putExtra("score", score);
                // Sends the player name to the Results screen
                resultIntent.putExtra("name", playerName);
                startActivity(resultIntent);
            }
        });
    }
}