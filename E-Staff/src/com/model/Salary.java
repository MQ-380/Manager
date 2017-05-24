package com.model;

/**
 * Salary entity. @author MyEclipse Persistence Tools
 */

public class Salary implements java.io.Serializable {

	// Fields

	private Integer id;
	private String staid;
	private Double total;
	private Integer year;
	private Integer month;
	private Double punish;
	private Double reward;

	// Constructors

	/** default constructor */
	public Salary() {
	}

	/** full constructor */
	public Salary(String staid, Double total, Integer year, Integer month,
			Double punish, Double reward) {
		this.staid = staid;
		this.total = total;
		this.year = year;
		this.month = month;
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

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
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