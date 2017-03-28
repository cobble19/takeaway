package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.RelWxRespMsgUserPOJO;
import com.cobble.takeaway.pojo.weixin.RelWxRespMsgUserSearchPOJO;


public interface RelWxRespMsgUserMapper {
	int insert(RelWxRespMsgUserPOJO relWxRespMsgUserPOJO) throws Exception;
	int update(RelWxRespMsgUserPOJO relWxRespMsgUserPOJO) throws Exception;
	List<RelWxRespMsgUserPOJO> finds(RelWxRespMsgUserSearchPOJO relWxRespMsgUserSearchPOJO) throws Exception;
	
	int getCount(RelWxRespMsgUserSearchPOJO relWxRespMsgUserSearchPOJO) throws Exception;
	RelWxRespMsgUserPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
}