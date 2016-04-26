package com.example.arnavbose.liby;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by arnavbose on 15-04-2016.
 */
public class RecyclerViewSearchAdapter extends RecyclerView.Adapter <RecyclerViewSearchAdapter.RecyclerViewHolder> {

    private ArrayList<DataSetSearch> arrayList = new ArrayList<>();
    private static MyClickListener myClickListener;
    Bundle bundleBookDetails;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title, author, biblioNumber;
        CardView cardViewSearch;
        String biblioNumberBookDetails;

        public RecyclerViewHolder(View view){
            super(view);
            title = (TextView)view.findViewById(R.id.textViewSearchTitle);
            author = (TextView)view.findViewById(R.id.textViewSearchAuthor);
            biblioNumber = (TextView)view.findViewById(R.id.textViewSearchBiblioNumber);
            cardViewSearch = (CardView)view.findViewById(R.id.searchCardViewLayout);
            view.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

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
        holder.biblioNumber.setText(dataSetSearch.getmBiblioNumber());
        final String biblionumberBookDetails = dataSetSearch.getmBiblioNumber();

        holder.cardViewSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(v.getContext(), BookDetails.class);
                bundleBookDetails = new Bundle();
                bundleBookDetails.putString("biblioNumberBookDetails", biblionumberBookDetails);
                i.putExtras(bundleBookDetails);
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}