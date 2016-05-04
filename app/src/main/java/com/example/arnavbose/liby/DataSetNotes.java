package com.example.arnavbose.liby;

import android.graphics.Bitmap;

/**
 * Created by Arnav on 28/04/2016.
 */
public class DataSetNotes {

    Bitmap imageViewNotes;
    String titleNotes;
    String contributerNotes;
    String subjectNotes;

    public DataSetNotes(Bitmap imageViewNotes, String titleNotes, String contributerNotes, String subjectNotes){
        this.imageViewNotes = imageViewNotes;
        this.titleNotes = titleNotes;
        this.contributerNotes = contributerNotes;
        this.subjectNotes = subjectNotes;
    }

    public Bitmap getImageViewNotes() {
        return imageViewNotes;
    }

    public void setImageViewNotes(Bitmap imageViewNotes) {
        this.imageViewNotes = imageViewNotes;
    }

    public String getTitleNotes() {
        return titleNotes;
    }

    public void setTitleNotes(String titleNotes) {
        this.titleNotes = titleNotes;
    }

    public String getContributerNotes() {
        return contributerNotes;
    }

    public void setContributerNotes(String contributerNotes) {
        this.contributerNotes = contributerNotes;
    }

    public String getSubjectNotes() {
        return subjectNotes;
    }

    public void setSubjectNotes(String subjectNotes) {
        this.subjectNotes = subjectNotes;
    }


}
