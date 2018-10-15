package com.cobble.takeaway.dao.ecommerce;

import com.cobble.takeaway.pojo.ecommerce.EcWxCardBasePOJO;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardBaseSearchPOJO;

import java.util.List;


public interface EcWxCardBaseMapper {
	int insert(EcWxCardBasePOJO ecWxCardBasePOJO) throws Exception;
	int update(EcWxCardBasePOJO ecWxCardBasePOJO) throws Exception;
	List<EcWxCardBasePOJO> finds(EcWxCardBaseSearchPOJO ecWxCardBaseSearchPOJO) throws Exception;
	int getCount(EcWxCardBaseSearchPOJO ecWxCardBaseSearchPOJO) throws Exception;
	EcWxCardBasePOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
}