package com.example.android.starwarsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sets the content of the activity to use the activity_results.xml layout file
        setContentView(R.layout.activity_results);

        // Initializes the TextView where the Result will be displayed
        TextView finalResult = findViewById(R.id.results);

        // Initializes the Reset Button which allows the Player to take the Quiz again
        Button reset = findViewById(R.id.reset_quiz);

        // Initializes the Bundle which holds the data passed between Activities
        Bundle bundle = getIntent().getExtras();

        // Set a Click Listener on the Reset Button
        reset.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the reset button is clicked
            @Override
            public void onClick(View v) {
                // Sends the Player from the Results screen to the Main screen to take quiz again
                Intent resetIntent = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(resetIntent);
            }
        });

        // Gets the data stored in the bundle and sent from the other activities: Main and Quiz
        if (bundle != null) {
            // Receives the name of the player inserted on the Main screen
            String playerName = bundle.getString("name");
            // Receives the score of the player achieved on the Quiz screen
            int playerScore = bundle.getInt("score");
            // Creates a result message for a score between 0 and 3
            StringBuilder answerYoungPadawan = new StringBuilder(getString(R.string.young_padawan, playerName, playerScore));
            // Creates the results message for a score between 4 and 7
            StringBuilder answerJediKnight = new StringBuilder(getString(R.string.jedi_knight, playerName, playerScore));
            // Creates the results message for a score between 8 and 12
            StringBuilder answerJediMaster = new StringBuilder(getString(R.string.jedi_master, playerName, playerScore));
            // If the Player scored between 0 and 4
            if (playerScore >= 0 && playerScore < 4) {
                // displays the Young Padawan message
                finalResult.setText(answerYoungPadawan);
                // Otherwise, if the Player scored between 4 and 7
            } else if (playerScore >= 4 && playerScore < 8) {
                // displays the Jedi Knight message
                finalResult.setText(answerJediKnight);
                // Otherwise, if the Player scored between 8 and 12
            } else if (playerScore >= 8 && playerScore <= 12) {
                // Displays the Jedi Master message
                finalResult.setText(answerJediMaster);
            }
        }
    }
}