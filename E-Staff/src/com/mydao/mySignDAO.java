package com.mydao;

import java.util.Date;
import java.util.List;

import com.model.Sign;

public interface mySignDAO {
public List<Sign> consultLogData(Date st,Date et);
}
