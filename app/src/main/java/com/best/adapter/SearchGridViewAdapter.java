package com.best.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.bean.GridViewItem;

import java.util.ArrayList;
import java.util.List;
import com.best.demo.julegou.R;
/**
 * Created by dell2 on 2015/12/21.
 */
public class SearchGridViewAdapter extends BaseAdapter {
    Context context;
    List<GridViewItem> list;
    public SearchGridViewAdapter(Context context,List<GridViewItem> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.search_gridview_layout,null);
            holder.iv = (ImageView) convertView.findViewById(R.id.img);
            holder.tv1 = (TextView) convertView.findViewById(R.id.textView1);
            holder.tv2 = (TextView) convertView.findViewById(R.id.textView2);
            holder.tv3 = (TextView) convertView.findViewById(R.id.textView3);
            holder.tv4 = (TextView) convertView.findViewById(R.id.textView4);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
    class ViewHolder{
        ImageView iv;
        TextView tv1,tv2,tv3,tv4;
    }
}
