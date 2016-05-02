package com.example.arnavbose.liby;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by arnavbose on 09-02-2016.
 */
public class ViewPagerAdapterHome extends FragmentStatePagerAdapter {

    public ViewPagerAdapterHome(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            FragmentHome home = new FragmentHome();
            return home;
        }
        else if(position == 1){
            FragmentNotes notes = new FragmentNotes();
            return notes;
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
