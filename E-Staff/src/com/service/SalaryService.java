package com.service;
import com.model.Fundamentalsalary;
import com.model.Rewardandpunish;
import com.model.Salary;
import com.model.Staff;

import java.util.Date;
import java.util.List;

public interface SalaryService {
public List<Salary> consultSalary(String id,Date st,Date et);
public List<Rewardandpunish> consultRePunSalary(String id,Date st,Date et);
public List<Fundamentalsalary> findByRank(int rank);
public List<Staff> findByStaid(String id);
}
