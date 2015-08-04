package com.pyntail.somabar.helpers;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pyntail.somabar.entities.DiscoverDrinkResponse;
import com.pyntail.somabar.entities.User;
import com.pyntail.somabar.retrofit.GsonFactory;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class BasePreferenceHelper extends PreferenceHelper {

	protected static final String KEY_LOGIN_STATUS = "islogin";
	protected static final String KEY_USER = "user";
	public static final String KEY_CONVERSATION_RESPONSE = "conversation_cache";
	public static final String KEY_GROUP_CONVERSATION_RESPONSE = "group_conversation_cache";
	protected static final String KEY_STATUS = "status";
	public static final String KEY_DEVICE_TOKEN = "device_token";
	public static final String KEY_SECURITY_TOKEN = "security_token";
	public static final String KEY_FONT_SIZE = "font_size";
	private static final String KEY_ENTER_TO_SEND = "enter_to_send";
	public static final String KEY_Str = "";
	public static final String KEY_sync = "sync_contacts";
	private static final String KEY_MSG_TONE = "message_tone";
	private static final String KEY_GROUP_TONE = "group_tone";
	private static final String KEY_All_NOTIFICATION = "all_notification";
	private static final String KEY_MSG_NOTIFICATION = "msg_notification";
	private static final String KEY_GRP_NOTIFICATION = "grp_notification";

	private static final String KEY_LAST_SEEN = "last_seen";
	private static final String KEY_PROFILE_PHOTO = "my_contacts";
	private Context context;

	private static final String FILENAME = "preferences";
	private static final String KEY_WALLPAPER_URI = "wallpaper_uri";
	private static final String KEY_CUSTOM_VALUE_TIMEBOMB = "custom_value_tb";
	private static final String KEY_DISCOVER_DRINKS = "discover_drinks";
	private static final String KEY_MY_DRINKS = "my_drinks";
	public BasePreferenceHelper(Context c) {
		this.context = c;
	}

	public SharedPreferences getSharedPreferences() {
		return context.getSharedPreferences(FILENAME, Activity.MODE_PRIVATE);
	}

	public User getUser() {
		String stringPreference = getStringPreference(context, FILENAME,
				KEY_USER);
		return new GsonBuilder().create()
				.fromJson(stringPreference, User.class);

	}

	public void putUser(User user) {
		putStringPreference(context, FILENAME, KEY_USER, new GsonBuilder()
				.create().toJson(user));
	}
	
	
	
	
	
	// //////////////// MY DRINKS////////////////////////
		public void putMyDrinks(ArrayList<DiscoverDrinkResponse> stories) {
			putStringPreference(context, FILENAME, KEY_MY_DRINKS,getMyDrinks(stories));
		}

		private String getMyDrinks(ArrayList<DiscoverDrinkResponse> stories) {
			return GsonFactory.getConfiguredGson().toJson(stories);
		}

		public ArrayList<DiscoverDrinkResponse> getSavedMyDrinks() {
			String json = getStringPreference(context, FILENAME,KEY_MY_DRINKS);
			if (Utils.isEmptyOrNull(json))
				return null;
			return getAllMyDrinks(json);
		}

		private ArrayList<DiscoverDrinkResponse> getAllMyDrinks(String json) {
			Type collectionType = new TypeToken<ArrayList<DiscoverDrinkResponse>>() {
			}.getType();
			return GsonFactory.getConfiguredGson().fromJson(json, collectionType);
		}
	
	

	// //////////////// DISCOVER DRINKS////////////////////////
	public void putDiscoverDrinks(ArrayList<DiscoverDrinkResponse> stories) {
		putStringPreference(context, FILENAME, KEY_DISCOVER_DRINKS,getDiscoverDrinks(stories));
	}

	private String getDiscoverDrinks(ArrayList<DiscoverDrinkResponse> stories) {
		return GsonFactory.getConfiguredGson().toJson(stories);
	}

	public ArrayList<DiscoverDrinkResponse> getSavedDiscoverDrinks() {
		String json = getStringPreference(context, FILENAME,KEY_DISCOVER_DRINKS);
		if (Utils.isEmptyOrNull(json))
			return null;
		return getAllDiscoverDrinks(json);
	}

	private ArrayList<DiscoverDrinkResponse> getAllDiscoverDrinks(String json) {
		Type collectionType = new TypeToken<ArrayList<DiscoverDrinkResponse>>() {
		}.getType();
		return GsonFactory.getConfiguredGson().fromJson(json, collectionType);
	}

	public void clear() {
		removePreference(context, FILENAME);
	}

	public void putUser_Field(String value) {
		putStringPreference(context, FILENAME, KEY_USER, value);
	}

	public void removeUser() {
		removePreference(context, FILENAME, KEY_USER);
	}

	public void setLoginStatus(boolean isLogin) {
		putBooleanPreference(context, FILENAME, KEY_LOGIN_STATUS, isLogin);
	}

	public boolean getLoginStatus() {
		return getBooleanPreference(context, FILENAME, KEY_LOGIN_STATUS);
	}

	public void putDeviceToken(String token) {
		putStringPreference(context, FILENAME, KEY_DEVICE_TOKEN, token);
	}
	
	
	public void putEncodedSecurityToken(String token) {
		putStringPreference(context, FILENAME, KEY_SECURITY_TOKEN, token);
	}
	
	public String getEncodedSecurityToken() {
		return getStringPreference(context, FILENAME, KEY_SECURITY_TOKEN);
	}

	

	/**
	 * 1 = small 2 = medium 3 = large
	 * 
	 * @param fontSize
	 */
	public void putFontSize(int fontSize) {
		putIntegerPreference(context, FILENAME, KEY_FONT_SIZE, fontSize);
	}

	/**
	 * 1 = small 2 = medium 3 = large
	 * 
	 * @param fontSize
	 */
	public int getFontSize() {
		return getIntegerPreference(context, FILENAME, KEY_FONT_SIZE);
	}

	public String getDeviceToken() {
		return getStringPreference(context, FILENAME, KEY_DEVICE_TOKEN);
	}

	public void SetSyncContactStatus(boolean value) {
		putBooleanPreference(context, FILENAME, KEY_sync, value);
	}

	public boolean getSyncContactStatus() {
		return getBooleanPreference(context, FILENAME, KEY_sync);
	}

	public void setEnterToSend(boolean isChecked) {
		putBooleanPreference(context, FILENAME, KEY_ENTER_TO_SEND, isChecked);

	}

	public boolean getEnterToSend() {
		return getBooleanPreference(context, FILENAME, KEY_ENTER_TO_SEND);

	}

	public void setWallpaperUri(String wallpaperUri) {
		putStringPreference(context, FILENAME, KEY_WALLPAPER_URI, wallpaperUri);

	}

	public String getWallpaperUri() {
		return getStringPreference(context, FILENAME, KEY_WALLPAPER_URI);

	}

	public void setNotification(boolean value) {
		putBooleanPreference(context, FILENAME, KEY_All_NOTIFICATION, value);

	}

	public boolean getNotification() {
		return getBooleanPreference(context, FILENAME, KEY_All_NOTIFICATION);

	}

	public void setMSGNotification(boolean value) {
		putBooleanPreference(context, FILENAME, KEY_MSG_NOTIFICATION, value);

	}

	public boolean getMSGNotification() {
		return getBooleanPreference(context, FILENAME, KEY_MSG_NOTIFICATION);

	}

	public void setGroupNotification(boolean value) {
		putBooleanPreference(context, FILENAME, KEY_GRP_NOTIFICATION, value);

	}

	public boolean getGroupNotification() {
		return getBooleanPreference(context, FILENAME, KEY_GRP_NOTIFICATION);

	}

	public void setLastSeen(String value) {
		putStringPreference(context, FILENAME, KEY_LAST_SEEN, value);

	}

	public String getLastSeen() {
		return getStringPreference(context, FILENAME, KEY_LAST_SEEN);

	}

	public void setProfilePhoto(String value) {
		putStringPreference(context, FILENAME, KEY_PROFILE_PHOTO, value);

	}

	public String getProfilePhoto() {
		return getStringPreference(context, FILENAME, KEY_PROFILE_PHOTO);

	}

	public void setCustomValueTimebomb(int valueInMinutes) {
		putIntegerPreference(context, FILENAME, KEY_CUSTOM_VALUE_TIMEBOMB,
				valueInMinutes);

	}

	public int getCustomValueTimebomb() {
		return getIntegerPreference(context, FILENAME,
				KEY_CUSTOM_VALUE_TIMEBOMB);

	}

}
