package com.best.demo.julegou;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.best.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import me.xiaopan.psts.PagerSlidingTabStrip;

/**
 * Created by dell2 on 2015/12/21.
 */
public class MyIntegralActivity extends Activity {
    private ImageView integralimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myintegral);
        integralimg = (ImageView) findViewById(R.id.integralimg);
        //返回按钮的点击事件
        integralimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyIntegralActivity.this.finish();
            }
        });
        PagerSlidingTabStrip pagerSlidingTabStrip1 = (PagerSlidingTabStrip) findViewById(R.id.slidingTabStrip);
        ViewPager viewPager1 = (ViewPager) findViewById(R.id.viewPager);
        init(0, pagerSlidingTabStrip1, viewPager1);
    }

    private void init(int index, PagerSlidingTabStrip pagerSlidingTabStrip, ViewPager viewPager) {
        int length = pagerSlidingTabStrip.getTabCount();
        List<View> views = new ArrayList<View>(length);
        views.add(LayoutInflater.from(MyIntegralActivity.this).inflate(R.layout.userintegral_layout, null));
        views.add(LayoutInflater.from(MyIntegralActivity.this).inflate(R.layout.shopintegral_layout, null));
        viewPager.setAdapter(new ViewPagerAdapter(views));
        viewPager.setCurrentItem(index < length ? index : length);
        pagerSlidingTabStrip.setViewPager(viewPager);
    }
}
