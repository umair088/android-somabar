package com.pyntail.somabar.helpers;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.provider.Settings.Secure;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;

public class Utils {

	static String deviceID = "";
	static String osVersion = "";
	static String mobileAppVersion = "";

	public static boolean isNull(String _field) {
		if (_field == null)
			return true;
		else
			return false;

	}

	public static boolean isNull(Object _field) {
		if (_field == null)
			return true;
		else
			return false;

	}
	
	public static String getOrdinal(int i) {
	    String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
	    switch (i % 100) {
	    case 11:
	    case 12:
	    case 13:
	        return i + "th";
	    default:
	        return i + sufixes[i % 10];

	    }
	}
	
	public static boolean isValidated(String field) {
		if (Utils.isEmptyOrNull(field))
			return false;
		else
			return true;

	}
	
	
	public static boolean isValidated(Object field) {
		if (field==null)
			return false;
		else
			return true;

	}

	public static boolean isValidated(Boolean field) {
		if (field == null)
			return false;
		else
			return true;

	}
	
	
	
	public static boolean isEntryExistAtThisIndex(final List list, final int index) {
		return index >= 0 && index < list.size();
	}

	public static Bitmap getBitmapFromURL(String src) {
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String ConvertDate(String newsDate) {

		// exmaple
		// 2014-06-16T05:20:30Z
		try {
			String time = "";
			String todaysDate = "";
			String compareResult;
			String[] separated = null;

			if (newsDate.contains("T"))
				separated = newsDate.split("T");
			else
				separated = newsDate.split(" ");

			if (separated[1] != null)
				time = separated[1].substring(0, 5);

			if (newsDate.contains("T"))
				newsDate = newsDate.replace('T', ' ');

			if (newsDate.contains("Z"))
				newsDate = newsDate.replace('Z', ' ');

			todaysDate = Utils.getTodaysDate();
			compareResult = CompareDates(newsDate.trim(), todaysDate);
			return compareResult.concat(", ").concat(
					convertTimeTo12HrFormat(newsDate));
		} catch (Throwable t) {
			return "";
		}

	}
	
	
	
	
	
	
	
	
	public static String getMonthShortName(int monthNumber) {
		String monthName = "";

		if (monthNumber >= 0 && monthNumber < 12)
			try {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.MONTH, monthNumber);

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM");
				simpleDateFormat.setCalendar(calendar);
				monthName = simpleDateFormat.format(calendar.getTime());
			} catch (Exception e) {
				if (e != null)
					e.printStackTrace();
			}
		return monthName;
	}
	

	public static String convertTimeTo12HrFormat(String date)
			throws ParseException {

		DateFormat f1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = f1.parse(date);
		DateFormat f2 = new SimpleDateFormat("h:mma");

		return f2.format(d).toLowerCase();

	}

	@SuppressWarnings("deprecation")
	public static String CompareDates(String dateToCompare, String todaysDate) {

		try {

			String status = "";
			SimpleDateFormat aad = new SimpleDateFormat("yyyy-MM-dd");

			Date date1 = aad.parse(todaysDate);
			Date date2 = aad.parse(dateToCompare);

			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(date1);
			cal2.setTime(date2);

			if (cal1.after(cal2)) {

				status = "Yesterday";
			}

			if (cal1.before(cal2)) {

				status = String.valueOf(cal2.get(Calendar.YEAR)) + "-"
						+ date2.getMonth() + "-" + date2.getDate();
			}

			if (cal1.equals(cal2)) {

				status = "Today";
			}

			return status;

		} catch (ParseException ex) {
			ex.printStackTrace();
			return "";
		}
	}

	public static String getApplicationName(Context context) {
		int stringId = context.getApplicationInfo().labelRes;
		return context.getString(stringId);
	}

	public static void unbindDrawables(View view) {
		try {
			
			if (view.getBackground() != null) {

				if (view.getBackground() instanceof android.graphics.drawable.BitmapDrawable) {

					/*
					 * if (((android.graphics.drawable.BitmapDrawable) view
					 * .getBackground()).getBitmap() != null &&
					 * !((android.graphics.drawable.BitmapDrawable) view
					 * .getBackground()).getBitmap().isRecycled()) {
					 * ((android.graphics.drawable.BitmapDrawable) view
					 * .getBackground()).getBitmap().recycle();
					 * 
					 * view.getBackground().setCallback(null); view = null; }
					 */

					// view = null;
				}

			}

			if (view instanceof android.view.ViewGroup) {
				for (int i = 0; i < ((android.view.ViewGroup) view)
						.getChildCount(); i++) {
					unbindDrawables(((android.view.ViewGroup) view)
							.getChildAt(i));
				}
				if (view instanceof ListView)
					((android.view.ViewGroup) view).removeViewInLayout(view);
				else
					((android.view.ViewGroup) view).removeAllViews();
				System.gc();
			}

		} catch (OutOfMemoryError e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();

		}
	}

	public static String CapitalizeFirstCharacter(String string) {

		return string.substring(0, 1).toUpperCase() + string.substring(1);

	}

	/*
	 * public static void DisableBroadCast(Context context) {
	 * 
	 * NetworkAvailability.CheckBroadcastEnableDisable(
	 * context.getApplicationContext(), "DisablingBroadcast");
	 * 
	 * }
	 * 
	 * public static void EnableBroadCast(Context context) {
	 * 
	 * NetworkAvailability.CheckBroadcastEnableDisable(
	 * context.getApplicationContext(), "EnablingBroadcast");
	 * NetworkAvailability.IsNetworkAvailable(context);
	 * NetworkAvailability.IsGpsEnabled(context);
	 * 
	 * }
	 */

	public static boolean isEmptyOrNull(String string) {
		if (string == null)
			return true;

		return (string.trim().length() <= 0);
	}

	
	
	
	public static boolean isResultEmpty(String string) {
		if (string == null)
			return true;

		if (string.trim().length() == 2)
			return true;

		return (string.trim().length() <= 0);
	}

	public static String trimDate(String date) {

		if (Utils.isEmptyOrNull(date))
			return "";

		String[] seperatedDateAndTime = date.split("\\s+");

		if (!Utils.isEmptyOrNull(seperatedDateAndTime[1]))
			;
		return seperatedDateAndTime[1].substring(0, 5);

	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmptyOrNull(ArrayList arrayList) {
		if (arrayList == null)
			return true;

		return (arrayList.size() <= 0);
	}

	

	public static void hideSoftKeyboard(Activity activity) {
		try {
			InputMethodManager inputManager = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManager.hideSoftInputFromWindow(activity.getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	

	public static String getTodaysDate() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date));
		return sdf.format(date);
	}

	@SuppressWarnings("deprecation")
	public static String getDifferentFormattedDate(String DateToFormat) {
		String FormattedDate;

		try {
			SimpleDateFormat fmt = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			String[] separated = DateToFormat.split("-");
			String time[] = separated[2].substring(
					separated[2].indexOf(' ') + 1).split(":");
			separated[2] = separated[2].substring(0, separated[2].indexOf(' '));

			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(separated[0]),
					Integer.parseInt(separated[1]),
					Integer.parseInt(separated[2]), Integer.parseInt(time[0]),
					Integer.parseInt(time[1]), Integer.parseInt(time[2]));

			int weekday = calendar.get(Calendar.DAY_OF_WEEK);

			String day = getDay(weekday);

			String dateString = fmt.format(calendar.getTime());

			FormattedDate = day + " " + dateString;

		} catch (Exception ex) {
			ex.printStackTrace();
			FormattedDate = DateToFormat;
		}

		return FormattedDate;
	}

	public static Boolean isModerateApiLevel() {

		return (Build.VERSION.SDK_INT >= 11) ? true : false;

	}

	public static Boolean isLowApiLevel() {

		return (Build.VERSION.SDK_INT < 11) ? true : false;

	}

	/**
	 * Disable hardware acceleration (releases memory)
	 */
	/*
	 * public void decelerate() { setLayerToSW(this); }
	 */

	@SuppressWarnings("unused")
	// from view
	/*
	 * public void decelerate() { setLayerToSW(this); }
	 */
	@SuppressLint("InlinedApi")
	private static void setLayerToSW(View v) {
		if (!v.isInEditMode() && isModerateApiLevel()) {
			v.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
	}

	// here private PieView mPieView;
	/**
	 * Enable hardware acceleration (consumes memory)
	 */
	// The user is interacting with the pie, so we want to turn on acceleration
	// so that the interaction is smooth.
	// mPieView.accelerate();

	// From View //userinteracting with view
	// when editing end or Lost focus then call
	// mPieView.decelerate();

	@SuppressLint("InlinedApi")
	/*
	 * public void accelerate() { setLayerToHW(this); }
	 */
	@SuppressWarnings("unused")
	private static void setLayerToHW(View v) {
		if (!v.isInEditMode() && isModerateApiLevel()) {
			v.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		}
	}

	public static String getEnhancedFormattedDate(long date) {
		String dayOfTheWeek = (String) android.text.format.DateFormat.format(
				"EEEE", date);// Thursday
		String stringMonth = (String) android.text.format.DateFormat.format(
				"MMM", date); // Jun
		String intMonth = (String) android.text.format.DateFormat.format("MM",
				date); // 06
		String year = (String) android.text.format.DateFormat.format("yyyy",
				date); // 2013
		String day = (String) android.text.format.DateFormat.format("dd", date); // 20

		return day;

	}

	public static String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month - 1];
	}

	public static String getFormatedTimeString() {

		Calendar c = Calendar.getInstance();
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		String Day = getDay(dayOfWeek);

		SimpleDateFormat formatter = new SimpleDateFormat(
				"dd MMM, yyyy hh:mm aa");
		String dateString = Day + ", "
				+ formatter.format(new Date(System.currentTimeMillis()));

		return dateString;
		// TODO Auto-generated method stub

	}

	public static String getGorgianDay(int year, int month, int day) {

		TimeZone MyTimezone = TimeZone.getDefault();
		Calendar calendar = new GregorianCalendar(MyTimezone);
		calendar.set(year, month, day, 0, 0, 0);

		// String month_name=calendar.getDisplayName(Calendar.MONTH,
		// Calendar.SHORT, Locale.getDefault());//Locale.US);
		return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT,
				Locale.getDefault());// Locale.US);

	}
	
	/*public static String getGorgianMonth(int year, int month, int day) {

		TimeZone MyTimezone = TimeZone.getDefault();
		Calendar calendar = new GregorianCalendar(MyTimezone);
		calendar.set(year, month, day, 0, 0, 0);

		 String month_name=calendar.getDisplayName(Calendar.MONTH,
		 Calendar.SHORT, Locale.getDefault());//Locale.US);
		return month_name;

	}*/

	public static String getGorgianMonth(int year, int month, int day) {

		TimeZone MyTimezone = TimeZone.getDefault();
		Calendar calendar = new GregorianCalendar(MyTimezone);
		calendar.set(year, month, day, 0, 0, 0);

		// String month_name=calendar.getDisplayName(Calendar.MONTH,
		// Calendar.SHORT, Locale.getDefault());//Locale.US);
		return calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT,
				Locale.getDefault());// Locale.US);

	}

	public static String getDay(int dayOfWeek) {
		// TODO Auto-generated method stub
		String weekDay = "";

		// Calendar c = Calendar.getInstance();
		// int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

		switch (dayOfWeek) {
		case Calendar.MONDAY:
			weekDay = "Mon";
			break;
		case Calendar.TUESDAY:
			weekDay = "Tue";
			break;
		case Calendar.WEDNESDAY:
			weekDay = "Wed";
			break;
		case Calendar.THURSDAY:
			weekDay = "Thur";
			break;
		case Calendar.FRIDAY:
			weekDay = "Fri";
			break;
		case Calendar.SATURDAY:
			weekDay = "Sat";
			break;
		case Calendar.SUNDAY:
			weekDay = "Sun";
			break;
		}

		return weekDay;
	}

	public static String getAddressString(Double lat, Double lng,
			Context context) {
		// TODO Auto-generated method stub

		String Address = "";

		Geocoder geocoder;
		List<Address> addresses = new ArrayList<Address>();
		geocoder = new Geocoder(context);
		try {
			addresses = geocoder.getFromLocation(lat, lng, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String address = addresses.get(0).getAddressLine(0);
		String city = addresses.get(0).getAddressLine(1);
		String country = addresses.get(0).getAddressLine(2);

		Address = address + " " + city + ", " + country;

		return Address;
	}

	public static String validateEmptyString(String string) {
		return Utils.validateEmptyString(string, "");
	}

	public static String validateEmptyString(String string, String defaultValue) {
		if (Utils.isEmptyOrNull(string))
			return defaultValue;

		return string;
	}

	public static String getDeviceID(Context context) {
		if (isEmptyOrNull(deviceID)) {
			deviceID = Secure.getString(context.getContentResolver(),
					Secure.ANDROID_ID);
		}
		return deviceID;
	}

	public static String getOSVersion() {
		if (isEmptyOrNull(osVersion)) {
			osVersion = "Android " + Build.VERSION.RELEASE;
		}
		return osVersion;
	}

	public static String getMobileAppVersion(Context context) {
		if (isEmptyOrNull(mobileAppVersion)) {
			try {
				PackageInfo pInfo = context.getPackageManager().getPackageInfo(
						context.getPackageName(), 0);
				mobileAppVersion = pInfo.versionName;
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
		}
		return mobileAppVersion;
	}

	public static int getPixelsFromDps(int dp, Context context) {
		Resources r = context.getResources();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				r.getDisplayMetrics());
	}

	public static String getFormattedDate(int secondsToAdd, String dateFormat) {
		long time = System.currentTimeMillis();
		time = time + (secondsToAdd * 1000);
		Date date = new Date(time);
		SimpleDateFormat postFormater = new SimpleDateFormat(dateFormat);
		return postFormater.format(date);
	}
}
