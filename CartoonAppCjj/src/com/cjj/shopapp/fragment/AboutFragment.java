package com.cjj.shopapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.shopapp.activity.R;
import com.cjj.shopapp.animation.ExpandAnimation;
import com.cjj.shopapp.custom.view.PullScrollView;
import com.cjj.shopapp.custom.view.PullScrollView.OnTurnListener;

/**
 * 关于界面
 * 
 * @author Administrator
 * 
 */
public class AboutFragment extends Fragment implements OnClickListener,
		OnTurnListener {
	private final int MAX_LINES = 4;
	private boolean isClickable = false;
	private TextView tv_info, tv_load_more;
	// 自定义的scrollview
	private PullScrollView mPullScrollView;
	// about 背景图
	private ImageView img_bigShopLogo;

	private TextView tv_Contact;
	private TextView tv_ContactContent;

	private boolean isAnimEnd = true;

	private ImageView img_otherContactArrow;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		mPullScrollView.init(img_bigShopLogo);
		mPullScrollView.setOnTurnListener(AboutFragment.this);
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_about, null);
		return v;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		tv_info = (TextView) view.findViewById(R.id.tv_info);
		tv_info.setMaxLines(MAX_LINES);
		tv_load_more = (TextView) view.findViewById(R.id.tv_loadmore);
		tv_load_more.setOnClickListener(this);

		mPullScrollView = (PullScrollView) view.findViewById(R.id.sv_about);
		img_bigShopLogo = (ImageView) view.findViewById(R.id.img_bigShopLogo);

		tv_Contact = (TextView) view.findViewById(R.id.tv_otherContact);
		tv_Contact.setOnClickListener(this);
		tv_ContactContent = (TextView) view
				.findViewById(R.id.tv_otherContactContent);

		img_otherContactArrow = (ImageView) view
				.findViewById(R.id.img_otherContactArrow);
		img_otherContactArrow.measure(0, 0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_loadmore:
			if (!isClickable) {
				tv_info.setMaxLines(tv_info.getLineCount());
				tv_load_more.setText("收起");
				isClickable = true;
			} else {
				tv_info.setMaxLines(MAX_LINES);
				tv_load_more.setText("查看更多");
				isClickable = false;
			}
			break;
		case R.id.tv_otherContact:

			if (isAnimEnd) {

				if (tv_ContactContent.getVisibility() == View.GONE) {
					rotateArrow(0, 90);
				} else {
					rotateArrow(90, 0);
				}

				ExpandAnimation mAnimation = new ExpandAnimation(
						tv_ContactContent);
				mAnimation.setAnimationListener(new ExpandAnimationListener());
				tv_ContactContent.startAnimation(mAnimation);
			}
		}
	}

	/** 防止用户频繁操作 */
	private class ExpandAnimationListener implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {

			isAnimEnd = false;
		}

		@Override
		public void onAnimationEnd(Animation animation) {

			isAnimEnd = true;
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}
	}

	/**
	 * 旋转指示器
	 * 
	 * @param fromDegrees
	 * @param toDegrees
	 */
	private void rotateArrow(float fromDegrees, float toDegrees) {
		RotateAnimation mRotateAnimation = new RotateAnimation(fromDegrees,
				toDegrees,
				(int) (img_otherContactArrow.getMeasuredWidth() / 2.0),
				(int) (img_otherContactArrow.getMeasuredHeight() / 2.0));
		mRotateAnimation.setDuration(150);
		mRotateAnimation.setFillAfter(true);
		img_otherContactArrow.startAnimation(mRotateAnimation);
	}

	@Override
	public void onTurn() {
		Toast.makeText(getActivity(), "拼命拉拉有惊喜哦！", 1000).show();
	}

}
