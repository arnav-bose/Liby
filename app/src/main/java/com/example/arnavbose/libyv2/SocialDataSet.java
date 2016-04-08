package com.example.arnavbose.libyv2;

/**
 * Created by arnavbose on 16-03-2016.
 */
public class SocialDataSet {

    private int mImage;
    private String mText1;
    private String mText2;

    SocialDataSet(int image, String text1, String text2){
        mImage = image;
        mText1 = text1;
        mText2 = text2;
    }

    public int getmImage(){
        return mImage;
    }

    public void setmImage(int mImage){
        this.mImage = mImage;
    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }
}
