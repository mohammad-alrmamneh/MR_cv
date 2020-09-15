package com.futureapp.mr_cv.adapters;

import android.content.Context;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.fragments.ApplicationsFragment;
import com.futureapp.mr_cv.fragments.ContactInfoFragment;
import com.futureapp.mr_cv.fragments.TechnologiesFragment;
import com.futureapp.mr_cv.util.TinyDB;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FmMainPagerAdapter extends FragmentStatePagerAdapter {

    int PAGE_COUNT = 3;
    private String tabTitles[];

    private Context mContext;

    TinyDB tinyDB;

    public FmMainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;

        tinyDB = new TinyDB(context);

        tabTitles = new String[]{
                mContext.getResources().getString(R.string.applications),
                mContext.getResources().getString(R.string.technologies),
                mContext.getResources().getString(R.string.contactInfo)
        };
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {


        if (position == 0) {

            return ApplicationsFragment.newInstance(position + 1);


        } else if (position == 1) {

            return TechnologiesFragment.newInstance(position + 1);


        } else if (position == 2) {

            return ContactInfoFragment.newInstance(position + 1);


        } else {
            return ApplicationsFragment.newInstance(position + 1);

        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}