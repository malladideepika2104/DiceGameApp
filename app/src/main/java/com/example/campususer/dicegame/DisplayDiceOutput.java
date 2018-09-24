package com.example.campususer.dicegame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class DisplayDiceOutput extends AppCompatActivity {

    //Documentation source: https://developer.android.com/training/basics/firstapp/starting-activity
    private ImageView systemImageView;
    private Random random = new Random();

    @Override
    @SuppressLint({"DefaultLocale"})
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_dice_output);

        TextView textViewInitial = findViewById(R.id.textView);
        TextView textViewFinal = findViewById(R.id.textView2);
        systemImageView = findViewById(R.id.system);

        Intent intent = getIntent();
        String userInputSelection = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        textViewInitial.setText(R.string.displaydiceoutput_textview);
        int number = setAndGetDiceImage();
        sleepForAWhile();
        textViewInitial.setText(String.format("Computer selection is \n %d",number));
        int finalScore = getFinalUserScore(userInputSelection,number);
        textViewFinal.setVisibility(View.VISIBLE);
        textViewFinal.setText(String.format("Your score is %d",finalScore));
    }

    public void sleepForAWhile() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //modify value
                //do something with new value
                handler.postDelayed(this, 5000);
            }
        }, 10000);

    }
    public int setAndGetDiceImage() {
        int number = random.nextInt(6)+1;
        systemImageView.setVisibility(View.VISIBLE);
        switch (number) {
            case 1:
                systemImageView.setImageResource(R.drawable.dice_one);
                break;
            case 2:
                systemImageView.setImageResource(R.drawable.dice_two);
                break;
            case 3:
                systemImageView.setImageResource(R.drawable.dice_three);
                break;
            case 4:
                systemImageView.setImageResource(R.drawable.dice_four);
                break;
            case 5:
                systemImageView.setImageResource(R.drawable.dice_five);
                break;
            case 6:
                systemImageView.setImageResource(R.drawable.dice_six);
                break;
        }

        return number;
    }

    public int getFinalUserScore(String userInputScoreString, int computerInputScore) {
        int userInputScoreInt = Integer.parseInt(userInputScoreString);
        return (userInputScoreInt + computerInputScore) % 6;
    }
}
