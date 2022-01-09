package com.example.canvasgamehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.canvasgamehub.Minesweeper.MinesweeperMainActivity;
import com.example.canvasgamehub.Spinwheel.SpinwheelMainActivity;

public class MainActivity extends AppCompatActivity {
    private CardView spinwheel_apk,Minesweeper_apk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.WHITE);
        setContentView(R.layout.activity_main);
        spinwheel_apk=findViewById(R.id.spinwheel_apk);
        Minesweeper_apk=findViewById(R.id.MinesweeperApk);
        spinwheel_apk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SpinwheelMainActivity.class);
                startActivity(intent);
            }
        });
        Minesweeper_apk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, MinesweeperMainActivity.class);
                startActivity(intent);
            }
        });
    }
}