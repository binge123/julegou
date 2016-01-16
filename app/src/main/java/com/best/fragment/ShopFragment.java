package com.best.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.best.adapter.PingLunListViewAdapter;
import com.best.bean.GoShopingCar;
import com.best.bean.PingLun;
import com.best.demo.julegou.BabyPopWindow;
import com.best.demo.julegou.PaymentActivity;
import com.best.demo.julegou.R;
import com.best.utils.HttpUtils;
import com.best.weidget.XCFlowLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dell2 on 2015/12/30.
 */
public class ShopFragment extends Fragment implements View.OnClickListener,BabyPopWindow.OnItemClickListener{
    private ListView lv;
    private LinearLayout line,all_choice_layout;
    private Button btn_addcar,btn_buy;
    private ImageView iv_shopimg,iv_userimg;
    private TextView tv_shopname,tv_shopprice,tv_marketprice,tv_name,tv_stock,tv_name1,tv_content,tv_username,tv_time;
    private String goodsname,shopprice,marketprice,shopid,goodsid,shopname,goodsstock,shopimg,username,userphoto,content,createtime,
                    userName,userPhoto;
    private RelativeLayout rl_color;
    private BabyPopWindow popWindow;
    //评价list
    private List<PingLun> list = new ArrayList<>();
    private LayoutInflater layoutInflater;
//    private TagFlowLayout mFlowLayout;
    private String[] mVals = new String[]
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView"};
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shop_viewpager,container,false);
        layoutInflater = inflater;
//        lv = (ListView) v.findViewById(R.id.listView);
        line = (LinearLayout) v.findViewById(R.id.share);
        rl_color = (RelativeLayout) v.findViewById(R.id.guige);
        btn_addcar = (Button) v.findViewById(R.id.addCar);
        btn_buy = (Button) v.findViewById(R.id.buyButton);
        all_choice_layout = (LinearLayout) v.findViewById(R.id.all_choice_layout);
        //商品图片
        iv_shopimg = (ImageView) v.findViewById(R.id.img);
        //goodsname
        tv_shopname = (TextView) v.findViewById(R.id.shopspec);
        //shopprice
        tv_shopprice = (TextView) v.findViewById(R.id.money_textView);
        //marketprice
        tv_marketprice = (TextView) v.findViewById(R.id.money_textView1);
        //商店名
        tv_name = (TextView) v.findViewById(R.id.tv2);
        tv_name1 = (TextView) v.findViewById(R.id.txt_dianpu_name);
        //库存
        tv_stock = (TextView) v.findViewById(R.id.tv1);
        //评价内容
        tv_content = (TextView) v.findViewById(R.id.content);
        //用户头像
        iv_userimg = (ImageView) v.findViewById(R.id.userimg);
        //用户名
        tv_username = (TextView) v.findViewById(R.id.username);
        //评论时间
        tv_time = (TextView) v.findViewById(R.id.time);
        //颜色分类  实例化对象
        popWindow = new BabyPopWindow(getContext());
        popWindow.setOnItemClickListener(this);

        //点击事件
        line.setOnClickListener(this);
        btn_addcar.setOnClickListener(this);
        btn_buy.setOnClickListener(this);
        rl_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                initChildViews();
                setBackgroundBlack(all_choice_layout, 0);
                popWindow.showAsDropDown(v);
            }
        });

        //接受数据
        SharedPreferences sp = getActivity().getSharedPreferences("content", Activity.MODE_PRIVATE);
        goodsname = sp.getString("goodsname","0");
        shopprice = sp.getString("shopprice","0");
        marketprice = sp.getString("marketprice","0");
        shopid = sp.getString("shopid","0");
        goodsid = sp.getString("goodsid","0");
        goodsstock = sp.getString("goodsstock","0");
        //设置text
        tv_shopname.setText(goodsname);
        tv_shopprice.setText(shopprice);
        tv_marketprice.setText(marketprice);
        tv_stock.setText("库存："+goodsstock);

        //读取数据，得到goodsimg
        HashMap<String,String> map = new HashMap<>();
        map.put("sql", "select goodsImg from wst_goods_gallerys where goodsId=\'" + goodsid + "\'");
        HttpUtils.httpGetRequest(map, "/Api/exeQuery", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
//                Log.i("goodsid", s);
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
//        获取商店名
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("sql","select shopName,shopImg from wst_shops where shopId=\'"+shopid+"\'");
        HttpUtils.httpGetRequest(map1, "/Api/exeQuery", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
//                Log.i("goodsid",s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray object = jsonObject.getJSONArray("data");
                    for(int i = 0;i < object.length();i++){
                        shopname = object.getJSONObject(i).getString("shopName");
                        shopimg = object.getJSONObject(i).getString("shopImg");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tv_name.setText(shopname);
                tv_name1.setText(shopname);
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
        //获取评价
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("sql", "select b.userName,b.userPhoto,a.content,a.createTime from wst_goods_appraises a join wst_users b on a.userId = b.userId where a.goodsId =\'"+84+"\'"+"and a.shopId=\'"+26+ "\'");
        HttpUtils.httpGetRequest(map2, "/Api/exeQuery", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
//                Log.i("shoptest", s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        list.add(new PingLun(object.getString("userName"), object.getString("userPhoto"), object.getString("content"), object.getString("createTime")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tv_username.setText(list.get(0).getUsername());
                tv_content.setText(list.get(0).getContent());
                tv_time.setText(list.get(0).getTime());
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
//        lv.setAdapter(new PingLunListViewAdapter(getContext()));
        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.share){
            ShareSDK.initSDK(getActivity());
            OnekeyShare oks = new OnekeyShare();
            //关闭sso授权
            oks.disableSSOWhenAuthorize();
            // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
            //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
            oks.setTitle("赵彦浩");
            // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
            oks.setTitleUrl("http://sharesdk.cn");
            // text是分享文本，所有平台都需要这个字段wwww
            oks.setText("我是分享文本");
            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//                oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
            // url仅在微信（包括好友和朋友圈）中使用
//                oks.setUrl("http://sharesdk.cn");
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//                oks.setComment("我是测试评论文本");
            // site是分享此内容的网站名称，仅在QQ空间使用
//                oks.setSite(getString(R.string.app_name));
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//                oks.setSiteUrl("http://sharesdk.cn");
            oks.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul
            // 启动分享GUI
            oks.show(getActivity());
        } else if (id == R.id.addCar) {
            setBackgroundBlack(all_choice_layout, 0);
            popWindow.showAsDropDown(v);
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setCancelable(false);
//            builder.setMessage("是否加入购物车？");
//            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    GoShopingCar gc = new GoShopingCar();
//
//                }
//            });
//            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
        }else if(id == R.id.buyButton){
            setBackgroundBlack(all_choice_layout, 0);
            popWindow.showAsDropDown(v);
//            Intent intent = new Intent(getActivity(), PaymentActivity.class);
//            startActivity(intent);
        }
    }
    /** 控制背景变暗 0变暗 1变亮 */
    public void setBackgroundBlack(View view, int what) {
        switch (what) {
            case 0:
                view.setVisibility(View.VISIBLE);
                break;
            case 1:
                view.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClickOKPop() {
        setBackgroundBlack(all_choice_layout, 1);
    }

//    private void initChildViews() {
//        // TODO Auto-generated method stub
//        View v = layoutInflater.inflate(R.layout.adapter_popwindow, null);
//        mFlowLayout = (XCFlowLayout) v.findViewById(R.id.flowlayout);
//        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
//                RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
//        lp.leftMargin = 5;
//        lp.rightMargin = 5;
//        lp.topMargin = 5;
//        lp.bottomMargin = 5;
//        for(int i = 0; i < mVals.length; i ++){
//            TextView view = new TextView(getActivity());
//            view.setText(mVals[i]);
//            view.setTextColor(Color.WHITE);
////            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
//            mFlowLayout.addView(view,lp);
//        }
//    }

}
