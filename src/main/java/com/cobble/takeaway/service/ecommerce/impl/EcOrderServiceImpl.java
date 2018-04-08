package com.cobble.takeaway.service.ecommerce.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.ecommerce.EcOrderMapper;
import com.cobble.takeaway.pojo.ecommerce.EcOrderPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderSearchPOJO;
import com.cobble.takeaway.service.ecommerce.EcOrderService;

@Service
public class EcOrderServiceImpl implements EcOrderService {
	
	@Autowired
	private EcOrderMapper ecOrderMapper;

	@Override
	public int insert(EcOrderPOJO ecOrderPOJO) throws Exception {
		int ret = 0;
		ret = ecOrderMapper.insert(ecOrderPOJO);
		return ret;
	}

	@Override
	public int update(EcOrderPOJO ecOrderPOJO) throws Exception {
		int ret = 0;
		ret = ecOrderMapper.update(ecOrderPOJO);
		return ret;
	}

	@Override
	public List<EcOrderPOJO> finds(
			EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception {
		List<EcOrderPOJO> ret = null;
		ret = ecOrderMapper.finds(ecOrderSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(EcOrderSearchPOJO ecOrderSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = ecOrderMapper.getCount(ecOrderSearchPOJO);
		return ret;
	}

	@Override
	public EcOrderPOJO findById(Long id) throws Exception {
		EcOrderPOJO ret = null;
		ret = ecOrderMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = ecOrderMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += ecOrderMapper.deleteById(id);
			}
		}
		return ret;
	}


}
