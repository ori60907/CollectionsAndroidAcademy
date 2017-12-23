package com.fundamentals.academy.ori.collections;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 15/12/2017.
 */

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<CollectionPageFragment> fragmentsList;
    private static final String SCREENS_PAGE_ADAPTER_LOG_TAG = "SlidePagerAdapter:";

    public void updateCollections(List<CollectionsResult> collections){
        Gson gson = new Gson();
        String collectionJson = "";
        for (int i=0; i<collections.size(); ++i){
            CollectionPageFragment collectionPageFragment = new CollectionPageFragment();
            collectionJson = gson.toJson(collections.get(i));
            Bundle arguments = new Bundle();
            arguments.putString(CollectionPageFragment.collectionResultKey, collectionJson);
            collectionPageFragment.setArguments(arguments);
            fragmentsList.add(collectionPageFragment);
        }
        this.notifyDataSetChanged();
    }

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentsList = new ArrayList<CollectionPageFragment>();
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
        return MainActivity.MaxNumberOfCollections>fragmentsList.size()?fragmentsList.size():MainActivity.MaxNumberOfCollections;
    }
}
