package com.okkuaixiu.formsclients.view;

import com.example.circlepregress.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BActivity extends Activity {

	private TextView tv;
	private String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b_layout);

		url = getIntent().getExtras().getString("url");
		Log.v("lala", "url:"+url);

		tv = (TextView) findViewById(R.id.textView2);
		tv.setText("url:" + url);
	}

}
