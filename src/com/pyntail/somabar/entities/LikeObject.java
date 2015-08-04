package com.pyntail.somabar.entities;

import com.google.gson.annotations.Expose;

public class LikeObject {

	
	@Expose
	private int Count;

	/**
	* 
	* @return
	* The Count
	*/
	public int getCount() {
	return Count;
	}

	/**
	* 
	* @param Count
	* The Count
	*/
	public void setCount(int Count) {
	this.Count = Count;
	}
}
