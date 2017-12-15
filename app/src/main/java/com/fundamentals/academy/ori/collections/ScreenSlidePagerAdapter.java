package com.fundamentals.academy.ori.collections;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by User on 15/12/2017.
 */

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<MockFragment> fragmentsList;
    private static final String SCREENS_PAGE_ADAPTER_LOG_TAG = "SlidePagerAdapter:";

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentsList = new ArrayList<MockFragment>();

        Bundle budle = new Bundle();
        budle.putString(MockFragment.messageKey, "hey u!");
        budle.putInt(MockFragment.colorKey, R.color.firstFragmentColor);
        MockFragment fragment = new MockFragment();
        fragment.setArguments(budle);
        fragmentsList.add(fragment);

        budle = new Bundle();
        budle.putString(MockFragment.messageKey, "wanted to tell u something...");
        budle.putInt(MockFragment.colorKey, R.color.secondFragmentColor);
        fragment = new MockFragment();
        fragment.setArguments(budle);
        fragmentsList.add(fragment);

        budle = new Bundle();
        budle.putString(MockFragment.messageKey, "I LOVE u <3");
        budle.putInt(MockFragment.colorKey, R.color.thirdFragmentColor);
        fragment = new MockFragment();
        fragment.setArguments(budle);
        fragmentsList.add(fragment);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }
}
