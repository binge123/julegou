package com.best.demo.julegou;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.best.adapter.FenLeiGridViewAdapter;
import com.best.adapter.FenLeiListViewAdapter;
import com.best.bean.FenLeiGridView;
import com.best.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * Created by Administrator on 2015/12/18.
 */
public class FenLeiActivity extends Activity {
    private ListView fenlei_lv;
    private ViewPager vp;
    private ImageHandler handler = new ImageHandler(new WeakReference<FenLeiActivity>(this));
    private View v1,v2,v3;;
    private GridViewWithHeaderAndFooter fenlei_gv;
    private List<String> list = new ArrayList<>();
    private List<FenLeiGridView> lists = new ArrayList<>();
    private List<View> list_view = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenlei);
        fenlei_lv = (ListView) findViewById(R.id.fenlei_lv);
        fenlei_gv = (GridViewWithHeaderAndFooter) findViewById(R.id.fenlei_gv);
        LayoutInflater layoutInflater = LayoutInflater.from(FenLeiActivity.this);
        View headerView = layoutInflater.inflate(R.layout.fenlei_viewpager, null);
        vp = (ViewPager) headerView.findViewById(R.id.viewPager);
        v1 =LayoutInflater.from(this).inflate(R.layout.first_banner1_layout,null);
        v2 =LayoutInflater.from(this).inflate(R.layout.first_banner2_layout,null);
        v3 =LayoutInflater.from(this).inflate(R.layout.first_banner3_layout,null);
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
        list_view.add(v1);
        list_view.add(v2);
        list_view.add(v3);

        vp.setAdapter(new MyViewPager());
        fenlei_gv.addHeaderView(headerView);
        HashMap<String,String> map = new HashMap<>();
            map.put("sql", "select catName from wst_goods_cats");
            HttpUtils.httpGetRequest(map, "/Api/exeQuery", new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for(int i = 0;i < jsonArray.length();i++){
                            list.add(jsonArray.getJSONObject(i).getString("catName"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    fenlei_lv.setAdapter(new FenLeiListViewAdapter(getApplicationContext(),list));
//                    Log.i("tokens",s);
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
        for(int i = 0;i<6;i++){
            lists.add(new FenLeiGridView("",""+i));
        }
        fenlei_gv.setAdapter(new FenLeiGridViewAdapter(getApplicationContext(),lists));
    }
    class MyViewPager extends PagerAdapter {

        @Override
        public int getCount() {
            return list_view.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list_view.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = list_view.get(position);
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
        private WeakReference<FenLeiActivity> weakReference;
        private int currentItem = 0;

        protected ImageHandler(WeakReference<FenLeiActivity> wk) {
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("LOG_TAG", "receive message " + msg.what);
            FenLeiActivity activity = weakReference.get();
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
