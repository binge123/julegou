package com.best.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.best.demo.julegou.MyDingDanActivity;
import com.best.demo.julegou.MyGuanZhuActivity;
import com.best.demo.julegou.MyIntegralActivity;
import com.best.demo.julegou.MyMessageActivity;
import com.best.demo.julegou.MyZuJiActivity;
import com.best.demo.julegou.R;

/**
 * Created by dell2 on 2015/12/17.
 */
public class MyFragment extends Fragment implements View.OnClickListener{
    LinearLayout line,line1,line2;
    RelativeLayout rel,rel1,rel2,rel3,rel4,rel5,rel6;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my,container,false);
        rel = (RelativeLayout) v.findViewById(R.id.rel);
        rel1 = (RelativeLayout) v.findViewById(R.id.rel1);
        rel2 = (RelativeLayout) v.findViewById(R.id.rel2);
        rel3 = (RelativeLayout) v.findViewById(R.id.rel3);
        rel4 = (RelativeLayout) v.findViewById(R.id.rel4);
        rel5 = (RelativeLayout) v.findViewById(R.id.rel5);
        rel6 = (RelativeLayout) v.findViewById(R.id.rel6);

        line = (LinearLayout) v.findViewById(R.id.daifaline);
        line1 = (LinearLayout) v.findViewById(R.id.daishouline);
        line2 = (LinearLayout) v.findViewById(R.id.daipingline);

        rel.setOnClickListener(this);
        rel1.setOnClickListener(this);
        rel2.setOnClickListener(this);
        rel3.setOnClickListener(this);
        rel4.setOnClickListener(this);
        rel5.setOnClickListener(this);
        rel6.setOnClickListener(this);
        line.setOnClickListener(this);
        line1.setOnClickListener(this);
        line2.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        int vid = v.getId();
        if(vid == R.id.daifaline){
            Intent intent = new Intent(getActivity(), MyDingDanActivity.class);
            startActivity(intent);
        }else if(vid == R.id.daishouline){
            Intent intent = new Intent(getActivity(), MyDingDanActivity.class);
            startActivity(intent);
        }else if(vid == R.id.rel){
            Intent intent = new Intent(getActivity(), MyDingDanActivity.class);
            startActivity(intent);
        }else if(vid == R.id.daipingline){
            Intent intent = new Intent(getActivity(), MyDingDanActivity.class);
            startActivity(intent);
        }else if(vid == R.id.rel2){
            Intent intent = new Intent(getActivity(), MyIntegralActivity.class);
            startActivity(intent);
        }
        else if(vid == R.id.rel1){
            Intent intent = new Intent(getActivity(), MyIntegralActivity.class);
            startActivity(intent);
        }
        else if(vid == R.id.rel2){
            Intent intent = new Intent(getActivity(), MyIntegralActivity.class);
            startActivity(intent);
        }
        else if(vid == R.id.rel3){
            Intent intent = new Intent(getActivity(), MyMessageActivity.class);
            startActivity(intent);
        }
        else if(vid == R.id.rel4){
            Intent intent = new Intent(getActivity(), MyZuJiActivity.class);
            startActivity(intent);
        }
        else if(vid == R.id.rel5){
            Intent intent = new Intent(getActivity(), MyGuanZhuActivity.class);
            startActivity(intent);
        }
        else if(vid == R.id.rel6){
            Intent intent = new Intent(getActivity(), MyIntegralActivity.class);
            startActivity(intent);
        }
    }
}
