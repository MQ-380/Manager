package com.service;

import java.util.List;

import com.model.Staff;

public interface StaffService {
	public List findByExample(Staff staff);
	public void save(Staff staff);
	public List findAll();
	public List findByStaid(String staid);
	public void deleteStaff(String[] staffID);
}
