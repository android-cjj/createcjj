package com.cjj.shopapp.models;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

import com.cjj.shopapp.activity.R;
import com.cjj.shopapp.constants.Constants;
import com.cjj.shopapp.utils.LogUtil;

public class ShopAppApplication extends Application {
	public static List<CategoryInfo> mDatas;

	@Override
	public void onCreate() {
		super.onCreate();
		LogUtil.LogMsg("---------------------onCreate start-------------");
		mDatas = new ArrayList<CategoryInfo>();
//		CategoryInfo categoryInfo = new CategoryInfo();
//		categoryInfo.setMsg("全部漫画");
//		categoryInfo.setIcon(R.drawable.all_icon);
//		mDatas.add(categoryInfo);
		
		for (int i = 0; i < Constants.category_msg.length; i++) {
			CategoryInfo categoryInfo2 = new CategoryInfo();
			categoryInfo2.setIcon(Constants.category_icon[i]);
			categoryInfo2.setMsg(Constants.category_msg[i]);
			mDatas.add(categoryInfo2);
		}
		
	}

	public ShopAppApplication() {
		LogUtil.LogMsg("---------------------ShopAppApplication start-------------");
	}

	
}
