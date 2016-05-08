package com.example.arnavbose.liby;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Arnav on 04/05/2016.
 */
public class AsyncTaskGetNotes extends AsyncTask<String, DataSetNotes, Bitmap> {

    Context contextNotes;
    Activity activityNotes;
    RecyclerView recyclerViewNotes;
    RecyclerView.Adapter adapterNotes;
    RecyclerView.LayoutManager layoutManagerNotes;
    ArrayList<DataSetNotes> arrayListNotes = new ArrayList<>();

    AsyncTaskGetNotes(Context contextNotes){
        this.contextNotes = contextNotes;
        activityNotes = (Activity)contextNotes;
    }

    @Override
    protected void onPreExecute() {
        recyclerViewNotes = (RecyclerView)activityNotes.findViewById(R.id.recyclerViewNotes);
        layoutManagerNotes = new LinearLayoutManager(contextNotes);
        recyclerViewNotes.setLayoutManager(layoutManagerNotes);
        recyclerViewNotes.setHasFixedSize(true);
        adapterNotes = new RecyclerViewNotesAdapter(arrayListNotes);
        recyclerViewNotes.setAdapter(adapterNotes);

    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String imageURL = urls[0];

        Bitmap bitmap = null;
        try {
            // Download Image from URL
            InputStream input = new java.net.URL(imageURL).openStream();
            // Decode Bitmap
            bitmap = BitmapFactory.decodeStream(input);
            DataSetNotes dataSetNotes = new DataSetNotes(bitmap, "<Title>", "<Contributer", "<Author>");
            publishProgress(dataSetNotes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onProgressUpdate(DataSetNotes... dataSetNotes) {
        arrayListNotes.add(dataSetNotes[0]);
        adapterNotes.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

    }
}
