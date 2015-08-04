package com.pyntail.somabar;

import com.pyntail.somabar.constants.AppConstants;

import android.app.Application;

public class BaseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		
		/* App Crash Handling*/
		if (!AppConstants.DEBUG) {
			CrashHandler crashHandler = CrashHandler.getInstance();
			crashHandler.init(getApplicationContext());
		}
	}

	

}
