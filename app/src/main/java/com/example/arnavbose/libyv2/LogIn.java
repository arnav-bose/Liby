package com.example.arnavbose.libyv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void onClickLogin(View view){
        Intent i = new Intent(LogIn.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
