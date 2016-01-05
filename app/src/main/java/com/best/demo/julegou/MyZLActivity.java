package com.best.demo.julegou;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by Administrator on 2015/12/29.
 */
@ContentView(R.layout.activity_myzl)
public class MyZLActivity extends BaseActivity {

    @ViewInject(R.id.zl_tx)
    ImageView txiv;
    @ViewInject(R.id.myzl_nick)
    RelativeLayout zlnick;
    @ViewInject(R.id.myzl_sex)
    RelativeLayout zlsex;
    @ViewInject(R.id.myzl_address)
    RelativeLayout zladdress;
    @ViewInject(R.id.myzl_nick1)
    TextView nick;
    @ViewInject(R.id.myzl_sex1)
    TextView sex;
    public static final int REQUEST_IMAGE = 2;
    String mpaths = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void ghtxEvent(View v){
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
                txiv.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
                // 处理你自己的逻辑 ....
            }
        }
    }
    public void ghnick(View v){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("请输入昵称"); //设置标题
        builder.setIcon(android.R.drawable.ic_dialog_info);
        View view = LayoutInflater.from(this).inflate(R.layout.dialogitem,null);
        final EditText ed = (EditText) view.findViewById(R.id.shuru);
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nick.setText(ed.getText().toString());
                dialog.dismiss(); //关闭dialog
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    public void ghsex(View v){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("请选择性别"); //设置标题
        builder.setIcon(android.R.drawable.ic_dialog_info);
        final String items[]={"男","女"};
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dialog.dismiss();
                sex.setText(items[which]);
                Toast.makeText(MyZLActivity.this, items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss(); //关闭dialog
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    public void zltz(View v){
        Intent i  = new Intent(this,SHAddressActivity.class);
        startActivity(i);
    }
}
