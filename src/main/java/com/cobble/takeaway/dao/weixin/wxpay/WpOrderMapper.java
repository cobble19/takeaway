package com.cobble.takeaway.dao.weixin.wxpay;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderSearchPOJO;


public interface WpOrderMapper {
	int insert(WpOrderPOJO wpOrderPOJO) throws Exception;
	int update(WpOrderPOJO wpOrderPOJO) throws Exception;
	List<WpOrderPOJO> finds(WpOrderSearchPOJO wpOrderSearchPOJO) throws Exception;
	int getCount(WpOrderSearchPOJO wpOrderSearchPOJO) throws Exception;
	
	WpOrderPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
}