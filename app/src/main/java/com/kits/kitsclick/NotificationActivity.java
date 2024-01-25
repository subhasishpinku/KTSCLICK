package com.kits.kitsclick;

import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kits.kitsclick.adapter.TabsAdapter;

public class NotificationActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabHost tabHost;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager =(ViewPager)findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("Information"));
        tabLayout.addTab(tabLayout.newTab().setText("Promotional"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        TabsAdapterNotification tabsAdapter = new TabsAdapterNotification(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);
        tabLayout.getTabAt(0);
        tabLayout.getTabAt(1);
        tabLayout.getTabAt(2);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0){
                    Log.e("TABB","0");
                }
                else if (tab.getPosition()==1){
                    Log.e("TABB","1");
                }

                else {

                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }
}
