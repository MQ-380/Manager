package com.mydao;

import java.util.Date;
import java.util.List;

import com.model.Fundamentalsalary;
import com.model.Rewardandpunish;
import com.model.Salary;

public interface mySalaryDAO {
	public List<Salary> consultSalary(String id,Date st,Date et);
	public List<Rewardandpunish> consultRePunSalary(String id,Date st,Date et);
}
