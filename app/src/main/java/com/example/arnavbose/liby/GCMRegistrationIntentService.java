package com.example.arnavbose.liby;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

            AppData.myData = PreferenceManager.getDefaultSharedPreferences(this);
            String borrowerNumber = AppData.myData.getString("borrowerNumber", "");
            sendRegistrationToServer(token, borrowerNumber);
            AppData.GCMRegisteration.edit().putBoolean(AppData.SENT_TOKEN_TO_SERVER, true).apply();



        } catch (Exception e) {
            Log.d("ID","Failed to retrieve");
            AppData.GCMRegisteration.edit().putBoolean(AppData.SENT_TOKEN_TO_SERVER, false).apply();
        }

        Intent registrationComplete = new Intent(AppData.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }


    private void sendRegistrationToServer(String token, String borrowerNumber) {
        String method = "Send Token";
        AsyncTaskGCM gcmTask = new AsyncTaskGCM();
        gcmTask.execute(method, token, borrowerNumber);
        Intent idSent = new Intent(AppData.SENT_TOKEN_TO_SERVER);
        LocalBroadcastManager.getInstance(this).sendBroadcast(idSent);
    }
}