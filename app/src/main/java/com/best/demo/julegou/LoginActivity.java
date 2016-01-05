package com.best.demo.julegou;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import android.widget.EditText;
import android.widget.Toast;
=======
>>>>>>> develop

import org.xutils.view.annotation.Event;

/**
 * Created by Administrator on 2015/12/16.
 */
public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
<<<<<<< HEAD
    @Event(value = R.id.login_button)
    private void loginClick(View v){
        String zhang  = lgzhang.getText().toString();
        String ma  = lgma.getText().toString();
        if (zhang.length()>6&&zhang.length()<18){
            
        }else{
            Toast.makeText(this, "账号长度不正确", Toast.LENGTH_SHORT).show();
        }
    }
=======

>>>>>>> develop
}
