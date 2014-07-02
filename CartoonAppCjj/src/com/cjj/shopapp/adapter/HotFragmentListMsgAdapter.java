package com.cjj.shopapp.adapter;

import java.util.List;

import com.cjj.shopapp.activity.R;
import com.cjj.shopapp.models.CategoryInfo;
import com.cjj.shopapp.models.HotInfos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HotFragmentListMsgAdapter extends BaseAdapter {

	private Context mContext;
	private List<HotInfos> mList;

	public HotFragmentListMsgAdapter(Context mContext, List<HotInfos> mList) {
		this.mContext = mContext;
		this.mList = mList;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public HotInfos getItem(int position) {
		return mList == null ? null : mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mHolder;
		HotInfos hotInfos = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_hot, null);
			mHolder = new ViewHolder();
			mHolder.icon = (ImageView) convertView.findViewById(R.id.img_dynamicMsg);
			mHolder.title = (TextView) convertView.findViewById(R.id.tv_dynamicMsgName);
			mHolder.intro = (TextView) convertView.findViewById(R.id.tv_dynamicMsgContent);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}

		mHolder.icon.setImageResource(hotInfos.getImg_bg());
		mHolder.title.setText(hotInfos.getTitle());
		mHolder.intro.setText(hotInfos.getIntro());

		return convertView;
	}

	private class ViewHolder {
		private ImageView icon;
		private TextView title;
		private TextView intro;
	}
}
