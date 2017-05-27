package com.model;

import java.util.Date;

/**
 * Applyleave entity. @author MyEclipse Persistence Tools
 */

public class Applyleave implements java.io.Serializable {

	// Fields

	private Integer id;
	private String leaveid;
	private String staid;
	private Integer type;
	private Date stime;
	private Date etime;
	private String description;
	private Integer isconfirm;
	private String operator;

	// Constructors

	/** default constructor */
	public Applyleave() {
	}

	/** full constructor */
	public Applyleave(String leaveid, String staid, Integer type, Date stime,
			Date etime, String description, Integer isconfirm, String operator) {
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

	public Date getStime() {
		return this.stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public Date getEtime() {
		return this.etime;
	}

	public void setEtime(Date etime) {
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