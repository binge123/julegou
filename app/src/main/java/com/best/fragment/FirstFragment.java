package com.best.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.best.adapter.FirstListViewAdapter;
import com.best.bean.FirstShops;
import com.best.demo.julegou.R;
import com.best.demo.julegou.ShopClassifyActivity;
import com.best.utils.HttpUtils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by dell2 on 2015/12/17.
 */

public class FirstFragment extends Fragment {

    List<FirstShops> lists = new ArrayList<>();
    ViewPager vp;
    View v1,v2,v3;
    private ImageHandler handler = new ImageHandler(new WeakReference<FirstFragment>(this));
    List<View> list = new ArrayList<>();
    GridView gv,gv1;
    RelativeLayout rl;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first,container,false);
        vp = (ViewPager) v.findViewById(R.id.first_banner);
        gv = (GridView) v.findViewById(R.id.xin_list);
        gv1 = (GridView) v.findViewById(R.id.re_list);
        rl = (RelativeLayout) v.findViewById(R.id.more);

        v1 =LayoutInflater.from(getActivity()).inflate(R.layout.first_banner1_layout,null);
        v2 =LayoutInflater.from(getActivity()).inflate(R.layout.first_banner2_layout,null);
        v3 =LayoutInflater.from(getActivity()).inflate(R.layout.first_banner3_layout,null);
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
        list.add(v1);
        list.add(v2);
        list.add(v3);
        vp.setAdapter(new MyViewPager());
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String,String> map = new HashMap<>();
                map.put("sql","select * from wst_goods");
                HttpUtils.httpGetRequest(map, "/Api/exeQuery", new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.i("jsons",s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray  = jsonObject.getJSONArray("data");
                            for(int i = 0;i < 6;i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                lists.add(new FirstShops(object.getString("goodsName"),object.getString("goodsSpec"),object.getString("shopPrice"),object.getString("goodsImg")));
                            }
                            gv.setAdapter(new FirstListViewAdapter(getContext(),lists));
                            gv1.setAdapter(new FirstListViewAdapter(getContext(),lists));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                    }

                    @Override
                    public void onCancelled(CancelledException e) {
                    }

                    @Override
                    public void onFinished() {

                    }
                });

            }
        }).start();
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShopClassifyActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
    class MyViewPager extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = list.get(position);
            container.addView(v,0);
            return v;
        }
    }
    /**
     * 下面是ViewPager的轮播效果
     */
    private static class ImageHandler extends Handler {

        /**
         * 请求更新显示的View。
         */
        protected static final int MSG_UPDATE_IMAGE = 1;
        /**
         * 请求暂停轮播。
         */
        protected static final int MSG_KEEP_SILENT = 2;
        /**
         * 请求恢复轮播。
         */
        protected static final int MSG_BREAK_SILENT = 3;
        /**
         * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
         * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
         * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        protected static final int MSG_PAGE_CHANGED = 4;
        /**
         * 轮播间隔时间
         */
        protected static final long MSG_DELAY = 3000;

        /**
         * 使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
         */
        private WeakReference<FirstFragment> weakReference;
        private int currentItem = 0;

        protected ImageHandler(WeakReference<FirstFragment> wk) {
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("LOG_TAG", "receive message " + msg.what);
            FirstFragment activity = weakReference.get();
            if (activity == null) {
                /**
                 *  Activity已经回收，无需再处理UI了
                 * */
                return;
            }
            /**
             * 检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
             * */
            if (activity.handler.hasMessages(MSG_UPDATE_IMAGE)) {
                activity.handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    if (currentItem > 3) {
                        currentItem = 0;
                    }
                    activity.vp.setCurrentItem(currentItem);
                    /**
                     * 准备下次播放
                     * */
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    /**
                     * 只要不发送消息就暂停了
                     * */
                    break;
                case MSG_BREAK_SILENT:
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    /**
                     * 记录当前的页号，避免播放的时候页面显示不正确。
                     * */
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }
}
