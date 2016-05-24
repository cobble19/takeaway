package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketSearchApiPOJO;


public interface WxComVerifyTicketMapper {
	int insert(WxComVerifyTicketApiPOJO wxComVerifyTicketPOJO) throws Exception;
	int update(WxComVerifyTicketApiPOJO wxComVerifyTicketPOJO) throws Exception;
	List<WxComVerifyTicketApiPOJO> finds(WxComVerifyTicketSearchApiPOJO wxComVerifyTicketSearchPOJO) throws Exception;
	
	List<WxComVerifyTicketApiPOJO> findsByIds(WxComVerifyTicketSearchApiPOJO wxComVerifyTicketSearchPOJO) throws Exception;
	
	int getCount(WxComVerifyTicketSearchApiPOJO wxComVerifyTicketSearchPOJO) throws Exception;
	WxComVerifyTicketApiPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
}