package com.example.arnavbose.liby;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStatus extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_status, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String borrowerNumber = sharedPreferences.getString("borrowerNumber", "");

        String method = "Current Issues";
        CurrentIssuesTask currentIssuesTask = new CurrentIssuesTask(getContext());
        currentIssuesTask.execute(method, borrowerNumber);

        return view;
    }
}
