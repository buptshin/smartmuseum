package com.example.smartmuseum.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.smartmuseum.R;
import com.example.smartmuseum.util.GoodsListFragmentFactory;

/**
 * 文创页面碎片适配器
 */
public class MainPageGoodsPagerAdapter extends FragmentPagerAdapter {

    private String[] titleArray;

    public MainPageGoodsPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        titleArray = mContext.getResources().getStringArray(R.array.goodsViewPager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = GoodsListFragmentFactory.createFragment(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleArray[position];
    }
}