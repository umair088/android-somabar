package com.pyntail.somabar.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.activities.SignInActivity;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.helpers.UIHelper;
import com.pyntail.somabar.ui.views.AnyTextView;
import com.pyntail.somabar.ui.views.TitleBar;

public class MyAccountFragment extends BaseSupportFragment {

	
	private final String TAG = this.getClass().getSimpleName();
	private View parentView;

	@InjectView(R.id.btnUpdate)
	ImageButton btnUpdate;
	
	
	@InjectView(R.id.txtLogOut)
	AnyTextView txtLogOut;
	

	public static MyAccountFragment newInstance() {
		return new MyAccountFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (parentView == null) {
			parentView = inflater.inflate(R.layout.fragment_my_account,
					container, false);
			ButterKnife.inject(this, parentView);
		} else
			((ViewGroup) parentView.getParent()).removeView(parentView);

		return parentView;
	}
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		txtLogOut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(activtyReference, SignInActivity.class));
				activtyReference.finish();
			}
		});
		btnUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				UIHelper.showLongToastInCenter(v.getContext(), "Profile updated");
			}
		});
	}
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		activtyReference = (MainActivity) activity;
	}
	
	@Override
	public void setTitleBar(TitleBar titleBar) {
		
		titleBar.setHeaderColor(this.getResources().getColor(R.color.orange));
		titleBar.setLeftButtonIcon(R.drawable.menu_icon_white);
		titleBar.setOnLeftClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				activtyReference.openMenu();
			}
		});
		titleBar.setHeadingText("My Account");
		titleBar.hideRightButton();
		titleBar.hideAddButton();

	}
}
