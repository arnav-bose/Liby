package com.example.arnavbose.liby;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class LogIn extends AppCompatActivity {

    EditText editTextusername, editTextpassword;
    String username, password;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "LoginActivity";
    public BroadcastReceiver mRegistrationBroadcastReceiver;
    private boolean isReceiverRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean sentToken = AppData.GCMRegisteration.getBoolean(AppData.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    Toast.makeText(getApplicationContext(), "Data sent to server.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Data not sent to server.", Toast.LENGTH_LONG).show();
                }
            }};

        registerReceiver();

        editTextusername = (EditText)findViewById(R.id.editTextUsername);
        editTextpassword = (EditText)findViewById(R.id.editTextPassword);
    }

    private void registerReceiver(){
        if(!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(AppData.REGISTRATION_COMPLETE));
            Log.d("Registeration", "Reciever Registered");
            isReceiverRegistered = true;
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        Log.d("Registeration", "Play Services Available");
        return true;
    }

    public void onClickLogin(View view){

        if (checkPlayServices()) {
            Intent intent = new Intent(getApplicationContext(), GCMRegistrationIntentService.class);
            startService(intent);
            Log.d("Registeration", "Registeration Intent Started");
        }
        username = editTextusername.getText().toString();
        password = editTextpassword.getText().toString();
        if(!username.equals("") & !password.equals("")){
            String method = "Login";
            LoginTask loginTask = new LoginTask(LogIn.this);
            loginTask.execute(method, username, password);
        }
        else if(username.equals("") || password.equals("")){
            Toast.makeText(this, "Please Enter All The Fields", Toast.LENGTH_SHORT).show();
        }
    }
}
