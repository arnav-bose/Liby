package com.example.arnavbose.liby;

/**
 * Created by Arnav on 23/05/2016.
 */
public class DataSetWishlist {

    private String mTitle;
    private String mAuthor;

    public DataSetWishlist(String title, String author){
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
