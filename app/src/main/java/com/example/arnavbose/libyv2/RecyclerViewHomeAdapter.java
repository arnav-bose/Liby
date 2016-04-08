package com.example.arnavbose.libyv2;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by arnavbose on 14-02-2016.
 */
public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.DataObjectHolder> {
    private ArrayList<HomeDataSet> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        ImageView bookIcon;
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookAvailable;
        CardView cardViewNewArrivals;

        public DataObjectHolder(View itemView) {
            super(itemView);
            bookIcon = (ImageView) itemView.findViewById(R.id.bookIcon);
            bookTitle = (TextView) itemView.findViewById(R.id.bookTitle);
            bookAuthor = (TextView) itemView.findViewById(R.id.bookAuthor);
            bookAvailable = (TextView) itemView.findViewById(R.id.bookAvailable);
            cardViewNewArrivals = (CardView) itemView.findViewById(R.id.homeCardViewLayout);
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

    public RecyclerViewHomeAdapter(ArrayList<HomeDataSet> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_card_row_layout, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.bookIcon.setImageResource(mDataset.get(position).getmImage());
        holder.bookTitle.setText(mDataset.get(position).getmText1());
        holder.bookAuthor.setText(mDataset.get(position).getmText2());
        holder.bookAvailable.setText(mDataset.get(position).getmText3());

        holder.cardViewNewArrivals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), BookReserve.class);
                v.getContext().startActivity(i);
            }
        });
    }

    public void addItem(HomeDataSet dataObj, int index) {
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
