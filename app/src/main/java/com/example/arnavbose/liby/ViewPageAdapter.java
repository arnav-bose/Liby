package com.example.arnavbose.liby;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by arnavbose on 09-02-2016.
 */
public class ViewPageAdapter extends FragmentStatePagerAdapter {

    public ViewPageAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            FragmentHome home = new FragmentHome();
            return home;
        }
        else if(position == 1){
            FragmentSocial social = new FragmentSocial();
            return social;
        }
        else {
            FragmentProfile profile = new FragmentProfile();
            return profile;
        }
    }

    public int getCount(){
        return 3;
    }
}
