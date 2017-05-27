package com.model;

import java.util.Date;

/**
 * Rewardandpunish entity. @author MyEclipse Persistence Tools
 */

public class Rewardandpunish implements java.io.Serializable {

	// Fields

	private Integer id;
	private String staid;
	private Integer type;
	private Double amount;
	private Date date;
	private String description;
	private String operator;

	// Constructors

	/** default constructor */
	public Rewardandpunish() {
	}

	/** full constructor */
	public Rewardandpunish(String staid, Integer type, Double amount,
			Date date, String description, String operator) {
		this.staid = staid;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.operator = operator;
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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}