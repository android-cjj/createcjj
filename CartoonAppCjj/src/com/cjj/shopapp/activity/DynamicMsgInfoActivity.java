package com.cjj.shopapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DynamicMsgInfoActivity extends Activity implements OnClickListener{
	//---------声明个个变量
	private int icon;
	private String title;
	private String intro;
	private TextView tv_title;
	private TextView tv_intro;
	private ImageView iv_icon;
	private TextView tv_back_title;
	private ImageButton img_btn_back;
	private Button btn_start;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic_msginfo);
		getDataByBundle();
		findView();
		showData();
	}

	/**
	 * showdata
	 */
	private void showData() {
		tv_back_title.setText(title);
		img_btn_back.setOnClickListener(this);
		btn_start.setOnClickListener(this);
		iv_icon.setImageResource(icon);
		tv_title.setText(title);
		tv_intro.setText(intro);
	}

	/**
	 * init
	 */
	private void findView() {
		tv_back_title = (TextView) this.findViewById(R.id.tv_title);
		img_btn_back = (ImageButton) this.findViewById(R.id.ibtn_back);
		iv_icon = (ImageView) this.findViewById(R.id.img_Photo);
		tv_title = (TextView) this.findViewById(R.id.tv_name);
		tv_intro = (TextView) this.findViewById(R.id.tv_content);
		btn_start = (Button) this.findViewById(R.id.btn_start);
	}

	/**
	 * get bundle data
	 */
	private void getDataByBundle() {
		Bundle bundle = this.getIntent().getExtras();
		if(bundle!=null){
			icon = bundle.getInt("icon");
			title = bundle.getString("title");
			intro = bundle.getString("intro");
		}
		
	}

	/**
	 * onclick event
	 */
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.ibtn_back:
			this.finish();
			break;
		case R.id.btn_start:
			Toast.makeText(this, "待开发。。。", 1000).show();
			break;
		}
	}
}
