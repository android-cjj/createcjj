package com.cjj.shopapp.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjj.shopapp.activity.R;
import com.cjj.shopapp.adapter.CategoryAdapter;
import com.cjj.shopapp.adapter.RecommendAdapter;
import com.cjj.shopapp.adapter.ViewPagerAdvAdapter;
import com.cjj.shopapp.models.CategoryInfo;
import com.cjj.shopapp.models.ShopAppApplication;
import com.cjj.shopapp.constants.Constants;;

public class HomeFragment extends Fragment {
	private ViewPager vp_ad;
	private ImageView[] mImageViews;
	private int currentPosition = 0;
	private TextView tv_title;
	private GridView gv_category, gv_recommend;

	private List<CategoryInfo> mList = new ArrayList<CategoryInfo>();

	int[] recommend_icon = new int[] { R.drawable.huoying_bg,
			R.drawable.haizie_bg, R.drawable.heizi, R.drawable.sishen_bg };
	String[] recommend_msg = new String[] { "火影忍者", "海贼王", "黑子的篮球", "死神" };

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// 获得服务端广告图片，这里我们就简单的直接取本地数据
		getAdData();
		getCategoryData();
		getRecommendData();
		super.onActivityCreated(savedInstanceState);
	}

	/**
	 * 获取gridView推荐漫画的数据
	 */
	private void getRecommendData() {
		List<CategoryInfo> list2 = new ArrayList<CategoryInfo>();
		for (int i = 0; i < recommend_icon.length; i++) {
			CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setIcon(recommend_icon[i]);
			categoryInfo.setMsg(recommend_msg[i]);
			list2.add(categoryInfo);
		}
		gv_recommend.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gv_recommend.setAdapter(new RecommendAdapter(getActivity(), list2));
	}

	/**
	 * 获得gridView分类数据
	 */
	private void getCategoryData() {
		
		gv_category.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gv_category.setAdapter(new CategoryAdapter(getActivity(), ShopAppApplication.mDatas));

	}

	/**
	 * 获得广告数据
	 */
	private void getAdData() {
		List<Integer> list = new ArrayList<Integer>();

		list.add(R.drawable.huoying);
		list.add(R.drawable.caomao);
		list.add(R.drawable.yinhun);
		list.add(R.drawable.diguang);
		list.add(R.drawable.jianxin);

		vp_ad.setAdapter(new ViewPagerAdvAdapter(getActivity(), list));
		vp_ad.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				super.onPageSelected(position);
				setCurPoint(position);
			}
		});

	}

	private void setCurPoint(int index) {
		if (index < 0 || index > mImageViews.length || index == currentPosition) {
			return;
		}
		mImageViews[currentPosition].setEnabled(true);
		mImageViews[index].setEnabled(false);
		// set tv title
		switch (index) {
		case 0:
			tv_title.setText("火影忍者");
			break;
		case 1:
			tv_title.setText("海贼王");
			break;
		case 2:
			tv_title.setText("银魂");
			break;
		case 3:
			tv_title.setText("黑子的篮球");
			break;
		case 4:
			tv_title.setText("浪客剑心");
			break;
		}
		currentPosition = index;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home, null);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		vp_ad = (ViewPager) view.findViewById(R.id.vp_ad);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		gv_category = (GridView) view.findViewById(R.id.gv_category);
		gv_recommend = (GridView) view.findViewById(R.id.gv_recommend);
		createPoint(view);
	}

	private void createPoint(View view) {
		// six index round point
		LinearLayout ll = (LinearLayout) view.findViewById(R.id.llayout);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(0, 0, 12, 0);
		mImageViews = new ImageView[5];
		for (int i = 0; i < mImageViews.length; i++) {
			mImageViews[i] = new ImageView(getActivity());
			mImageViews[i].setImageResource(R.drawable.guide_round);
			mImageViews[i].setEnabled(true);
			mImageViews[i].setLayoutParams(lp);
			ll.addView(mImageViews[i]);
		}
		mImageViews[currentPosition].setEnabled(false);

	}
}
