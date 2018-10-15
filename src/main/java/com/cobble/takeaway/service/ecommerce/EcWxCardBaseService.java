package com.cobble.takeaway.service.ecommerce;

import com.cobble.takeaway.pojo.ecommerce.EcWxCardBasePOJO;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardBaseSearchPOJO;

import java.util.List;

public interface EcWxCardBaseService {
	int insert(EcWxCardBasePOJO ecWxCardBasePOJO) throws Exception;
	int update(EcWxCardBasePOJO ecWxCardBasePOJO) throws Exception;
	List<EcWxCardBasePOJO> finds(EcWxCardBaseSearchPOJO ecWxCardBaseSearchPOJO) throws Exception;
	int getCount(EcWxCardBaseSearchPOJO ecWxCardBaseSearchPOJO) throws Exception;
	EcWxCardBasePOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

	EcWxCardBasePOJO initEcWxCardBase(EcWxCardBasePOJO ecWxCardBasePOJO) throws Exception;
}
