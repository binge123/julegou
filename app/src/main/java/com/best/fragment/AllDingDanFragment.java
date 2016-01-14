package com.best.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.best.adapter.DingDanAdapter;
import com.best.bean.DingDan;
import com.best.demo.julegou.R;
import com.best.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import medusa.theone.waterdroplistview.view.WaterDropListView;

/**
 * Created by dell2 on 2016/1/12.
 */
public class AllDingDanFragment extends Fragment{
    private String shopimg,shopname,shopprice,buypeople,dingdanstate,buypepphone,buypepaddress;
    private WaterDropListView waterDropListView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    waterDropListView.stopRefresh();
                    break;
                case 2:
                    waterDropListView.stopLoadMore();
                    break;
            }

        }
    };
    private List<DingDan> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_alldingdan,null);
        waterDropListView = (WaterDropListView) v.findViewById(R.id.listView_alldingdan);

        //连接数据库
        SharedPreferences sp = getActivity().getSharedPreferences("token", Activity.MODE_PRIVATE);
        HashMap<String,String> map = new HashMap<>();
        Log.i("token", sp.getString("tokens", "1"));
        map.put("token", sp.getString("tokens", "1"));
        HttpUtils.httpPostRequest(map, "/AllOrders/allorder", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
//
                Log.i("token", s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        shopimg = object.getString("goodsThums");
                        shopname = object.getString("goodsName");
                        shopprice = object.getString("goodsPrice");
                        buypeople = object.getString("userName");
                        dingdanstate = object.getString("orderStatus");
                        buypepphone = object.getString("userPhone");
                        buypepaddress = object.getString("userAddress");
                        list.add(new DingDan(shopimg, shopname, shopprice, buypeople, dingdanstate, buypepphone, buypepaddress));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                waterDropListView.setAdapter(new DingDanAdapter(getContext(), list));
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
        waterDropListView.setWaterDropListViewListener(new WaterDropListView.IWaterDropListViewListener() {
            @Override
            public void onRefresh() {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            handler.sendEmptyMessage(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onLoadMore() {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            handler.sendEmptyMessage(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        waterDropListView.setPullLoadEnable(true);
        return v;
    }
}
