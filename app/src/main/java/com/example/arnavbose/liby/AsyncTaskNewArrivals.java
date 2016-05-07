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

import android.widget.Toast;

import com.example.arnavbose.liby.DataSetSearch;
import com.example.arnavbose.liby.R;
import com.example.arnavbose.liby.RecyclerViewSearchAdapter;


/**
 * Created by arnavbose on 15-04-2016.
 */
public class AsyncTaskNewArrivals extends AsyncTask<String, DataSetSearch, Void> {

    Context contextSearch;
    Activity activity;
    RecyclerView recyclerViewSearch;
    RecyclerView.Adapter adapterSearch;
    RecyclerView.LayoutManager layoutManagerSearch;
    ArrayList<DataSetSearch> arrayList = new ArrayList<>();
    Bundle bundleBookDetails;

    AsyncTaskNewArrivals(Context context) {
        this.contextSearch = context;
        activity = (Activity) context;
    }

    @Override
    protected void onPreExecute() {

        recyclerViewSearch = (RecyclerView) activity.findViewById(R.id.recyclerViewSearch);
        layoutManagerSearch = new LinearLayoutManager(contextSearch);
        recyclerViewSearch.setLayoutManager(layoutManagerSearch);
        recyclerViewSearch.setHasFixedSize(true);
        adapterSearch = new RecyclerViewSearchAdapter(arrayList);
        recyclerViewSearch.setAdapter(adapterSearch);
    }

    @Override
    protected Void doInBackground(String... params) {
        String new_arrivals_url = "http://192.168.99.1/cgi-bin/search.pl"; //10.0.2.2 for Emulator and 192.168.43.140 for Micromax
        //String new_arrivals_url = "http://172.19.17.58/cgi-bin/koha/new_arrivals_main.pl"; //TODO: Add PHP(Write) URL here.
        //String search_url = "http://10.0.2.2/cgi-bin/search.pl";
        String method = params[0];
        if (method.equals("Search")) {
            String titleSearch = params[1];
            String type = params[2];
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

                    DataSetSearch dataSetSearch = new DataSetSearch(jsonTitle, jsonAuthor, jsonBiblioNumber);
                    Log.d("FALCON", jsonBiblioNumber + " : " + jsonTitle + " : " + jsonAuthor);
                    publishProgress(dataSetSearch);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(contextSearch, "No records Found.", Toast.LENGTH_LONG);
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
