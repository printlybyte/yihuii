package com.example.yihuii.yihuii.MainFrame;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */

public class PagerAdapterr extends PagerAdapter {
    private List<LinearLayout> mDate=new ArrayList<>();
    public PagerAdapterr(List<LinearLayout>  mDate){
        this.mDate=mDate;
    }
    @Override
    public int getCount() {
        return mDate.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mDate.get(position));
        return mDate.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mDate.get(position));
    }
}

