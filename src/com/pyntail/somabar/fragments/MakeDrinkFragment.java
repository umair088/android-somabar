package com.pyntail.somabar.fragments;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.ui.views.TitleBar;

public class MakeDrinkFragment extends BaseSupportFragment {

	
	private final String TAG = this.getClass().getSimpleName();
	public static MakeDrinkFragment newInstance() {
		return new MakeDrinkFragment();
	}

	@Override
	public void setTitleBar(TitleBar titleBar) {
		titleBar.setHeaderColor(this.getResources().getColor(
				R.color.milky_white));
		titleBar.setLeftButtonIcon(R.drawable.menu_icon_orange);

		titleBar.setOnLeftClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				activtyReference.openMenu();
			}
		});
		titleBar.setHeadingText("Make Drink");
		titleBar.hideRightButton();
		titleBar.hideAddButton();

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		activtyReference = (MainActivity) activity;
	}
}
