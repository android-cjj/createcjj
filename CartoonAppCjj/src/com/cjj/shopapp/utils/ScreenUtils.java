package com.cjj.shopapp.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 获取设备分辨�?
 * @author Water
 *
 */
public class ScreenUtils {
	
	/**设备宽度Px*/
	public static int screenWidthPx = 0;
	
	/**设备高度Px*/
	public static int screenHeightPx = 0;
	
	/**设备密度*/
	public static float densityPx = 0;
	
	private static ScreenUtils screenUtils;
	
	public ScreenUtils()
	{
	}
	
	public static ScreenUtils getInstance()
	{
		if(screenUtils == null)
		{
			synchronized (ScreenUtils.class)
			{
				if(screenUtils == null)
				{
					screenUtils = new ScreenUtils();
				}
				
			}
		}
		
		return screenUtils;
	}
	
	
	
	/**
	 * 获取设备的宽高单位Px
	 * @param activity
	 */
	public static void initScreenSize(Context mContext)
	{
		DisplayMetrics dm = mContext.getResources().getDisplayMetrics();

		screenHeightPx = dm.heightPixels;
		
		screenWidthPx = dm.widthPixels;
		
		densityPx = dm.density;
	}
	
	public static int dipConvertPx(int size)
	{
		return (int)(size*densityPx);
	}


}
