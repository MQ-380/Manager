package com.service;

import java.util.List;

import com.model.*;

public interface DepartmentService {
	public void save(Department dep);
	public List findByDeid(String deid);
}
