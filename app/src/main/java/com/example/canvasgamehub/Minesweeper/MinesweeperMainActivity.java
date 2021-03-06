package com.example.canvasgamehub.Minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canvasgamehub.Minesweeper.model.MinesweeperModel;
import com.example.canvasgamehub.R;
import com.google.android.material.snackbar.Snackbar;

public class MinesweeperMainActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    Vibrator vibrator;
    public int point1=0;
    public boolean player1=false,player2=false;
    public int Totflags,amine;
    private TextView numflag,points,High_Score;
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.snackbar);

        ImageButton easyplay = (ImageButton) findViewById(R.id.btnRestart);
        ImageButton hardplay = (ImageButton) findViewById(R.id.HardPlay);
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        Totflags=MinesweeperModel.getInstance().countMines();
        numflag=findViewById(R.id.numflag);
        numflag.setText(Integer.toString(Totflags));
        points=findViewById(R.id.points);
        High_Score=findViewById(R.id.highscore);

        chronometer=findViewById(R.id.chrono);
        chronometer.setBase(SystemClock.elapsedRealtime());
//        chronometer.setCountDown(false);
        chronometer.start();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                amine=MinesweeperModel.getInstance().Points();
                Totflags=MinesweeperModel.getInstance().countMines();
                point1=(Totflags-amine)*10;
                points.setText("Points:"+Integer.toString(point1));
                numflag.setText(Integer.toString(amine));
            }
        });

        SharedPreferences settings=getSharedPreferences("Quiz_DATA", Context.MODE_PRIVATE);
        int highscore2=settings.getInt("HIGH_SCORE",0);
        if(point1>highscore2){
            High_Score.setText("High Score:"+Integer.toString(point1));
            SharedPreferences.Editor editor=settings.edit();
            editor.putInt("HIGH_SCORE",point1);
            editor.commit();
        }
        else {
            High_Score.setText("High Score:"+Integer.toString(highscore2));
        }




        easyplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings=getSharedPreferences("Quiz_DATA", Context.MODE_PRIVATE);
                amine=MinesweeperModel.getInstance().Points();
                Totflags=MinesweeperModel.getInstance().countMines();
                point1=(Totflags-amine)*10;
                int highscore2=settings.getInt("HIGH_SCORE",0);
                if(point1>highscore2){
                    High_Score.setText("High Score:"+Integer.toString(point1));
                    SharedPreferences.Editor editor=settings.edit();
                    editor.putInt("HIGH_SCORE",point1);
                    editor.commit();
                }
                else {
                    High_Score.setText("High Score:"+Integer.toString(highscore2));
                }

                MinesweeperModel.getInstance().cleanBoard();
                MinesweeperModel.getInstance().setMines(1);
                MinesweeperModel.getInstance().setMineCount();
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                point1=0;
                points.setText("Points:"+Integer.toString(point1));
                amine=MinesweeperModel.getInstance().Points();
                numflag.setText(Integer.toString(amine));
                Totflags=MinesweeperModel.getInstance().countMines();
                player1=true;
                player2=false;

                Snackbar restartSnackbar = Snackbar.make(linearLayout, "Easy Mode", Snackbar.LENGTH_LONG);
                restartSnackbar.show();
                Toast.makeText(MinesweeperMainActivity.this,"Tap on Middle of Grid to Start",Toast.LENGTH_SHORT).show();
            }
        });

        hardplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings=getSharedPreferences("Quiz_DATA", Context.MODE_PRIVATE);
                amine=MinesweeperModel.getInstance().Points();
                Totflags=MinesweeperModel.getInstance().countMines();
                point1=(Totflags-amine)*10;
                int highscore2=settings.getInt("HIGH_SCORE",0);
                if(point1>highscore2){
                    High_Score.setText("High Score:"+Integer.toString(point1));
                    SharedPreferences.Editor editor=settings.edit();
                    editor.putInt("HIGH_SCORE",point1);
                    editor.commit();
                }
                else {
                    High_Score.setText("High Score:"+Integer.toString(highscore2));
                }

                MinesweeperModel.getInstance().cleanBoard();
                MinesweeperModel.getInstance().setMines(2);
                MinesweeperModel.getInstance().setMineCount();
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                point1=0;
                points.setText("Points:"+Integer.toString(point1));
                amine=MinesweeperModel.getInstance().Points();
                numflag.setText(Integer.toString(amine));
                Totflags=MinesweeperModel.getInstance().countMines();
                player2=true;
                player1=false;


                Snackbar restartSnackbar = Snackbar.make(linearLayout, "Hard Mode", Snackbar.LENGTH_LONG);
                restartSnackbar.show();
                Toast.makeText(MinesweeperMainActivity.this,"Tap on Middle of Grid to Start",Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton btnFlag = (ImageButton) findViewById(R.id.btnFlag);
        btnFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Function that sets touches to place flags

                MinesweeperModel.getInstance().actionFlag();


                Snackbar flagSnackbar = Snackbar.make(linearLayout, "Flagging on", Snackbar.LENGTH_LONG);
                flagSnackbar.show();
            }
        });

        ImageButton btnReveal = (ImageButton) findViewById(R.id.btnReveal);
        btnReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Function that sets touches to open tiles
                MinesweeperModel.getInstance().actionReveal();

                Snackbar revealSnackbar = Snackbar.make(linearLayout, "Flagging off", Snackbar.LENGTH_LONG);
                revealSnackbar.show();
            }
        });
    }
    public void showSnackBarWithDelete(String msg) {
        SharedPreferences settings=getSharedPreferences("Quiz_DATA", Context.MODE_PRIVATE);
        amine=MinesweeperModel.getInstance().Points();
        Totflags=MinesweeperModel.getInstance().countMines();
        point1=(Totflags-amine)*10;
        int highscore2=settings.getInt("HIGH_SCORE",0);
        if(point1>highscore2){
            High_Score.setText("High Score:"+Integer.toString(point1));
            SharedPreferences.Editor editor=settings.edit();
            editor.putInt("HIGH_SCORE",point1);
            editor.commit();
        }
        else {
            High_Score.setText("High Score:"+Integer.toString(highscore2));
        }
        Snackbar.make(linearLayout, msg,
                Snackbar.LENGTH_LONG).setAction(
                "Restart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Restart the game
                        MinesweeperModel.getInstance().cleanBoard();
                        if(!player2){
                            MinesweeperModel.getInstance().setMines(1);
                        }
                        else if(!player1){
                            MinesweeperModel.getInstance().setMines(2);
                        }

                        MinesweeperModel.getInstance().setMineCount();
                    }
                }
        ).show();
        if(MinesweeperModel.getInstance().gameLost()){
            vibrator.vibrate(1000);
            point1=0;
            numflag.setText(Integer.toString(point1));
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            amine=MinesweeperModel.getInstance().Points();
            point1=(Totflags-amine)*10;
            points.setText(Integer.toString(point1));
            numflag.setText(Integer.toString(amine));
            Toast.makeText(MinesweeperMainActivity.this,"Tap on Middle of Grid to ReStart ",Toast.LENGTH_SHORT).show();
        }
    }
}