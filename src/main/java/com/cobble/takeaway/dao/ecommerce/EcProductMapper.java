package com.cobble.takeaway.dao.ecommerce;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cobble.takeaway.pojo.ecommerce.EcProductPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcProductSearchPOJO;


public interface EcProductMapper {
	int insert(EcProductPOJO ecProductPOJO) throws Exception;
	int update(EcProductPOJO ecProductPOJO) throws Exception;
	int decreaseStock(@Param("productId") Long productId, @Param("quantity") Integer quantity) throws Exception;
	int increaseStock(@Param("productId") Long productId, @Param("quantity") Integer quantity) throws Exception;
	List<EcProductPOJO> finds(EcProductSearchPOJO ecProductSearchPOJO) throws Exception;
	int getCount(EcProductSearchPOJO ecProductSearchPOJO) throws Exception;
	List<EcProductPOJO> findActives(EcProductSearchPOJO ecProductSearchPOJO) throws Exception;
	EcProductPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;

	int increasePageView(Long productId) throws Exception;
}