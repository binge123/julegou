package com.best.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.bean.FirstShops;
import com.best.demo.julegou.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dell2 on 2016/1/4.
 */
public class FirstListViewAdapter extends BaseAdapter {
    Context context;
    List<FirstShops> list;
    public FirstListViewAdapter(Context context,List<FirstShops> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.first_listview_adapter,null);
            holder.iv = (ImageView) convertView.findViewById(R.id.img);
            holder.tv1 = (TextView) convertView.findViewById(R.id.goodsName);
            holder.tv2 = (TextView) convertView.findViewById(R.id.goodsSpec);
            holder.tv3 = (TextView) convertView.findViewById(R.id.shopPrice);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(list.get(position).getGoodsImg()).into(holder.iv);
        holder.tv1.setText(list.get(position).getGoodsName());
        holder.tv2.setText(list.get(position).getGoodsSpec());
        holder.tv3.setText(list.get(position).getShopPrice());
        return convertView;
    }
    class ViewHolder{
        ImageView iv;
        TextView tv1,tv2,tv3;
    }
}
