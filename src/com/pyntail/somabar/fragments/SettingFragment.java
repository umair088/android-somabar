package com.pyntail.somabar.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.ui.views.TitleBar;
import com.slidinglayer.SlidingLayer;

public class SettingFragment extends BaseSupportFragment {

	private final String TAG = this.getClass().getSimpleName();
	private View parentView;

	@InjectView(R.id.slidingLayer)
	SlidingLayer sliderLayer;

	@InjectView(R.id.measurmentContainer)
	RelativeLayout measurmentContainer;

	public static SettingFragment newInstance() {
		return new SettingFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (parentView == null) {
			parentView = inflater.inflate(R.layout.fragment_settings,
					container, false);
			ButterKnife.inject(this, parentView);
		} else
			((ViewGroup) parentView.getParent()).removeView(parentView);

		return parentView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		activtyReference = (MainActivity) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		measurmentContainer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (sliderLayer.isOpened())
					sliderLayer.closeLayer(true);
				else
					sliderLayer.openLayer(true);

			}
		});
	}

	@Override
	public void setTitleBar(TitleBar titleBar) {

		titleBar.setHeaderColor(this.getResources().getColor(
				R.color.setting_grey));
		titleBar.setLeftButtonIcon(R.drawable.menu_icon_orange);
		titleBar.setOnLeftClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				activtyReference.openMenu();
			}
		});
		titleBar.setHeadingText("Settings");
		titleBar.hideRightButton();
		titleBar.hideAddButton();

	}

}
