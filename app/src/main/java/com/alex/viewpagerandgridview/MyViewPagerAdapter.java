package com.alex.viewpagerandgridview;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Alex on 2017/3/24.
 */

public class MyViewPagerAdapter extends PagerAdapter {
    private List<View> viewList;

    public MyViewPagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList!=null ? viewList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
    /**
     * 将当前的View添加到ViewGroup容器中
     * 这个方法，return一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPage上
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
