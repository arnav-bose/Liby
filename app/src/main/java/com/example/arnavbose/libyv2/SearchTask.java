package com.example.arnavbose.libyv2;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

/**
 * Created by arnavbose on 15-04-2016.
 */
public class SearchTask extends AsyncTask<String, DataSetSearch, Void> {

    Context contextSearch;
    Activity activity;
    RecyclerView recyclerViewSearch;
    RecyclerView.Adapter adapterSearch;
    RecyclerView.LayoutManager layoutManagerSearch;
    ArrayList<DataSetSearch> arrayList = new ArrayList<>();

    SearchTask(Context context) {
        this.contextSearch = context;
        activity = (Activity)context;
    }

    @Override
    protected void onPreExecute() {

        recyclerViewSearch = (RecyclerView)activity.findViewById(R.id.recyclerViewSearch);
        layoutManagerSearch = new LinearLayoutManager(contextSearch);
        recyclerViewSearch.setLayoutManager(layoutManagerSearch);
        recyclerViewSearch.setHasFixedSize(true);
        adapterSearch = new RecyclerViewSearchAdapter(arrayList);
        recyclerViewSearch.setAdapter(adapterSearch);


    }

    @Override
    protected Void doInBackground(String... params) {
        String retrieve_url = "http://192.168.43.164/cgi-bin/search.pl";
        String method = params[0];
        if (method.equals("Search")) {
            String titleSearch = params[1];
            try {
                URL url = new URL(retrieve_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data =
                        URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(titleSearch, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                //JSON Parsing
                JSONObject reader = new JSONObject(response);
                JSONObject biblioNumber = reader.getJSONObject(titleSearch);
                for (int i = 0; i < biblioNumber.length() - 1; i++) {
                    String jsonTitle = biblioNumber.getString("title");
                    String jsonAuthor = biblioNumber.getString("author");
                    DataSetSearch dataSetSearch = new DataSetSearch(jsonTitle, jsonAuthor);
                    Log.d("ARNAV", jsonTitle + " : " + jsonAuthor);
                    publishProgress(dataSetSearch);
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


    protected void onProgressUpdate(DataSetSearch... dataSetSearches) {
        arrayList.add(dataSetSearches[0]);
        adapterSearch.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}
