package com.model;

import java.sql.Timestamp;

/**
 * Leave entity. @author MyEclipse Persistence Tools
 */

public class Leave implements java.io.Serializable {

	// Fields

	private Integer id;
	private String leaveid;
	private String staid;
	private Integer tp;
	private Timestamp stime;
	private Timestamp etime;
	private String description;
	private Integer isconfirm;
	private String operator;

	// Constructors

	/** default constructor */
	public Leave() {
	}

	/** full constructor */
	public Leave(String leaveid, String staid, Integer tp, Timestamp stime,
			Timestamp etime, String description, Integer isconfirm,
			String operator) {
		this.leaveid = leaveid;
		this.staid = staid;
		this.tp = tp;
		this.stime = stime;
		this.etime = etime;
		this.description = description;
		this.isconfirm = isconfirm;
		this.operator = operator;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLeaveid() {
		return this.leaveid;
	}

	public void setLeaveid(String leaveid) {
		this.leaveid = leaveid;
	}

	public String getStaid() {
		return this.staid;
	}

	public void setStaid(String staid) {
		this.staid = staid;
	}

	public Integer getTp() {
		return this.tp;
	}

	public void setTp(Integer tp) {
		this.tp = tp;
	}

	public Timestamp getStime() {
		return this.stime;
	}

	public void setStime(Timestamp stime) {
		this.stime = stime;
	}

	public Timestamp getEtime() {
		return this.etime;
	}

	public void setEtime(Timestamp etime) {
		this.etime = etime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsconfirm() {
		return this.isconfirm;
	}

	public void setIsconfirm(Integer isconfirm) {
		this.isconfirm = isconfirm;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}