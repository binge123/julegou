package com.best.demo.julegou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Administrator on 2015/12/21.
 */
public class MyZuJiActivity extends Activity {

    ListView zjlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myzuji);
        zjlist = (ListView)findViewById(R.id.myzj_list);
        zjlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
    public void zjback(View v){
        MyZuJiActivity.this.finish();
    }
}
