package com.example.android.starwarsquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    // Tracks the name of the user
    String userName;
    // Tracks the answer to the EditText type question
    EditText answer1;
    // Tracks all the answers to the Checkbox type question
    CheckBox answer2a;
    CheckBox answer2b;
    CheckBox answer2c;
    CheckBox answer2d;
    // Tracks the right answers to the Radio button type questions
    RadioButton answer3;
    RadioButton answer4;
    RadioButton answer5;
    RadioButton answer6;
    RadioButton answer7;
    RadioButton answer8;
    RadioButton answer9;
    RadioButton answer10;
    // Tracks any answers to the Radio button type questions
    RadioGroup radioGroupQ3;
    RadioGroup radioGroupQ4;
    RadioGroup radioGroupQ5;
    RadioGroup radioGroupQ6;
    RadioGroup radioGroupQ7;
    RadioGroup radioGroupQ8;
    RadioGroup radioGroupQ9;
    RadioGroup radioGroupQ10;
    // Tracks the View Score button
    Button result;
    // Tracks if a question has been answered (true) or not (false)
    boolean isAnswered;

    /**
     * This method hides the soft keyboard
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
     * This method is called when clicking outside the EditText area and hides the keyboard
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
            userName = bundle.getString("name");
        }

        // Initializes the view of the answer to the EditText type question
        answer1 = findViewById(R.id.q1_a);
        // Initializes the views of the answers to the Checkbox type question
        answer2a = findViewById(R.id.q2_a1);
        answer2b = findViewById(R.id.q2_b1);
        answer2c = findViewById(R.id.q2_c1);
        answer2d = findViewById(R.id.q2_d0);
        // Initializes the views of right answers to the Radio button type questions
        answer3 = findViewById(R.id.q3_d1);
        answer4 = findViewById(R.id.q4_b1);
        answer5 = findViewById(R.id.q5_d1);
        answer6 = findViewById(R.id.q6_a1);
        answer7 = findViewById(R.id.q7_a1);
        answer8 = findViewById(R.id.q8_d1);
        answer9 = findViewById(R.id.q9_b1);
        answer10 = findViewById(R.id.q10_d1);
        // Initializes the views of the Radio groups type questions
        radioGroupQ3 = findViewById(R.id.question3_radiogroup);
        radioGroupQ4 = findViewById(R.id.question4_radiogroup);
        radioGroupQ5 = findViewById(R.id.question5_radiogroup);
        radioGroupQ6 = findViewById(R.id.question6_radiogroup);
        radioGroupQ7 = findViewById(R.id.question7_radiogroup);
        radioGroupQ8 = findViewById(R.id.question8_radiogroup);
        radioGroupQ9 = findViewById(R.id.question9_radiogroup);
        radioGroupQ10 = findViewById(R.id.question10_radiogroup);

        //Adds an event listener to the EditText field
        answer1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // If the user has answered all questions, enable View Score button
                if (checkAllAnswers()) result.setClickable(true);
//                else result.setClickable(false);
            }
        });

        // Adds an event listener to the Checkbox 1st answer
        answer2a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // If the user has answered all questions, enable View Score button
                if (checkAllAnswers()) result.setClickable(true);
//                else result.setClickable(false);
            }
        });

        // Adds an event listener to the Checkbox 2nd answer
        answer2b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // If the user has answered all questions, enable View Score button
                if (checkAllAnswers()) result.setClickable(true);
//                else result.setClickable(false);
            }
        });

        // Adds an event listener to the Checkbox 3rd answer
        answer2c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // If the user has answered all questions, enable View Score button
                if (checkAllAnswers()) result.setClickable(true);
//                else result.setClickable(false);
            }
        });

        // Adds an event listener to the Checkbox 4th answer
        answer2d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // If the user has answered all questions, enable View Score button
                if (checkAllAnswers()) result.setClickable(true);
//                else result.setClickable(false);
            }
        });

        // Adds an event listener to the Radiogroup of the question 3
        radioGroupQ3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // If the user has answered all questions, enable View Score button
                if (checkAllAnswers()) result.setClickable(true);
//                else result.setClickable(false);
            }
        });

        // Adds an event listener to the Radiogroup of the question 4
        radioGroupQ4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // If the user has answered all questions, enable View Score button
                if (checkAllAnswers()) result.setClickable(true);
//                else result.setClickable(false);
            }
        });

        // Adds an event listener to the Radiogroup of the question 5
        radioGroupQ5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // If the user has answered all questions, enable View Score button
                if (checkAllAnswers()) result.setClickable(true);
//                else result.setClickable(false);
            }
        });

        // Adds an event listener to the Radiogroup of the question 6
        radioGroupQ6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // If the user has answered all questions, enable View Score button
                if (checkAllAnswers()) result.setClickable(true);
//                else result.setClickable(false);
            }
        });

        // Adds an event listener to the Radiogroup of the question 7
        radioGroupQ7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // If the user has answered all questions, enable View Score button
                if (checkAllAnswers()) result.setClickable(true);
//                else result.setClickable(false);
            }
        });

        // Adds an event listener to the Radiogroup of the question 8
        radioGroupQ8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // If the user has answered all questions, enable View Score button
                if (checkAllAnswers()) result.setClickable(true);
//                else result.setClickable(false);
            }
        });

        // Adds an event listener to the Radiogroup of the question 9
        radioGroupQ9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // If the user has answered all questions, enable View Score button
                if (checkAllAnswers()) result.setClickable(true);
//                else result.setClickable(false);
            }
        });

        // Adds an event listener to the Radiogroup of the question 10
        radioGroupQ10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // If the user has answered all questions, enable View Score button
                if (checkAllAnswers()) result.setClickable(true);
//                else result.setClickable(false);
            }
        });

        // Initialize the View of the View Score button
        result = findViewById(R.id.view_score);
        // Set the button disabled
        result.setClickable(false);
        // Set the button clickable
//        result.setClickable(true);

        // Set a click listener on the View Score button View and calculates score
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                // If all the questions have been answered
                if (checkAllAnswers()) {
                    int score = 0;

                    // Set the button clickable
                    result.setClickable(true);

                    // Calculate the user's score
                    // Correct answers for the question 1 to compare against the user input
                    String correctAnswer = getString(R.string.answer_1);

                    // Gets the typed answer of the user
                    String userAnswer = answer1.getText().toString().trim();

                    // If the typed answer is right
                    if (correctAnswer.equalsIgnoreCase(userAnswer))
                        // Increase score by 1
                        score++;

                    // If the checked boxes are a, b, c and not d
                    if (answer2a.isChecked() && answer2b.isChecked() && answer2c.isChecked() && !answer2d.isChecked())
                        // Increase score by 1
                        score++;

                    // If the selected the right answers increase score by 1
                    if (answer3.isChecked()) score++;
                    if (answer4.isChecked()) score++;
                    if (answer5.isChecked()) score++;
                    if (answer6.isChecked()) score++;
                    if (answer7.isChecked()) score++;
                    if (answer8.isChecked()) score++;
                    if (answer9.isChecked()) score++;
                    if (answer10.isChecked()) score++;

                    // Show the user a toast message with the results
                    // Creates a result message for a score between 0 and 3
                    StringBuilder answerYoungPadawan = new StringBuilder(getString(R.string.young_padawan, userName, score));
                    // Creates the results message for a score between 4 and 7
                    StringBuilder answerJediKnight = new StringBuilder(getString(R.string.jedi_knight, userName, score));
                    // Creates the results message for a score between 8 and 10
                    StringBuilder answerJediMaster = new StringBuilder(getString(R.string.jedi_master, userName, score));

                    if (score >= 0 && score <= 3) {
                        // Toasts the Young Padawan message
                        Toast.makeText(QuizActivity.this, answerYoungPadawan, Toast.LENGTH_LONG).show();
                        // Otherwise, if the Player scored between 4 and 7
                    } else if (score >= 4 && score <= 7) {
                        // Toasts the Jedi Knight message
                        Toast.makeText(QuizActivity.this, answerJediKnight, Toast.LENGTH_LONG).show();
                        // Otherwise, if the Player scored between 8 and 10
                    } else if (score >= 8 && score <= 10) {
                        // Displays the Jedi Master message
                        Toast.makeText(QuizActivity.this, answerJediMaster, Toast.LENGTH_LONG).show();
                    }

                    // Creates intent to pass data and send the user to the Results screen when the button View Score is clicked
                    Intent resultIntent = new Intent(QuizActivity.this, ResultsActivity.class);

                    // Sends the user score to the Results screen
                    resultIntent.putExtra("score", score);

                    // Sends the user name to the Results screen
                    resultIntent.putExtra("name", userName);
                    startActivity(resultIntent);

                } else {

                    // Set the button not clickable
                    result.setClickable(false);
                    // Display a message that asks the user to answer all questions
                    StringBuilder warning = new StringBuilder(getString(R.string.warning_message, userName));
                    // Show Toast message asking the user to answer all questions
                    Toast.makeText(QuizActivity.this, warning, Toast.LENGTH_LONG).show();
                }
                }
        });
    }

    /**
     * This method checks if the user has answered all questions in order to activate the
     * View Score button, calculate score and show it to the user
     *
     * @return isAnswered - tells the events listener if the questions were answered or not
     */
    private boolean checkAllAnswers() {
        // Check Question 1
        String userAnswer = answer1.getText().toString();
        this.isAnswered = userAnswer.length() != 0;
        // Check Question 2
        this.isAnswered = isAnswered && (answer2a.isChecked() || answer2b.isChecked() || answer2c.isChecked() || answer2d.isChecked());
        // Check Question 3
        this.isAnswered = isAnswered && radioGroupQ3.getCheckedRadioButtonId() != -1;
        // Check Question 4
        this.isAnswered = isAnswered && radioGroupQ4.getCheckedRadioButtonId() != -1;
        // Check Question 5
        this.isAnswered = isAnswered && radioGroupQ5.getCheckedRadioButtonId() != -1;
        // Check Question 6
        this.isAnswered = isAnswered && radioGroupQ6.getCheckedRadioButtonId() != -1;
        // Check Question 7
        this.isAnswered = isAnswered && radioGroupQ7.getCheckedRadioButtonId() != -1;
        // Check Question 8
        this.isAnswered = isAnswered && radioGroupQ8.getCheckedRadioButtonId() != -1;
        // Check Question 9
        this.isAnswered = isAnswered && radioGroupQ9.getCheckedRadioButtonId() != -1;
        // Check Question 10
        this.isAnswered = isAnswered && radioGroupQ10.getCheckedRadioButtonId() != -1;
        return isAnswered;
    }
}