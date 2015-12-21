package com.best.demo.julegou;





import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.best.adapter.PingLunListViewAdapter;
import com.best.adapter.ViewPagerAdapter;
import com.best.bean.Colors;
import java.util.ArrayList;
import java.util.List;

import me.xiaopan.psts.PagerSlidingTabStrip;


/**
 * Created by dell2 on 2015/12/18.
 */

public class ShopDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopdetails_layout);
        PagerSlidingTabStrip pagerSlidingTabStrip1 = (PagerSlidingTabStrip) findViewById(R.id.slidingTabStrip_1);
        ViewPager viewPager1 = (ViewPager) findViewById(R.id.viewPager_1);
        init(0, pagerSlidingTabStrip1, viewPager1);
    }
    private void init(int index, PagerSlidingTabStrip pagerSlidingTabStrip, ViewPager viewPager){
        int length = pagerSlidingTabStrip.getTabCount();
        List<View> views = new ArrayList<View>(length);
        views.add(LayoutInflater.from(ShopDetailsActivity.this).inflate(R.layout.shop_viewpager,null));
        views.add(LayoutInflater.from(ShopDetailsActivity.this).inflate(R.layout.details_viewpager,null));
        views.add(LayoutInflater.from(ShopDetailsActivity.this).inflate(R.layout.wirte_viewpager,null));
        viewPager.setAdapter(new ViewPagerAdapter(views));
        viewPager.setCurrentItem(index < length ? index : length);
        pagerSlidingTabStrip.setViewPager(viewPager);
    }

}
