package com.cobble.takeaway.service.ecommerce.impl;

import com.cobble.takeaway.dao.ecommerce.EcCartMapper;
import com.cobble.takeaway.pojo.ecommerce.EcCartPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcCartSearchPOJO;
import com.cobble.takeaway.service.ecommerce.EcCartService;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcCartServiceImpl implements EcCartService {
	private static final Logger logger = LoggerFactory.getLogger(EcCartServiceImpl.class);
	
	@Autowired
	private EcCartMapper ecCartMapper;

	@Override
	public int insert(EcCartPOJO ecCartPOJO) throws Exception {
		int ret = 0;
		ret = ecCartMapper.insert(ecCartPOJO);
		return ret;
	}

	@Override
	public int update(EcCartPOJO ecCartPOJO) throws Exception {
		int ret = 0;
		ret = ecCartMapper.update(ecCartPOJO);
		return ret;
	}

	@Override
	public List<EcCartPOJO> finds(
			EcCartSearchPOJO ecCartSearchPOJO) throws Exception {
		List<EcCartPOJO> ret = null;
		ret = ecCartMapper.finds(ecCartSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(EcCartSearchPOJO ecCartSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = ecCartMapper.getCount(ecCartSearchPOJO);
		return ret;
	}

	@Override
	public EcCartPOJO findById(Long id) throws Exception {
		EcCartPOJO ret = null;
		ret = ecCartMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = ecCartMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += ecCartMapper.deleteById(id);
			}
		}
		return ret;
	}

}
