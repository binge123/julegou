package com.best.adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.bean.PingLun;
import com.best.demo.julegou.R;

import java.util.List;

/**
 * Created by dell2 on 2015/12/20.
 */
public class PingLunListViewAdapter extends BaseAdapter {
    Context context;
    List<PingLun> list;
    public  PingLunListViewAdapter(Context context,List<PingLun> list){
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
        Log.i("adapters","getView");
        ViewHolder vh = null;
        if(convertView == null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.pinglun_listview_layout,null);
            vh.iv = (ImageView) convertView.findViewById(R.id.userimg);
            vh.tv = (TextView) convertView.findViewById(R.id.username);
            vh.tv1 = (TextView) convertView.findViewById(R.id.content);
            vh.tv2 = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv.setText(list.get(position).getUsername());
        vh.tv1.setText(list.get(position).getContent());
        vh.tv2.setText(list.get(position).getTime());
        return convertView;
    }

    class ViewHolder{
        ImageView iv;
        TextView tv,tv1,tv2;
    }
}
