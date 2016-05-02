package com.example.arnavbose.liby;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by arnavbose on 09-02-2016.
 */
public class ViewPagerAdapterProfile extends FragmentStatePagerAdapter {

    public ViewPagerAdapterProfile(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            FragmentStatus status = new FragmentStatus();
            return status;
        }
        else if(position == 1){
            FragmentHistory history = new FragmentHistory();
            return history;
        }
        else if(position == 2){
            FragmentWishlist wishlist = new FragmentWishlist();
            return wishlist;
        }
        else{
            FragmentStatus status = new FragmentStatus();
            return status;
        }
    }

    public int getCount(){
        return 3;
    }
}
