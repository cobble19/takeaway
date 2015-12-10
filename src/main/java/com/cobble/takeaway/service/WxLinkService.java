package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.WxLinkPOJO;
import com.cobble.takeaway.pojo.WxLinkSearchPOJO;

public interface WxLinkService {
	int insert(WxLinkPOJO wxLinkPOJO) throws Exception;
	int update(WxLinkPOJO wxLinkPOJO) throws Exception;
	List<WxLinkPOJO> finds(WxLinkSearchPOJO wxLinkSearchPOJO) throws Exception;
	int getCount(WxLinkSearchPOJO wxLinkSearchPOJO) throws Exception;
	WxLinkPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
	int updateByKey(WxLinkPOJO wxLinkPOJO) throws Exception;
	int getCountByKey(WxLinkPOJO wxLinkPOJO) throws Exception;
	
}
