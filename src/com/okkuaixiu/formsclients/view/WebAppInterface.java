package com.okkuaixiu.formsclients.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * 自定义的Android代码和JavaScript代码之间的桥梁类
 * 
 * @author 1
 * 
 */
public class WebAppInterface {
	Context mContext;

	/** Instantiate the interface and set the context */
	WebAppInterface(Context c) {
		mContext = c;
	}

	/** Show a toast from the web page */
	// 如果target 大于等于API 17，则需要加上如下注解
	// @JavascriptInterface
	public void showToast(String toast) {
		// Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
		Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
	}

	public void callPhone(String num) {
		// 传入服务， parse（）解析号码
		Log.v("lala", "num:" + num);
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num));
		// 通知activtity处理传入的call服务
		mContext.startActivity(intent);
	}
	
	public void startToBActivity(String url){
		Intent in = new Intent(mContext, BActivity.class);
		in.putExtra("url", url);
		mContext.startActivity(in);
	}
}