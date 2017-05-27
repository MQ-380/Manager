package com.model;

import java.util.Date;

/**
 * Salary entity. @author MyEclipse Persistence Tools
 */

public class Salary implements java.io.Serializable {

	// Fields

	private Integer id;
	private String staid;
	private Double total;
	private Date date;
	private Double punish;
	private Double reward;

	// Constructors

	/** default constructor */
	public Salary() {
	}

	/** full constructor */
	public Salary(String staid, Double total, Date date, Double punish,
			Double reward) {
		this.staid = staid;
		this.total = total;
		this.date = date;
		this.punish = punish;
		this.reward = reward;
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

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPunish() {
		return this.punish;
	}

	public void setPunish(Double punish) {
		this.punish = punish;
	}

	public Double getReward() {
		return this.reward;
	}

	public void setReward(Double reward) {
		this.reward = reward;
	}

}