package com.model;

import java.util.Date;

/**
 * Punishbysign entity. @author MyEclipse Persistence Tools
 */

public class Punishbysign implements java.io.Serializable {

	// Fields

	private Integer id;
	private String staid;
	private Double amount;
	private Date date;
	private Double hour;
	private String description;

	// Constructors

	/** default constructor */
	public Punishbysign() {
	}

	/** full constructor */
	public Punishbysign(String staid, Double amount, Date date, Double hour,
			String description) {
		this.staid = staid;
		this.amount = amount;
		this.date = date;
		this.hour = hour;
		this.description = description;
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

	public Double getHour() {
		return this.hour;
	}

	public void setHour(Double hour) {
		this.hour = hour;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}