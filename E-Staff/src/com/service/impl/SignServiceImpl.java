package com.service.impl;

import java.util.Date;
import java.util.List;

import com.model.Sign;
import com.model.SignDAO;
import com.service.SignService;
import com.mydao.*;
public class SignServiceImpl implements SignService{
	private SignDAO signDAO;
	private myLoginDAO myLoginDAO;
	public myLoginDAO getMyLoginDAO() {
		return myLoginDAO;
	}
	public void setMyLoginDAO(myLoginDAO myLoginDAO) {
		this.myLoginDAO = myLoginDAO;
	}
	public List findByExample(Sign sign)
	{
		return signDAO.findByExample(sign);
	}
	public void save(Sign sign)
	{
		signDAO.save(sign);
	}
	public List<Sign> consultLogData(String id,Date st,Date et)
	{
		return myLoginDAO.consultLogData(id,st, et);
	}
	public SignDAO getSignDAO() {
		return signDAO;
	}

	public void setSignDAO(SignDAO signDAO) {
		this.signDAO = signDAO;
	}
}
