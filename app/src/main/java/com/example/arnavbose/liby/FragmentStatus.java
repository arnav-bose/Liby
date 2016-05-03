package com.example.arnavbose.liby;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStatus extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_status, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String borrowerNumber = sharedPreferences.getString("borrowerNumber", "");

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewCurrentIssues);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewCurrentIssuesAdapter(getDataSetCurrentIssues());
        mRecyclerView.setAdapter(mAdapter);

        String method = "Current Issues";
        //CurrentIssuesTask currentIssuesTask = new CurrentIssuesTask(getContext());
        //currentIssuesTask.execute(method, borrowerNumber);

        return view;
    }

    private ArrayList<DataSetCurrentIssues> getDataSetCurrentIssues() {
        ArrayList results = new ArrayList<DataSetCurrentIssues>();
        for (int index = 0; index < 20; index++) {
            DataSetCurrentIssues object = new DataSetCurrentIssues("<Title " + index + ">",
                    "<Author " + index + ">", "Due Date: 20th May");
            results.add(index, object);
        }
        return results;
    }
}
