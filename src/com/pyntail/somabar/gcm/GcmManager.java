package com.pyntail.somabar.gcm;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.pyntail.somabar.activities.MainActivity;


public class GcmManager {

	public static final String EXTRA_MESSAGE = "message";
	public static final String PROPERTY_REG_ID = "registration_id";
	private static final String PROPERTY_APP_VERSION = "appVersion";
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	GoogleCloudMessaging gcm;
	AtomicInteger msgId = new AtomicInteger();

	private String regid;

	private Context context;

	public interface GcmListener {
		void onRegistrationComplete(String registrationId);
	}

	protected GcmListener listener;

	public GcmManager(Context context) {
		this.context = context;
		if (context != null)
			initGCM();
	}

	private void initGCM() {

		if (checkPlayServices()) {
			registerInBackground();
		} else {
		}
	}

	private void registerInBackground() {
		try {
			new AsyncTask<Object, Object, Object>() {
				@Override
				protected void onPostExecute(Object result) {
					super.onPostExecute(result);
				}

				@Override
				protected Object doInBackground(Object... params) {
					String msg = "";
					try {

						if (gcm == null) {
							gcm = GoogleCloudMessaging.getInstance(context);
						}

						regid = gcm.register(GCMConstants.SENDER_ID);

						msg = "Device registered, registration ID=" + regid;

						sendRegistrationIdToBackend(regid);

						// Persist the regID - no need to register again.
						storeRegistrationId(context, regid);

					} catch (IOException ex) {
						msg = "Error :" + ex.getMessage();
						// registerInBackground();
					}
					return msg;
				}
			}.execute(null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getRegistrationId(Context context) {
		final SharedPreferences prefs = getGCMPreferences(context);
		String registrationId = prefs.getString(PROPERTY_REG_ID, "");
		if (registrationId.isEmpty()) {
			return "";
		}
		// Check if app was updated; if so, it must clear the registration ID
		// since the existing regID is not guaranteed to work with the new
		// app version.
		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION,
				Integer.MIN_VALUE);
		int currentVersion = getAppVersion(context);
		if (registeredVersion != currentVersion) {
			return "";
		}
		return registrationId;
	}

	/**
	 * @return Application's {@code SharedPreferences}.
	 */
	private static SharedPreferences getGCMPreferences(Context context) {
		// This sample app persists the registration ID in shared preferences,
		// but
		// how you store the regID in your app is up to you.
		return context.getSharedPreferences(MainActivity.class.getSimpleName(),
				Context.MODE_PRIVATE);
	}

	/**
	 * @return Application's version code from the {@code PackageManager}.
	 */
	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// should never happen
			throw new RuntimeException("Could not get package name: " + e);
		}
	}

	/**
	 * Stores the registration ID and app versionCode in the application's
	 * {@code SharedPreferences}.
	 * 
	 * @param context
	 *            application's context.
	 * @param regId
	 *            registration ID
	 */
	private void storeRegistrationId(Context context, String regId) {
		final SharedPreferences prefs = getGCMPreferences(context);
		int appVersion = getAppVersion(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(PROPERTY_REG_ID, regId);
		editor.putInt(PROPERTY_APP_VERSION, appVersion);
		editor.commit();
	}

	private boolean checkPlayServices() {

		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(context);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode,
						(Activity) context, PLAY_SERVICES_RESOLUTION_REQUEST)
						.show();
			} else {
			}
			return false;
		}
		return true;
	}

	private void sendRegistrationIdToBackend(String regId) {
		listener.onRegistrationComplete(regId);
	}

	public void setOnGcmListener(GcmListener listener) {
		this.listener = listener;
	}

}
