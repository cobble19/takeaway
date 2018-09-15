package com.cobble.takeaway.service.ecommerce;

import com.cobble.takeaway.pojo.ecommerce.EcWxCardPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardSearchPOJO;

import java.util.List;

public interface EcWxCardService {
	int insert(EcWxCardPOJO ecWxCardPOJO) throws Exception;
	int update(EcWxCardPOJO ecWxCardPOJO) throws Exception;
	int updateCardAcquireFlag(Long ecWxCardId, Integer cardAcquireFlag) throws Exception;
	int appendDescription(Long ecWxCardId, String descriptionAppended) throws Exception;
	List<EcWxCardPOJO> finds(EcWxCardSearchPOJO ecWxCardSearchPOJO) throws Exception;
	int getCount(EcWxCardSearchPOJO ecWxCardSearchPOJO) throws Exception;
	List<EcWxCardPOJO> findWithEcOrders(EcWxCardSearchPOJO ecWxCardSearchPOJO) throws Exception;
	int getCountWithEcOrder(EcWxCardSearchPOJO ecWxCardSearchPOJO) throws Exception;
	EcWxCardPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}
