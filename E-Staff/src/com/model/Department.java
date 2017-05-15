package com.model;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */

public class Department implements java.io.Serializable {

	// Fields

	private Integer id;
	private String deid;
	private String name;
	private Integer stanum;

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** full constructor */
	public Department(String deid, String name, Integer stanum) {
		this.deid = deid;
		this.name = name;
		this.stanum = stanum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeid() {
		return this.deid;
	}

	public void setDeid(String deid) {
		this.deid = deid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStanum() {
		return this.stanum;
	}

	public void setStanum(Integer stanum) {
		this.stanum = stanum;
	}

}