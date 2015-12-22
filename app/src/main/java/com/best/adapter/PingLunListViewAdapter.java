package com.best.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.best.demo.julegou.R;

/**
 * Created by dell2 on 2015/12/20.
 */
public class PingLunListViewAdapter extends BaseAdapter {
    Context context;
    String[] a = {"清风","海浪","百度"};
    public  PingLunListViewAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return a.length;
    }

    @Override
    public Object getItem(int position) {
        return a[position];
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
            vh.tv = (TextView) convertView.findViewById(R.id.textView);
            vh.tv1 = (TextView) convertView.findViewById(R.id.textView1);
            vh.tv2 = (TextView) convertView.findViewById(R.id.textView2);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv.setText(a[position]);
        Log.i("adapters", "getViews");
        return convertView;
    }

    class ViewHolder{
        TextView tv,tv1,tv2;
    }
}
