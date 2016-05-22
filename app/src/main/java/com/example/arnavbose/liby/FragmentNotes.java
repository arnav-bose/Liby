package com.example.arnavbose.liby;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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

        setRetainInstance(true);

        AsyncTaskGetURL asyncTaskGetURL = new AsyncTaskGetURL(getContext());
        asyncTaskGetURL.execute();

        buttonNotes = (Button)view.findViewById(R.id.buttonNotes);
        buttonNotes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity(), ImageUpload.class);
                startActivity(i);
            }
        });

        getActivity().setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return view;
    }
}