package com.best.utils;

import android.app.DownloadManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.util.MD5;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.callback.Callback;

/**
 * Created by Administrator on 2015/12/16.
 */
public class HttpUtils {

    public static void httpPostRequest(Map map,String d, org.xutils.common.Callback.CommonCallback<String> commonCallback){
        RequestParams params = new RequestParams("http://122.114.62.25:8686/index.php/Admin"+d);
        Collection collection = map.keySet();
        Iterator i = collection.iterator();
        List<String> sets = new ArrayList<>();
        while (i.hasNext()){
            sets.add(i.next().toString());
        }
        Collections.sort(sets);
        String lian = "";
        for (int i1=0;i1<sets.size();i1++){
            lian += sets.get(i1)+map.get(sets.get(i1));
            params.addQueryStringParameter(sets.get(i1),map.get(sets.get(i1)).toString());
        }
        String sibuyaolian = lian+"sunsai123";
        String jiami = sibuyaolian;
        jiami = MD5.md5(jiami);
        map.put("sign",jiami);
        params.addQueryStringParameter("sign",map.get("sign").toString());
        x.http().post(params,commonCallback);
    }
    public static void httpGetRequest(Map map,String d, org.xutils.common.Callback.CommonCallback<String> commonCallback){
        RequestParams params = new RequestParams("http://122.114.62.25:8686/index.php/Admin"+d);
        Collection collection = map.keySet();
        Iterator i = collection.iterator();
        List<String> sets = new ArrayList<>();
        while (i.hasNext()){
            sets.add(i.next().toString());
        }
        Collections.sort(sets);
        String lian = "";
        for (int i1=0;i1<sets.size();i1++){
            lian += sets.get(i1)+map.get(sets.get(i1));
            params.addQueryStringParameter(sets.get(i1),map.get(sets.get(i1)).toString());
        }
        String sibuyaolian = lian+"sunsai123";
        String jiami = sibuyaolian;
        jiami = MD5.md5(jiami);
        map.put("sign",jiami);
        params.addQueryStringParameter("sign",map.get("sign").toString());
        x.http().get(params,commonCallback);
    }
}
