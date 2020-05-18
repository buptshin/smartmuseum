package com.example.smartmuseum.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ExploreFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public ExploreFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public ExploreFragmentAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
