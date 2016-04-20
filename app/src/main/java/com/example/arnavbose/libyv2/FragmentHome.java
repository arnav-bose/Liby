package com.example.arnavbose.libyv2;

import android.content.Intent;
import android.os.Bundle;
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

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewHome);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewHomeAdapter(getHomeDataSet());
        mRecyclerView.setAdapter(mAdapter);

        Bundle bundleLogin = getActivity().getIntent().getExtras();
        String borrowerNumber = bundleLogin.getString("borrowerNumber");
        String firstName = bundleLogin.getString("firstName");
        String lastName = bundleLogin.getString("lastName");

        Toast.makeText(getContext(), "Welcome " + firstName + " " + lastName, Toast.LENGTH_LONG).show();

        return view;
    }

    private ArrayList<HomeDataSet> getHomeDataSet() {
        ArrayList results = new ArrayList<HomeDataSet>();
        for (int index = 0; index < 20; index++) {
            HomeDataSet object = new HomeDataSet(R.drawable.liby_logo, "<Title " + index + ">",
                    "<Author " + index + ">", "Available: 3", "<BiblioNumber>");
            results.add(index, object);
        }
        return results;
    }
}
