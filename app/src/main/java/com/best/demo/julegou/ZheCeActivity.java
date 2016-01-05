package com.best.demo.julegou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.best.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by Administrator on 2015/12/17.
 */
@ContentView(R.layout.activity_zhuce)
public class ZheCeActivity extends BaseActivity {

    @ViewInject(R.id.zhuce_zhang)
    EditText username;
    @ViewInject(R.id.zhuce_ma)
    EditText yzm;
    @ViewInject(R.id.zhuce_mima)
    EditText password;

    HashMap<String,String> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //启动短信验证
        SMSSDK.initSDK(this, "da9170a37e88", "7ad0f84c0bdd803d6d9eb4ece05bf56f");
        EventHandler eh=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);
    }
    @Event(value = R.id.zhuce_yan)
     private void yanClink(View v){
        Log.i("heheh","6666666");
        SMSSDK.getVerificationCode("86", username.getText().toString());

    }
    public void yanzheng(View v){
        if (!TextUtils.isEmpty(yzm.getText().toString())) {
            SMSSDK.submitVerificationCode("86", username.getText().toString(), yzm.getText().toString());
            zhuce();
        } else {
            Toast.makeText(getApplicationContext(), "验证码不能为空", Toast.LENGTH_LONG).show();
        }
    }
    public void zhuce(){
        //属性
        String types = "/Api/register";
        //获取用户名和密码
        String usernametext = username.getText().toString();
        String passwordtext = password.getText().toString();
        String siyao = "sunsai123";
        map.put("userPhone",usernametext);
        map.put("loginPwd", passwordtext);
        map.put("isTure", "1");
        HttpUtils.httpGetRequest( map,types,new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String s) {
                System.out.println("onSuccess::::::" + s.toString());
                String ct = null;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    ct = jsonObject.getString("code");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (ct.equals("200")) {
                    startActivity(new Intent(ZheCeActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(ZheCeActivity.this,"请输入正确的手机号格式",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                System.out.println("onError::::::" + throwable.getMessage());
                Toast.makeText(ZheCeActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("GG", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Toast.makeText(getApplicationContext(), "验证成功", Toast.LENGTH_SHORT).show();

                    Log.i("GG","成功了走这里");
                }
                else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){//返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "验证失败", Toast.LENGTH_SHORT).show();
                ((Throwable) data).printStackTrace();
            }
        }
    };
}
