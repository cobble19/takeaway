package com.cobble.takeaway.dao.ecommerce;

import com.cobble.takeaway.pojo.ecommerce.EcWxCardPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardSearchPOJO;

import java.util.List;


public interface EcWxCardMapper {
	int insert(EcWxCardPOJO ecWxCardPOJO) throws Exception;
	int update(EcWxCardPOJO ecWxCardPOJO) throws Exception;
	int appendDescription(EcWxCardPOJO ecWxCardPOJO) throws Exception;
	List<EcWxCardPOJO> finds(EcWxCardSearchPOJO ecWxCardSearchPOJO) throws Exception;
	int getCount(EcWxCardSearchPOJO ecWxCardSearchPOJO) throws Exception;
	List<EcWxCardPOJO> findWithEcOrders(EcWxCardSearchPOJO ecWxCardSearchPOJO) throws Exception;
	int getCountWithEcOrder(EcWxCardSearchPOJO ecWxCardSearchPOJO) throws Exception;
	EcWxCardPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
}