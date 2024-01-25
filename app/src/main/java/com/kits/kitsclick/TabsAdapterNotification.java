package com.kits.kitsclick;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kits.kitsclick.fragment.Information;
import com.kits.kitsclick.fragment.Promotional;

public class TabsAdapterNotification extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapterNotification(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                Information infor = new Information();
                return infor;
            case 1:
                Promotional pro = new Promotional();
                return pro;
            default:
                return null;
        }
    }
}