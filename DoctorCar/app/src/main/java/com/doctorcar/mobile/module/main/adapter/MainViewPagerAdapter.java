package com.doctorcar.mobile.module.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.doctorcar.mobile.module.main.fragment.MainFragment;

import java.util.ArrayList;

/**
 * Created by dd on 2017/5/19.
 */

public class MainViewPagerAdapter  extends FragmentPagerAdapter {

    private ArrayList<MainFragment> fragments = new ArrayList<MainFragment>();
    private MainFragment currentFragment;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.clear();
        fragments.add(MainFragment.newInstance(0));
        fragments.add(MainFragment.newInstance(1));
        fragments.add(MainFragment.newInstance(2));
        fragments.add(MainFragment.newInstance(3));
        fragments.add(MainFragment.newInstance(4));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((MainFragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * Get the current fragment
     */
    public MainFragment getCurrentFragment() {
        return currentFragment;
    }

}
