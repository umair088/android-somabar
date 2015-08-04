package com.pyntail.somabar.entities.request;

import com.google.gson.annotations.Expose;

public class User {
	@Expose
	private String Name;
	@Expose
	private String ImageUrl;
	@Expose
	private int UserId;

	/**
	* 
	* @return
	* The Name
	*/
	public String getName() {
	return Name;
	}

	/**
	* 
	* @param Name
	* The Name
	*/
	public void setName(String Name) {
	this.Name = Name;
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
	* The UserId
	*/
	public int getUserId() {
	return UserId;
	}

	/**
	* 
	* @param UserId
	* The UserId
	*/
	public void setUserId(int UserId) {
	this.UserId = UserId;
	}

	
}
