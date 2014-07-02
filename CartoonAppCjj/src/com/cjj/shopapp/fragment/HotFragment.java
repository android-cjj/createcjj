package com.cjj.shopapp.fragment;

import java.util.ArrayList;
import java.util.List;

import com.cjj.shopapp.activity.DynamicMsgInfoActivity;
import com.cjj.shopapp.activity.R;
import com.cjj.shopapp.adapter.HotFragmentListMsgAdapter;
import com.cjj.shopapp.constants.Constants;
import com.cjj.shopapp.models.HotInfos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class HotFragment extends Fragment implements OnItemClickListener{
	//变量声明
	private ListView lv_hot;
	private HotFragmentListMsgAdapter hotAdapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//这里应该或者网络上的数据的，我数据直接在本地取的
		getHotData();
	}

	/**
	 * 获得数据
	 */
	private void getHotData() {
		List<HotInfos> list = new ArrayList<HotInfos>();
		for(int i = 0; i<Constants.hot_icon.length;i++){
			HotInfos info = new HotInfos();
			info.setImg_bg(Constants.hot_icon[i]);
			info.setTitle(Constants.hot_title[i]);
			info.setIntro(Constants.hot_intro[i]);
			list.add(info);
		}
		hotAdapter = new HotFragmentListMsgAdapter(getActivity(), list);
		lv_hot.setAdapter(hotAdapter);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_hot, null);
		return v;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		lv_hot = (ListView) view.findViewById(R.id.lv_hot);
		lv_hot.setOnItemClickListener(this);
	}

	/**
	 * listview item 监听
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		HotInfos infos = hotAdapter.getItem(position);
		Intent intent = new Intent();
		intent.setClass(getActivity(), DynamicMsgInfoActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("icon", infos.getImg_bg());
		bundle.putString("title", infos.getTitle());
		bundle.putString("intro", infos.getIntro());
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
