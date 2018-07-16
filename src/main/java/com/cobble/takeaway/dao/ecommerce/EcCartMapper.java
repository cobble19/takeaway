package com.cobble.takeaway.dao.ecommerce;

import com.cobble.takeaway.pojo.ecommerce.EcCartPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcCartSearchPOJO;

import java.util.List;


public interface EcCartMapper {
	int insert(EcCartPOJO ecCartPOJO) throws Exception;
	int update(EcCartPOJO ecCartPOJO) throws Exception;
	List<EcCartPOJO> finds(EcCartSearchPOJO ecCartSearchPOJO) throws Exception;
	int getCount(EcCartSearchPOJO ecCartSearchPOJO) throws Exception;
	EcCartPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
}