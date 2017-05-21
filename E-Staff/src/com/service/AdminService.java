package com.service;

import java.util.List;

import com.model.Admin;

public interface AdminService {
	public List findByExample(Admin admin);
	public List findByAdid(String adid);
	public void save(Admin admin);
}
