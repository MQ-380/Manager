package com.model;

import java.sql.Time;
import java.util.Date;

/**
 * Sign entity. @author MyEclipse Persistence Tools
 */

public class Sign implements java.io.Serializable {

	// Fields

	private Integer id;
	private String staid;
	private Time signin;
	private Time signout;
	private Date date;

	// Constructors

	/** default constructor */
	public Sign() {
	}

	/** full constructor */
	public Sign(String staid, Time signin, Time signout, Date date) {
		this.staid = staid;
		this.signin = signin;
		this.signout = signout;
		this.date = date;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStaid() {
		return this.staid;
	}

	public void setStaid(String staid) {
		this.staid = staid;
	}

	public Time getSignin() {
		return this.signin;
	}

	public void setSignin(Time signin) {
		this.signin = signin;
	}

	public Time getSignout() {
		return this.signout;
	}

	public void setSignout(Time signout) {
		this.signout = signout;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}