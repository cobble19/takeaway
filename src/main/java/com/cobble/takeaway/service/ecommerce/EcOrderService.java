package com.cobble.takeaway.service.ecommerce;

import java.util.List;

import com.cobble.takeaway.pojo.ecommerce.EcOrderPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderSearchPOJO;

public interface EcOrderService {
	int insert(EcOrderPOJO ecOrderPOJO) throws Exception;
	int update(EcOrderPOJO ecOrderPOJO) throws Exception;
	List<EcOrderPOJO> finds(EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception;
	int getCount(EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception;
	EcOrderPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

	int getCountReally(EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception;
	int getCountTodayTotal(EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception;
	int getCountTodayClose(EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception;
	
}
