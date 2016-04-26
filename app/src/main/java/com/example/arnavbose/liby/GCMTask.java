package com.example.arnavbose.liby;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Arnav on 26/04/2016.
 */
public class GCMTask extends AsyncTask<String, Void, Void> {

    Context contextGCMTask;
    Activity activityGCMTask;

    GCMTask(Context context){
        this.contextGCMTask = context;
        activityGCMTask = (Activity)context;
    }

    @Override
    protected Void doInBackground(String... params) {

        String search_url = "http://10.0.2.2/cgi-bin/"; //TODO: Add PHP(Write) URL here.
        String method = params[0];
        if (method.equals("Send Token")) {
            String token = params[1];
            String name = params[2];
            Log.d("FALCON", "!!THE NAME!! " + name);
            try {
                URL url = new URL(search_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data =
                        URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8");//TODO: Add borrowernumber

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
