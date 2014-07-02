
package com.cjj.shopapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;

import com.cjj.shopapp.fragment.HomeFrameFragment;

public class MainActivity extends BaseActivity {
	private HomeFrameFragment homeFrameFragment;

	public MainActivity() {
		super(R.string.viewpager);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		init();
	}
	/**
	 * 初始化数据
	 */
	private void init() {
		//加载首页fragment
		homeFrameFragment = new HomeFrameFragment();
		replaceFragment(homeFrameFragment);
	}
	
	
	/**
	 * 替换fragment
	 */
	public void replaceFragment(Fragment fragment){
		getSupportFragmentManager().beginTransaction().replace(R.id.fl_Container, fragment).commit();
	}
	
	/**
	 * 选择加载一个新的Fragment
	 */
	public void switchNewFragment(Fragment newFragment){
	   replaceFragment(newFragment);   
	   getSlidingMenu().showContent();
	}
	
	/**
	 * 更新首页Fragment
	 * @return
	 */
	public HomeFrameFragment createNewHomeFragment()
	{
		HomeFrameFragment  homeFrameFragment = new HomeFrameFragment();
		return homeFrameFragment;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		
		super.onActivityResult(arg0, arg1, arg2);
	}

}
