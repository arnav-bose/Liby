package com.example.arnavbose.liby;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * Created by arnavbose on 13-02-2016.
 */
public class FragmentSocial extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.social, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewSocial);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewSocialAdapter(getSocialDataSet());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private ArrayList<SocialDataSet> getSocialDataSet() {
        ArrayList results = new ArrayList<SocialDataSet>();
        for (int index = 0; index < 10; index++) {
            SocialDataSet object = new SocialDataSet(R.drawable.liby_logo, "<Title " + index + ">",
                    "<Author " + index + ">");
            results.add(index, object);
        }
        return results;
    }
}
