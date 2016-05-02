package com.example.arnavbose.liby;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;

/**
 * Created by arnavbose on 09-02-2016.
 */
public class FragmentProfile extends Fragment {

    ViewPager viewPagerProfile;
    ViewPagerAdapterProfile viewPagerAdapterProfile;
    TabLayout tabLayoutProfile;
    CircularImageView imageViewProfilePicture;
    TextView textViewProfileName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile, container, false);

        setRetainInstance(true);
        tabLayoutProfile = (TabLayout)view.findViewById(R.id.tabLayoutProfile);
        viewPagerProfile = (ViewPager)view.findViewById(R.id.viewPagerProfile);
        imageViewProfilePicture = (CircularImageView)view.findViewById(R.id.imageViewProfilePicture);
        textViewProfileName = (TextView)view.findViewById(R.id.textViewProfileName);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        textViewProfileName.setText(name);

        //Tab Layout================================================================
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

        tabLayoutProfile.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        viewPagerProfile.setCurrentItem(0);
                        break;
                    case 1:
                        viewPagerProfile.setCurrentItem(1);
                        break;
                    case 2:
                        viewPagerProfile.setCurrentItem(2);
                        break;
                    default:
                        viewPagerProfile.setCurrentItem(tab.getPosition());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //END=======================================================================

        return view;

    }


}
