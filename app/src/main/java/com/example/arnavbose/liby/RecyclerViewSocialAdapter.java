package com.example.arnavbose.liby;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by arnavbose on 16-03-2016.
 */
public class RecyclerViewSocialAdapter extends RecyclerView.Adapter<RecyclerViewSocialAdapter.DataObjectHolder>{
    private ArrayList<SocialDataSet> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        ImageView bookIcon;
        TextView bookTitle;
        TextView bookAuthor;
        CardView cardViewSocial;

        public DataObjectHolder(View itemView) {
            super(itemView);
            bookIcon = (ImageView) itemView.findViewById(R.id.imageViewSocialBook);
            bookTitle = (TextView) itemView.findViewById(R.id.textViewSocialBookTitle);
            bookAuthor = (TextView) itemView.findViewById(R.id.textViewSocialAuthor);
            cardViewSocial = (CardView) itemView.findViewById(R.id.socialCardViewLayout);
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

    public RecyclerViewSocialAdapter(ArrayList<SocialDataSet> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.social_card_row_layout, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.bookIcon.setImageResource(mDataset.get(position).getmImage());
        holder.bookTitle.setText(mDataset.get(position).getmText1());
        holder.bookAuthor.setText(mDataset.get(position).getmText2());

        holder.cardViewSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What happens when you click?
            }
        });
    }

    public void addItem(SocialDataSet dataObj, int index) {
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
