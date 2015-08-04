package com.pyntail.somabar.activities;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;

import com.pyntail.somabar.gcm.GcmManager;
import com.pyntail.somabar.helpers.Applog;
import com.pyntail.somabar.helpers.BasePreferenceHelper;
import com.pyntail.somabar.ui.dialog.DialogFactory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	
	
	/* App Dialog */
	protected SweetAlertDialog appDialog;
	
	/* Pref Helper */
	public BasePreferenceHelper prefHelper;
	
	/* Push Notification */
	private GcmManager gcm = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefHelper = new BasePreferenceHelper(BaseActivity.this);
		initGcm();
	}
	
	
	
	
	@SuppressLint("NewApi") private void initGcm() {
		if (prefHelper.getDeviceToken() == null
				|| prefHelper.getDeviceToken().isEmpty()
				|| prefHelper.getDeviceToken().equalsIgnoreCase("")) {
			gcm = new GcmManager(getApplicationContext());
			gcm.setOnGcmListener(new GcmManager.GcmListener() {

				@Override
				public void onRegistrationComplete(String registrationId) {
					Applog.Debug("registrationId = ", registrationId);
					prefHelper.putDeviceToken(registrationId);
					if (prefHelper.getUser() != null) {
						updateDeviceToken(prefHelper.getUser().getUserId(), "android",
								prefHelper.getDeviceToken());
					}
				}

				
			});
		}
		else {
			
			Applog.Debug("registrationId = ", prefHelper.getDeviceToken());
			if (prefHelper.getUser() != null) {
			updateDeviceToken(prefHelper.getUser().getUserId(), "android",
					prefHelper.getDeviceToken());
			}
		}
	}
	
	
	private void updateDeviceToken(int userId, String string,
			String deviceToken) {
		
	}
	
	protected void showSweetLoader() {

		if (appDialog != null)
			appDialog = null;

		appDialog = DialogFactory.showSweetLoader(BaseActivity.this,
				SweetAlertDialog.PROGRESS_TYPE, "Loading...");

	}
	
	protected void showSweetLoader(final String msgToShow) {

		if (appDialog != null)
			appDialog = null;

		appDialog = DialogFactory.showSweetLoader(BaseActivity.this,
				SweetAlertDialog.PROGRESS_TYPE, msgToShow);

	}
	
	
	public void showSweetWarningAlert(String btnPositive, String btnNegative,
			String title, String msgToShow) {

		if (appDialog != null) {
			appDialog.dismissWithAnimation();
			appDialog = null;
		}
		appDialog = DialogFactory.showSweetAlertDialogTwoChoice(
				BaseActivity.this, SweetAlertDialog.WARNING_TYPE, msgToShow,
				title, btnPositive, btnNegative, new OnSweetClickListener() {

					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						appDialog.dismissWithAnimation();
					}
				}, new OnSweetClickListener() {

					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						appDialog.dismissWithAnimation();
					}
				});
		appDialog.show();
	}

	public void showSweetSuccessAlert(String btnPositive, String btnNegative,
			String title, String msgToShow) {

		if (appDialog != null) {
			appDialog.dismissWithAnimation();
			appDialog = null;
		}

		appDialog = DialogFactory.showSweetAlertDialogTwoChoice(
				BaseActivity.this, SweetAlertDialog.SUCCESS_TYPE, msgToShow,
				title, btnPositive, btnNegative, new OnSweetClickListener() {

					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						appDialog.dismissWithAnimation();
					}
				}, new OnSweetClickListener() {

					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						appDialog.dismissWithAnimation();
					}
				});
		appDialog.show();
	}

	public void showSweetFailureAlert(String btnPositive, String btnNegative,
			String title, String msgToShow) {

		if (appDialog != null) {
			appDialog.dismissWithAnimation();
			appDialog = null;
		}

		appDialog = DialogFactory.showSweetAlertDialogTwoChoice(
				BaseActivity.this, SweetAlertDialog.ERROR_TYPE, msgToShow,
				title, btnPositive, btnNegative, new OnSweetClickListener() {

					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						appDialog.dismissWithAnimation();
					}
				}, new OnSweetClickListener() {

					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						appDialog.dismissWithAnimation();
					}
				});
		appDialog.show();
	}

	public void hideSweetLoader() {

		if (appDialog == null)
			return;

		appDialog.dismissWithAnimation();

	}

}
