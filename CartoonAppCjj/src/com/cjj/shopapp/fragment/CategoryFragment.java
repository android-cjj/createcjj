package com.cjj.shopapp.fragment;

import java.util.ArrayList;
import java.util.List;

import com.ab.view.carousel.CarouselAdapter;
import com.ab.view.carousel.CarouselView;
import com.ab.view.carousel.CarouselViewAdapter;
import com.ab.view.carousel.CarouselAdapter.OnItemClickListener;
import com.ab.view.carousel.CarouselAdapter.OnItemSelectedListener;
import com.cjj.shopapp.activity.R;
import com.cjj.shopapp.constants.Constants;
import com.cjj.shopapp.models.CategoryInfo;
import com.cjj.shopapp.models.ShopAppApplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryFragment extends Fragment implements OnItemClickListener,OnItemSelectedListener{
	private CarouselView carousel;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		getCategoryData();

		super.onActivityCreated(savedInstanceState);
	}

	private void getCategoryData() {
		// 不支持的动态添加adapter.notifyDataSetChanged()增强滑动的流畅
		List<View> mViews = new ArrayList<View>();
		List<CategoryInfo> data = new ArrayList<CategoryInfo>();
		for (int i = 0; i < Constants.category_msg.length; i++) {
			CategoryInfo categoryInfo2 = new CategoryInfo();
			categoryInfo2.setIcon(Constants.category_icon[i]);
			categoryInfo2.setMsg(Constants.category_msg[i]);
			data.add(categoryInfo2);
		}
		
		
		for (int i = 0; i < data.size(); i++) {
			View view = getActivity().getLayoutInflater().inflate(
					R.layout.item_carousel_view, null);
			
			ImageView icon = (ImageView) view.findViewById(R.id.itemsIcon);
			icon.setImageResource(data.get(i).getIcon());
			TextView msg = (TextView) view.findViewById(R.id.itemsText);
			msg.setText(data.get(i).getMsg());
			mViews.add(view);
		}

		CarouselViewAdapter adapter = new CarouselViewAdapter(getActivity(),
				mViews, false);
		carousel.setOnItemClickListener(this);
		carousel.setOnItemSelectedListener(this);
		carousel.setAdapter(adapter);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.from(getActivity()).inflate(
				R.layout.fragment_category, null);
		return v;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		carousel = (CarouselView) view.findViewById(R.id.carousel);
		super.onViewCreated(view, savedInstanceState);

	}

	

	@Override
	public void onItemSelected(CarouselAdapter<?> parent, View view,
			int position, long id) {
		Toast.makeText(getActivity(), " slect position="+position, 1000).show();
	}

	@Override
	public void onNothingSelected(CarouselAdapter<?> parent) {
		
	}

	@Override
	public void onItemClick(CarouselAdapter<?> parent, View view, int position,
			long id) {
		Toast.makeText(getActivity(), " onclick position="+position, 1000).show();
	}
}
