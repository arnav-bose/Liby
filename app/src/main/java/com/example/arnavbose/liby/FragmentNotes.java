package com.example.arnavbose.liby;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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

        final String URL = "http://www.angrymetalguy.com/wp-content/uploads/2016/01/Steven-Wilson-2016.jpg";

        AsyncTaskGetNotes asyncTaskGetNotes = new AsyncTaskGetNotes(getContext());
        asyncTaskGetNotes.execute(URL);

        setRetainInstance(true);
        buttonNotes = (Button)view.findViewById(R.id.buttonNotes);
        buttonNotes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 0);
            }
        });

        getActivity().setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return view;
    }
}
