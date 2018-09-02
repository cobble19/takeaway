package com.cobble.takeaway.service.ecommerce.impl;

import com.cobble.takeaway.dao.ecommerce.EcWxCardMapper;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardSearchPOJO;
import com.cobble.takeaway.service.ecommerce.EcWxCardService;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcWxCardServiceImpl implements EcWxCardService {
	private static final Logger logger = LoggerFactory.getLogger(EcWxCardServiceImpl.class);
	
	@Autowired
	private EcWxCardMapper ecWxCardMapper;

	@Override
	public int insert(EcWxCardPOJO ecWxCardPOJO) throws Exception {
		int ret = 0;
		ret = ecWxCardMapper.insert(ecWxCardPOJO);
		return ret;
	}

	@Override
	public int update(EcWxCardPOJO ecWxCardPOJO) throws Exception {
		int ret = 0;
		ret = ecWxCardMapper.update(ecWxCardPOJO);
		return ret;
	}

	@Override
	public List<EcWxCardPOJO> finds(
			EcWxCardSearchPOJO ecWxCardSearchPOJO) throws Exception {
		List<EcWxCardPOJO> ret = null;
		ret = ecWxCardMapper.finds(ecWxCardSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(EcWxCardSearchPOJO ecWxCardSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = ecWxCardMapper.getCount(ecWxCardSearchPOJO);
		return ret;
	}

	@Override
	public List<EcWxCardPOJO> findWithEcOrders(
			EcWxCardSearchPOJO ecWxCardSearchPOJO) throws Exception {
		List<EcWxCardPOJO> ret = null;
		ret = ecWxCardMapper.finds(ecWxCardSearchPOJO);
		return ret;
	}

	@Override
	public int getCountWithEcOrder(EcWxCardSearchPOJO ecWxCardSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = ecWxCardMapper.getCount(ecWxCardSearchPOJO);
		return ret;
	}

	@Override
	public EcWxCardPOJO findById(Long id) throws Exception {
		EcWxCardPOJO ret = null;
		ret = ecWxCardMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = ecWxCardMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += ecWxCardMapper.deleteById(id);
			}
		}
		return ret;
	}

}
