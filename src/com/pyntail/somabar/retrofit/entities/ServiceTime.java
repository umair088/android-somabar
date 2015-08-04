package com.pyntail.somabar.retrofit.entities;

import java.util.Date;

/**
 * Only consider the time portion of any instance of this class. The date
 * portion is uninitialized.
 */
public class ServiceTime {

	Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
