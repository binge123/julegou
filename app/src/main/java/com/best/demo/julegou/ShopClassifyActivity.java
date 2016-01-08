package com.best.demo.julegou;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.best.fragment.BoyClothingFragment;
import com.best.fragment.BoyShoesFragment;
import com.best.fragment.CarsFragment;
import com.best.fragment.FoodFragment;
import com.best.fragment.FurnitureFragment;
import com.best.fragment.GirlClothingFragment;
import com.best.fragment.GirlShoesFragment;
import com.best.fragment.JewelryFragment;
import com.best.fragment.NumberFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by dell2 on 2016/1/6.
 */
@ContentView(R.layout.activity_shopclassify)
public class ShopClassifyActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.radioButton1)
    private RadioButton rb1;

    @ViewInject(R.id.radioButton2)
    private RadioButton rb2;

    @ViewInject(R.id.radioButton3)
    private RadioButton rb3;

    @ViewInject(R.id.radioButton4)
    private RadioButton rb4;

    @ViewInject(R.id.radioButton5)
    private RadioButton rb5;

    @ViewInject(R.id.radioButton6)
    private RadioButton rb6;

    @ViewInject(R.id.radioButton7)
    private RadioButton rb7;

    @ViewInject(R.id.radioButton8)
    private RadioButton rb8;

    @ViewInject(R.id.radioButton9)
    private RadioButton rb9;

    FragmentManager fm;
    FragmentTransaction ftt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);
        rb5.setOnClickListener(this);
        rb6.setOnClickListener(this);
        rb7.setOnClickListener(this);
        rb8.setOnClickListener(this);
        rb9.setOnClickListener(this);
        fm = getSupportFragmentManager();
        rb1.setChecked(true);
        if(savedInstanceState == null){
            ftt = fm.beginTransaction();
            BoyClothingFragment bf = new BoyClothingFragment();
            ftt.add(R.id.fragment_parent,bf,"radioButton1");
            ftt.commit();
        }
    }

    @Override
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
                BoyClothingFragment bf = new BoyClothingFragment();
                ftt.add(R.id.fragment_parent,bf,"radioButton1");
            }
        }else if(id == R.id.radioButton2){
            if(fm.findFragmentByTag("radioButton2")!=null){
                ftt.show(fm.findFragmentByTag("radioButton2"));
            }else{
                GirlClothingFragment gf = new GirlClothingFragment();
                ftt.add(R.id.fragment_parent,gf,"radioButton2");
            }
        }else if(id == R.id.radioButton3){
            if(fm.findFragmentByTag("radioButton3")!=null){
                ftt.show(fm.findFragmentByTag("radioButton3"));
            }else{
                BoyShoesFragment af = new BoyShoesFragment();
                ftt.add(R.id.fragment_parent,af,"radioButton3");
            }
        }else if(id == R.id.radioButton4){
            if(fm.findFragmentByTag("radioButton4")!=null){
                ftt.show(fm.findFragmentByTag("radioButton4"));
            }else{
                GirlShoesFragment sf = new GirlShoesFragment();
                ftt.add(R.id.fragment_parent,sf,"radioButton4");
            }
        }else if(id == R.id.radioButton5){
            if(fm.findFragmentByTag("radioButton4")!=null){
                ftt.show(fm.findFragmentByTag("radioButton4"));
            }else{
                FoodFragment food = new FoodFragment();
                ftt.add(R.id.fragment_parent,food,"radioButton4");
            }
        }else if(id == R.id.radioButton6){
            if(fm.findFragmentByTag("radioButton4")!=null){
                ftt.show(fm.findFragmentByTag("radioButton4"));
            }else{
                NumberFragment nf = new NumberFragment();
                ftt.add(R.id.fragment_parent,nf,"radioButton4");
            }
        }else if(id == R.id.radioButton7){
            if(fm.findFragmentByTag("radioButton4")!=null){
                ftt.show(fm.findFragmentByTag("radioButton4"));
            }else{
                JewelryFragment jf = new JewelryFragment();
                ftt.add(R.id.fragment_parent,jf,"radioButton4");
            }
        }else if(id == R.id.radioButton8){
            if(fm.findFragmentByTag("radioButton4")!=null){
                ftt.show(fm.findFragmentByTag("radioButton4"));
            }else{
                FurnitureFragment fun = new FurnitureFragment();
                ftt.add(R.id.fragment_parent,fun,"radioButton4");
            }
        }else {
            if(fm.findFragmentByTag("radioButton4")!=null){
                ftt.show(fm.findFragmentByTag("radioButton4"));
            }else{
                CarsFragment cf = new CarsFragment();
                ftt.add(R.id.fragment_parent,cf,"radioButton4");
            }
        }
        ftt.commit();
    }
}
