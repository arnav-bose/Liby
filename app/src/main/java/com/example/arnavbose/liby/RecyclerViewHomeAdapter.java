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
public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.RecyclerViewHolder> {

    private ArrayList<DataSetHome> mDataset;
    private static MyClickListener myClickListener;
    Bundle bundleBookDetails;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView bookIcon;
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookAvailable;
        TextView bookBiblioNumber;
        CardView cardViewNewArrivals;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            bookIcon = (ImageView) itemView.findViewById(R.id.bookIcon);
            bookTitle = (TextView) itemView.findViewById(R.id.bookTitle);
            bookAuthor = (TextView) itemView.findViewById(R.id.bookAuthor);
            bookAvailable = (TextView) itemView.findViewById(R.id.bookAvailable);
            bookBiblioNumber = (TextView)itemView.findViewById(R.id.textViewBiblioNumberHome);
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

    public RecyclerViewHomeAdapter(ArrayList<DataSetHome> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card_row_layout, parent, false);
        RecyclerViewHolder dataObjectHolder = new RecyclerViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        DataSetHome homeDataSet = mDataset.get(position);
        holder.bookIcon.setImageResource(mDataset.get(position).getmImage());
        holder.bookTitle.setText(mDataset.get(position).getmText1());
        holder.bookAuthor.setText(mDataset.get(position).getmText2());
        holder.bookAvailable.setText(mDataset.get(position).getmText3());
        holder.bookBiblioNumber.setText((mDataset.get(position).getmText4()));
        final String biblionumberBookDetails = homeDataSet.getmText4();


        holder.cardViewNewArrivals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), BookDetails.class);
                bundleBookDetails = new Bundle();
                bundleBookDetails.putString("biblioNumberBookDetails", biblionumberBookDetails);
                i.putExtras(bundleBookDetails);
                v.getContext().startActivity(i);
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