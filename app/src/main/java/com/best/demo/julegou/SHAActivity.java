package com.best.demo.julegou;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.best.adapter.SHAddAdapter;
import com.best.bean.SHAddress;
import com.best.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/1/6.
 */
@ContentView(R.layout.activity_shaddress)
public class SHAActivity extends BaseActivity {

    @ViewInject(R.id.shadd_list)
    ListView list;
    List<SHAddress> lists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String,String> map = new HashMap<>();
                map.put("sql","select * from wst_user_address");
                HttpUtils.httpGetRequest(map, "/Api/exeQuery", new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.i("jsonss", s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                Log.i("jsonss",object.toString());
                                lists.add(new SHAddress(object.getString("userName"),object.getString("userPhone"),object.getString("areaId1"),object.getString("areaId2"),object.getString("areaId3"),object.getString("address")));
                            }
                            list.setAdapter(new SHAddAdapter(lists,SHAActivity.this));
                        } catch (JSONException e) {
                            e.printStackTrace();
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
    }
    @Event(value = R.id.shadd_add)
    private void addaddClick(View v){
        Intent i = new Intent(this,AddSHAActivity.class);
        startActivity(i);
    }
}
