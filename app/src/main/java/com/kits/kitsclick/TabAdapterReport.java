package com.kits.kitsclick;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kits.kitsclick.fragment.Information;
import com.kits.kitsclick.fragment.Other;
import com.kits.kitsclick.fragment.Promotional;
import com.kits.kitsclick.fragment.Transaction;

public class TabAdapterReport extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabAdapterReport(FragmentManager fm, int NoofTabs){
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
                Transaction infor = new Transaction();
                return infor;
            case 1:
                Other pro = new Other();
                return pro;
            default:
                return null;
        }
    }
}
