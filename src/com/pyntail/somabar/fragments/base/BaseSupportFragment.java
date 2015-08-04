package com.pyntail.somabar.fragments.base;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.DockActivity;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.helpers.BasePreferenceHelper;
import com.pyntail.somabar.helpers.UIHelper;
import com.pyntail.somabar.retrofit.WebService;
import com.pyntail.somabar.retrofit.WebServiceFactory;
import com.pyntail.somabar.ui.views.TitleBar;





import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

public class BaseSupportFragment extends Fragment {
	

	
	protected Handler handler = new Handler();
	public BasePreferenceHelper prefHelper;
	public static WebService webService;
	protected MainActivity activtyReference;
	public DockActivity myDockActivity;
	protected Activity ctx;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createClient();
		if (webService == null)
			webService = WebServiceFactory.getInstance();

		// if (databaseHelper == null)
		// databaseHelper = new DatabaseHelper(getActivity());

		// DO not try to turn this into a singleton

	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ctx = getActivity();
		prefHelper = new BasePreferenceHelper(ctx);
		myDockActivity = getDockActivity();
	}
	

	@Override
	public void onResume() {
		super.onResume();
		setTitleBar(((MainActivity) getDockActivity()).titleBar);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	protected void createClient() {
		// webService = WebServiceFactory.getInstanceWithBasicGsonConversion();

	}

	@Override
	public void onPause() {
		super.onPause();

		if (getDockActivity().getWindow() != null)
			if (getDockActivity().getWindow().getDecorView() != null)
				UIHelper.hideSoftKeyboard(getDockActivity(), getDockActivity()
						.getWindow().getDecorView());

	}

	public void loadingStarted() {

		// if (getParentFragment() != null)
		// ((LoadingListener) getParentFragment()).onLoadingStarted();
		// else
		getDockActivity().onLoadingStarted();

		isLoading = true;
	}

	public void loadingFinished() {

		// if (getParentFragment() != null)
		// ((LoadingListener) getParentFragment()).onLoadingFinished();
		// else
		if (getDockActivity() != null)
			getDockActivity().onLoadingFinished();

		isLoading = false;
		// else
		// ( (LoadingListener) super.getParentFragment() ).onLoadingFinished();
	}

	protected DockActivity getDockActivity() {

		DockActivity activity = (DockActivity) getActivity();
		
		return activity;

	}

	protected MainActivity getMainActivity() {
		return (MainActivity) getActivity();
	}

	protected Activity getBaseActivity() {
		return ctx;
	}

	protected TitleBar getTitleBar() {
		return getMainActivity().titleBar;
	}

	public String getTitleName() {
		return "";
	}

	/**
	 * This is called in the end to modify titlebar. after all changes.
	 * 
	 * @param activity
	 */
	public void setTitleBar(TitleBar titleBar) {
		titleBar.showTitleBar();
		// titleBar.refreshListener();
	}

	/**
	 * Gets the preferred height for each item in the ListView, in pixels, after
	 * accounting for screen density. ImageLoader uses this value to resize
	 * thumbnail images to match the ListView item height.
	 * 
	 * @return The preferred height in pixels, based on the current theme.
	 */
	protected int getListPreferredItemHeight() {
		final TypedValue typedValue = new TypedValue();

		// Resolve list item preferred height theme attribute into typedValue
		getActivity().getTheme().resolveAttribute(
				android.R.attr.listPreferredItemHeight, typedValue, true);

		// Create a new DisplayMetrics object
		final DisplayMetrics metrics = new android.util.DisplayMetrics();

		// Populate the DisplayMetrics
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);

		// Return theme value based on DisplayMetrics
		return (int) typedValue.getDimension(metrics);
	}

	protected void notImplemented() {
		UIHelper.showLongToastInCenter(getActivity(), "Coming Soon");
	}

	protected void serverNotFound() {
		UIHelper.showLongToastInCenter(getActivity(),
				"Unable to connect to the server, "
						+ "are you connected to the internet?");
	}

	

	/**
	 * Trigger when receives broadcasts from device to check wifi connectivity
	 * using connectivity manager
	 * 
	 * Usage : registerBroadcastReceiver() on resume of activity to receive
	 * notifications where needed and unregisterBroadcastReceiver() when not
	 * needed.
	 * 
	 * @return The connectivity of wifi/mobile carrier connectivity.
	 * 
	 */

	protected BroadcastReceiver mConnectionReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			boolean isWifiConnected = false;
			boolean isMobileConnected = false;

			ConnectivityManager connMgr = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			NetworkInfo networkInfo = connMgr
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

			if (networkInfo != null)
				isWifiConnected = networkInfo.isConnected();

			networkInfo = connMgr
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

			if (networkInfo != null)
				isMobileConnected = networkInfo.isConnected();

			Log.d("NETWORK STATUS", "wifi==" + isWifiConnected + " & mobile=="
					+ isMobileConnected);
		}
	};

	private boolean isLoading;

	protected void finishLoading() {
		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				loadingFinished();
			}
		});
	}

	protected boolean checkLoading() {
		if (isLoading) {
			UIHelper.showLongToastInCenter(getActivity(), R.string.message_wait);
			return false;
		} else {
			return true;
		}

	}

}
