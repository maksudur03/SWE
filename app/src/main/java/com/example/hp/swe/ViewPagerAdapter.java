package com.example.hp.swe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Priyabrat on 21-08-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0)
        {
            ClassSchedule fragment = new ClassSchedule();
            return  fragment;
        }
        else if (position == 1)
        {
            ExamSchedule fragment= new ExamSchedule();
            return fragment;
        }
        else {
            return null;
        }

//        else if (position == 2)
//        {
////            fragment = new FragmentC();
//        }
//        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Tab-1";
        }
        else if (position == 1)
        {
            title = "Tab-2";
        }

        return title;
    }
}
