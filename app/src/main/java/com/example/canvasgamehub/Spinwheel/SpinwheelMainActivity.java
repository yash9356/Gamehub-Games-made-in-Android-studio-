package com.example.canvasgamehub.Spinwheel;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anupkumarpanwar.scratchview.ScratchView;
import com.example.canvasgamehub.R;

import java.util.Random;

public class SpinwheelMainActivity extends AppCompatActivity {
    private ImageView spin;
    private TextView coinsview;
    private static int Coins =75;
    private static final String [] sectors ={"10","0","2","50","1","5","20","Jackpot","15","100","1","5"};
    private static final int [] sectorDegrees = new int[sectors.length];
    private static final int [] spin_result={0,1,3,4,5,6,7,9,10,11};
    private static final Random random =new Random();
    private int degree =0;
    private boolean isSpinning =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinwheel);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.WHITE);
        coinsview= findViewById(R.id.moneyText);
        spin =findViewById(R.id.spin_wheel2);
        getDegreeForSectores();


        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Coins>=15){
                    if (!isSpinning){
                        Coins =Coins-15;
                        coinsview.setText(Integer.toString(Coins));
                        spin_wheel();
                        isSpinning=true;

                    }
                }else
                    Toast.makeText(SpinwheelMainActivity.this, "Insufficient coins", Toast.LENGTH_SHORT).show();


            }
        });
    }


    private void spin_wheel() {

        degree =spin_result[random.nextInt(9)];
        //degree =random.nextInt(sectors.length-1);
        RotateAnimation rotateAnimation =new RotateAnimation(0,(360* sectors.length)+sectorDegrees[degree],
                RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(3600);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (degree==4){
                    Dialog dialog =new Dialog(SpinwheelMainActivity.this);
                    dialog.setContentView(R.layout.popup);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    ScratchView scratchView = dialog.findViewById(R.id.scratchview);
                    scratchView.setRevealListener(new ScratchView.IRevealListener() {
                        @Override
                        public void onRevealed(ScratchView scratchView) {

                            Toast.makeText(SpinwheelMainActivity.this, "you got design course free", Toast.LENGTH_SHORT).show();
//                            Intent intent =new Intent(MainActivity2.this,MainActivity3.class);
//                            startActivity(intent);
                        }

                        @Override
                        public void onRevealPercentChangedListener(ScratchView scratchView, float percent) {

                        }
                    });
                    dialog.show();

                }
                isSpinning =false;
                Toast.makeText(SpinwheelMainActivity.this,"You Got "+sectors[sectors.length-(degree+1)]+"Points",Toast.LENGTH_SHORT).show();
                Log.d("Original","index :"+ (sectors.length-(degree+1)));
                if ((sectors.length-(degree+1))==7){

                }else {
                    Coins =Coins+ Integer.parseInt(sectors[sectors.length-(degree+1)]);
                    coinsview.setText(Integer.toString(Coins));
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        spin.startAnimation(rotateAnimation);
    }

    private void getDegreeForSectores(){
        int sectorDegree =360/sectors.length;
//        for (int i=0;i < sectors.length ;i++){
//            sectorDegrees[i] = (i+1) * sectorDegree;
//        }
        int j;
        for ( j=0;j < 2;j++){
            sectorDegrees[j] = (j+1) * sectorDegree;
        }
        sectorDegrees[2] = 2* sectorDegree;
        for (j=3;j<8;j++){
            sectorDegrees[j] = (j+1) * sectorDegree;
        }
        sectorDegrees[8] = 8* sectorDegree;
        for (j=9;j<sectors.length;j++){
            sectorDegrees[j] = (j+1) * sectorDegree;
        }
    }

}