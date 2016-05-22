package com.example.arnavbose.liby;

import android.app.Activity;
import android.content.Context;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
import java.util.ArrayList;

import android.view.View;
import android.widget.Toast;



/**
 * Created by arnavbose on 15-04-2016.
 */
public class AsyncTaskHome extends AsyncTask<String, DataSetHome, Void> {

    Context contextHome;
    Activity activityHome;
    RecyclerView recyclerViewHome;
    RecyclerView.Adapter adapterHome;
    RecyclerView.LayoutManager layoutManagerHome;
    ArrayList<DataSetHome> arrayListHome = new ArrayList<>();

    AsyncTaskHome(Context contextHome, RecyclerView recyclerView) {
        this.contextHome = contextHome;
        activityHome = (Activity)contextHome;
        this.recyclerViewHome = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        //try {
            //recyclerViewHome = (RecyclerView) activityHome.findViewById(R.id.recyclerViewHome);
            layoutManagerHome = new LinearLayoutManager(contextHome);
            recyclerViewHome.setLayoutManager(layoutManagerHome);
            recyclerViewHome.setHasFixedSize(true);
            adapterHome = new RecyclerViewHomeAdapter(arrayListHome);
            recyclerViewHome.setAdapter(adapterHome);
        //}catch(NullPointerException e){
        //    Toast.makeText(contextHome, "YOLO", Toast.LENGTH_SHORT).show();
        //}
    }

    @Override
    protected Void doInBackground(String... params) {
        String new_arrivals_url = "http://" + AppData.SERVER_ADDRESS + "/cgi-bin/home.pl";
        String method = params[0];
        if (method.equals("Home")) {
            try {
                URL url = new URL(new_arrivals_url);
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
                JSONArray parentArray = parentObject.getJSONArray("Books");
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    String jsonTitle = finalObject.getString("title");
                    String jsonAuthor = finalObject.getString("author");
                    String jsonBiblioNumber = finalObject.getString("biblionumber");

                    DataSetHome dataSetHome = new DataSetHome(jsonTitle, jsonAuthor, "Available: 4",jsonBiblioNumber);
                    Log.d("FALCON", jsonBiblioNumber + " : " + jsonTitle + " : " + jsonAuthor);
                    publishProgress(dataSetHome);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    protected void onProgressUpdate(DataSetHome... dataSetHomes) {
        try {
            arrayListHome.add(dataSetHomes[0]);
            adapterHome.notifyDataSetChanged();
        }catch(NullPointerException e){

        }
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}
