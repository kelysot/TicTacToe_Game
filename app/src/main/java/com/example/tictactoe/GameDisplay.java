package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDisplay extends AppCompatActivity {

    private  TicTacToeBoard ticTacToeBoard;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_display);

        Button playAgainButton = findViewById(R.id.play_again_button);
        Button homeButton = findViewById(R.id.home_button);
        TextView playerTurn = findViewById(R.id.player_turn);

        TextView player1Score = findViewById(R.id.player1_score);
        TextView player2Score = findViewById(R.id.player2_score);;

        //so this buttons will show only when we have winner
        playAgainButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);

        String[] playersNames = getIntent().getStringArrayExtra("PLAYER_NAMES");

        //check if there were names
        if(playersNames[0].length() != 0 && playersNames[1].length() != 0){ //both names exist
           // playerTurn.setText(playersNames[0] + "'s Turn");
            player1Score.setText(playersNames[0] + " points: 0");
            player2Score.setText(playersNames[1] + " points: 0");
        }
        else if(playersNames[0].length() != 0){ // only first name exist
            player1Score.setText(playersNames[0] + " points: 0");
            player2Score.setText("Player 2 points: 0");
            playersNames[1] = "Player 2";
        }
        else if(playersNames[1].length() != 0){ // only second name exist
            player2Score.setText(playersNames[1] + " points: 0");
            player1Score.setText("Player 1 points: 0");
            playersNames[0] = "Player 1";
        }
        else {//no name exist
            player1Score.setText("Player 1 points: 0");
            player2Score.setText("Player 2 points: 0");
            playersNames[0] = "Player 1";
            playersNames[1] = "Player 2";
        }

        playerTurn.setText(playersNames[0] + "'s Turn");

        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);

        ticTacToeBoard.setUpGame(playAgainButton, homeButton, playerTurn, playersNames, player1Score, player2Score);

    }

    public void playAgainButtonClick(View view){
        ticTacToeBoard.resetGame();
        ticTacToeBoard.invalidate();
    }

    public void homeButtonClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}