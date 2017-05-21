package com.service;

import java.util.Date;
import java.util.List;

import com.model.Sign;

public interface SignService {
	 public List findByExample(Sign sign);
	 public void save(Sign sign);
	 public List<Sign> consultLogData(Date st,Date et);
}
