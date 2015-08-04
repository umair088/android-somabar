package com.pyntail.somabar.entities.request;

import com.google.gson.annotations.SerializedName;

public class SignUpEntity {

	@SerializedName("Email")
	private String email;

	@SerializedName("UserName")
	private String userName;

	@SerializedName("Password")
	private String password;

	@SerializedName("ImageUrl")
	private String imageUrl;

	@SerializedName("FullName")
	private String fullName;

	@SerializedName("DateOfBirth")
	private String dateOfBirth;

	public void setEmail(String email) {
		this.email = email;

	}

	public String getEmail() {
		return this.email;

	}

	public void setUserName(String userName) {
		this.userName = userName;

	}

	public String getUserName() {
		return this.userName;

	}

	public void setImageUrl(String imgUrl) {
		this.imageUrl = imgUrl;

	}

	public String getImageUrl() {
		return this.imageUrl;

	}

	public void setFullName(String fullName) {
		this.fullName = fullName;

	}

	public String getFullName() {
		return this.fullName;

	}

	public void setPassword(String password) {
		this.password = password;

	}

	public String getPassword() {
		return this.password;

	}

	public void setDob(String dob) {
		this.dateOfBirth = dob;

	}

	public String getDob() {
		return this.dateOfBirth;

	}

}
