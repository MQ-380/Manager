package com.model;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private Integer id;
	private String adid;
	private String adpwd;
	private Integer adpro;
	private Integer denum;

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** full constructor */
	public Admin(String adid, String adpwd, Integer adpro, Integer denum) {
		this.adid = adid;
		this.adpwd = adpwd;
		this.adpro = adpro;
		this.denum = denum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdid() {
		return this.adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}

	public String getAdpwd() {
		return this.adpwd;
	}

	public void setAdpwd(String adpwd) {
		this.adpwd = adpwd;
	}

	public Integer getAdpro() {
		return this.adpro;
	}

	public void setAdpro(Integer adpro) {
		this.adpro = adpro;
	}

	public Integer getDenum() {
		return this.denum;
	}

	public void setDenum(Integer denum) {
		this.denum = denum;
	}

}