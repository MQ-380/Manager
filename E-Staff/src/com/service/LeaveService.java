package com.service;

import java.sql.Timestamp;
import java.util.List;

import com.model.Applyleave;
public interface LeaveService {
public void save(Applyleave leave);
public void delete(Applyleave leave);
public List<Applyleave> findByLeaveid(String leaveid);
public List<Applyleave>consultPersonalAllApplyByST(Timestamp st,Timestamp et,String staid);
public List<Applyleave>consultPersonalAllApplyByET(Timestamp st,Timestamp et,String staid);
public List<Applyleave> consultAllApply(String id,String deid,int rank);
}
