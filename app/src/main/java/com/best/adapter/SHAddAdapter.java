package com.best.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.best.bean.SHAddress;
import com.best.demo.julegou.R;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/6.
 */
public class SHAddAdapter extends BaseAdapter {

    List<SHAddress> list = new ArrayList();

    public SHAddAdapter(List<SHAddress> list, Context context) {
        this.list = list;
        this.context = context;
    }

    Context context;
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.shaddress_adapter,null);
            vh.adaName = (TextView) convertView.findViewById(R.id.addada_name);
            vh.adaPhone = (TextView)convertView.findViewById(R.id.addada_phone);
            vh.adaAdd = (TextView) convertView.findViewById(R.id.addada_add);
            convertView.setTag(vh);
        }else {
        vh = (ViewHolder) convertView.getTag();
        }
        vh.adaName.setText(list.get(position).getUserName());
        vh.adaPhone.setText(list.get(position).getUserPhone());
        vh.adaAdd.setText(list.get(position).getAddress());
        return convertView;
    }
    class ViewHolder{
        TextView adaName,adaPhone,adaAdd;
    }
}
