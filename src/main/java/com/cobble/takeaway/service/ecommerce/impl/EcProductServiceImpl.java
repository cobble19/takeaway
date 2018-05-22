package com.cobble.takeaway.service.ecommerce.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.ecommerce.EcProductMapper;
import com.cobble.takeaway.pojo.ecommerce.EcProductPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcProductSearchPOJO;
import com.cobble.takeaway.service.ecommerce.EcProductService;

@Service
public class EcProductServiceImpl implements EcProductService {
	
	@Autowired
	private EcProductMapper ecProductMapper;

	@Override
	public int insert(EcProductPOJO ecProductPOJO) throws Exception {
		int ret = 0;
		ret = ecProductMapper.insert(ecProductPOJO);
		return ret;
	}

	@Override
	public int update(EcProductPOJO ecProductPOJO) throws Exception {
		int ret = 0;
		ret = ecProductMapper.update(ecProductPOJO);
		return ret;
	}

	@Override
	public List<EcProductPOJO> finds(
			EcProductSearchPOJO ecProductSearchPOJO) throws Exception {
		List<EcProductPOJO> ret = null;
		ret = ecProductMapper.finds(ecProductSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(EcProductSearchPOJO ecProductSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = ecProductMapper.getCount(ecProductSearchPOJO);
		return ret;
	}

	@Override
	public EcProductPOJO findById(Long id) throws Exception {
		EcProductPOJO ret = null;
		ret = ecProductMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = ecProductMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += ecProductMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public int decreaseStock(Long productId, Integer quantity) throws Exception {
		int ret = 0;
		ret = ecProductMapper.decreaseStock(productId, quantity);
		return ret;
	}

	@Override
	public int increaseStock(Long productId, Integer quantity) throws Exception {
		int ret = 0;
		ret = ecProductMapper.increaseStock(productId, quantity);
		return ret;
	}

}
