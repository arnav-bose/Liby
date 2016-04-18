package com.example.arnavbose.libyv2;

/**
 * Created by arnavbose on 11-04-2016.
 */
public class DataSetSearch {

    private String mTitle;
    private String mAuthor;
    private String mBiblioNumber;

    public DataSetSearch(String title, String author, String biblioNumber){
        this.mTitle = title;
        this.mAuthor = author;
        this.mBiblioNumber = biblioNumber;
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

    public String getmBiblioNumber(){
        return mBiblioNumber;
    }

    public void setmBiblioNumber(String mBiblioNumber){
        this.mBiblioNumber = mBiblioNumber;
    }
}
