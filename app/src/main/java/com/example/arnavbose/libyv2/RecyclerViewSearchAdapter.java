package com.example.arnavbose.libyv2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by arnavbose on 28-03-2016.
 */
public class RecyclerViewSearchAdapter extends RecyclerView.Adapter<RecyclerViewSearchAdapter.DataObjectHolder> {

    private ArrayList<SearchDataSet> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookBiblioNumber;
        CardView cardViewSearch;

        public DataObjectHolder(View itemView) {
            super(itemView);
            bookTitle = (TextView) itemView.findViewById(R.id.textViewSearchTitle);
            bookAuthor = (TextView) itemView.findViewById(R.id.textViewSearchAuthor);
            bookBiblioNumber = (TextView) itemView.findViewById(R.id.textViewSearchBiblioNumber);
            cardViewSearch = (CardView) itemView.findViewById(R.id.searchCardViewLayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public RecyclerViewSearchAdapter(ArrayList<SearchDataSet> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_card_row_layout, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.bookTitle.setText(mDataset.get(position).getmText1());
        holder.bookAuthor.setText(mDataset.get(position).getmText2());
        holder.bookBiblioNumber.setText(mDataset.get(position).getmText3());

        holder.cardViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What happens when you click?
            }
        });
    }

    public void addItem(SearchDataSet dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
