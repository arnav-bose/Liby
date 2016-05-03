package com.example.arnavbose.liby;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPagerMain;
    ViewPagerAdapterHome viewPagerAdapterMain;
    TabLayout tabLayoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar, TabLayout, ViewPager
        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        tabLayoutMain = (TabLayout) findViewById(R.id.tabLayoutMain);
        viewPagerMain = (ViewPager) findViewById(R.id.viewPagerMain);
        setSupportActionBar(toolbar);

        //View Pager Adapter
        viewPagerAdapterMain = new ViewPagerAdapterHome(getSupportFragmentManager());
        viewPagerMain.setAdapter(viewPagerAdapterMain);

        final TabLayout.Tab home = tabLayoutMain.newTab();
        final TabLayout.Tab notes = tabLayoutMain.newTab();
        final TabLayout.Tab profile = tabLayoutMain.newTab();

        home.setIcon(R.drawable.ic_home_variant_white_48dp);
        profile.setIcon(R.drawable.ic_account_grey600_48dp);
        notes.setIcon(R.drawable.ic_star_grey600_48dp);

        tabLayoutMain.addTab(home, 0);
        tabLayoutMain.addTab(notes, 1);
        tabLayoutMain.addTab(profile, 2);

        tabLayoutMain.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        tabLayoutMain.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));

        tabLayoutMain.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPagerMain));
        viewPagerMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMain));

        //Icon Change For Selected Tab
        viewPagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        /*
                        setting Home as White and rest grey
                        and like wise for all other positions
                         */
                        home.setIcon(R.drawable.ic_home_variant_white_48dp);
                        profile.setIcon(R.drawable.ic_account_grey600_48dp);
                        notes.setIcon(R.drawable.ic_star_grey600_48dp);
                        break;
                    case 1:
                        home.setIcon(R.drawable.ic_home_variant_grey600_48dp);
                        notes.setIcon(R.drawable.ic_star_white_48dp);
                        profile.setIcon(R.drawable.ic_account_grey600_48dp);
                        break;
                    case 2:
                        home.setIcon(R.drawable.ic_home_variant_grey600_48dp);
                        profile.setIcon(R.drawable.ic_account_white_48dp);
                        notes.setIcon(R.drawable.ic_star_grey600_48dp);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Title Change For Selected Tab
        tabLayoutMain.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        viewPagerMain.setCurrentItem(0);
                        toolbar.setTitle("Home");
                        break;
                    case 1:
                        viewPagerMain.setCurrentItem(1);
                        toolbar.setTitle("Notes");
                        break;
                    case 2:
                        viewPagerMain.setCurrentItem(2);
                        toolbar.setTitle("Profile");
                        break;
                    default:
                        viewPagerMain.setCurrentItem(tab.getPosition());
                        toolbar.setTitle("Liby");
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

        if(AppData.WELCOME_MESSAGE == false) {
            AppData.myData = PreferenceManager.getDefaultSharedPreferences(this);
            String name = AppData.myData.getString("name", "");

            Toast.makeText(this, "Welcome " + name, Toast.LENGTH_LONG).show();

            AppData.WELCOME_MESSAGE = true;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        switch(id){
            case R.id.action_search:
                startActivity(new Intent(this, Search.class));
                return true;

            case R.id.logout_button:
                AppData.LOGIN_CHECK = false;
                AppData.WELCOME_MESSAGE = false;
                AppData.myData = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = AppData.myData.edit();
                editor.putString("name", "");
                editor.putString("borrowerNumber", "");
                editor.putBoolean("LOGIN_CHECK", AppData.LOGIN_CHECK);
                editor.putBoolean("WELCOME_MESSAGE", AppData.WELCOME_MESSAGE);
                editor.commit();
                Intent intent = new Intent(MainActivity.this, LogIn.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
