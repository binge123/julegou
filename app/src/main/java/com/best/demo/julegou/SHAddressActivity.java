package com.best.demo.julegou;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import org.xutils.view.annotation.ContentView;

/**
 * Created by Administrator on 2015/12/31.
 */
@ContentView(R.layout.activity_shaddress)
public class SHAddressActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void newadd(View v){
        Intent i = new Intent(this,AddSHAdActiviy.class);
        startActivity(i);
    }

}
