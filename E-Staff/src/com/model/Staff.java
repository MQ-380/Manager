package com.model;

import java.util.Date;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

public class Staff implements java.io.Serializable {

	// Fields

	private Integer id;
	private String staid;
	private String name;
	private String password;
	private String sex;
	private String email;
	private String phone;
	private Date time;
	private Integer rank;
	private String deid;
	private String training;
	private String skill;
	private Integer state;
	private Integer leavenum;

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** full constructor */
	public Staff(String staid, String name, String password, String sex,
			String email, String phone, Date time, Integer rank, String deid,
			String training, String skill, Integer state, Integer leavenum) {
		this.staid = staid;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.email = email;
		this.phone = phone;
		this.time = time;
		this.rank = rank;
		this.deid = deid;
		this.training = training;
		this.skill = skill;
		this.state = state;
		this.leavenum = leavenum;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getDeid() {
		return this.deid;
	}

	public void setDeid(String deid) {
		this.deid = deid;
	}

	public String getTraining() {
		return this.training;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	public String getSkill() {
		return this.skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getLeavenum() {
		return this.leavenum;
	}

	public void setLeavenum(Integer leavenum) {
		this.leavenum = leavenum;
	}

}