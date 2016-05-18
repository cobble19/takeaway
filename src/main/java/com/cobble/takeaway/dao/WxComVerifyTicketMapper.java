package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.WxComVerifyTicketPOJO;
import com.cobble.takeaway.pojo.WxComVerifyTicketSearchPOJO;


public interface WxComVerifyTicketMapper {
	int insert(WxComVerifyTicketPOJO wxComVerifyTicketPOJO) throws Exception;
	int update(WxComVerifyTicketPOJO wxComVerifyTicketPOJO) throws Exception;
	List<WxComVerifyTicketPOJO> finds(WxComVerifyTicketSearchPOJO wxComVerifyTicketSearchPOJO) throws Exception;
	
	List<WxComVerifyTicketPOJO> findsByIds(WxComVerifyTicketSearchPOJO wxComVerifyTicketSearchPOJO) throws Exception;
	
	int getCount(WxComVerifyTicketSearchPOJO wxComVerifyTicketSearchPOJO) throws Exception;
	WxComVerifyTicketPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
}