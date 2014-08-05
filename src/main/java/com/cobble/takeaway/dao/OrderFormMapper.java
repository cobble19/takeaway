package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.OrderFormPOJO;
import com.cobble.takeaway.pojo.OrderFormSearchPOJO;


public interface OrderFormMapper {
	int insert(OrderFormPOJO orderFormPOJO) throws Exception;
	int update(OrderFormPOJO orderFormPOJO) throws Exception;
	List<OrderFormPOJO> finds(OrderFormSearchPOJO orderFormSearchPOJO) throws Exception;
	int getCount(OrderFormSearchPOJO orderFormSearchPOJO) throws Exception;
	OrderFormPOJO findById(Integer id) throws Exception;
	int deleteById(Integer id) throws Exception;
}