package com.example.arnavbose.liby;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by arnavbose on 09-02-2016.
 */
public class FragmentHome extends Fragment {

    public RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);

        setRetainInstance(true);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewHome);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mAdapter = new RecyclerViewHomeAdapter(getHomeDataSet());
//        mRecyclerView.setAdapter(mAdapter);

        String method = "Home";
        AsyncTaskHome asyncTaskHome = new AsyncTaskHome(getContext(), mRecyclerView);
        asyncTaskHome.execute(method);


        return view;
    }

    private ArrayList<DataSetHome> getHomeDataSet() {
        ArrayList results = new ArrayList<DataSetHome>();
        for (int index = 0; index < 20; index++) {
            DataSetHome object = new DataSetHome("<Title " + index + ">",
                    "<Author " + index + ">", "Available: 3", "<BiblioNumber>");
            results.add(index, object);
        }
        return results;
    }
}
