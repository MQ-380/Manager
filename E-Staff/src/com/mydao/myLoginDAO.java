package com.mydao;

import java.util.Date;
import java.util.List;

import com.model.Applyleave;
import com.model.Sign;

public interface myLoginDAO {
	public List<Sign> consultLogData(String id,Date st,Date et);
}
