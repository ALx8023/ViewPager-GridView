package com.alex.viewpagerandgridview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;//轮播
    private LinearLayout group;//圆点指示
    private ImageView[] ivPoints;//圆点图片集合
    private int totalPage;//总页数
    private int mPageSize = 8;//每页显示的最大的数量
    private List<ProductBean> listData;//数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private String[] proName = {"美食", "甜品饮料", "商店超市", "预定早餐",
            "果蔬生鲜", "新店特惠", "准时达", "简餐",
            "土豪推荐", "鲜花蛋糕", "汉堡", "日韩料理",
            "麻辣烫", "披萨意面", "川湘菜", "包子粥店"};
    private int[] proImg = {R.mipmap.ms, R.mipmap.tpyl, R.mipmap.sdcs, R.mipmap.ydzc,
            R.mipmap.gssx, R.mipmap.xdth, R.mipmap.zsd, R.mipmap.jc,
            R.mipmap.thtj, R.mipmap.xhdg, R.mipmap.hb, R.mipmap.rhll,
            R.mipmap.mlt, R.mipmap.psym, R.mipmap.cxc, R.mipmap.bzzd};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //添加业务逻辑
        initData();

    }


    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        group = (LinearLayout) findViewById(R.id.points);
        listData = new ArrayList<ProductBean>();
        for (int i = 0; i < proName.length; i++) {
            listData.add(new ProductBean(proName[i], proImg[i]));
        }
    }

    private void initData() {
        //总的页数向上取整
        totalPage = (int) Math.ceil(listData.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<View>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(this, R.layout.item_gridview, null);
            gridView.setAdapter(new MyGridViewAdapter(this, listData, i, mPageSize));
            //添加item点击监听
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    Object obj = gridView.getItemAtPosition(position);
                    if (obj != null && obj instanceof ProductBean) {
                        System.out.println(obj);
                        Toast.makeText(MainActivity.this, ((ProductBean) obj).getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        viewPager.setAdapter(new MyViewPagerAdapter(viewPagerList));

        //添加小圆点
        ivPoints = new ImageView[totalPage];
        for (int i = 0; i < totalPage; i++) {
            //循坏加入点点图片组
            ivPoints[i] = new ImageView(this);
            if (i == 0) {
                ivPoints[i].setImageResource(R.mipmap.page_focuese);
            } else {
                ivPoints[i].setImageResource(R.mipmap.page_unfocused);
            }
            ivPoints[i].setPadding(8, 8, 8, 8);
            group.addView(ivPoints[i]);
        }
        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < totalPage; i++) {
                    if (i == position) {
                        ivPoints[i].setImageResource(R.mipmap.page_focuese);
                    } else {
                        ivPoints[i].setImageResource(R.mipmap.page_unfocused);
                    }
                }
            }
        });
    }
}

