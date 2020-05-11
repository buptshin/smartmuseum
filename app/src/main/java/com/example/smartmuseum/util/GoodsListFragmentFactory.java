package com.example.smartmuseum.util;

import androidx.fragment.app.Fragment;

import com.example.smartmuseum.view.goods.GoodsMarketFragment;
import com.example.smartmuseum.view.goods.GoodsPurchasedFragment;

/**
 * 文创碎片页面工厂
 */
public class GoodsListFragmentFactory {

    public static Fragment createFragment(int pos) {
        Fragment fragment = null;
        switch (pos) {
            case 0:
                fragment = new GoodsPurchasedFragment();
                break;
            case 1:
                fragment = new GoodsMarketFragment();
                break;
            default:
                break;
        }

        return fragment;

    }
}