package com.best.demo.julegou;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2015/12/21.
 */
@ContentView(R.layout.activity_dianpuxq)
public class DianPuXQActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void dpxqback(View v){
        DianPuXQActivity.this.finish();
    }
}
