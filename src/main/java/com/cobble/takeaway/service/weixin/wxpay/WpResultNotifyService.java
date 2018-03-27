package com.cobble.takeaway.service.weixin.wxpay;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.wxpay.WpResultNotifyPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpResultNotifySearchPOJO;

public interface WpResultNotifyService {

	int insert(WpResultNotifyPOJO wpResultNotifyPOJO) throws Exception;
	int update(WpResultNotifyPOJO wpResultNotifyPOJO) throws Exception;
	List<WpResultNotifyPOJO> finds(WpResultNotifySearchPOJO wpResultNotifySearchPOJO) throws Exception;
	
	int getCount(WpResultNotifySearchPOJO wpResultNotifySearchPOJO) throws Exception;
	WpResultNotifyPOJO findById(Long id) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

	int insert(String resultNotify) throws Exception;
}
