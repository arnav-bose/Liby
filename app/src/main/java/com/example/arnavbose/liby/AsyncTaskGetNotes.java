package com.example.arnavbose.liby;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Arnav on 04/05/2016.
 */
public class AsyncTaskGetNotes extends AsyncTask<List<String>, Void, Bitmap> {

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
    protected Bitmap doInBackground(List<String>... urls) {
        List<String> imageURL = urls[0];

        Bitmap bitmap = null;
        try {
            Iterator iteratorImageURL = imageURL.iterator();
            while(iteratorImageURL.hasNext()){
                // Download Image from URL
                InputStream input = new java.net.URL(iteratorImageURL.next().toString()).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
                DataSetNotes dataSetNotes = new DataSetNotes(bitmap, "<Title>", "<Contributer>", "<Author>");
                arrayListNotes.add(dataSetNotes);
                publishProgress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Void... aVoid) {
        //arrayListNotes.add(dataSetNotes[0]);
        adapterNotes.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

    }
}
