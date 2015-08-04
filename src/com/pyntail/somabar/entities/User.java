package com.pyntail.somabar.entities;

import com.google.gson.annotations.Expose;

public class User {

	@Expose
	private int UserId;
	@Expose
	private String FullName;
	@Expose
	private String Username;
	@Expose
	private String Email;
	@Expose
	private String ImageUrl;
	@Expose
	private String Bio;
	@Expose
	private String Gender;
	@Expose
	private String DateOfBirth;
	@Expose
	private String SecurityToken;
	@Expose
	private String ExpiresOn;
	@Expose
	private Facebook Facebook;

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
	* The SecurityToken
	*/
	public String getSecurityToken() {
	return SecurityToken;
	}

	/**
	* 
	* @param SecurityToken
	* The SecurityToken
	*/
	public void setSecurityToken(String SecurityToken) {
	this.SecurityToken = SecurityToken;
	}

	/**
	* 
	* @return
	* The ExpiresOn
	*/
	public String getExpiresOn() {
	return ExpiresOn;
	}

	/**
	* 
	* @param ExpiresOn
	* The ExpiresOn
	*/
	public void setExpiresOn(String ExpiresOn) {
	this.ExpiresOn = ExpiresOn;
	}

	/**
	* 
	* @return
	* The Facebook
	*/
	public Facebook getFacebook() {
	return Facebook;
	}

	/**
	* 
	* @param Facebook
	* The Facebook
	*/
	public void setFacebook(Facebook Facebook) {
	this.Facebook = Facebook;
	}
	
	
	
	private class Facebook {
		@Expose
		private Object FbId;
		@Expose
		private Object FbEmail;
		@Expose
		private Object FbAccessToken;

		/**
		* 
		* @return
		* The FbId
		*/
		public Object getFbId() {
		return FbId;
		}

		/**
		* 
		* @param FbId
		* The FbId
		*/
		public void setFbId(Object FbId) {
		this.FbId = FbId;
		}

		/**
		* 
		* @return
		* The FbEmail
		*/
		public Object getFbEmail() {
		return FbEmail;
		}

		/**
		* 
		* @param FbEmail
		* The FbEmail
		*/
		public void setFbEmail(Object FbEmail) {
		this.FbEmail = FbEmail;
		}

		/**
		* 
		* @return
		* The FbAccessToken
		*/
		public Object getFbAccessToken() {
		return FbAccessToken;
		}

		/**
		* 
		* @param FbAccessToken
		* The FbAccessToken
		*/
		public void setFbAccessToken(Object FbAccessToken) {
		this.FbAccessToken = FbAccessToken;
		}
		
		
	}
	
}
