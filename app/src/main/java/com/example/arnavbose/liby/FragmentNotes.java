package com.example.arnavbose.liby;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by arnavbose on 13-02-2016.
 */
public class FragmentNotes extends Fragment {

    Button buttonNotes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notes, container, false);

        //NotesTask notesTask = new NotesTask(getContext());
        //notesTask.execute();

        buttonNotes = (Button)view.findViewById(R.id.buttonNotes);
        buttonNotes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 0);
            }
        });

        return view;
    }
}
