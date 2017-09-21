package com.bawei.slidingmenutablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> tabs = new ArrayList<>();
    List<View> views = new ArrayList<>();
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //处理测滑
        //第一步创建slidingmenu
        final SlidingMenu slidingMenu = new SlidingMenu(this);
        //设置测滑从哪个方向滑出
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置测滑宽度
        slidingMenu.setBehindOffset(200);
        //设置slidingmenu依附在activity
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        //设置slidingmenu布局
        slidingMenu.setMenu(R.layout.menu);
        //找控件
        ImageView iv = (ImageView) findViewById(R.id.iv);
        TabLayout tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        //创建数据源
        tabs.add("NO:1");
        tabs.add("NO:2");
        tabs.add("NO:3");
        tabs.add("NO:4");
        tabs.add("NO:5");
        View view1 = View.inflate(this, R.layout.page, null);
        View view2 = View.inflate(this, R.layout.page, null);
        View view3 = View.inflate(this, R.layout.page, null);
        View view4 = View.inflate(this, R.layout.page, null);
        View view5 = View.inflate(this, R.layout.page, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        views.add(view5);

        //设置默认的模式
        tab.setTabMode(TabLayout.MODE_FIXED);
        //添加数据
        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addTab(tab.newTab().setText(tabs.get(2)));
        tab.addTab(tab.newTab().setText(tabs.get(3)));
        tab.addTab(tab.newTab().setText(tabs.get(4)));
        MyViewPager viewPager = new MyViewPager();
        vp.setAdapter(viewPager);
        //进行关联
        tab.setupWithViewPager(vp);
        tab.setTabsFromPagerAdapter(viewPager);

        //通过点击图片按钮控制slidingmenu
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Toggle the SlidingMenu. If it is open, it will be closed, and vice versa.
                 */
                slidingMenu.toggle();
            }
        });

    }

    private class MyViewPager extends PagerAdapter {
     //viewpager对应的页面跟tablayout的选项卡一一对应
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = views.get(position);
            vp.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = views.get(position);
            vp.removeView(view);
        }
    }
}
