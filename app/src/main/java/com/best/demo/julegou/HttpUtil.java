package com.best.demo.julegou;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wen on 2015/12/16.
 */
public class HttpUtil extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建一个hashmap集合
        HashMap<String ,String> map = new HashMap<>();
        //把map中所有的key放入collection中
        Collection collection = map.keySet();
        //创建一个list集合
                List<String> sets = (List<String>)collection;
        //利用collections来进行排序
        Collections.sort(sets);
        //创建一个变量来储存连接后的字符串
        String lian="";
        //循环把map中的栈和值放入到lian中
        for(int i=0;i<sets.size();i++){
            lian+=sets.get(i)+map.get(sets.get(i));
        }
        //连接私钥
        String siyaolian = lian+"sunsai123";
        //加密

    }
}
