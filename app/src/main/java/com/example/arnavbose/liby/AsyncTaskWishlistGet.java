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
public class AsyncTaskWishlistGet extends AsyncTask<String, DataSetWishlist, Void> {

    Context contextWishlistGet;
    Activity activityWishlistGet;
    RecyclerView recyclerViewWishlistGet;
    RecyclerView.Adapter adapterWishlistGet;
    RecyclerView.LayoutManager layoutManagerWishlistGet;
    ArrayList<DataSetWishlist> arrayListWishlistAdd = new ArrayList<>();

    AsyncTaskWishlistGet(Context contextWishlistGet, RecyclerView recyclerViewWishlistGet) {
        this.contextWishlistGet = contextWishlistGet;
        activityWishlistGet = (Activity)contextWishlistGet;
        this.recyclerViewWishlistGet = recyclerViewWishlistGet;
    }

    @Override
    protected void onPreExecute() {
        layoutManagerWishlistGet = new LinearLayoutManager(contextWishlistGet);
        recyclerViewWishlistGet.setLayoutManager(layoutManagerWishlistGet);
        recyclerViewWishlistGet.setHasFixedSize(true);
        adapterWishlistGet = new RecyclerViewWishlistAdapter(arrayListWishlistAdd);
        recyclerViewWishlistGet.setAdapter(adapterWishlistGet);
    }

    @Override
    protected Void doInBackground(String... params) {
        String new_arrivals_url = "http://" + AppData.SERVER_ADDRESS + "/cgi-bin/wishlist_get.pl";
        String method = params[0];
        if (method.equals("Wishlist_Get")) {
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
                JSONArray parentArray = parentObject.getJSONArray("Wishlist");
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    String jsonTitle = finalObject.getString("title");
                    String jsonAuthor = finalObject.getString("author");

                    DataSetWishlist dataSetWishlist = new DataSetWishlist(jsonTitle, jsonAuthor);
                    publishProgress(dataSetWishlist);
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


    protected void onProgressUpdate(DataSetWishlist... dataSetWishlists) {
        try {
            arrayListWishlistAdd.add(dataSetWishlists[0]);
            adapterWishlistGet.notifyDataSetChanged();
        }catch(NullPointerException e){

        }
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}