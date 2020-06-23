package com.example.smartmuseum.view.navigation.exhibition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.ExhibitsDetailsPagerAdapter;
import com.example.smartmuseum.databinding.ActivityExhibitsDetailsBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/*展品详情activity*/
public class ExhibitsDetailsActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityExhibitsDetailsBinding mBinding;

    private ExhibitsDetailsPagerAdapter exhibitsDetailsPagerAdapter;

    private ArrayList<View> aList;

    private ExhibitsDetailsPagerAdapter pagerAdapter;

    private boolean isLike = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public ExhibitsDetailsActivity bindView() {
        mBinding = DataBindingUtil.setContentView(ExhibitsDetailsActivity.this, R.layout.activity_exhibits_details);

        //设置全屏
        ScreenUtil.fullScreen(ExhibitsDetailsActivity.this);

        TabLayout.Tab tab1 = mBinding.exhibitsDetailsTblayout.newTab();
        TabLayout.Tab tab2 = mBinding.exhibitsDetailsTblayout.newTab();
        TabLayout.Tab tab3 = mBinding.exhibitsDetailsTblayout.newTab();
        tab1.setText("信息介绍");
        tab2.setText("AR互动");
        tab3.setText("评价");
        mBinding.exhibitsDetailsTblayout.addTab(tab1);
        mBinding.exhibitsDetailsTblayout.addTab(tab2);
        mBinding.exhibitsDetailsTblayout.addTab(tab3);

        aList = new ArrayList<View>();
        LayoutInflater li = getLayoutInflater();
        aList.add(li.inflate(R.layout.exhibits_details_item1,null,false));
        aList.add(li.inflate(R.layout.exhibits_details_item2,null,false));
        aList.add(li.inflate(R.layout.exhibits_details_item3,null,false));
        pagerAdapter = new ExhibitsDetailsPagerAdapter(aList);

        //exhibitsDetailsPagerAdapter = new ExhibitsDetailsPagerAdapter(ExhibitsDetailsActivity.this);

       /* mBinding.exhibitsDetailViewpager.setAdapter(exhibitsDetailsPagerAdapter);
        mBinding.exhibitsDetailsTblayout.setupWithViewPager(mBinding.exhibitsDetailViewpager);
*/


        mBinding.exhibitsDetailViewpager.setAdapter(pagerAdapter);

        mBinding.exhibitsDetailViewpager.setCurrentItem(0);
        return this;
    }

    @Override
    public ExhibitsDetailsActivity bindData() {
        return this;
    }

    @Override
    public ExhibitsDetailsActivity bindEvent() {

        mBinding.exhibitsDetailsTblayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                mBinding.exhibitsDetailViewpager.setCurrentItem(position);
                /*if (position == 0){

                }else if (position == 1){
                    mBinding.exhibitsDetailViewpager.setCurrentItem(position);
                }else if (position == 2){
                    mBinding.exhibitsDetailViewpager.setCurrentItem(position);
                }*/

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //返回键
        mBinding.exhibitsDetailsBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //是否喜欢
        mBinding.exhibitsDetailsLikeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLike){
                    isLike = false;
                    mBinding.exhibitsDetailsLikeIv.setImageResource(R.mipmap.mainpage_exhibition_like_not_selected);
                }else {
                    isLike = true;
                    mBinding.exhibitsDetailsLikeIv.setImageResource(R.mipmap.mainpage_exhibition_like_selected);
                }
            }
        });

        //签到
        mBinding.exhibitsDetailsSignTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return this;
    }
}
