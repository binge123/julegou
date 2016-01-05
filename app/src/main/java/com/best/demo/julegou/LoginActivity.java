package com.best.demo.julegou;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2015/12/16.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity{
    @ViewInject(R.id.login_zhang)
    EditText lgzhang;
    @ViewInject(R.id.login_ma)
    EditText lgma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Event(value = R.id.login_button)
    private void loginClick(View v){
        String zhang  = lgzhang.getText().toString();
        String ma  = lgma.getText().toString();
        if (zhang.length()>6&&zhang.length()<18){
            
        }else{
            Toast.makeText(this, "账号长度不正确", Toast.LENGTH_SHORT).show();
        }
    }
}
