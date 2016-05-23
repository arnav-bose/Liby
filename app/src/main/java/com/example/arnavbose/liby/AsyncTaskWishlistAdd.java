package com.example.arnavbose.liby;

import android.app.Activity;
import android.app.ProgressDialog;
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


/**
 * Created by arnavbose on 15-04-2016.
 */
public class AsyncTaskWishlistAdd extends AsyncTask<String, DataSetSearch, Void> {

    Context contextWishlistAdd;
    Activity activityWishlistAdd;
    ProgressDialog progressDialogWishlistAdd;

    AsyncTaskWishlistAdd(Context context) {
        this.contextWishlistAdd = context;
        activityWishlistAdd = (Activity) context;
    }

    @Override
    protected void onPreExecute() {

        progressDialogWishlistAdd = new ProgressDialog(activityWishlistAdd);
        progressDialogWishlistAdd.setTitle("Liby");
        progressDialogWishlistAdd.setMessage("Adding to Wishlist...");
        progressDialogWishlistAdd.setIndeterminate(false);
        progressDialogWishlistAdd.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        String search_url = "http://" + AppData.SERVER_ADDRESS + "/cgi-bin/wishlist_add.pl";
        String method = params[0];
        if (method.equals("Wishlist_Add")) {
            String title = params[1];
            String author = params[2];
            try {
                URL url = new URL(search_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data =
                        URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8") + "&" +
                                URLEncoder.encode("author", "UTF-8") + "=" + URLEncoder.encode(author, "UTF-8");

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


    protected void onProgressUpdate(DataSetSearch... dataSetSearches) {

    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        progressDialogWishlistAdd.dismiss();
        Toast.makeText(contextWishlistAdd, "Your book has been added!", Toast.LENGTH_SHORT).show();
    }
}
