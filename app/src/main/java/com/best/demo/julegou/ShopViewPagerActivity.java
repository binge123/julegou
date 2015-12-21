package com.best.demo.julegou;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.best.adapter.PingLunListViewAdapter;

/**
 * Created by dell2 on 2015/12/21.
 */
public class ShopViewPagerActivity extends Activity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_viewpager);
        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new PingLunListViewAdapter(getApplicationContext()));
        //设置listView不让滚动
        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
