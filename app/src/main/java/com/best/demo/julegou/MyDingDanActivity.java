package com.best.demo.julegou;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.best.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import me.xiaopan.psts.PagerSlidingTabStrip;

/**
 * Created by dell2 on 2015/12/21.
 */
public class MyDingDanActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydingdan_layout);
        PagerSlidingTabStrip pagerSlidingTabStrip1 = (PagerSlidingTabStrip) findViewById(R.id.slidingTabStrip);
        ViewPager viewPager1 = (ViewPager) findViewById(R.id.viewPager);
        init(0, pagerSlidingTabStrip1, viewPager1);
    }

    private void init(int index, PagerSlidingTabStrip pagerSlidingTabStrip, ViewPager viewPager) {
        int length = pagerSlidingTabStrip.getTabCount();
        List<View> views = new ArrayList<View>(length);
        View all = LayoutInflater.from(MyDingDanActivity.this).inflate(R.layout.activity_alldingdan, null);
        View daifa = LayoutInflater.from(MyDingDanActivity.this).inflate(R.layout.activity_daifahuo, null);
        View daishou = LayoutInflater.from(MyDingDanActivity.this).inflate(R.layout.activity_daishouhuo, null);
        View daiping = LayoutInflater.from(MyDingDanActivity.this).inflate(R.layout.activity_daipingjia, null);
        views.add(all);
        views.add(daifa);
        views.add(daishou);
        views.add(daiping);

        viewPager.setAdapter(new ViewPagerAdapter(views));
        viewPager.setCurrentItem(index < length ? index : length);
        pagerSlidingTabStrip.setViewPager(viewPager);
    }
}
