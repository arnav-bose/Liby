package com.example.arnavbose.liby;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Arnav on 29/04/2016.
 */
public class RecyclerViewCurrentIssuesAdapter extends RecyclerView.Adapter <RecyclerViewCurrentIssuesAdapter.RecyclerViewHolder> {

    private ArrayList<DataSetCurrentIssues> arrayListCurrentIssues = new ArrayList<>();

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView textViewCurrentIssuesTitle;
        TextView textViewCurrentIssuesAuthor;
        TextView textViewCurrentIssuesDueDate;
        CardView cardViewCurrentIssues;

        public RecyclerViewHolder(View view){
            super(view);
            textViewCurrentIssuesTitle = (TextView)view.findViewById(R.id.textViewCurrentIssuesTitle);
            textViewCurrentIssuesAuthor = (TextView)view.findViewById(R.id.textViewCurrentIssuesAuthor);
            textViewCurrentIssuesDueDate = (TextView)view.findViewById(R.id.textViewCurrentIssuesDueDate);
            cardViewCurrentIssues = (CardView)view.findViewById(R.id.currentIssuesCardViewLayout);
        }
    }

    public RecyclerViewCurrentIssuesAdapter(ArrayList<DataSetCurrentIssues> arrayListCurrentIssues){
        this.arrayListCurrentIssues = arrayListCurrentIssues;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_issues_card_row_layout, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position){
        DataSetCurrentIssues dataSetCurrentIssues = arrayListCurrentIssues.get(position);
        holder.textViewCurrentIssuesTitle.setText(dataSetCurrentIssues.getCurrentIssuesTitle());
        holder.textViewCurrentIssuesAuthor.setText(dataSetCurrentIssues.getCurrentIssuesAuthor());
        holder.textViewCurrentIssuesDueDate.setText(dataSetCurrentIssues.getCurrentIssuesDueDate());
    }

    @Override
    public int getItemCount() {
        return arrayListCurrentIssues.size();
    }
}
