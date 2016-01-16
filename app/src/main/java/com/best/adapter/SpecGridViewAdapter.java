package com.best.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.best.bean.DingDan;
import com.best.demo.julegou.R;

import java.util.List;

/**
 * Created by dell2 on 2016/1/15.
 */
public class SpecGridViewAdapter extends BaseAdapter {
    Context context;
    List<DingDan> list;
    public SpecGridViewAdapter(Context context,List<DingDan> list){
        this.context = context;
        this.list = list;
    }

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
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.spec_gridview_layout,null);
            holder.tv1 = (TextView) convertView.findViewById(R.id.shuxing);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv1.setText(list.get(position).getShopname());
        return convertView;
    }
    class ViewHolder{
        TextView tv1;
    }
}
