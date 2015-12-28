package com.best.demo.julegou;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2015/12/21.
 */
@ContentView(R.layout.activity_myguanzhu)
public class MyGuanZhuActivity extends BaseActivity{

    @ViewInject(R.id.mygz_rl2)
    RadioGroup rg1;
    @ViewInject(R.id.mygz_dp)
    RadioButton rb1;
    @ViewInject(R.id.mygz_sp)
    RadioButton rb2;
    @ViewInject(R.id.gz_list)
    ListView gzlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rb1.isChecked();
        rb1.setTextColor(getResources().getColor(R.color.blue2));
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rb1.setTextColor(getResources().getColor(R.color.blue2));
                rb2.setTextColor(getResources().getColor(R.color.blue2));
                int radioButtonId = group.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton) MyGuanZhuActivity.this.findViewById(radioButtonId);
                rb.setTextColor(getResources().getColor(R.color.bai));
            }
        });
    }
}
