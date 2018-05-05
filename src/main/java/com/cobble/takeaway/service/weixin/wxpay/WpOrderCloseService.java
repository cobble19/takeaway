package com.cobble.takeaway.service.weixin.wxpay;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderClosePOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderCloseSearchPOJO;

public interface WpOrderCloseService {

	int insert(WpOrderClosePOJO wpOrderClosePOJO) throws Exception;
	int update(WpOrderClosePOJO wpOrderClosePOJO) throws Exception;
	int updateByOutTradeNo(WpOrderClosePOJO wpOrderClosePOJO) throws Exception;
	List<WpOrderClosePOJO> finds(WpOrderCloseSearchPOJO wpOrderCloseSearchPOJO) throws Exception;
	
	int getCount(WpOrderCloseSearchPOJO wpOrderCloseSearchPOJO) throws Exception;
	WpOrderClosePOJO findById(Long id) throws Exception;
	WpOrderClosePOJO findByOutTradeNo(String outTradeNo) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}
