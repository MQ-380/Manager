package com.service.impl;

import com.model.Rewardandpunish;
import com.model.RewardandpunishDAO;
import com.service.RePunService;

public class RePunServiceImpl implements RePunService {
private RewardandpunishDAO rewardandpunishDAO ;

public RewardandpunishDAO getRewardandpunishDAO() {
	return rewardandpunishDAO;
}

public void setRewardandpunishDAO(RewardandpunishDAO rewardandpunishDAO) {
	this.rewardandpunishDAO = rewardandpunishDAO;
}
public void save(Rewardandpunish repun)
{
	rewardandpunishDAO.save(repun);
	}
}
