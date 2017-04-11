package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxRespMsgPOJO;
import com.cobble.takeaway.pojo.weixin.WxRespMsgSearchPOJO;

public interface WxRespMsgService {
	int insert(WxRespMsgPOJO wxRespMsgPOJO) throws Exception;
	int update(WxRespMsgPOJO wxRespMsgPOJO) throws Exception;
	List<WxRespMsgPOJO> finds(WxRespMsgSearchPOJO wxRespMsgSearchPOJO) throws Exception;
	
	int getCount(WxRespMsgSearchPOJO wxRespMsgSearchPOJO) throws Exception;
	WxRespMsgPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
	int delete(WxRespMsgPOJO wxRespMsgPOJO) throws Exception;
	int delete(List<WxRespMsgPOJO> wxRespMsgPOJOs) throws Exception;
	
}
