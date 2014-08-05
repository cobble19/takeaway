package com.cobble.takeaway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.OrderFormMapper;
import com.cobble.takeaway.pojo.OrderFormPOJO;
import com.cobble.takeaway.pojo.OrderFormSearchPOJO;
import com.cobble.takeaway.service.OrderFormService;

@Service
public class OrderFormServiceImpl implements OrderFormService {
	
	@Autowired
	private OrderFormMapper orderFormMapper;

	@Override
	public int insert(OrderFormPOJO orderFormPOJO) throws Exception {
		int ret = 0;
		ret = orderFormMapper.insert(orderFormPOJO);
		return ret;
	}

	@Override
	public int update(OrderFormPOJO orderFormPOJO) throws Exception {
		int ret = 0;
		ret = orderFormMapper.update(orderFormPOJO);
		return ret;
	}

	@Override
	public List<OrderFormPOJO> finds(
			OrderFormSearchPOJO orderFormSearchPOJO) throws Exception {
		List<OrderFormPOJO> ret = null;
		ret = orderFormMapper.finds(orderFormSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(OrderFormSearchPOJO orderFormSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = orderFormMapper.getCount(orderFormSearchPOJO);
		return ret;
	}

	@Override
	public OrderFormPOJO findById(Integer id) throws Exception {
		OrderFormPOJO ret = null;
		ret = orderFormMapper.findById(id);
		return ret;
	}

	@Override
	public int deleteById(Integer id) throws Exception {
		int ret = 0;
		ret = orderFormMapper.deleteById(id);
		return ret;
	}

}
