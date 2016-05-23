package com.example.arnavbose.liby;

import android.content.Intent;
import android.os.Bundle;
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
public class RecyclerViewWishlistAdapter extends RecyclerView.Adapter<RecyclerViewWishlistAdapter.RecyclerViewHolder> {

    private ArrayList<DataSetWishlist> mDataset;
    private static MyClickListener myClickListener;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewWishlistTitle;
        TextView textViewWishlistAuthor;
        CardView cardViewWishlist;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            textViewWishlistTitle = (TextView) itemView.findViewById(R.id.textViewWishlistTitle);
            textViewWishlistAuthor = (TextView) itemView.findViewById(R.id.textViewWishlistAuthor);
            cardViewWishlist = (CardView)itemView.findViewById(R.id.wishlistCardViewLayout);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public RecyclerViewWishlistAdapter(ArrayList<DataSetWishlist> myDataset) {
        mDataset = myDataset;
    }

    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_card_row_layout, parent, false);
        RecyclerViewHolder dataObjectHolder = new RecyclerViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        DataSetWishlist dataSetWishlist = mDataset.get(position);
        //holder.bookIcon.setImageResource(mDataset.get(position).getmImage());
        holder.textViewWishlistTitle.setText(mDataset.get(position).getmTitle());
        holder.textViewWishlistAuthor.setText(mDataset.get(position).getmAuthor());



        holder.cardViewWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

//    public void addItem(DataSetHome dataObj, int index) {
//        mDataset.add(index, dataObj);
//        notifyItemInserted(index);
//    }
//
//    public void deleteItem(int index) {
//        mDataset.remove(index);
//        notifyItemRemoved(index);
//    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
