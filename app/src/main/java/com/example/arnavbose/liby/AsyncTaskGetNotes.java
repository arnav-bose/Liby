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
        recyclerViewNotes = (RecyclerView) activityNotes.findViewById(R.id.recyclerViewNotes);

    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    @Override
    protected void onProgressUpdate(DataSetNotes... dataSetNotes) {
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        DataSetNotes dataSetNotes = new DataSetNotes(bitmap, "Music", "Steven Wilson", "Progressive Rock");
        arrayListNotes.add(dataSetNotes);
        recyclerViewNotes = (RecyclerView) activityNotes.findViewById(R.id.recyclerViewNotes);
        layoutManagerNotes = new LinearLayoutManager(contextNotes);
        recyclerViewNotes.setLayoutManager(layoutManagerNotes);
        recyclerViewNotes.setHasFixedSize(true);
        adapterNotes = new RecyclerViewNotesAdapter(arrayListNotes);
        recyclerViewNotes.setAdapter(adapterNotes);

    }
}
