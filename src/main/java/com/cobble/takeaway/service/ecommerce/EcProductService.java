package com.cobble.takeaway.service.ecommerce;

import java.util.List;

import com.cobble.takeaway.pojo.ecommerce.EcProductPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcProductSearchPOJO;

public interface EcProductService {
	int insert(EcProductPOJO ecProductPOJO) throws Exception;
	int update(EcProductPOJO ecProductPOJO) throws Exception;
	int decreaseStock(Long productId, Integer quantity) throws Exception;
	int increaseStock(Long productId, Integer quantity) throws Exception;
	List<EcProductPOJO> finds(EcProductSearchPOJO ecProductSearchPOJO) throws Exception;
	int getCount(EcProductSearchPOJO ecProductSearchPOJO) throws Exception;
	List<EcProductPOJO> findActives(EcProductSearchPOJO ecProductSearchPOJO) throws Exception;
	EcProductPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

	int increasePageView(Long productId) throws Exception;
	
}
