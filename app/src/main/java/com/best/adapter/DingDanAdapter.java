package com.best.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.bean.DingDan;
import com.best.bean.FirstShops;
import com.best.demo.julegou.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dell2 on 2016/1/6.
 */
public class DingDanAdapter extends BaseAdapter {
    Context context;
    List<DingDan> list;
    public DingDanAdapter(Context context,List<DingDan> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.alldingdan_listview_layout,null);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_dingdan);
            holder.tv1 = (TextView) convertView.findViewById(R.id.shopname);
            holder.tv2 = (TextView) convertView.findViewById(R.id.shopprice);
            holder.tv3 = (TextView) convertView.findViewById(R.id.orderStatus);
            holder.tv4 = (TextView) convertView.findViewById(R.id.buypeople);
            holder.tv5 = (TextView) convertView.findViewById(R.id.address);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(list.get(position).getShopimg()).into(holder.iv);
        holder.tv1.setText(list.get(position).getShopname());
        holder.tv2.setText(list.get(position).getShopprice());
        holder.tv3.setText(list.get(position).getDingdanstate());
        holder.tv4.setText(list.get(position).getBuypeople());
        holder.tv5.setText(list.get(position).getBuypepaddress());
        return convertView;
    }
    class ViewHolder{
        ImageView iv;
        TextView tv1,tv2,tv3,tv4,tv5;
    }
}
