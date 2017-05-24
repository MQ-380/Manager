package com.model;

/**
 * Fundamentalsalary entity. @author MyEclipse Persistence Tools
 */

public class Fundamentalsalary implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer rank;
	private Double amount;

	// Constructors

	/** default constructor */
	public Fundamentalsalary() {
	}

	/** full constructor */
	public Fundamentalsalary(Integer rank, Double amount) {
		this.rank = rank;
		this.amount = amount;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}