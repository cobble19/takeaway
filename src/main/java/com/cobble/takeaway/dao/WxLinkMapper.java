package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.RelWxLinkPOJO;
import com.cobble.takeaway.pojo.WxLinkPOJO;
import com.cobble.takeaway.pojo.WxLinkSearchPOJO;


public interface WxLinkMapper {
	int insert(WxLinkPOJO wxLinkPOJO) throws Exception;
	int update(WxLinkPOJO wxLinkPOJO) throws Exception;
	List<WxLinkPOJO> finds(WxLinkSearchPOJO wxLinkSearchPOJO) throws Exception;
	
	List<WxLinkPOJO> findsByIds(WxLinkPOJO wxLinkPOJO) throws Exception;
	
	int getCount(WxLinkSearchPOJO wxLinkSearchPOJO) throws Exception;
	WxLinkPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
	int updateByKey(WxLinkPOJO wxLinkPOJO) throws Exception;
	int getCountByKey(WxLinkPOJO wxLinkPOJO) throws Exception;
	
	int insertRelWxLink(RelWxLinkPOJO relWxLinkPOJO) throws Exception;
}