package com.example.arnavbose.liby;

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
 * Created by Arnav on 28/04/2016.
 */
public class RecyclerViewNotesAdapter extends RecyclerView.Adapter<RecyclerViewNotesAdapter.RecyclerViewHolder>{

    private ArrayList<DataSetNotes> arrayList = new ArrayList<>();
    private static MyClickListener myClickListener;
    Bundle bundleNotes;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageViewNotes;
        TextView textViewNotesTitle, textViewNotesContributer, textViewNotesSubject;
        CardView cardViewNotes;
        public RecyclerViewHolder(View view){
            super(view);
            imageViewNotes = (ImageView)view.findViewById(R.id.imageViewNotes);
            textViewNotesTitle = (TextView)view.findViewById(R.id.textViewNotesTitle);
            textViewNotesContributer = (TextView)view.findViewById(R.id.textViewNotesContributer);
            textViewNotesSubject = (TextView)view.findViewById(R.id.textViewNotesSubject);
            cardViewNotes = (CardView)view.findViewById(R.id.notesCardViewLayout);
            view.setOnClickListener(this);
        }

        public void onClick(View v){
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener){
        this.myClickListener = myClickListener;
    }

    public RecyclerViewNotesAdapter(ArrayList<DataSetNotes> dataSetNotes){
        this.arrayList = dataSetNotes;
    }

    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_card_row_layout, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        DataSetNotes dataSetNotes = arrayList.get(position);
        holder.imageViewNotes.setImageBitmap(dataSetNotes.getImageViewNotes());
        holder.textViewNotesTitle.setText(dataSetNotes.getTitleNotes());
        holder.textViewNotesContributer.setText(dataSetNotes.getContributerNotes());
        holder.textViewNotesSubject.setText(dataSetNotes.getSubjectNotes());
        holder.cardViewNotes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO: Make NotesDetails.java
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
