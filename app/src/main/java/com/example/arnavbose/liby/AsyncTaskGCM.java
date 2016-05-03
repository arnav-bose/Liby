package com.example.arnavbose.liby;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Arnav on 26/04/2016.
 */
public class AsyncTaskGCM extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {

        //String search_url = "http://192.168.99.1/cgi-bin/gcm_register.pl"; //TODO: Add PHP(Write) URL here.
        //String search_url = "http://172.19.21.93/cgi-bin/koha/gcm_register.pl"; //TODO: Add PHP(Write) URL here.
        String gcm_url = "http://10.0.2.2/cgi-bin/gcm_register.pl";
        String method = params[0];
        if (method.equals("Send Token")) {
            String token = params[1];
            String borrowerNumber = params[2];

            try {
                URL url = new URL(gcm_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data =
                        URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8") + "&" +
                        URLEncoder.encode("borrowernumber", "UTF-8") + "=" + URLEncoder.encode(borrowerNumber, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
