package com.example.arnavbose.liby;

/**
 * Created by Arnav on 28/04/2016.
 */
public class DataSetNotes {

    int imageViewNotes;
    String titleNotes;
    String contributerNotes;
    String subjectNotes;

    public DataSetNotes(int imageViewNotes, String titleNotes, String contributerNotes, String subjectNotes){
        this.imageViewNotes = imageViewNotes;
        this.titleNotes = titleNotes;
        this.contributerNotes = contributerNotes;
        this.subjectNotes = subjectNotes;
    }

    public int getImageViewNotes() {
        return imageViewNotes;
    }

    public void setImageViewNotes(int imageViewNotes) {
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
