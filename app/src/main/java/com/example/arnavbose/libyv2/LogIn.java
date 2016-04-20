package com.example.arnavbose.libyv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    EditText editTextusername, editTextpassword;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editTextusername = (EditText)findViewById(R.id.editTextUsername);
        editTextpassword = (EditText)findViewById(R.id.editTextPassword);
    }

    public void onClickLogin(View view){
        username = editTextusername.getText().toString();
        password = editTextpassword.getText().toString();
        if(!username.equals("") & !password.equals("")){
            String method = "Login";
            LoginTask loginTask = new LoginTask(LogIn.this);
            loginTask.execute(method, username, password);
            finish();
        }
        else if(username.equals("") || password.equals("")){
            Toast.makeText(this, "Please Enter All The Fields", Toast.LENGTH_SHORT).show();
        }
    }
}
