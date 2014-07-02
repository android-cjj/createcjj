package com.cjj.shopapp.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.shopapp.activity.R;
import com.cjj.shopapp.adapter.ExpandableListViewSlideMenuAdapter;
import com.cjj.shopapp.utils.ImageCropUtils;

public class SlideMenuFragment extends Fragment implements OnClickListener {
	private ImageView img_head;
	private TextView tv_title;
	private AlertDialog headDialog;
	private ImageCropUtils cropUtils;
	private static final int OPEN_CAMERA = 0x101;
	private static final int CUT_CAMERA_RESULT = 0x102;
	private static final int CUT_GALLERY_RESULT = 0x103;
	private Bitmap bitmap;
	private ExpandableListView expandableListView;
	private ExpandableListViewSlideMenuAdapter adapter;
	private TextView tv_exit;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_slide_menu, null);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new ExpandableListViewSlideMenuAdapter();
		expandableListView.setAdapter(adapter);
		expandableListView.setGroupIndicator(null);
		int groupCount = expandableListView.getCount();

		for (int i = 0; i < groupCount; i++) {
			expandableListView.expandGroup(i);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroyView() {
		img_head = null;
		tv_title = null;
		headDialog = null;
		cropUtils = null;
		bitmap = null;
		super.onDestroyView();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		expandableListView = (ExpandableListView) view
				.findViewById(R.id.elv_slide_menu);
		img_head = (ImageView) view.findViewById(R.id.img_head);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_exit = (TextView) view.findViewById(R.id.tv_exit);
		tv_exit.setOnClickListener(this);
		tv_title.setOnClickListener(this);
		img_head.setOnClickListener(this);
		initAlertUserHeadDialog();
		cropUtils = new ImageCropUtils(getActivity());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_head:
		case R.id.tv_title:
			headDialog.show();
			break;
		case R.id.tv_exit:
			System.exit(0);
			break;
		}
	}


	/**
	 * 显示修改公会头像的对话框
	 */
	private String[] photoItems = new String[] { "相册", "拍照" };

	private void initAlertUserHeadDialog() {
		headDialog = new AlertDialog.Builder(getActivity()).setTitle("修改头像")
				.setItems(photoItems, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();

						switch (which) {
						case 0: // 相册
							cropUtils
									.openGalleryAndCropSmallPhoto(CUT_GALLERY_RESULT);
							break;

						case 1: // 拍照

							cropUtils.openCamera(OPEN_CAMERA);
							break;
						}
					}
				}).create();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case OPEN_CAMERA:// 从相机

			cropUtils.cropBigPhotoByCamera(CUT_CAMERA_RESULT);

			break;

		case CUT_CAMERA_RESULT:// 返回记过

			bitmap = cropUtils.getBitmapByUri();
			img_head.setImageBitmap(bitmap);

			break;

		case CUT_GALLERY_RESULT:

			if (data != null) {
				bitmap = cropUtils.getBitmapByIntent(data);
				img_head.setImageBitmap(bitmap);
			}

			break;
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

}
