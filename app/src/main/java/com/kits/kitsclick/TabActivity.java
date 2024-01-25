package com.kits.kitsclick;

import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kits.kitsclick.adapter.TabsAdapter;

public class TabActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabHost tabHost;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager =(ViewPager)findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("KYC"));
        tabLayout.addTab(tabLayout.newTab().setText("PERSONAL DETAILS"));
        tabLayout.addTab(tabLayout.newTab().setText("BUSINESS DETAILS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);
        // tabLayout = getHost();
        // tabLayout.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.ab);
        // tabLayout.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.ab);
        // tabLayout.getTabWidget().setCurrentTab(0);
        // tabLayout.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.abch);
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
                else if (tab.getPosition()==2){
                    Log.e("TABB","2");
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
