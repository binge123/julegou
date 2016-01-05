package com.best.demo.julegou;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.best.utils.CubeTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/21.
 */
public class YinDaoActivity extends Activity {
    ViewPager viewPager;
    List<View> list  = new ArrayList<>();
    List<RadioButton> rbs= new ArrayList<>();
    View v1,v2,v3;
    RadioButton rb1,rb2,rb3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yindao);
        viewPager = (ViewPager) findViewById(R.id.view);
        SharedPreferences fsp = getSharedPreferences("sp",MODE_PRIVATE);
        SharedPreferences.Editor feditor= fsp.edit();
        feditor.putInt("first", 1);
        feditor.commit();
        v1 = LayoutInflater.from(this).inflate(R.layout.viewpagelayout1,null);
        v2 = LayoutInflater.from(this).inflate(R.layout.viewpagelayout2,null);
        v3 = LayoutInflater.from(this).inflate(R.layout.viewpagelayout3,null);
        rb1 = (RadioButton) findViewById(R.id.page1);
        rb2 = (RadioButton) findViewById(R.id.page2);
        rb3 = (RadioButton) findViewById(R.id.page3);
        rb1.setChecked(true);
        rbs.add(rb1);
        rbs.add(rb2);
        rbs.add(rb3);
        list.add(v1);
        list.add(v2);
        list.add(v3);
        viewPager.setPageTransformer(true,new CubeTransformer());
        viewPager.setAdapter(new MyAdapter());

    }
    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = list.get(position);
            container.addView(v,0);
            return v;
        }
    }
    public void tNext(View v)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        this.finish();
    }
}
