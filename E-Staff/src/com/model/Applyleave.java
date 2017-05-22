package com.model;

import java.sql.Timestamp;

/**
 * Applyleave entity. @author MyEclipse Persistence Tools
 */

public class Applyleave implements java.io.Serializable {

	// Fields

	private Integer id;
	private String leaveid;
	private String staid;
	private Integer type;
	private Timestamp stime;
	private Timestamp etime;
	private String description;
	private Integer isconfirm;
	private String operator;

	// Constructors

	/** default constructor */
	public Applyleave() {
	}

	/** full constructor */
	public Applyleave(String leaveid, String staid, Integer type,
			Timestamp stime, Timestamp etime, String description,
			Integer isconfirm, String operator) {
		this.leaveid = leaveid;
		this.staid = staid;
		this.type = type;
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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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