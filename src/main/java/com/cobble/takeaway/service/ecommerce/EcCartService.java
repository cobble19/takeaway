package com.cobble.takeaway.service.ecommerce;

import com.cobble.takeaway.pojo.ecommerce.EcCartPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcCartSearchPOJO;

import java.util.List;

public interface EcCartService {
	int insert(EcCartPOJO ecCartPOJO) throws Exception;
	int update(EcCartPOJO ecCartPOJO) throws Exception;
	List<EcCartPOJO> finds(EcCartSearchPOJO ecCartSearchPOJO) throws Exception;
	int getCount(EcCartSearchPOJO ecCartSearchPOJO) throws Exception;
	EcCartPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

}
