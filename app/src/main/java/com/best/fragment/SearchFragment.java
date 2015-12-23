package com.best.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.best.adapter.SearchGridViewAdapter;
import com.best.bean.GridViewItem;
import com.best.demo.julegou.R;
import com.best.demo.julegou.ShopDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell2 on 2015/12/17.
 */
public class SearchFragment extends Fragment {
    List<GridViewItem> list = new ArrayList<>();
    GridView gv;
    private MapView mapView;
    private AMap aMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search,container,false);
        gv = (GridView) v.findViewById(R.id.gridView);
        list.add(new GridViewItem("1","2","3","4","5"));
        list.add(new GridViewItem("1", "2", "3", "4", "5"));
        list.add(new GridViewItem("1","2","3","4","5"));
        list.add(new GridViewItem("1","2","3","4","5"));
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
