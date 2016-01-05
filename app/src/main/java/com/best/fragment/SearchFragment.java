package com.best.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.best.adapter.SearchGridViewAdapter;
import com.best.bean.GridViewItem;
import com.best.demo.julegou.R;
import com.best.demo.julegou.ShopDetailsActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dell2 on 2015/12/17.
 */
public class SearchFragment extends Fragment {
    List<GridViewItem> list = new ArrayList<>();
    GridView gv;
    private MapView mapView;
    private AMap aMap;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    static int biao = 0;
    double j,w;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search,container,false);
        gv = (GridView) v.findViewById(R.id.gridView);
        list.add(new GridViewItem("1", "2", "3", "4", "5"));
        list.add(new GridViewItem("1", "2", "3", "4", "5"));
        list.add(new GridViewItem("1", "2", "3", "4", "5"));
        list.add(new GridViewItem("1", "2", "3", "4", "5"));
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (biao == 0){
                    if (amapLocation != null) {
                        if (amapLocation.getErrorCode() == 0) {
                            //定位成功回调信息，设置相关消息
                            amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                            j = amapLocation.getLatitude();//获取纬度
                            w = amapLocation.getLongitude();//获取经度
                            amapLocation.getAccuracy();//获取精度信息
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(amapLocation.getTime());
                            df.format(date);//定位时间
                            amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果
                            amapLocation.getCountry();//国家信息
                            amapLocation.getProvince();//省信息
                            amapLocation.getCity();//城市信息
                            amapLocation.getDistrict();//城区信息
                            amapLocation.getRoad();//街道信息
                            amapLocation.getCityCode();//城市编码
                            amapLocation.getAdCode();//地区编码
                            CircleOptions circleOptions = new CircleOptions();
                            Log.i("yinxu", j + " " + w);
                            LatLng l = new LatLng(j,w);
                            circleOptions.center(l);
                            circleOptions.radius(10);
                            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(l,14));
                            aMap.clear();
                            aMap.addCircle(circleOptions);
                            biao+=1;
                        } else {
                            //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                            Log.e("AmapError", "location Error, ErrCode:"
                                    + amapLocation.getErrorCode() + ", errInfo:"
                                    + amapLocation.getErrorInfo());
                        }
                    }
                }

            }
        };
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

        //在onCreat方法中给aMap对象赋值
        mapView = (MapView) v.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 必须要写
        aMap = mapView.getMap();
        final Intent intent = new Intent(getActivity(), ShopDetailsActivity.class);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(intent);
            }
        });
        gv.setAdapter(new SearchGridViewAdapter(getContext(),list));

        return v;
    }
}
