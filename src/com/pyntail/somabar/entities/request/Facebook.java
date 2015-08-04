package com.pyntail.somabar.entities.request;

import com.google.gson.annotations.Expose;

public class Facebook {



	@Expose
	private String FbUsername;
	@Expose
	private String FbLink;
	@Expose
	private String Photo;
	@Expose
	private String DateOfBirth;
	@Expose
	private String Gender;
	@Expose
	private String Bio;
	@Expose
	private Device Device;
	@Expose
	private String FbId;
	@Expose
	private String FbEmail;
	@Expose
	private String FbAccessToken;

	/**
	* 
	* @return
	* The FbUsername
	*/
	public String getFbUsername() {
	return FbUsername;
	}

	/**
	* 
	* @param FbUsername
	* The FbUsername
	*/
	public void setFbUsername(String FbUsername) {
	this.FbUsername = FbUsername;
	}

	/**
	* 
	* @return
	* The FbLink
	*/
	public String getFbLink() {
	return FbLink;
	}

	/**
	* 
	* @param FbLink
	* The FbLink
	*/
	public void setFbLink(String FbLink) {
	this.FbLink = FbLink;
	}

	/**
	* 
	* @return
	* The Photo
	*/
	public String getPhoto() {
	return Photo;
	}

	/**
	* 
	* @param Photo
	* The Photo
	*/
	public void setPhoto(String Photo) {
	this.Photo = Photo;
	}

	/**
	* 
	* @return
	* The DateOfBirth
	*/
	public String getDateOfBirth() {
	return DateOfBirth;
	}

	/**
	* 
	* @param DateOfBirth
	* The DateOfBirth
	*/
	public void setDateOfBirth(String DateOfBirth) {
	this.DateOfBirth = DateOfBirth;
	}

	/**
	* 
	* @return
	* The Gender
	*/
	public String getGender() {
	return Gender;
	}

	/**
	* 
	* @param Gender
	* The Gender
	*/
	public void setGender(String Gender) {
	this.Gender = Gender;
	}

	/**
	* 
	* @return
	* The Bio
	*/
	public String getBio() {
	return Bio;
	}

	/**
	* 
	* @param Bio
	* The Bio
	*/
	public void setBio(String Bio) {
	this.Bio = Bio;
	}

	/**
	* 
	* @return
	* The Device
	*/
	public Device getDevice() {
	return Device;
	}

	/**
	* 
	* @param Device
	* The Device
	*/
	public void setDevice(Device Device) {
	this.Device = Device;
	}

	/**
	* 
	* @return
	* The FbId
	*/
	public String getFbId() {
	return FbId;
	}

	/**
	* 
	* @param FbId
	* The FbId
	*/
	public void setFbId(String FbId) {
	this.FbId = FbId;
	}

	/**
	* 
	* @return
	* The FbEmail
	*/
	public String getFbEmail() {
	return FbEmail;
	}

	/**
	* 
	* @param FbEmail
	* The FbEmail
	*/
	public void setFbEmail(String FbEmail) {
	this.FbEmail = FbEmail;
	}

	/**
	* 
	* @return
	* The FbAccessToken
	*/
	public String getFbAccessToken() {
	return FbAccessToken;
	}

	/**
	* 
	* @param FbAccessToken
	* The FbAccessToken
	*/
	public void setFbAccessToken(String FbAccessToken) {
	this.FbAccessToken = FbAccessToken;
	}

	
}
