package com.best.demo.julegou;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.best.utils.HttpUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 *商品详情界面的弹窗
 *
 */
@SuppressLint("CommitPrefEdits")
public class BabyPopWindow implements OnDismissListener, OnClickListener {
	private TextView pop_choice_16g,pop_choice_32g,pop_choice_16m,pop_choice_32m,pop_choice_black,pop_choice_white,pop_add,pop_reduce,pop_num,pop_ok;
	private ImageView pop_del;

	private PopupWindow popupWindow;
	private OnItemClickListener listener;
	private final int ADDORREDUCE=1;
	private Context context;
	/**保存选择的颜色的数据*/
	private String str_color="";
	/**保存选择的类型的数据*/
	private String str_type="";

	private String ShopId;//店铺ID
	private String GoodsId;//商品ID

	private LinearLayout line_comm;
//	private TagFlowLayout id_flowlayout;
	List<String> list1 = new ArrayList();
	List<String> ll = new ArrayList<>();
	private int abc = 0;


	public BabyPopWindow(Context context) {
		this.context=context;
		View view=LayoutInflater.from(context).inflate(R.layout.adapter_popwindow, null);

		//接受数据
		SharedPreferences sp = context.getSharedPreferences("content", Activity.MODE_PRIVATE);
		ShopId = sp.getString("shopid","0");
		GoodsId = sp.getString("goodsid","0");

//		pop_choice_16g=(TextView) view.findViewById(R.id.pop_choice_16g);
//		pop_choice_32g=(TextView) view.findViewById(R.id.pop_choice_32g);
//		pop_choice_16m=(TextView) view.findViewById(R.id.pop_choice_16m);
//		pop_choice_32m=(TextView) view.findViewById(R.id.pop_choice_32m);
//		pop_choice_black=(TextView) view.findViewById(R.id.pop_choice_black);
//		pop_choice_white=(TextView) view.findViewById(R.id.pop_choice_white);
		pop_add=(TextView) view.findViewById(R.id.pop_add);
		pop_reduce=(TextView) view.findViewById(R.id.pop_reduce);
		pop_num=(TextView) view.findViewById(R.id.pop_num);
		pop_del=(ImageView) view.findViewById(R.id.pop_del);
		pop_ok = (TextView) view.findViewById(R.id.pop_ok);

		line_comm = (LinearLayout) view.findViewById(R.id.line_comm);
//		id_flowlayout = (TagFlowLayout) view.findViewById(R.id.id_flowlayout);
		Log.i("babyaciss",GoodsId+ShopId);
		//商品属性方法
		shopsshuxing();

		ll.add("4g");
		ll.add("5g");

//		pop_choice_16g.setOnClickListener(this);
//		pop_choice_32g.setOnClickListener(this);
//		pop_choice_16m.setOnClickListener(this);
//		pop_choice_32m.setOnClickListener(this);
//		pop_choice_black.setOnClickListener(this);
//		pop_choice_white.setOnClickListener(this);
		pop_add.setOnClickListener(this);
		pop_reduce.setOnClickListener(this);
		pop_ok.setOnClickListener(this);
		pop_del.setOnClickListener(this);



		popupWindow=new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		//设置popwindow的动画效果
		popupWindow.setAnimationStyle(R.style.popWindow_anim_style);
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		popupWindow.setOnDismissListener(this);// 当popWindow消失时的监听
	}

	/**
	 * 获得商品属性数据
	 * */
	public void shopsshuxing() {
		String sql = "select * from wst_products where goodId="+54;
		String types = "/Api/exeQuery";
		HashMap<String,String> map = new HashMap<>();
		map.put("sql", sql);
		HttpUtils.httpGetRequest(map, types, new Callback.CommonCallback<String>() {
			@Override
			public void onSuccess(String s) {
				System.out.println("onSuccess" + s.toString());
				Log.i("shuxing", "onSuccess " + s.toString());
				try {
					JSONObject jsonon = new JSONObject(s);
					JSONArray data = jsonon.getJSONArray("data");
					if (data.length() == 0) {

					} else {
						for (int i = 0; i < data.length(); i++) {
							JSONObject jo = (JSONObject) data.get(i);
							String a = jo.getString("specDefault");

							HashMap<String, String> maps = new HashMap<String, String>();
							try {
								JSONObject jsonObject = new JSONObject(a);
								Iterator keys = jsonObject.keys();


								while (keys.hasNext()) {
									abc += 1;
									List<String> list1 = new ArrayList();

									String key = (String) keys.next();
//									Log.i("shuxing", "key :" + key);
									String st = jsonObject.get(key).toString();
//									Log.i("shuxing", "st只  :" + st);
									String[] stt = st.split(",");
//									Log.i("shuxing", "stt  :" + stt.toString());
									for (int j = 0; j < stt.length; j++) {
										list1.add(stt[j]);
//										Log.i("shuxing", list1.get(j));
									}
//									maps.put("1",list1.);
									//调用创建布局方法
									addzhi(line_comm, key, list1);

									Log.i("shuxing", list1.size() + "for长度" + list1.toString());
								}

								for (int k = 0; k < abc; k++) {

								}

//								Log.i("shuxing",list1.size()+"最后长度");
							} catch (JSONException e) {
								e.printStackTrace();
							}

//							Log.i("shuxing", "list: "+list1.toString());
//						JSONObject specSelect = jo.getJSONObject(a);
							Log.i("shuxing", a);
//						for (int j = 0;j<specSelect.length();j++){
//							JSONObject jos = (JSONObject) specSelect.get(j);
//							String b= jos.getString("版本");
//							Log.i("shuxing",b);
//						}
//						String name = jo.getString("goodsName");
//						String jiage = jo.getString("shopPrice");
//						String kucun = jo.getString("goodsStock");
//						String shopimage = jo.getString("goodsImg");
//						String xiaoliang = jo.getString("saleCount");
//						String dianpuid = jo.getString("shopId");
//						String danwei = jo.getString("goodsUnit");
//						String goodsid = jo.getString("goodsId");
//						String shichangjia = jo.getString("marketPrice");
//						String tuwen = jo.getString("goodsDesc");
							Log.i("asdqqq", GoodsId);
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onError(Throwable throwable, boolean b) {
				System.out.println("onError" + throwable.toString());
				Log.i("shuxing", "onError " + throwable.toString());
			}

			@Override
			public void onCancelled(Callback.CancelledException e) {
			}

			@Override
			public void onFinished() {

			}
		});
	}

	/**
	 * 添加布局
	 * */
	public void addzhi(final LinearLayout xuanzhong, String types, final List<String> list){
		final View otherView = View.inflate(context, R.layout.flayou_comm, null);
		xuanzhong.addView(otherView);
		TextView txt_txt = (TextView) otherView.findViewById(R.id.txt_txt);
		txt_txt.setText(types);

		LinearLayout lineflow = (LinearLayout) otherView.findViewById(R.id.lineflow);
		final TagFlowLayout id_flowlayouts = (TagFlowLayout) otherView.findViewById(R.id.id_flowlayouts);
		id_flowlayouts.setAdapter(new TagAdapter<String>(list) {
			@Override
			public View getView(FlowLayout parent, int position, String s) {
				TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.tv,
						id_flowlayouts, false);
				tv.setText(s);
				return tv;
			}
		});

		id_flowlayouts.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
			@Override
			public boolean onTagClick(View view, int position, FlowLayout parent) {
				Log.i("commtag","事件: "+list.get(position));
				Toast.makeText(context, list.get(position), Toast.LENGTH_SHORT).show();
				return true;
			}
		});

		id_flowlayouts.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
			@Override
			public void onSelected(Set<Integer> selectPosSet) {
				Log.i("commtag", "事件回调: " + selectPosSet.toString());
//				context.setTitle("choose:" + selectPosSet.toString());
			}
		});

	}



	public interface OnItemClickListener{
		/** 设置点击确认按钮时监听接口*/
		public void onClickOKPop();
	}

	/**设置监听*/
	public void setOnItemClickListener(OnItemClickListener listener){
		this.listener=listener;
	}


	// 当popWindow消失时响应
	@Override
	public void onDismiss() {
		listener.onClickOKPop();
	}

	/**弹窗显示的位置*/
	public void showAsDropDown(View parent){
		popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
	}

	/**消除弹窗*/
	public void dissmiss(){
		popupWindow.dismiss();
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
//		case R.id.pop_choice_16g:
//
//			pop_choice_16g.setBackgroundResource(R.drawable.yuanjiao_choice);
//			pop_choice_32g.setBackgroundResource(R.drawable.yuanjiao);
//			pop_choice_16m.setBackgroundResource(R.drawable.yuanjiao);
//			pop_choice_32m.setBackgroundResource(R.drawable.yuanjiao);
//			str_type=pop_choice_16g.getText().toString();
//			break;
//		case R.id.pop_choice_32g:
//			pop_choice_16g.setBackgroundResource(R.drawable.yuanjiao);
//			pop_choice_32g.setBackgroundResource(R.drawable.yuanjiao_choice);
//			pop_choice_16m.setBackgroundResource(R.drawable.yuanjiao);
//			pop_choice_32m.setBackgroundResource(R.drawable.yuanjiao);
//
//			str_type=pop_choice_32g.getText().toString();
//			break;
//		case R.id.pop_choice_16m:
//
//			pop_choice_16g.setBackgroundResource(R.drawable.yuanjiao);
//			pop_choice_32g.setBackgroundResource(R.drawable.yuanjiao);
//			pop_choice_16m.setBackgroundResource(R.drawable.yuanjiao_choice);
//			pop_choice_32m.setBackgroundResource(R.drawable.yuanjiao);
//			str_type=pop_choice_16m.getText().toString();
//			break;
//		case R.id.pop_choice_32m:
//
//			pop_choice_16g.setBackgroundResource(R.drawable.yuanjiao);
//			pop_choice_32g.setBackgroundResource(R.drawable.yuanjiao);
//			pop_choice_16m.setBackgroundResource(R.drawable.yuanjiao);
//			pop_choice_32m.setBackgroundResource(R.drawable.yuanjiao_choice);
//
//			str_type=pop_choice_32m.getText().toString();
//
//			break;
//		case R.id.pop_choice_black:
//
//			pop_choice_black.setBackgroundResource(R.drawable.yuanjiao_choice);
//			pop_choice_white.setBackgroundResource(R.drawable.yuanjiao);
//
//			str_color=pop_choice_black.getText().toString();
//			break;
//		case R.id.pop_choice_white:
//
//			pop_choice_black.setBackgroundResource(R.drawable.yuanjiao);
//			pop_choice_white.setBackgroundResource(R.drawable.yuanjiao_choice);
//
//			str_color=pop_choice_white.getText().toString();
//			break;
			case R.id.pop_add:
				if (!pop_num.getText().toString().equals("750")) {

					String num_add=Integer.valueOf(pop_num.getText().toString())+ADDORREDUCE+"";
					pop_num.setText(num_add);
				}else {
//					Toast.makeText(context, "���ܳ�������Ʒ����", Toast.LENGTH_SHORT).show();
				}

				break;
			case R.id.pop_reduce:
				if (!pop_num.getText().toString().equals("1")) {
					String num_reduce=Integer.valueOf(pop_num.getText().toString())-ADDORREDUCE+"";
					pop_num.setText(num_reduce);
				}else {
					Toast.makeText(context, "已经最少了", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.pop_del:
				listener.onClickOKPop();
				dissmiss();
				break;
			case R.id.pop_ok:
				listener.onClickOKPop();
//			if (str_color.equals("")) {
//				Toast.makeText(context, "�ף��㻹û��ѡ����ɫӴ~", Toast.LENGTH_SHORT).show();
//			}else if (str_type.equals("")) {
//				Toast.makeText(context, "�ף��㻹û��ѡ������Ӵ~",Toast.LENGTH_SHORT).show();
//			}else {
//				HashMap<String, Object> allHashMap=new HashMap<String,Object>();
//
//				allHashMap.put("color",str_color);
//				allHashMap.put("type",str_type);
//				allHashMap.put("num",pop_num.getText().toString());
//				allHashMap.put("id",Data.arrayList_cart_id+=1);
//
//				Data.arrayList_cart.add(allHashMap);
//				setSaveData();
				dissmiss();

//			}
				break;
//
			default:
				break;
		}
	}
	/**保存购物车的数据*/
	private void setSaveData(){
//		SharedPreferences sp=context.getSharedPreferences("SAVE_CART", Context.MODE_PRIVATE);
//		Editor editor=sp.edit();
//		editor.putInt("ArrayCart_size", Data.arrayList_cart.size());
//		for (int i = 0; i < Data.arrayList_cart.size(); i++) {
//			editor.remove("ArrayCart_type_"+i);
//			editor.remove("ArrayCart_color_"+i);
//			editor.remove("ArrayCart_num_"+i);
//			editor.putString("ArrayCart_type_"+i, Data.arrayList_cart.get(i).get("type").toString());
//			editor.putString("ArrayCart_color_"+i, Data.arrayList_cart.get(i).get("color").toString());
//			editor.putString("ArrayCart_num_"+i, Data.arrayList_cart.get(i).get("num").toString());
//
//		}



	}

}
