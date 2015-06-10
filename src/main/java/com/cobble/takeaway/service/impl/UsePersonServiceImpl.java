package com.cobble.takeaway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.UserPersonMapper;
import com.cobble.takeaway.pojo.UserPersonPOJO;
import com.cobble.takeaway.service.UserPersonService;

@Service
public class UsePersonServiceImpl implements UserPersonService {
	
	@Autowired
	private UserPersonMapper userPersonMapper;

	@Override
	public int reg(UserPersonPOJO userPersonPOJO) throws Exception {
		int ret = 0;
		ret = userPersonMapper.reg(userPersonPOJO);
		return ret;
	}

}
