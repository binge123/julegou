package com.best.demo.julegou;





import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.best.fragment.PingLunFragment;
import com.best.fragment.ShopDetailFragment;
import com.best.fragment.ShopFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


/**
 * Created by dell2 on 2015/12/18.
 */
@ContentView(R.layout.shopdetails_layout)
public class ShopDetailsActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.radioButton1)
    private RadioButton rb1;

    @ViewInject(R.id.radioButton2)
    private RadioButton rb2;

    @ViewInject(R.id.radioButton3)
    private RadioButton rb3;

    @ViewInject(R.id.backimg)
    private ImageView backimg;

    FragmentManager fm;
    FragmentTransaction ftt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        backimg.setOnClickListener(this);
        fm = getSupportFragmentManager();
        rb1.setChecked(true);
        if(savedInstanceState == null){
            ftt = fm.beginTransaction();
            ShopFragment ff = new ShopFragment();
            ftt.add(R.id.fragment_parent,ff,"radioButton1");
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
        int id = v.getId();
        if(id == R.id.radioButton1){
            if(fm.findFragmentByTag("radioButton1")!=null){
                ftt.show(fm.findFragmentByTag("radioButton1"));
            }else{
                ShopFragment ff = new ShopFragment();
                ftt.add(R.id.fragment_parent,ff,"radioButton1");
            }
        }else if(id == R.id.radioButton2){
            if(fm.findFragmentByTag("radioButton2")!=null){
                ftt.show(fm.findFragmentByTag("radioButton2"));
            }else{
                ShopDetailFragment bf = new ShopDetailFragment();
                ftt.add(R.id.fragment_parent,bf,"radioButton2");
            }
        }else if(id == R.id.radioButton3){
            if(fm.findFragmentByTag("radioButton3")!=null){
                ftt.show(fm.findFragmentByTag("radioButton3"));
            }else{
                PingLunFragment af = new PingLunFragment();
                ftt.add(R.id.fragment_parent,af,"radioButton3");
            }
        }else if(id == R.id.backimg){
            ShopDetailsActivity.this.finish();
        }
        ftt.commit();
    }
}
