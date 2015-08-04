package com.pyntail.somabar.activities;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.view.WindowInsets;

import com.pyntail.somabar.BaseApplication;
import com.pyntail.somabar.R;
import com.pyntail.somabar.fragments.PostDrinkFragment;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.helpers.BasePreferenceHelper;
import com.pyntail.somabar.interfaces.LoadingListener;
import com.pyntail.somabar.ui.dialog.DialogFactory;

public abstract class DockActivity extends FragmentActivity implements
		LoadingListener {

	private static final String TAG = DockActivity.class.getSimpleName();
	public BasePreferenceHelper prefHelper;
	public static final String KEY_FRAG_FIRST = "firstFrag";

	public abstract int getDockFrameLayoutId();

	public abstract boolean isLoggedIn();

	/**
	 * This is the last added dockable fragment, this fragment will receive the
	 * action id of the menu clicked.
	 */
	private BaseSupportFragment baseSuppFragment;

	public Bundle mSharedNonPersistentDataBundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSharedNonPersistentDataBundle = new Bundle();
		prefHelper = new BasePreferenceHelper(this);
	}

	@Override
	protected void onPause() {
		super.onPause();

	}

	protected void onResume() {
		super.onResume();
		// if ( BuildConfig.DEBUG )
		// ViewServer.get( this ).setFocusedWindow( this );
	}

	/**
	 * Please note animator will not work in support V4 use Anim instead.
	 * 
	 * @param frag
	 */
	public void addDockableSupportFragment(BaseSupportFragment frag, String tag) {

		
		baseSuppFragment = frag;
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		transaction.replace(getDockFrameLayoutId(), frag, tag);

		transaction.addToBackStack( getSupportFragmentManager().getBackStackEntryCount() == 0 ? KEY_FRAG_FIRST	: null).commit();// AllowingStateLoss();


	}

	public void addDockableSupportFragmentWithAnim(BaseSupportFragment frag,
			String tag) {

		baseSuppFragment = frag;
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		transaction.setCustomAnimations(R.anim.push_right_in,R.anim.push_right_out, R.anim.push_left_in,
				R.anim.push_left_out);

		transaction.replace(getDockFrameLayoutId(), frag);
		transaction
		.addToBackStack(
				getSupportFragmentManager().getBackStackEntryCount() == 0 ? KEY_FRAG_FIRST
						: null).commit();// AllowingStateLoss();
		
	}
	
	
	public void addDockableSupportFragmentWithFlipAnim(BaseSupportFragment frag,
			String tag) {

		baseSuppFragment = frag;
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		transaction.setCustomAnimations(R.anim.card_flip_horizontal_right_in,R.anim.card_flip_horizontal_right_out, R.anim.card_flip_horizontal_left_in,
				R.anim.card_flip_horizontal_left_out);

		transaction.replace(getDockFrameLayoutId(), frag);
		transaction
		.addToBackStack(
				getSupportFragmentManager().getBackStackEntryCount() == 0 ? KEY_FRAG_FIRST
						: null).commit();// AllowingStateLoss();
		
	}

	public void addAndShowDialogFragment(DialogFragment dialog) {
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		dialog.show(transaction, "tag");

	}

	public void prepareAndShowDialog(DialogFragment frag, String TAG) {
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		Fragment prev = getFragmentManager().findFragmentByTag(TAG);

		if (prev != null)
			transaction.remove(prev);

		transaction.addToBackStack(null);

		frag.show(transaction, TAG);
	}

	@Override
	public void onBackPressed() {
		

		if (getSupportFragmentManager().getBackStackEntryCount() > 1)
			super.onBackPressed();
		else
			DialogFactory.createQuitDialog(this,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							DockActivity.this.finish();
						}
					}, R.string.message_quit).show();
	
		
	
	}

	public BaseSupportFragment getLastAddedSuppFragment() {
		return baseSuppFragment;
	}

	public void emptyBackStack() {
		popBackStackTillEntry(0);
	}

	/**
	 * 
	 * @param entryIndex
	 *            is the index of fragment to be popped to, for example the
	 *            first fragment will have a index 0;
	 */
	public void popBackStackTillEntry(int entryIndex) {
		if (getSupportFragmentManager() == null)
			return;
		if (getSupportFragmentManager().getBackStackEntryCount() <= entryIndex)
			return;
		BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(
				entryIndex);
		if (entry != null) {
			getSupportFragmentManager().popBackStack(entry.getId(),
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
	}

	public BaseApplication getBaseApplication() {
		return (BaseApplication) getApplication();
	}



	

}
