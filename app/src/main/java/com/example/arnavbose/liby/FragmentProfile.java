package com.example.arnavbose.liby;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by arnavbose on 09-02-2016.
 */
public class FragmentProfile extends Fragment {

    private int PICK_IMAGE_REQUEST = 1;

    ViewPager viewPagerProfile;
    ViewPagerAdapterProfile viewPagerAdapterProfile;
    TabLayout tabLayoutProfile;
    TextView textViewProfileName;
    FloatingActionButton floatingActionButtonProfile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile, container, false);

        setRetainInstance(true);
        tabLayoutProfile = (TabLayout)view.findViewById(R.id.tabLayoutProfile);
        viewPagerProfile = (ViewPager)view.findViewById(R.id.viewPagerProfile);
        textViewProfileName = (TextView)view.findViewById(R.id.textViewProfileName);

        AppData.myData = PreferenceManager.getDefaultSharedPreferences(getContext());
        String name = AppData.myData.getString("name", "");
        textViewProfileName.setText(name);

        floatingActionButtonProfile = (FloatingActionButton)view.findViewById(R.id.fabProfile);
        floatingActionButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });

        //Tab Layout================================================================
        viewPagerAdapterProfile = new ViewPagerAdapterProfile(getChildFragmentManager());
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView)getActivity().findViewById(R.id.imageViewProfilePicture);

                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
