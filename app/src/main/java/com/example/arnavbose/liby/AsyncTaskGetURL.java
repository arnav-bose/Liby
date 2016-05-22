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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arnav on 04/05/2016.
 */
public class AsyncTaskGetURL extends AsyncTask <Void, Void, List<String>> {

    Context context;
    Activity activity;
    List<String> jsonPath;

    AsyncTaskGetURL(Context context){
        this.context = context;
        activity = (Activity)context;
    }

    @Override
    protected void onPreExecute() {
        jsonPath = new ArrayList<String>();
    }

    @Override
    protected List<String> doInBackground(Void... params) {
        String get_url = "http://" + AppData.SERVER_ADDRESS + "/cgi-bin/get_image_urls.pl";
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
                    jsonPath.add(finalObject.getString("path"));
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
    protected void onPostExecute(List<String> path) {
        super.onPostExecute(path);
        AsyncTaskGetNotes asyncTaskGetNotes = new AsyncTaskGetNotes(context);
        asyncTaskGetNotes.execute(path);
    }
}
