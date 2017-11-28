package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.RelWxIndexMapPOJO;
import com.cobble.takeaway.pojo.RelWxIndexMapSearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoSearchPOJO;

public interface RelWxIndexMapService {
	int insert(RelWxIndexMapPOJO relWxIndexMapPOJO) throws Exception;
	int update(RelWxIndexMapPOJO relWxIndexMapPOJO) throws Exception;
	List<RelWxIndexMapPOJO> finds(RelWxIndexMapSearchPOJO relWxIndexMapSearchPOJO) throws Exception;
	List<RelWxIndexMapPOJO> findsByAuthorizerInfo(
			WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO) throws Exception;
	int getCount(RelWxIndexMapSearchPOJO relWxIndexMapSearchPOJO) throws Exception;
	RelWxIndexMapPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

}
