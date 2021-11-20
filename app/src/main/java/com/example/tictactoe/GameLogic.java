package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {

    private int[][] gameBoard;

    private Button playAgainButton;
    private Button homeButton;
    private TextView playerTurn;

    private TextView player1Score;
    private TextView player2Score;
    private int player1Points = 0;
    private int player2Points = 0;

    private String[] playerNames;

    private int[] winType = {-1, -1, -1}; //row, col, line type

    private int player = 1;

    //all places are 0
    GameLogic(){
        gameBoard = new int[3][3];
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                gameBoard[r][c] = 0;
    }

    //updated which player turn is now
    @SuppressLint("SetTextI18n")
    public boolean updateGameBoard(int row, int col){
        if( gameBoard[row - 1][col -1] == 0){
            gameBoard[row - 1][col -1] = player;

            if(player == 1)
                playerTurn.setText((playerNames[1]) + "'s Turn");
            else
                playerTurn.setText((playerNames[0]) + "'s Turn");
            return true;
        }

        return false;
    }

    @SuppressLint("SetTextI18n")
    public boolean winnerCheck(){
        boolean isWinner = false;

        //check horizontal line (win type = 1)
        for(int r = 0; r < 3; r++){ //check rows
            if(gameBoard[r][0] == gameBoard[r][1] &&
                    gameBoard[r][0] == gameBoard[r][2] && gameBoard[r][0] != 0){
                winType = new int[] {r, 0, 1};
                isWinner = true;
            }
        }

        // check vertical line (win type = 2)
        for(int c = 0; c < 3; c++){ // check cols
            if(gameBoard[0][c] == gameBoard[1][c] &&
                    gameBoard[2][c] == gameBoard[0][c] && gameBoard[0][c] != 0){
                winType = new int[] {0, c, 2};
                isWinner = true;
            }
        }

        // check diagonal line neg (win type = 3)
        if(gameBoard[0][0] == gameBoard[1][1] &&
                gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] != 0){
            winType = new int[] {0, 2, 3};
            isWinner = true;
        }

        // check diagonal line pos (win type = 4)
        if(gameBoard[2][0] == gameBoard[1][1] &&
                gameBoard[0][2] == gameBoard[2][0] && gameBoard[2][0] != 0){
            winType = new int[] {2, 2, 4};
            isWinner = true;
        }

        int boardFilled = 0;

        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
              if(gameBoard[r][c] != 0){
                  boardFilled +=1;
              }
            }
        }

        if(isWinner){ // case that there is a winner
            //make button visible
            playAgainButton.setVisibility(View.VISIBLE);
            homeButton.setVisibility(View.VISIBLE);
            String winnerName = playerNames[player - 1];
            if(player == 1){
                player1Points++;
                player1Score.setText(winnerName + " points: " + player1Points);
            }
            else {
                player2Points++;
                player2Score.setText(winnerName + " points: " + player2Points);
            }
            playerTurn.setText( "\uD83E\uDD73 " + winnerName + " WON!!! \uD83E\uDD73");

            return true;
        }
        else if(boardFilled == 9){ //case that no one wins
            playAgainButton.setVisibility(View.VISIBLE);
            homeButton.setVisibility(View.VISIBLE);
            playerTurn.setText("Tie Game!");
            return false;
        }

        return false;
    }

    @SuppressLint("SetTextI18n")
    public void resetGame(){
        gameBoard = new int[3][3];
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                gameBoard[r][c] = 0;

        player = 1;
        playAgainButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);
        winType = new int[]{-1, -1, -1};

        player1Score.setText(playerNames[0] + " points: " + player1Points);
        player2Score.setText(playerNames[1] + " points: " + player2Points);
        playerTurn.setText((playerNames[0] + "'s Turn"));

    }

    public void setPlayAgainButton(Button playAgainButton) {
        this.playAgainButton = playAgainButton;
    }

    public void setPlayer1Score(TextView player1Score) {
        this.player1Score = player1Score;
    }

    public void setPlayer2Score(TextView player2Score) {
        this.player2Score = player2Score;
    }

    public void setHomeButton(Button homeButton) {
        this.homeButton = homeButton;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer(){
        return player;
    }

    public int[] getWinType() {
        return winType;
    }
}
