package com.cjj.shopapp.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 首页商家广告适配器
 *
 */
public class ViewPagerAdvAdapter extends PagerAdapter implements OnClickListener{
	
	private List<Integer> mDatas;
	private List<ImageView> mViews;
	private ImageView mImageView;
	private Context mContext;
	private Bundle mBundle;
	private Intent mIntent;
	
	public ViewPagerAdvAdapter(Context mContext,List<Integer> mDatas)
	{
		mIntent = new Intent();
		mBundle = new Bundle();
		this.mContext = mContext;
		mViews = new ArrayList<ImageView>();
		this.mDatas = mDatas;
		int length = mDatas == null ? 0 : mDatas.size();
		
		for(int i=0;i<length;i++)
		{
			ImageView mImageView = new ImageView(mContext);
			mViews.add(mImageView);
		}
		
		length = 0;
	}

	@Override
	public int getCount() {
		
		return mDatas == null ? 0 : mDatas.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		
		return arg0==(arg1);
	}
	
	@Override
	public Object instantiateItem(View container, int position) {
		
		int pos = mDatas.get(position);

		mImageView = mViews.get(position);
		
		mImageView.setAdjustViewBounds(true);
		mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
		mImageView.setTag(pos);
		mImageView.setOnClickListener(this);
		
		mImageView.setImageResource(pos);
		((ViewPager)container).addView(mImageView,0);
		
		return mImageView;
	}
	
	@Override
	public void destroyItem(View container, int position, Object object) {
		
		mImageView = mViews.get(position);
		((ViewPager)container).removeView(mImageView);
	}

	@Override
	public void onClick(View v) {
		
	
	}
}
