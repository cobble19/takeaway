package com.cobble.takeaway.service.weixin.wxpay;

import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderRespPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderRespSearchPOJO;

import java.util.List;

public interface WpOrderRespService {
	int insert(WpOrderRespPOJO wpOrderRespPOJO) throws Exception;
	int update(WpOrderRespPOJO wpOrderRespPOJO) throws Exception;
	List<WpOrderRespPOJO> finds(WpOrderRespSearchPOJO wpOrderRespSearchPOJO) throws Exception;

	int getCount(WpOrderRespSearchPOJO wpOrderRespSearchPOJO) throws Exception;
	WpOrderRespPOJO findById(Long id) throws Exception;

	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
}
