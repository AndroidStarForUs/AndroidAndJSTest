package com.okkuaixiu.formsclients.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.circlepregress.R;

public class MyWebViewJSTest extends Activity {

	private WebView myWebView = null;
	private Button myButton = null;
	private Button myButton2 = null;
	private ProgressBar progressBar;// 绿色进度条

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_js_test_layout);

		myWebView = (WebView) findViewById(R.id.myWebView);
		progressBar = (ProgressBar) findViewById(R.id.pb);
		progressBar.setMax(100);

		// 得到设置属性的对象
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);// 使能JavaScript
		webSettings.setSupportZoom(true); // 支持缩放
		webSettings.setBuiltInZoomControls(true); // 启用内置缩放装置
		webSettings.setDefaultTextEncodingName("GBK");// 支持中文，否则页面中中文显示乱码
		// 限制在WebView中打开网页，而不用默认浏览器
		myWebView.setWebViewClient(new WebViewClient());
		// 如果不设置这个，JS代码中的按钮会显示，但是按下去却不弹出对话框
		// Sets the chrome handler. This is an implementation of WebChromeClient
		// for use in handling JavaScript dialogs, favicons, titles, and the
		// progress. This will replace the current handler.
		myWebView.setWebChromeClient(new WebChromeClient() {

			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					JsResult result) {
				// TODO Auto-generated method stub
				return super.onJsAlert(view, url, message, result);
			}

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				progressBar.setProgress(newProgress);
				if (newProgress == 100) {
					progressBar.setVisibility(View.GONE);
				}
				super.onProgressChanged(view, newProgress);
			}
		});

		myWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url); // 加载新的url
				return true; // 返回true,代表事件已处理,事件流到此终止
			}
		});

		// 点击后退按钮,让WebView后退一页(也可以覆写Activity的onKeyDown方法)
		myWebView.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_BACK
							&& myWebView.canGoBack()) {
						myWebView.goBack(); // 后退
						return true; // 已处理
					}
				}
				return false;
			}
		});

		// 用JavaScript调用Android函数：
		// 先建立桥梁类，将要调用的Android代码写入桥梁类的public函数
		// 绑定桥梁类和WebView中运行的JavaScript代码
		// 将一个对象起一个别名传入，在JS代码中用这个别名代替这个对象，可以调用这个对象的一些方法
		myWebView.addJavascriptInterface(new WebAppInterface(this),
				"myInterfaceName");

		// 载入页面：本地html资源文件
		myWebView.loadUrl("file:///android_asset/sample.html");
		myWebView.requestFocus(); // 获取焦点

		// 这里用一个Android按钮按下后调用JS中的代码
		myButton = (Button) findViewById(R.id.button1);
		myButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 用Android代码调用JavaScript函数：
				myWebView.loadUrl("javascript:myFunction()");
				// 这里实现的效果和在网页中点击第一个按钮的效果一致
			}
		});
		// 这里用一个Android按钮按下后调用JS中的代码
		myButton2 = (Button) findViewById(R.id.button2);
		myButton2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 用Android代码调用JavaScript函数打电话
				myWebView.loadUrl("javascript:callPhone('13861156032')");
				// 这里实现的效果和在网页中call按钮的效果一致
			}
		});
	}

}
