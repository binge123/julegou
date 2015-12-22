package com.best.demo.julegou;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.best.fragment.CarFragment;
import com.best.fragment.FirstFragment;
import com.best.fragment.MyFragment;
import com.best.fragment.SearchFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.smssdk.SMSSDK;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.radioButton1)
    private RadioButton rb1;

    @ViewInject(R.id.radioButton2)
    private RadioButton rb2;

    @ViewInject(R.id.radioButton3)
    private RadioButton rb3;

    @ViewInject(R.id.radioButton4)
    private RadioButton rb4;

    FragmentManager fm;
    FragmentTransaction ftt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);
        fm = getSupportFragmentManager();
        rb1.setChecked(true);
        SMSSDK.initSDK(this, "da9170a37e88", "7ad0f84c0bdd803d6d9eb4ece05bf56f");
        if(savedInstanceState == null){
            ftt = fm.beginTransaction();
            FirstFragment ff = new FirstFragment();
            ftt.add(R.id.fragment_parent,ff,"radioButton1");
            ftt.commit();
        }
    }
    public void onClick(View v) {
        ftt = fm.beginTransaction();
        if(fm.findFragmentByTag("radioButton1") != null){
            ftt.hide(fm.findFragmentByTag("radioButton1"));
        }
        if(fm.findFragmentByTag("radioButton2") != null){
            ftt.hide(fm.findFragmentByTag("radioButton2"));
        }
        if(fm.findFragmentByTag("radioButton3") != null){
            ftt.hide(fm.findFragmentByTag("radioButton3"));
        }
        if(fm.findFragmentByTag("radioButton4") != null){
            ftt.hide(fm.findFragmentByTag("radioButton4"));
        }
        int id = v.getId();
        if(id == R.id.radioButton1){
            if(fm.findFragmentByTag("radioButton1")!=null){
                ftt.show(fm.findFragmentByTag("radioButton1"));
            }else{
                FirstFragment ff = new FirstFragment();
                ftt.add(R.id.fragment_parent,ff,"radioButton1");
            }
        }else if(id == R.id.radioButton2){
            if(fm.findFragmentByTag("radioButton2")!=null){
                ftt.show(fm.findFragmentByTag("radioButton2"));
            }else{
                SearchFragment bf = new SearchFragment();
                ftt.add(R.id.fragment_parent,bf,"radioButton2");
            }
        }else if(id == R.id.radioButton3){
            if(fm.findFragmentByTag("radioButton3")!=null){
                ftt.show(fm.findFragmentByTag("radioButton3"));
            }else{
                CarFragment af = new CarFragment();
                ftt.add(R.id.fragment_parent,af,"radioButton3");
            }
        }else {
            if(fm.findFragmentByTag("radioButton4")!=null){
                ftt.show(fm.findFragmentByTag("radioButton4"));
            }else{
                MyFragment sf = new MyFragment();
                ftt.add(R.id.fragment_parent,sf,"radioButton4");
            }
        }

        ftt.commit();
    }
}
