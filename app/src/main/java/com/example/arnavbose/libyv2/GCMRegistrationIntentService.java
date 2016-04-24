package com.example.arnavbose.libyv2;

import android.app.IntentService;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by Pradumn K Mahanta on 24-04-2016.
 **/

public class GCMRegistrationIntentService extends IntentService {

    private static final String TAG = "RegisterationIntentService";

    public GCMRegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        AppData.GCMRegisteration = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            InstanceID instanceID = InstanceID.getInstance(this);

            String token = instanceID.getToken("186506294213", GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            Log.d("ID", token);

            //TODO: Change the function according to your need
            //sendRegistrationToServer(token, rUserName);

            AppData.GCMRegisteration.edit().putBoolean(AppData.SENT_TOKEN_TO_SERVER, true).apply();

        } catch (Exception e) {
            Log.d("ID","Failed to retrieve");
            AppData.GCMRegisteration.edit().putBoolean(AppData.SENT_TOKEN_TO_SERVER, false).apply();
        }

        Intent registrationComplete = new Intent(AppData.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }


    private void sendRegistrationToServer(String token, String rUserName) {
       //TODO: Connect to database here.
    }
}