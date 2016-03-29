package com.okkuaixiu.formsclients.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * �Զ����Android�����JavaScript����֮���������
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
	// ���target ���ڵ���API 17������Ҫ��������ע��
	// @JavascriptInterface
	public void showToast(String toast) {
		// Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
		Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
	}

	public void callPhone(String num) {
		// ������� parse������������
		Log.v("lala", "num:" + num);
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num));
		// ֪ͨactivtity�������call����
		mContext.startActivity(intent);
	}
	
	public void startToBActivity(String url){
		Intent in = new Intent(mContext, BActivity.class);
		in.putExtra("url", url);
		mContext.startActivity(in);
	}
}