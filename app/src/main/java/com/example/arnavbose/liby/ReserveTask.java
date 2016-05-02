package com.example.arnavbose.liby;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
 * Created by Arnav on 29/04/2016.
 */
public class ReserveTask extends AsyncTask<String, Void, Void> {

    Context contextReserve;
    Activity activityReserve;

    public ReserveTask(Context contextReserve){
        this.contextReserve = contextReserve;
        activityReserve = (Activity)contextReserve;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params) {

        String book_details_url = "http://10.0.2.2/cgi-bin/reserve.pl"; //10.0.2.2 for Emulator and 192.168.43.140 for Micromax
        String method = params[0];
        if (method.equals("Reserve")) {
            String borrowerNumber = params[1];
            String biblionumber = params[2];
            try {
                URL url = new URL(book_details_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data =
                        URLEncoder.encode("borrowernumber", "UTF-8") + "=" + URLEncoder.encode(borrowerNumber, "UTF-8") + "&" +
                        URLEncoder.encode("biblionumber", "UTF-8") + "=" + URLEncoder.encode(biblionumber, "UTF-8");

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

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
