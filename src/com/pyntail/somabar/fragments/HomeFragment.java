package com.pyntail.somabar.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.ui.views.AnyTextView;
import com.pyntail.somabar.ui.views.RippleBackground;
import com.pyntail.somabar.ui.views.TitleBar;

public class HomeFragment extends BaseSupportFragment {

	private final String TAG = this.getClass().getSimpleName();
	private View parentView;

	@InjectView(R.id.content)
	RippleBackground rippleBackground;

	@InjectView(R.id.btnStatus)
	View btnStatus;

	@InjectView(R.id.txtDiscoverDrinks)
	AnyTextView txtDiscoverDrinks;

	@InjectView(R.id.txtCommunity)
	AnyTextView txtCommunity;

	@InjectView(R.id.txtMyDrinks)
	AnyTextView txtMyDrinks;

	public static HomeFragment newInstance() {
		return new HomeFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (parentView == null) {
			parentView = inflater.inflate(R.layout.fragment_home, container,
					false);
			ButterKnife.inject(this, parentView);
		} else
			((ViewGroup) parentView.getParent()).removeView(parentView);

		return parentView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		rippleBackground.startRippleAnimation();
		setListener();
	}

	private void setListener() {
		txtDiscoverDrinks.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activtyReference.setDiscoverFragment();
			}
		});

		txtCommunity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activtyReference.setCommunityAndSocialFragment();
			}
		});

		txtMyDrinks.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activtyReference.setMyDrinksFragment();
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		
	}

	@Override
	public void onPause() {
		super.onPause();
		// rippleBackground.stopRippleAnimation();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		activtyReference = (MainActivity) activity;

	}

	@Override
	public void setTitleBar(TitleBar titleBar) {
		titleBar.showTitleBar();
		titleBar.setHeaderColor(this.getResources().getColor(R.color.orange));
		titleBar.setLeftButtonIcon(R.drawable.menu_icon_white);
		titleBar.setOnLeftClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				activtyReference.openMenu();
			}
		});
		titleBar.setHeadingText("Welcome");
		titleBar.hideRightButton();
		titleBar.hideAddButton();

	}

}
