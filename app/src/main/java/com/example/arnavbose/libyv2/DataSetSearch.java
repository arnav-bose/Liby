package com.example.arnavbose.libyv2;

/**
 * Created by arnavbose on 11-04-2016.
 */
public class DataSetSearch {

    private String mTitle;
    private String mAuthor;

    public DataSetSearch(String title, String author){
        this.mTitle = title;
        this.mAuthor = author;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }
}
