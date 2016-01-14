package com.best.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.bean.FenLeiGridView;
import com.best.bean.FirstShops;
import com.best.demo.julegou.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dell2 on 2016/1/10.
 */
public class FenLeiGridViewAdapter extends BaseAdapter {
    Context context;
    List<FenLeiGridView> list;
    public FenLeiGridViewAdapter(Context context,List<FenLeiGridView> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fenlei_gridview_layout,null);
            holder.iv = (ImageView) convertView.findViewById(R.id.image);
            holder.tv1 = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
//        Picasso.with(context).load(list.get(position).getShopimg()).into(holder.iv);
        holder.tv1.setText(list.get(position).getShopname());
        return convertView;
    }
    class ViewHolder{
        ImageView iv;
        TextView tv1;
    }
}
