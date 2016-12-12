package com.example.yusuf.game;

import android.graphics.Canvas;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GameManager extends AppCompatActivity {

    RelativeLayout layout; // XML Layout which lays the board and the game panel
    Button btnDice; //Android Button
    Board gameBoard;    //Instance of Board that will be displayed
    Dice dice;  // Instance of Dice object
    int roll = 0;
    boolean turn = false; // Boolean variable which indicates whose turn it is to move
    Player player1, player2; //Playes intialised
    int type = 1; // Type of game where 1 is single player with computer and 2 is multiplayer vs another person


    @Override
    // This is like the constructor of the class and is called when the Activity is started
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialises the dice, players and game board according to the parameters passed from the previous Activity where user defines his character properties
        dice = new Dice();
        player1 = new Player("Yusuf", true, this);
        player2 = new Player("Haya", false, this);

        // Toast allows system to display message to user and is an Android feature
        final Toast toast = Toast.makeText(this, "Computer's Turn", Toast.LENGTH_SHORT);

        // Game Board is intialised
        gameBoard = new Board(this, player1, player2, 1);

        // Layout is set
        layout = (RelativeLayout) View.inflate(this, R.layout.panel, null);
        layout.addView( gameBoard);
        setContentView(layout);

        btnDice = (Button)findViewById(R.id.btnDice);

        //OnClickListener of the button is defined in the onCreate method which acts as a constructor fot this class
        btnDice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // On the click of the dice button a random number from 1 to 6 is returned by the dice objec tand the system checks which player to move
                if (type == 2){
                    roll = dice.getNum();
                    turn = !turn;
                    if (turn){
                        gameBoard.getPlayer1().updatePos(roll);
                        Log.d("diceNum", roll +"");
                    }
                    else{
                        gameBoard.getPlayer2().updatePos(roll);
                    }

                    checkPosition();
                }

                else{

                    roll = dice.getNum();
                    turn = !turn;
                    gameBoard.getPlayer1().updatePos(roll);
                    Log.d("diceNum", roll +"");
                    checkPosition();
                    btnDice.setEnabled(false);
                    gameBoard.invalidate();

                    toast.show();
                    Runnable r = new Runnable() {
                        @Override
                        public void run(){
                            roll = dice.getNum();
                            turn = !turn;
                            gameBoard.getPlayer2().updatePos(roll);
                            checkPosition();
                            gameBoard.invalidate();
                            btnDice.setEnabled(true);
                        }
                    };

                    Handler h = new Handler();
                    h.postDelayed(r, 2000);

                }



            }
        });

    }

    // This method checks the position fo the players and updates them if necessary
    public void checkPosition(){
        if (gameBoard.getPlayer1().getBlock() == gameBoard.getPlayer2().getBlock()){
            if(turn){
                gameBoard.getPlayer2().updatePos(-3);
            }
            else{
                gameBoard.getPlayer1().updatePos(-3);
            }
        }
    }

    /**
     * More Methods shall be defined which I havnt created yet but you should write about them in the report
     *
     * Method: public void checkBlock ----> Checks if there is an object on the block or if there is a Snake or a Ladder on the blokc that the player has just landed on
     * Method: public void pickItem ------> Updates the player's item inventory if the player has landed on a block with an item
     * Method: public void useItem ------> Empties the player's item inventory when the player uses the item.
     * Method: public void checkWinner ----> Checks if there is a winner of the game yet and is called wheneve the player's final position is set after the dice is rolled
     *
     */




}
