package com.cobble.takeaway.dao.weixin;


import com.cobble.takeaway.pojo.weixin.WxMsgEventLogPOJO;
import com.cobble.takeaway.pojo.weixin.WxMsgEventLogSearchPOJO;

import java.util.List;


public interface WxMsgEventLogMapper {
	int insert(WxMsgEventLogPOJO wxMsgEventLogPOJO) throws Exception;
	int update(WxMsgEventLogPOJO wxMsgEventLogPOJO) throws Exception;
	List<WxMsgEventLogPOJO> finds(WxMsgEventLogSearchPOJO wxMsgEventLogSearchPOJO) throws Exception;
	int getCount(WxMsgEventLogSearchPOJO wxMsgEventLogSearchPOJO) throws Exception;
	WxMsgEventLogPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
}