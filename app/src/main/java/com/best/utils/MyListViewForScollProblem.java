package com.best.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by dell2 on 2015/10/29.
 */
public class MyListViewForScollProblem extends ListView{
    public MyListViewForScollProblem(Context context){
        super(context);
    }

    public MyListViewForScollProblem(Context context,AttributeSet attributeSet){
        super(context,attributeSet);

    }
    public MyListViewForScollProblem(Context context,AttributeSet attributeSet, int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int endHeight =MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, endHeight);
    }
}
