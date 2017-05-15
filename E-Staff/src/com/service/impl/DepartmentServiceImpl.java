package com.service.impl;

import java.util.List;

import com.model.*;
import com.service.*;

public class DepartmentServiceImpl implements DepartmentService{
	private DepartmentDAO departmentDAO;
	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
	public void save(Department dep)
	{
		
		departmentDAO.save(dep);
	}
	public List findByDeid(String deid)
	{
		return departmentDAO.findByDeid(deid);
	}
}
