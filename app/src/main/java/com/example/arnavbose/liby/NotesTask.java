package com.example.arnavbose.liby;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Arnav on 28/04/2016.
 */
public class NotesTask extends AsyncTask<Void, Void, Void> {

    Context contextNotes;
    Activity activityNotes;
    RecyclerView recyclerViewNotes;
    RecyclerView.Adapter adapterNotes;
    RecyclerView.LayoutManager layoutManagerNotes;
    ArrayList<DataSetNotes> arrayListDataSetNotes = new ArrayList<>();

    NotesTask(Context contextNotes){
        this.contextNotes = contextNotes;
        activityNotes = (Activity)contextNotes;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        recyclerViewNotes = (RecyclerView) activityNotes.findViewById(R.id.recyclerViewNotes);
        layoutManagerNotes = new LinearLayoutManager(contextNotes);
        recyclerViewNotes.setLayoutManager(layoutManagerNotes);
        recyclerViewNotes.setHasFixedSize(true);
        //adapterNotes = new RecyclerViewNotesAdapter(arrayListDataSetNotes);
        adapterNotes = new RecyclerViewNotesAdapter(getDataSetNotes());
        recyclerViewNotes.setAdapter(adapterNotes);
    }

    private ArrayList<DataSetNotes> getDataSetNotes() {
        ArrayList results = new ArrayList<DataSetNotes>();
        for (int index = 0; index < 20; index++) {
            DataSetNotes object = new DataSetNotes(R.drawable.liby_logo, "<Title " + index + ">",
                    "<Contributer " + index + ">", "Subject");
            results.add(index, object);
        }
        return results;
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
