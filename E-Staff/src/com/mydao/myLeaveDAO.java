package com.mydao;

import java.sql.Timestamp;
import java.util.List;

import com.model.*;
public interface myLeaveDAO {
public List<Applyleave> consultPersonalAllApplyByST(Timestamp st,Timestamp et,String staid);
public List<Applyleave> consultPersonalAllApplyByET(Timestamp st,Timestamp et,String staid);
public List<Applyleave> consultAllApply(String id,String deid,int rank);
}
