package com.pyntail.somabar.entities.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginObject {

	
	@Expose
	private String Email;
	@Expose
	private String Password;
	
	
	@SerializedName("Facebook")
	private Facebook facebook;
	@Expose
	private String FullName;
	@Expose
	private String ImageUrl;
	@Expose
	private String Bio;
	@Expose
	private String Gender;
	@Expose
	private String DateOfBirth;
	@Expose
	private String Username;
	
	@SerializedName("Device")
	private Device device;

	
	 
	
	/**
	* 
	* @return
	* The Email
	*/
	public String getEmail() {
	return Email;
	}

	/**
	* 
	* @param Email
	* The Email
	*/
	public void setEmail(String Email) {
	this.Email = Email;
	}

	/**
	* 
	* @return
	* The Password
	*/
	public String getPassword() {
	return Password;
	}

	/**
	* 
	* @param Password
	* The Password
	*/
	public void setPassword(String Password) {
	this.Password = Password;
	}

	/**
	* 
	* @return
	* The Facebook
	*/
	public Facebook getFacebook() {
	return facebook;
	}

	/**
	* 
	* @param Facebook
	* The Facebook
	*/
	public void setFacebook(Facebook Facebook) {
	this.facebook = Facebook;
	}

	/**
	* 
	* @return
	* The FullName
	*/
	public String getFullName() {
	return FullName;
	}

	/**
	* 
	* @param FullName
	* The FullName
	*/
	public void setFullName(String FullName) {
	this.FullName = FullName;
	}

	/**
	* 
	* @return
	* The ImageUrl
	*/
	public String getImageUrl() {
	return ImageUrl;
	}

	/**
	* 
	* @param ImageUrl
	* The ImageUrl
	*/
	public void setImageUrl(String ImageUrl) {
	this.ImageUrl = ImageUrl;
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
	* The Username
	*/
	public String getUsername() {
	return Username;
	}

	/**
	* 
	* @param Username
	* The Username
	*/
	public void setUsername(String Username) {
	this.Username = Username;
	}

	/**
	* 
	* @return
	* The Device
	*/
	public Device getDevice() {
	return this.device;
	}

	/**
	* 
	* @param Device
	* The Device
	*/
	public void setDevice(Device Device) {
	this.device = Device;
	}

	

	

}
