package com.kits.kitsclick.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kits.kitsclick.fragment.Busnessdetails;
import com.kits.kitsclick.fragment.Personaldetails;
import com.kits.kitsclick.fragment.Photokyc;

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fm, int NoofTabs){
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
                Photokyc home = new Photokyc();
                return home;
            case 1:
                Personaldetails personal = new Personaldetails();
                return personal;
            case 2:
                Busnessdetails busniess = new Busnessdetails();
                return busniess;
            default:
                return null;
        }
    }
}