package com.example.arnavbose.libyv2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by arnavbose on 15-04-2016.
 */
public class RecyclerViewSearchAdapter extends RecyclerView.Adapter <RecyclerViewSearchAdapter.RecyclerViewHolder> {

    ArrayList<DataSetSearch> arrayList = new ArrayList<>();

    public RecyclerViewSearchAdapter(ArrayList<DataSetSearch> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card_row_layout, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        DataSetSearch dataSetSearch = arrayList.get(position);
        holder.title.setText(dataSetSearch.getmTitle());
        holder.author.setText(dataSetSearch.getmAuthor());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView title, author;

        public RecyclerViewHolder(View view){
            super(view);
            title = (TextView)view.findViewById(R.id.textViewSearchTitle);
            author = (TextView)view.findViewById(R.id.textViewSearchAuthor);

        }
    }
}