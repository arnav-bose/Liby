package com.example.arnavbose.liby;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.siyamed.shapeimageview.CircularImageView;

/**
 * Created by arnavbose on 09-02-2016.
 */
public class FragmentProfile extends Fragment {

    ViewPager viewPagerProfile;
    ViewPagerAdapterProfile viewPagerAdapterProfile;
    TabLayout tabLayoutProfile;
    CircularImageView imageViewProfilePicture;
    ImageView imageViewProfilePictureBlur;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile, container, false);

        tabLayoutProfile = (TabLayout)view.findViewById(R.id.tabLayoutProfile);
        viewPagerProfile = (ViewPager)view.findViewById(R.id.viewPagerProfile);
        imageViewProfilePicture = (CircularImageView)view.findViewById(R.id.imageViewProfilePicture);

        viewPagerAdapterProfile = new ViewPagerAdapterProfile(getFragmentManager());
        viewPagerProfile.setAdapter(viewPagerAdapterProfile);

        final TabLayout.Tab status = tabLayoutProfile.newTab();
        final TabLayout.Tab history = tabLayoutProfile.newTab();
        final TabLayout.Tab wishlist = tabLayoutProfile.newTab();

        status.setText("Status");
        history.setText("History");
        wishlist.setText("Wishlist");

        tabLayoutProfile.addTab(status, 0);
        tabLayoutProfile.addTab(history, 1);
        tabLayoutProfile.addTab(wishlist, 2);

        tabLayoutProfile.setTabTextColors(ContextCompat.getColorStateList(getContext(), R.color.colorAccent));
        tabLayoutProfile.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

        tabLayoutProfile.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPagerProfile));
        viewPagerProfile.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutProfile));



        return view;

    }


}
