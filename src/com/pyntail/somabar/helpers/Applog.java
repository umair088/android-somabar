package com.pyntail.somabar.helpers;

import android.util.Log;

import com.pyntail.somabar.constants.AppConstants;

public class Applog {
	
	

	public static void Debug(final String tag, final String msg){
		
		if(Utils.isEmptyOrNull(msg))
		return;
		
		if(AppConstants.DEBUG)
		Log.d(tag, msg);
	}

	public static void Error(final String tag, final String msg) {
		
		if(Utils.isEmptyOrNull(msg))
			return;
		
		if(AppConstants.DEBUG)
		Log.e(tag, msg);
	} 
	
	


}
