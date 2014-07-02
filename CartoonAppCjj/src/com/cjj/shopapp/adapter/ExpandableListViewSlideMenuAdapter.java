package com.cjj.shopapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.shopapp.activity.MainActivity;
import com.cjj.shopapp.activity.R;
import com.cjj.shopapp.fragment.AboutFragment;
import com.cjj.shopapp.fragment.CategoryFragment;
import com.cjj.shopapp.fragment.HotFragment;

public class ExpandableListViewSlideMenuAdapter extends
		BaseExpandableListAdapter implements OnClickListener {
	private Context mContext;
	private MainActivity mGroupActivity;
	private static final String[] title = new String[] { "功能", "其他" };
	private static final String[][] msg = new String[][] {
			{ "主界面", "分类", "热门", "关于" }, { "分享", "反馈", "更多" } };

	private static final int[][] icon = new int[][] {
			{ R.drawable.menu_home, R.drawable.slide_allgoods,
					R.drawable.menu_shop_order, R.drawable.menu_enter_order },
			{  R.drawable.menu_message, R.drawable.menu_aler_user_info,
					R.drawable.menu_shop_commend, } };

	@Override
	public int getGroupCount() {
		return title.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return msg[groupPosition].length;
	}

	@Override
	public String getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return title[groupPosition];
	}

	@Override
	public String getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return msg[groupPosition][childPosition];
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		mContext = parent.getContext();
		mGroupActivity = (MainActivity) mContext;

		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.item_elv_slide_menu_group, null);
			mGroupHolder = new GroupHolder();
			mGroupHolder.tv_menuGroup = (TextView) convertView
					.findViewById(R.id.tv_menuGroup);
			mGroupHolder.ab_top_line = convertView
					.findViewById(R.id.ab_top_line);
			convertView.setTag(mGroupHolder);
		} else {
			mGroupHolder = (GroupHolder) convertView.getTag();
		}

		String groupName = getGroup(groupPosition);
		mGroupHolder.tv_menuGroup.setText(groupName);

		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.item_elv_slide_menu_child, null);
			mChildHolder = new ChildHolder();
			mChildHolder.tv_menuChild = (TextView) convertView
					.findViewById(R.id.tv_menuChild);
			mChildHolder.ab_top_line = convertView
					.findViewById(R.id.ab_top_line);
			convertView.setTag(mChildHolder);
		} else {
			mChildHolder = (ChildHolder) convertView.getTag();
		}

		String childName = getChild(groupPosition, childPosition);

		mChildHolder.tv_menuChild.setText(childName);
		drawLeftByTextView(mChildHolder.tv_menuChild,
				icon[groupPosition][childPosition]);
		mChildHolder.tv_menuChild.setTag(groupPosition + "," + childPosition);
		mChildHolder.tv_menuChild.setOnClickListener(this);

		return convertView;
	}

	private void drawLeftByTextView(TextView mTextView, int resource) {
		Drawable mDrawable = mContext.getResources().getDrawable(resource);
		mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(),
				mDrawable.getIntrinsicHeight());
		mTextView.setCompoundDrawables(mDrawable, null, null, null);
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	private class GroupHolder {
		private TextView tv_menuGroup;
		private View ab_top_line;
	}

	private class ChildHolder {
		private TextView tv_menuChild;
		private View ab_top_line;
	}

	private GroupHolder mGroupHolder;
	private ChildHolder mChildHolder;

	@Override
	public void onClick(View v) {

		String tag = v.getTag().toString();
		String[] strTag = tag.split(",");
		int childPosition = Integer.parseInt(strTag[1].toString());
		int groupPosition = Integer.parseInt(strTag[0].toString());

		if (groupPosition == 0) {
			if (childPosition == 0) {
				mGroupActivity.switchNewFragment(mGroupActivity
						.createNewHomeFragment());
			} else if (childPosition == 1) {
				mGroupActivity.switchNewFragment(new CategoryFragment());
			} else if (childPosition == 2) {
				mGroupActivity.switchNewFragment(new HotFragment());
			} else if (childPosition == 3) {
				mGroupActivity.switchNewFragment(new AboutFragment());
			}
		} else {
			if (childPosition == 0) {
				Toast.makeText(mContext, "待开发", 1000).show();
			} else if (childPosition == 1) {
				Toast.makeText(mContext, "待开发", 1000).show();
			} else if (childPosition == 2) {
				Toast.makeText(mContext, "待开发", 1000).show();
			}
		}

	}

}
