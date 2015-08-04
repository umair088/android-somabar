package com.pyntail.somabar.entities.request;

import com.google.gson.annotations.Expose;
import com.pyntail.somabar.helpers.OSHelper;

public class Device {


	@Expose
	private String Udid;
	@Expose
	private String DeviceType;
	@Expose
	private String Nickname;
	@Expose
	private String Platform;
	@Expose
	private String Language;
	@Expose
	private int TimeZoneOffset;
	@Expose
	private String PushToken ="";
	@Expose
	private boolean OptIn;

	
	
	
	
	
	/**
	* 
	* @return
	* The Udid
	*/
	public String getUdid() {
	return Udid;
	}

	/**
	* 
	* @param Udid
	* The Udid
	*/
	public void setUdid(String Udid) {
	this.Udid = Udid;
	}

	/**
	* 
	* @return
	* The DeviceType
	*/
	public String getDeviceType() {
	return DeviceType;
	}

	/**
	* 
	* @param DeviceType
	* The DeviceType
	*/
	public void setDeviceType(String DeviceType) {
	this.DeviceType = DeviceType;
	}

	/**
	* 
	* @return
	* The Nickname
	*/
	public String getNickname() {
	return Nickname;
	}

	/**
	* 
	* @param Nickname
	* The Nickname
	*/
	public void setNickname(String Nickname) {
	this.Nickname = Nickname;
	}

	/**
	* 
	* @return
	* The Platform
	*/
	public String getPlatform() {
	return Platform;
	}

	/**
	* 
	* @param Platform
	* The Platform
	*/
	public void setPlatform(String Platform) {
	this.Platform = Platform;
	}

	/**
	* 
	* @return
	* The Language
	*/
	public String getLanguage() {
	return Language;
	}

	/**
	* 
	* @param Language
	* The Language
	*/
	public void setLanguage(String Language) {
	this.Language = Language;
	}

	/**
	* 
	* @return
	* The TimeZoneOffset
	*/
	public int getTimeZoneOffset() {
	return TimeZoneOffset;
	}

	/**
	* 
	* @param TimeZoneOffset
	* The TimeZoneOffset
	*/
	public void setTimeZoneOffset(int TimeZoneOffset) {
	this.TimeZoneOffset = TimeZoneOffset;
	}

	/**
	* 
	* @return
	* The PushToken
	*/
	public String getPushToken() {
	return PushToken;
	}

	/**
	* 
	* @param PushToken
	* The PushToken
	*/
	public void setPushToken(String PushToken) {
	this.PushToken = PushToken;
	}

	/**
	* 
	* @return
	* The OptIn
	*/
	public boolean isOptIn() {
	return OptIn;
	}

	/**
	* 
	* @param OptIn
	* The OptIn
	*/
	public void setOptIn(boolean OptIn) {
	this.OptIn = OptIn;
	}

	
}
