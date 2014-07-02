package com.cjj.shopapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.cjj.shopapp.fragment.SlideMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class BaseActivity extends SlidingFragmentActivity {
	private int mTitleRes;

	private SlideMenuFragment SlideMenu;

	public BaseActivity(int titleRes) {
		mTitleRes = titleRes;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setTitle(mTitleRes);
		//隐藏的slidingmenu
		setBehindContentView(R.layout.menu_frame);

		FragmentManager fm = getSupportFragmentManager();
		if (savedInstanceState == null) {
			FragmentTransaction ft = fm.beginTransaction();
			SlideMenu = new SlideMenuFragment();
			ft.replace(R.id.menu_frame, SlideMenu);
			ft.commit();
		} else {
			SlideMenu = (SlideMenuFragment) fm
					.findFragmentById(R.id.menu_frame);
		}

		// 获得SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		// sm.setShadowDrawable(R.drawable.ic_launcher);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
	//	sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
	}

	private void toastMsg(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	/*退出的间隔时间 */
	private static final long EXIT_INTERVAL_TIME = 2000;
	private long touchTime = 0;

	/**
	 * On key up.
	 * 
	 * @param keyCode
	 *            the key code
	 * @param event
	 *            the event
	 * @return true, if successful
	 */
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& getSlidingMenu().isMenuShowing()) {
			long currentTime = System.currentTimeMillis();

			if ((currentTime - touchTime) >= EXIT_INTERVAL_TIME) {
				toastMsg("在按一次退出程序");
				touchTime = currentTime;
			} else {
				finish();
				System.exit(0);
			}

			return false;
		} else {
			getSlidingMenu().showMenu();
			return true;
		}
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		SlideMenu.onActivityResult(arg0, arg1, arg2);
		super.onActivityResult(arg0, arg1, arg2);
	}

}
