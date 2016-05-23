package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketSearchPOJO;

public interface WxComVerifyTicketService {

	int insert(WxComVerifyTicketPOJO wxComVerifyTicketPOJO) throws Exception;
	int update(WxComVerifyTicketPOJO wxComVerifyTicketPOJO) throws Exception;
	List<WxComVerifyTicketPOJO> finds(WxComVerifyTicketSearchPOJO wxComVerifyTicketSearchPOJO) throws Exception;
	
	int getCount(WxComVerifyTicketSearchPOJO wxComVerifyTicketSearchPOJO) throws Exception;
	WxComVerifyTicketPOJO findById(Long id) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}
