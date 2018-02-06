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
import android.widget.EditText;

/**
 * This app displays a quiz about the Star Wars movies
 * It calculates and reports the final score of the player
 * It resets and allows for a new game
 */
public class MainActivity extends AppCompatActivity {

    // Tracks the EditText where the Player writes their name
    EditText name;

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
        //Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Initializes the name view
        name = findViewById(R.id.name_text_view);

        // Initializes the Button view to Start the Quiz
        Button start = findViewById(R.id.start_quiz);

        // Set a click listener on that View
        start.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the button View is clicked
            @Override
            public void onClick(View v) {
                // Creates intent to pass data and send Player to the Quiz screen
                Intent startIntent = new Intent(MainActivity.this, QuizActivity.class);
                // Sends the Player name to the Quiz screen
                startIntent.putExtra("name", name.getText().toString());
                startActivity(startIntent);
            }
        });
    }
}

