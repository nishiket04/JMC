package com.nishiket.jmc.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nishiket.jmc.views.ActiveFragment;
import com.nishiket.jmc.views.DelayedFragment;
import com.nishiket.jmc.views.SolvedFragment;

// This is ViewPagerAdapter its apply on fragmemt on viewpager and also give Tablyout its name
public class ViewPagerComplainAdapter extends FragmentPagerAdapter {
    // First its accept Fragment Manager in its constructor
    public ViewPagerComplainAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    // now we get on which position its selected and scrolled and apply its fragment
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return (new ActiveFragment());
        } else if (position == 1) {
            return (new SolvedFragment());
        }else {
            return (new DelayedFragment());
        }
    }

    @Override
    public int getCount() {
        return 3;
    } // this returns how many tabs are there

    // This will set Its name on Tab with its Positions
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Active";
        } else if (position == 1) {
            return "Solved";
        }else {
            return "Delayed";
        }
    }
}
