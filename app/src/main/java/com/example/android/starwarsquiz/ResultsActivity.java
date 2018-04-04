package com.example.android.starwarsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    int playerScore;
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sets the content of the activity to use the activity_results.xml layout file
        setContentView(R.layout.activity_results);

        // Initializes the TextView where the Result will be displayed
        TextView finalResult = findViewById(R.id.results);

        // Initializes the Reset Button which allows the Player to take the Quiz again
        Button reset = findViewById(R.id.reset_quiz);

        // Initializes the Share Button which allows the Player to share their results
        Button share = findViewById(R.id.share_result);

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

        // Set a Click Listener on the Share Button
        share.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, (getString(R.string.share_message, playerScore)));
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_result, playerName, playerScore)));
            }
        });

        // Gets the data stored in the bundle and sent from the other activities: Main and Quiz
        if (bundle != null) {
            // Receives the name of the player inserted on the Main screen
            playerName = bundle.getString("name");
            // Receives the score of the player achieved on the Quiz screen
            playerScore = bundle.getInt("score");
            // Creates a result message for a score between 0 and 3
            StringBuilder answerYoungPadawan = new StringBuilder(getString(R.string.young_padawan, playerName, playerScore));
            // Creates the results message for a score between 4 and 7
            StringBuilder answerJediKnight = new StringBuilder(getString(R.string.jedi_knight, playerName, playerScore));
            // Creates the results message for a score between 8 and 10
            StringBuilder answerJediMaster = new StringBuilder(getString(R.string.jedi_master, playerName, playerScore));
            // If the Player scored between 0 and 3
            if (playerScore >= 0 && playerScore <= 3) {
                // displays the Young Padawan message
                finalResult.setText(answerYoungPadawan);
                // Otherwise, if the Player scored between 4 and 7
            } else if (playerScore >= 4 && playerScore <= 7) {
                // displays the Jedi Knight message
                finalResult.setText(answerJediKnight);
                // Otherwise, if the Player scored between 8 and 10
            } else if (playerScore >= 8 && playerScore <= 10) {
                // Displays the Jedi Master message
                finalResult.setText(answerJediMaster);
            }
        }
    }
}