package com.kits.kitsclick;

import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ReporttabActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabHost tabHost;
    ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager =(ViewPager)findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("TRANSACTION"));
        tabLayout.addTab(tabLayout.newTab().setText("OTHER"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        TabAdapterReport tabsAdapter = new TabAdapterReport(getSupportFragmentManager(), tabLayout.getTabCount());
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
