package com.best.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.best.demo.julegou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell2 on 2016/1/6.
 */
public class BoyClothingFragment extends Fragment {
    ViewPager vp;
    GridView gv;
    List<View> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shopclassify_listview_layout,null);
        vp = (ViewPager) v.findViewById(R.id.viewPager);
        gv = (GridView) v.findViewById(R.id.mygridview);

        return v;
    }
}
