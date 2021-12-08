package com.example.tic_tac_toe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 = player with x, 1 = player with y
    int activePlayer = 0;
    char player = 'x';
    char nextPlayer = 'x';
    boolean gameActive = true;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public void dropIn(View view) {
        ImageView img = (ImageView) view;
        int tag = Integer.parseInt(img.getTag().toString());
        TextView winnerText = (TextView) findViewById(R.id.winner);

        if (gameState[tag] == 2 && gameActive) {
            img.setTranslationY(-2000);
            gameState[tag] = activePlayer;

            if (activePlayer == 0) {
                player = 'x';
                nextPlayer = 'o';
            } else {
                player = 'o';
                nextPlayer = 'x';
            }
            winnerText.setText("Next move is for "+ nextPlayer);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
            }

            img.animate().translationY(0).rotation(3600).setDuration(500);

            for (int[] position : winningPositions) {
                if (gameState[position[0]] == gameState[position[1]] && gameState[position[1]] == gameState[position[2]] && gameState[position[1]] != 2) {
                    gameActive = false;
                    winnerText.setText(player + " is the winner");
                    Toast.makeText(this, player + " is the winner", Toast.LENGTH_LONG).show();

                    Button playAgainBtn = (Button) findViewById(R.id.playAgainBtn);

                    playAgainBtn.setVisibility(View.VISIBLE);

                    if(player == 'x'){
                        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
                        grid.setBackgroundResource(R.drawable.board_red);
                        playAgainBtn.setBackgroundColor(getResources().getColor(R.color.red));
                        winnerText.setTextColor(getResources().getColor(R.color.red));


                        if (Build.VERSION.SDK_INT >= 21) {
                            Window window = this.getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                            window.setStatusBarColor(this.getResources().getColor(R.color.red));
                        }
                        ActionBar actionBar;
                        actionBar = getSupportActionBar();
                        ColorDrawable colorDrawable
                                = new ColorDrawable(Color.parseColor("#ff0000"));
                        actionBar.setBackgroundDrawable(colorDrawable);
                    }
                    else{
                        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
                        grid.setBackgroundResource(R.drawable.board_blue);
                        playAgainBtn.setBackgroundColor(getResources().getColor(R.color.blue));
                        winnerText.setTextColor(getResources().getColor(R.color.blue));
                        if (Build.VERSION.SDK_INT >= 21) {
                            Window window = this.getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                            window.setStatusBarColor(this.getResources().getColor(R.color.blue));
                        }
                        ActionBar actionBar;
                        actionBar = getSupportActionBar();
                        ColorDrawable colorDrawable
                                = new ColorDrawable(Color.parseColor("#0071bc"));
                        actionBar.setBackgroundDrawable(colorDrawable);
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
        grid.setBackgroundResource(R.drawable.board);
        for(int i = 0; i < grid.getChildCount(); i++){
            ImageView img =(ImageView) grid.getChildAt(i);
            img.setImageResource(0);
        }
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FF000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        gameState = new int[]{2,2,2,2,2,2,2,2,2};

        TextView winnerText = (TextView) findViewById(R.id.winner);
        winnerText.setText("The first move is for X");
        winnerText.setTextColor(getResources().getColor(R.color.black));

        gameActive = true;

        activePlayer = 0;

        Button playAgainBtn = (Button) findViewById(R.id.playAgainBtn);
        playAgainBtn.setVisibility(View.INVISIBLE);
        playAgainBtn.setBackgroundColor(getResources().getColor(R.color.black));

    }
        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            if (Build.VERSION.SDK_INT >= 21) {
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.black));
            }

            ActionBar actionBar;
            actionBar = getSupportActionBar();

            // Define ColorDrawable object and parse color
            // using parseColor method
            // with color hash code as its parameter
            ColorDrawable colorDrawable
                    = new ColorDrawable(Color.parseColor("#FF000000"));

            // Set BackgroundDrawable
            actionBar.setBackgroundDrawable(colorDrawable);
        }

}