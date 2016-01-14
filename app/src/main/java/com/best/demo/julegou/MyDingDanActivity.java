package com.best.demo.julegou;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.best.fragment.AllDingDanFragment;
import com.best.fragment.DaiFaFragment;
import com.best.fragment.DaiPingFragment;
import com.best.fragment.DaiShouFragment;


/**
 * Created by dell2 on 2015/12/21.
 */
public class MyDingDanActivity extends AppCompatActivity implements View.OnClickListener {
//    ProgressDialog pd;
    private RadioGroup rg1;
    private RadioButton rb1,rb2,rb3,rb4;
    private FragmentManager fm;
    private FragmentTransaction ftt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydingdan_layout);
//        pd = ProgressDialog.show(this,"","正在加载中");
//        PagerSlidingTabStrip pagerSlidingTabStrip1 = (PagerSlidingTabStrip) findViewById(R.id.slidingTabStrip);
//        ViewPager viewPager1 = (ViewPager) findViewById(R.id.viewPager);
//        init(0, pagerSlidingTabStrip1, viewPager1);
        rg1 = (RadioGroup) findViewById(R.id.rg);
        rb1 = (RadioButton) findViewById(R.id.radioButton1);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);

        fm = getSupportFragmentManager();
        rb1.setChecked(true);
        if(savedInstanceState == null){
            ftt = fm.beginTransaction();
            AllDingDanFragment ff = new AllDingDanFragment();
            ftt.add(R.id.fragment_parent,ff,"radioButton1");
            ftt.commit();
        }
        rb1.setTextColor(getResources().getColor(R.color.blue2));
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rb1.setTextColor(getResources().getColor(R.color.blue2));
                rb2.setTextColor(getResources().getColor(R.color.blue2));
                rb3.setTextColor(getResources().getColor(R.color.blue2));
                rb4.setTextColor(getResources().getColor(R.color.blue2));
                int radioButtonId = group.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton) MyDingDanActivity.this.findViewById(radioButtonId);
                rb.setTextColor(getResources().getColor(R.color.bai));
            }
        });
    }

//    private void init(int index, PagerSlidingTabStrip pagerSlidingTabStrip, ViewPager viewPager) {
//        int length = pagerSlidingTabStrip.getTabCount();
//        List<View> views = new ArrayList<View>(length);
//        View all = LayoutInflater.from(MyDingDanActivity.this).inflate(R.layout.activity_alldingdan, null);
//        View daifa = LayoutInflater.from(MyDingDanActivity.this).inflate(R.layout.activity_daifahuo, null);
//        View daishou = LayoutInflater.from(MyDingDanActivity.this).inflate(R.layout.activity_daishouhuo, null);
//        View daiping = LayoutInflater.from(MyDingDanActivity.this).inflate(R.layout.activity_daipingjia, null);
//
//        waterDropListView = (WaterDropListView) all.findViewById(R.id.listView_alldingdan);
////        all_lv = (ListView) all.findViewById(R.id.listView_alldingdan);
//        daifa_lv = (ListView) all.findViewById(R.id.listView_daifa);
//        daishou_lv = (ListView) all.findViewById(R.id.listView_daishou);
//        daiping_lv = (ListView) all.findViewById(R.id.listView_daiping);
//
//        SharedPreferences sp = getSharedPreferences("token",Activity.MODE_PRIVATE);
//        HashMap<String,String> map = new HashMap<>();
//        Log.i("token", sp.getString("tokens", "1"));
//        map.put("token", sp.getString("tokens", "1"));
//        HttpUtils.httpPostRequest(map, "/AllOrders/allorder", new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String s) {
////
//                Log.i("token", s);
//                try {
//                    JSONObject jsonObject = new JSONObject(s);
//                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject object = jsonArray.getJSONObject(i);
//                        shopimg = object.getString("goodsThums");
//                        shopname = object.getString("goodsName");
//                        shopprice = object.getString("goodsPrice");
//                        buypeople = object.getString("userName");
//                        dingdanstate = object.getString("orderStatus");
//                        buypepphone = object.getString("userPhone");
//                        buypepaddress = object.getString("userAddress");
//                        list.add(new DingDan(shopimg, shopname, shopprice, buypeople, dingdanstate, buypepphone, buypepaddress));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                waterDropListView.setAdapter(new DingDanAdapter(getApplicationContext(), list));
//                waterDropListView.setWaterDropListViewListener(MyDingDanActivity.this);
//                waterDropListView.setPullLoadEnable(true);
////                daifa_lv.setAdapter(new DingDanAdapter(getApplicationContext(), list));
//
//            }
//
//            @Override
//            public void onError(Throwable throwable, boolean b) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException e) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//        views.add(all);
//        views.add(daifa);
//        views.add(daishou);
//        views.add(daiping);
//        viewPager.setAdapter(new ViewPagerAdapter(views));
//        viewPager.setCurrentItem(index < length ? index : length);
//        pagerSlidingTabStrip.setViewPager(viewPager);
//    }

    @Override
    public void onClick(View v) {
        ftt = fm.beginTransaction();
        if(fm.findFragmentByTag("radioButton1") != null){
            ftt.hide(fm.findFragmentByTag("radioButton1"));
        }
        if(fm.findFragmentByTag("radioButton2") != null){
            ftt.hide(fm.findFragmentByTag("radioButton2"));
        }
        if(fm.findFragmentByTag("radioButton3") != null){
            ftt.hide(fm.findFragmentByTag("radioButton3"));
        }
        if(fm.findFragmentByTag("radioButton4") != null){
            ftt.hide(fm.findFragmentByTag("radioButton4"));
        }
        int id = v.getId();
        if(id == R.id.radioButton1){
            if(fm.findFragmentByTag("radioButton1")!=null){
                ftt.show(fm.findFragmentByTag("radioButton1"));
            }else{
                AllDingDanFragment ff = new AllDingDanFragment();
                ftt.add(R.id.fragment_parent,ff,"radioButton1");
            }
        }else if(id == R.id.radioButton2){
            if(fm.findFragmentByTag("radioButton2")!=null){
                ftt.show(fm.findFragmentByTag("radioButton2"));
            }else{
                DaiFaFragment bf = new DaiFaFragment();
                ftt.add(R.id.fragment_parent,bf,"radioButton2");
            }
        }else if(id == R.id.radioButton3){
            if(fm.findFragmentByTag("radioButton3")!=null){
                ftt.show(fm.findFragmentByTag("radioButton3"));
            }else{
                DaiShouFragment af = new DaiShouFragment();
                ftt.add(R.id.fragment_parent,af,"radioButton3");
            }
        }else {
            if(fm.findFragmentByTag("radioButton4")!=null){
                ftt.show(fm.findFragmentByTag("radioButton4"));
            }else{
                DaiPingFragment sf = new DaiPingFragment();
                ftt.add(R.id.fragment_parent,sf,"radioButton4");
            }
        }
        ftt.commit();
    }
}
