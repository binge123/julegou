package com.best.demo.julegou;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by Administrator on 2016/1/5.
 */
@ContentView(R.layout.activity_myzl)
public class MyZLActivity extends BaseActivity{
    public static final int REQUEST_IMAGE = 2;
    String mpaths = null;
    @ViewInject(R.id.zl_tx1)
    ImageView iv;
    @ViewInject(R.id.zl_nick1)
    TextView zlnick;
    @ViewInject(R.id.zl_sex1)
    TextView zlsex;
    AlertDialog.Builder  builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Event(value = R.id.zl_tx)
    private void udtxClick(View v){
        Intent intent = new Intent(this, MultiImageSelectorActivity.class);

        // 是否显示调用相机拍照
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);

        // 最大图片选择数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);

        // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

        startActivityForResult(intent, REQUEST_IMAGE);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                mpaths = path.get(0);
                iv.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
                // 处理你自己的逻辑 ....
            }
        }
    }
    @Event(value = R.id.zl_nick)
    private void udnickClick(View v){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("请输入昵称");
        View vi = LayoutInflater.from(this).inflate(R.layout.item_dialog,null);
        final TextView dialogtext;
        dialogtext = (TextView) vi.findViewById(R.id.dialog_text);

        builder.setView(vi);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                zlnick.setText(dialogtext.getText().toString());
            }
        });
    }
    @Event(value = R.id.zl_sex)
    private void udSexClick(View v){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择性别");
        final String items[] = {"男","女"};
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                zlnick.setText(items[which]);
            }
        });
    }
    @Event(value = R.id.zl_add)
    private void udadd(View v){
        Intent i = new Intent(this,SHAActivity.class);
        startActivity(i);
    }
}
