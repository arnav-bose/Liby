package com.example.arnavbose.liby;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Arnav on 04/05/2016.
 */
public class AsyncTaskGetURL extends AsyncTask <Void, Void, String[]> {

    Context context;
    Activity activity;
    String[] jsonPath;

    AsyncTaskGetURL(Context context){
        this.context = context;
        activity = (Activity)context;
    }

    @Override
    protected void onPreExecute() {
        jsonPath = new String[10];
    }

    @Override
    protected String[] doInBackground(Void... params) {
        //String get_url = "http://192.168.99.1/cgi-bin/get_image_urls.pl"; //10.0.2.2 for Emulator and 192.168.43.140 for Micromax
        //String search_url = "http://172.19.17.58/cgi-bin/koha/get_image_urls.pl"; //TODO: Add PHP(Write) URL here.
        String get_url = "http://10.0.2.2/cgi-bin/get_image_urls.pl";
            try {
                URL url = new URL(get_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();

                inputStream.close();
                httpURLConnection.disconnect();

                //JSON Parsing
                JSONObject parentObject = new JSONObject(response);
                JSONArray parentArray = parentObject.getJSONArray("Urls");
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    jsonPath[i] = finalObject.getString("path");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return jsonPath;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String[] path) {
        super.onPostExecute(path);
        AsyncTaskGetNotes asyncTaskGetNotes = new AsyncTaskGetNotes(context);
        asyncTaskGetNotes.execute("http://10.0.2.2/pictures/my_photo.jpg");

    }
}
