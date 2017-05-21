package com.service.impl;

import java.util.Date;
import java.util.List;

import com.model.Sign;
import com.model.SignDAO;
import com.service.SignService;
import com.mydao.*;
public class SignServiceImpl implements SignService{
	private SignDAO signDAO;
	private mySignDAO mySignDAO;
	public mySignDAO getMySignDAO() {
		return mySignDAO;
	}
	public void setMySignDAO(mySignDAO mySignDAO) {
		this.mySignDAO = mySignDAO;
	}
	public List findByExample(Sign sign)
	{
		return signDAO.findByExample(sign);
	}
	public void save(Sign sign)
	{
		signDAO.save(sign);
	}
	public List<Sign> consultLogData(Date st,Date et)
	{
		return mySignDAO.consultLogData(st, et);
	}
	public SignDAO getSignDAO() {
		return signDAO;
	}

	public void setSignDAO(SignDAO signDAO) {
		this.signDAO = signDAO;
	}
}
