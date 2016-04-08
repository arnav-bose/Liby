package com.example.arnavbose.libyv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread splashThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                    Intent i = new Intent(SplashScreen.this, LogIn.class);
                    startActivity(i);
                    finish();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        splashThread.start();
    }
}
