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
	private ProgressBar progressBar;// ��ɫ������

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_js_test_layout);

		myWebView = (WebView) findViewById(R.id.myWebView);
		progressBar = (ProgressBar) findViewById(R.id.pb);
		progressBar.setMax(100);

		// �õ��������ԵĶ���
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);// ʹ��JavaScript
		webSettings.setSupportZoom(true); // ֧������
		webSettings.setBuiltInZoomControls(true); // ������������װ��
		webSettings.setDefaultTextEncodingName("GBK");// ֧�����ģ�����ҳ����������ʾ����
		// ������WebView�д���ҳ��������Ĭ�������
		myWebView.setWebViewClient(new WebViewClient());
		// ��������������JS�����еİ�ť����ʾ�����ǰ���ȥȴ�������Ի���
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
				view.loadUrl(url); // �����µ�url
				return true; // ����true,�����¼��Ѵ���,�¼���������ֹ
			}
		});

		// ������˰�ť,��WebView����һҳ(Ҳ���Ը�дActivity��onKeyDown����)
		myWebView.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_BACK
							&& myWebView.canGoBack()) {
						myWebView.goBack(); // ����
						return true; // �Ѵ���
					}
				}
				return false;
			}
		});

		// ��JavaScript����Android������
		// �Ƚ��������࣬��Ҫ���õ�Android����д���������public����
		// ���������WebView�����е�JavaScript����
		// ��һ��������һ���������룬��JS�������������������������󣬿��Ե�����������һЩ����
		myWebView.addJavascriptInterface(new WebAppInterface(this),
				"myInterfaceName");

		// ����ҳ�棺����html��Դ�ļ�
		myWebView.loadUrl("file:///android_asset/sample.html");
		myWebView.requestFocus(); // ��ȡ����

		// ������һ��Android��ť���º����JS�еĴ���
		myButton = (Button) findViewById(R.id.button1);
		myButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��Android�������JavaScript������
				myWebView.loadUrl("javascript:myFunction()");
				// ����ʵ�ֵ�Ч��������ҳ�е����һ����ť��Ч��һ��
			}
		});
		// ������һ��Android��ť���º����JS�еĴ���
		myButton2 = (Button) findViewById(R.id.button2);
		myButton2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��Android�������JavaScript������绰
				myWebView.loadUrl("javascript:callPhone('13861156032')");
				// ����ʵ�ֵ�Ч��������ҳ��call��ť��Ч��һ��
			}
		});
	}

}
