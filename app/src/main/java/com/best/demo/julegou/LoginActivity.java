package com.best.demo.julegou;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.best.utils.HttpUtils;
import com.best.utils.NetworkState;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.HashMap;


/**
 * Created by Administrator on 2015/12/16.
 */
public class LoginActivity extends Activity {
    private EditText et_username,et_password;
    private Button btn;
    private String username = null,password = null,code = null,token = null;
    private ImageView loginimg;
    boolean networkState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_username = (EditText) findViewById(R.id.login_zhang);
        et_password = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.button);
        loginimg = (ImageView) findViewById(R.id.loginimg);
        //返回按钮的点击事件
        loginimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });


        //登录按钮的点击事件
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = et_username.getText().toString();
                password = et_password.getText().toString();
                networkState = NetworkState.detect(LoginActivity.this);
                if("".equals(username) || "".equals(password)){
                    Toast.makeText(LoginActivity.this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    if( networkState ){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                HashMap<String,String> map = new HashMap<String, String>();
                                map.put("loginName",username);
                                map.put("loginPwd",password);
                                map.put("clientType","android");
                                HttpUtils.httpPostRequest(map, "/Api/login", new Callback.CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Log.i("token",s);
                                        try {
                                            JSONObject jsonObject = new JSONObject(s);
                                            code = jsonObject.getString("code");
                                            JSONObject object = jsonObject.getJSONObject("data");
                                            token = object.getString("token");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        Log.i("token",code);
                                        if("200".equals(code)){
                                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                            SharedPreferences sp = getSharedPreferences("token",Activity.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sp.edit();
                                            editor.putString("tokens",token);
                                            editor.commit();
                                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                            LoginActivity.this.startActivity(intent);
                                            LoginActivity.this.finish();
                                        }else {
                                            Toast.makeText(LoginActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable throwable, boolean b) {

                                    }

                                    @Override
                                    public void onCancelled(CancelledException e) {

                                    }

                                    @Override
                                    public void onFinished() {

                                    }
                                });
                            }
                        }).start();
                    }else {
                        Toast.makeText(LoginActivity.this,"网络不给力，请检查网络连接",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
