package com.apartyagi.opener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.apartyagi.tictactao.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer timer=new Timer();
         timer.schedule(new TimerTask() {
             @Override
             public void run() {
                 Intent intent=new Intent(Splash.this,Chooser.class);
                     startActivity(intent);
                     finish();
             }
         },2000);

    }
}