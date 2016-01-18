package com.best.demo.julegou;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/12/21.
 */
public class MyMessageActivity extends Activity {
    private ImageView messageimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymessage);
        messageimg = (ImageView) findViewById(R.id.messageimg);
        messageimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMessageActivity.this.finish();
            }
        });
    }
}
