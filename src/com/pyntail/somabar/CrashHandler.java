package com.pyntail.somabar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;

import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.helpers.Applog;

/**
 * 
 * This class overrides the app crash mechanism
 * 
 * 
 */
public class CrashHandler implements UncaughtExceptionHandler {

	public static final String TAG = "CrashHandler";

	// CrashHandler case making
	private static CrashHandler INSTANCE = new CrashHandler();

	private Context mContext;

	private Thread.UncaughtExceptionHandler mDefaultHandler;

	private Map<String, String> infos = new HashMap<String, String>();

	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

	/** That only one instance of CrashHandler */
	private CrashHandler() {
	}

	/** Gets CrashHandler examples, singleton pattern */
	public static CrashHandler getInstance() {
		return INSTANCE;
	}

	/**
	 * Making making * initialization
	 * 
	 * @param context
	 */
	public void init(Context context) {
		mContext = context;

		// Acquisition system default UncaughtException processor making
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

		// The CrashHandler settings for the default processor making program
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * Making making * when UncaughtException occurs to the function to process
	 * the
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			// The exception handler if the user does not have the processing
			// allows the system default to deal making
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Applog.Debug(TAG,  " error : " + e);
			}

			// Exit the program, startup program code to restart the following
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(1);
			// Restart Now
			Intent intent = new Intent();
			intent.setClass(mContext, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mContext.startActivity(intent);
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}

	/**
	 * Making making * custom error handling, collect error information, send a
	 * bug report and other operations are completed in this
	 * 
	 * @param ex
	 * @return true: If the exception information; otherwise it returns false
	 */
	private boolean handleException(final Throwable ex) {
		if (ex == null) {
			return false;
		}

		// Handler Crash here
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				Applog.Debug(TAG,  "App Crash Due to : " + saveCrashInfo2File(ex));
				Looper.loop();

			}
		}.start();

		// Collection device parameter information making
		collectDeviceInfo(mContext);
		// Save log documentation
		saveCrashInfo2File(ex);
		return true;
	}

	/**
	 * Making making * collection device parameter information
	 * 
	 * @param ctx
	 */
	public void collectDeviceInfo(Context ctx) {
		try {
			PackageManager pm = ctx.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
					PackageManager.GET_ACTIVITIES);

			if (pi != null) {
				String versionName = pi.versionName == null ? "null"
						: pi.versionName;
				String versionCode = pi.versionCode + "";
				infos.put("versionName", versionName);
				infos.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {

			Applog.Debug(TAG, " an error occured when collect package info  "
					+ e);
			

		}

		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				infos.put(field.getName(), field.get(null).toString());

				Applog.Debug(TAG, field.getName() + " : " + field.get(null));
			} catch (Exception e) {
				Applog.Debug(TAG, field.getName() +" an error occured when collect crash info  " + e);
				

			}
		}
	}

	/**
	 * Making making * save error information to a file *
	 * 
	 * @param ex
	 * @Return making returns the file name, the file is transferred to the
	 *         server
	 */
	private String saveCrashInfo2File(Throwable ex) {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : infos.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key + "=" + value + "\n");
		}

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();

		String result = writer.toString();
		sb.append(result);
		try {
			long timestamp = System.currentTimeMillis();
			String time = formatter.format(new Date());
			String fileName = "crash-" + time + "-" + timestamp + ".log";

			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				String path = "/sdcard/crash/";
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(path + fileName);
				fos.write(sb.toString().getBytes());
				fos.close();
			}

			return fileName;
		} catch (Exception e) {

			Applog.Debug(TAG," an error occured while writing file...  " + e);
			
		}

		return null;
	}
}
