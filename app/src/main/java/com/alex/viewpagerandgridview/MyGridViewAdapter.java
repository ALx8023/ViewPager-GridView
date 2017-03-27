package com.alex.viewpagerandgridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alex on 2017/3/24.
 */

public class MyGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<ProductBean> lists;//数据源
    private int mIndex; // 页数下标，标示第几页，从0开始
    private int mPagerSize;// 每页显示的最大的数量


    public MyGridViewAdapter(Context context, List<ProductBean> lists, int mIndex, int mPagerSize) {
        this.context = context;
        this.lists = lists;
        this.mIndex = mIndex;
        this.mPagerSize = mPagerSize;
    }

    /**
     * 先判断数据及的大小是否显示满本页lists.size() > (mIndex + 1)*mPagerSize
     * 如果满足，则此页就显示最大数量lists的个数
     * 如果不够显示每页的最大数量，那么剩下几个就显示几个
     */
    @Override
    public int getCount() {
        return lists.size() > (mIndex + 1) * mPagerSize ?
                mPagerSize : (lists.size() - mIndex * mPagerSize);
    }

    @Override
    public ProductBean getItem(int arg0) {
        return lists.get(arg0 + mIndex * mPagerSize);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0 + mIndex * mPagerSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_view, null);
            holder.tv_name = (TextView) convertView.findViewById(R.id.item_name);
            holder.iv_nul = (ImageView) convertView.findViewById(R.id.item_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //重新确定position因为拿到的总是数据源，数据源是分页加载到每页的GridView上的
        final int pos = position + mIndex * mPagerSize;//假设mPageSiez
        //假设mPagerSize=8，假如点击的是第二页（即mIndex=1）上的第二个位置item(position=1),那么这个item的实际位置就是pos=9
        holder.tv_name.setText(lists.get(pos).getName() + "");
        holder.iv_nul.setImageResource(lists.get(pos).getUrl());
        return convertView;
    }

    static class ViewHolder {
        private TextView tv_name;
        private ImageView iv_nul;
    }
}
