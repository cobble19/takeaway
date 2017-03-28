package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxRespMsgPOJO;
import com.cobble.takeaway.pojo.weixin.WxRespMsgSearchPOJO;


public interface WxRespMsgMapper {
	int insert(WxRespMsgPOJO wxRespMsgPOJO) throws Exception;
	int update(WxRespMsgPOJO wxRespMsgPOJO) throws Exception;
	List<WxRespMsgPOJO> finds(WxRespMsgSearchPOJO wxRespMsgSearchPOJO) throws Exception;
	
	int getCount(WxRespMsgSearchPOJO wxRespMsgSearchPOJO) throws Exception;
	WxRespMsgPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
}