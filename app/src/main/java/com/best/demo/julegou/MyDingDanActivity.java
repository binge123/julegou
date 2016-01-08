package com.best.demo.julegou;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.best.adapter.DingDanAdapter;
import com.best.adapter.ViewPagerAdapter;
import com.best.bean.DingDan;
import com.best.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.xiaopan.psts.PagerSlidingTabStrip;

/**
 * Created by dell2 on 2015/12/21.
 */
public class MyDingDanActivity extends Activity {
    ListView all_lv,daifa_lv,daishou_lv,daiping_lv;
    List<DingDan> list = new ArrayList<>();
    String shopimg;
    String shopname;
    String shopprice;
    String buypeople;
    String dingdanstate;
    String buypepphone;
    String buypepaddress;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydingdan_layout);
        pd = ProgressDialog.show(this,"","正在加载中");
        PagerSlidingTabStrip pagerSlidingTabStrip1 = (PagerSlidingTabStrip) findViewById(R.id.slidingTabStrip);
        ViewPager viewPager1 = (ViewPager) findViewById(R.id.viewPager);
        init(0, pagerSlidingTabStrip1, viewPager1);
    }

    private void init(int index, PagerSlidingTabStrip pagerSlidingTabStrip, ViewPager viewPager) {
        int length = pagerSlidingTabStrip.getTabCount();
        List<View> views = new ArrayList<View>(length);
        View all = LayoutInflater.from(MyDingDanActivity.this).inflate(R.layout.activity_alldingdan, null);
        View daifa = LayoutInflater.from(MyDingDanActivity.this).inflate(R.layout.activity_daifahuo, null);
        View daishou = LayoutInflater.from(MyDingDanActivity.this).inflate(R.layout.activity_daishouhuo, null);
        View daiping = LayoutInflater.from(MyDingDanActivity.this).inflate(R.layout.activity_daipingjia, null);

        all_lv = (ListView) all.findViewById(R.id.listView_alldingdan);
        daifa_lv = (ListView) all.findViewById(R.id.listView_daifa);
        daishou_lv = (ListView) all.findViewById(R.id.listView_daishou);
        daiping_lv = (ListView) all.findViewById(R.id.listView_daiping);

        SharedPreferences sp = getSharedPreferences("token",Activity.MODE_PRIVATE);
        HashMap<String,String> map = new HashMap<>();
        Log.i("token", sp.getString("tokens", "1"));
        map.put("token", sp.getString("tokens", "1"));
        HttpUtils.httpPostRequest(map, "/AllOrders/allorder", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                pd.dismiss();
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
                all_lv.setAdapter(new DingDanAdapter(getApplicationContext(), list));
                daifa_lv.setAdapter(new DingDanAdapter(getApplicationContext(), list));

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
        views.add(all);
        views.add(daifa);
        views.add(daishou);
        views.add(daiping);
        viewPager.setAdapter(new ViewPagerAdapter(views));
        viewPager.setCurrentItem(index < length ? index : length);
        pagerSlidingTabStrip.setViewPager(viewPager);
    }
}
