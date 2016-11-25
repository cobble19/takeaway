package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxMenuMgrFullPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrFullSearchPOJO;

public interface WxMenuMgrFullService {
	int insert(WxMenuMgrFullPOJO wxMenuMgrFullPOJO) throws Exception;
	int update(WxMenuMgrFullPOJO wxMenuMgrFullPOJO) throws Exception;
	List<WxMenuMgrFullPOJO> finds(WxMenuMgrFullSearchPOJO wxMenuMgrFullSearchPOJO) throws Exception;
	
	int getCount(WxMenuMgrFullSearchPOJO wxMenuMgrFullSearchPOJO) throws Exception;
	WxMenuMgrFullPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}
