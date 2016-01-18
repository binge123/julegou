package com.best.demo.julegou;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.best.adapter.PingLunListViewAdapter;
import com.best.bean.PingLun;

import java.util.ArrayList;

/**
 * Created by dell2 on 2016/1/18.
 */
public class PingLunListActivity extends BaseActivity {
    private ListView pinglun_lv;
    private ImageView backimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinglun_all_layout);
        pinglun_lv = (ListView) findViewById(R.id.pinglun_listview);
        backimg = (ImageView) findViewById(R.id.backimg);
        //返回按钮的点击事件
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PingLunListActivity.this.finish();
            }
        });
        pinglun_lv.setAdapter(new PingLunListViewAdapter(getApplicationContext(),(ArrayList<PingLun>)getIntent().getSerializableExtra("listobj")));
    }
}
