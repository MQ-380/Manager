package com.service.impl;

import com.model.SalaryDAO;
import com.service.SalaryService;

public class SalaryServiceImpl implements SalaryService{
private SalaryDAO salaryDAO;

public SalaryDAO getSalaryDAO() {
	return salaryDAO;
}

public void setSalaryDAO(SalaryDAO salaryDAO) {
	this.salaryDAO = salaryDAO;
}
}
