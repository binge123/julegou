package com.best.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.best.adapter.PingLunListViewAdapter;
import com.best.demo.julegou.PaymentActivity;
import com.best.demo.julegou.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dell2 on 2015/12/30.
 */
public class ShopFragment extends Fragment implements View.OnClickListener{
    private ListView lv;
    private LinearLayout line;
    private Button btn_addcar,btn_buy;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shop_viewpager,container,false);
        lv = (ListView) v.findViewById(R.id.listView);
        line = (LinearLayout) v.findViewById(R.id.share);
        btn_addcar = (Button) v.findViewById(R.id.addCar);
        btn_buy = (Button) v.findViewById(R.id.buyButton);

        line.setOnClickListener(this);
        btn_addcar.setOnClickListener(this);
        btn_buy.setOnClickListener(this);

        lv.setAdapter(new PingLunListViewAdapter(getContext()));
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
        }else if(id == R.id.addCar){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        }else if(id == R.id.buyButton){
            Intent intent = new Intent(getActivity(), PaymentActivity.class);
            startActivity(intent);
        }
    }
}
