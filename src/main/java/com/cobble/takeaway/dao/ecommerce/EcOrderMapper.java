package com.cobble.takeaway.dao.ecommerce;

import java.util.List;

import com.cobble.takeaway.pojo.ecommerce.EcOrderPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderSearchPOJO;


public interface EcOrderMapper {
	int insert(EcOrderPOJO ecOrderPOJO) throws Exception;
	int update(EcOrderPOJO ecOrderPOJO) throws Exception;
	List<EcOrderPOJO> finds(EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception;
	int getCount(EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception;
	EcOrderPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	int updatePayResult(EcOrderPOJO ecOrderPOJO) throws Exception;
}