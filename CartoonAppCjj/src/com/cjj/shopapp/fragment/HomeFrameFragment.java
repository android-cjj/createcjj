package com.cjj.shopapp.fragment;

import com.cjj.shopapp.activity.MainActivity;
import com.cjj.shopapp.activity.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class HomeFrameFragment extends Fragment implements OnClickListener,OnCheckedChangeListener{
	/**显示slidming按钮*/
	private ImageButton ibtn_left_menu;
	/**四个子Fragment*/
	private HomeFragment homeFragment;
	private CategoryFragment categoryFragment;
	private HotFragment hotFragment;
	private AboutFragment aboutFragment;
	/**四个子Fragment的Tag*/
    public static final String TAG_HOME = "Home";
    public static final String TAG_CATEGORY = "Category";
    public static final String TAG_HOT = "hot";
    public static final String TAG_ABOUT = "About";
    
    /**显示RG按钮*/
    private RadioGroup rg_home_tab_menu;
    
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private String hideTag;
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home_frame, null);
		return v;
	}

	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ibtn_left_menu = (ImageButton) view.findViewById(R.id.ibtn_left_menu);
		ibtn_left_menu.setOnClickListener(this);
		
		rg_home_tab_menu = (RadioGroup) view.findViewById(R.id.rg_tab_menu);
		//设置第一个radiobutton为选中状态
		RadioButton rbtn = (RadioButton) rg_home_tab_menu.getChildAt(0);
		rbtn.setChecked(true);
		//设置radiogroud监听
		rg_home_tab_menu.setOnCheckedChangeListener(this);
		
		categoryFragment = new CategoryFragment();
		switchFragment(categoryFragment,TAG_CATEGORY);
		
		homeFragment = new HomeFragment();
		switchFragment(homeFragment,TAG_HOME);
		
		
	}
	/**
	 * 选择不同的fragment
	 */
	private void switchFragment(Fragment fragment,String tag) {
		mFragmentManager = getChildFragmentManager();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		
		Fragment tagFragment = mFragmentManager.findFragmentByTag(tag);
		
		if(tagFragment == null){
			mFragmentTransaction.add(R.id.fl_tab_menu, fragment, tag);
		}else{
			mFragmentTransaction.show(tagFragment);
		}
		
		tagFragment = mFragmentManager.findFragmentByTag(hideTag);
		
		if(tagFragment!=null){
			mFragmentTransaction.hide(tagFragment);
		}
		
		hideTag = tag;
		mFragmentTransaction.commit();
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.ibtn_left_menu:
			((MainActivity) getActivity()).showMenu();
			break;
		}
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch(checkedId){
		case R.id.rbtn_one:
			if(homeFragment == null){
				homeFragment = new HomeFragment();
			}
			switchFragment(homeFragment, TAG_HOME);
			break;
		case R.id.rbtn_two:
			if(categoryFragment == null){
				categoryFragment = new CategoryFragment();
			}
			switchFragment(categoryFragment, TAG_CATEGORY);
			break;
		case R.id.rbtn_three:
			if(hotFragment == null){
				hotFragment = new HotFragment();
			}
			switchFragment(hotFragment, TAG_HOT);
			break;
		case R.id.rbtn_four:
			if(aboutFragment == null){
				aboutFragment = new AboutFragment();
			}
			switchFragment(aboutFragment, TAG_ABOUT);
			break;
		}
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
	}


}
