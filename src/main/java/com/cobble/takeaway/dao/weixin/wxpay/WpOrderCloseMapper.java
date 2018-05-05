package com.cobble.takeaway.dao.weixin.wxpay;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderClosePOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderCloseSearchPOJO;


public interface WpOrderCloseMapper {
	int insert(WpOrderClosePOJO wpOrderClosePOJO) throws Exception;
	int update(WpOrderClosePOJO wpOrderClosePOJO) throws Exception;
	int updateByOutTradeNo(WpOrderClosePOJO wpOrderClosePOJO) throws Exception;
	List<WpOrderClosePOJO> finds(WpOrderCloseSearchPOJO wpOrderCloseSearchPOJO) throws Exception;
	int getCount(WpOrderCloseSearchPOJO wpOrderCloseSearchPOJO) throws Exception;
	
	WpOrderClosePOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
}