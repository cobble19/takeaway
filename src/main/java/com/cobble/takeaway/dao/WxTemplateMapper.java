package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.RelWxTemplateUserPOJO;
import com.cobble.takeaway.pojo.WxTemplatePOJO;
import com.cobble.takeaway.pojo.WxTemplateSearchPOJO;


public interface WxTemplateMapper {
	int insert(WxTemplatePOJO wxTemplatePOJO) throws Exception;
	int update(WxTemplatePOJO wxTemplatePOJO) throws Exception;
	List<WxTemplatePOJO> finds(WxTemplateSearchPOJO wxTemplateSearchPOJO) throws Exception;
	
	int getCount(WxTemplateSearchPOJO wxTemplateSearchPOJO) throws Exception;
	WxTemplatePOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;

	List<WxTemplatePOJO> findsByUserId(WxTemplateSearchPOJO wxTemplateSearchPOJO) throws Exception;
	int updateRelWxTemplateUser4Status(RelWxTemplateUserPOJO relWxTemplateUserPOJO) throws Exception;
	
	int updateRelWxTemplateUser4WxStaticPage(RelWxTemplateUserPOJO relWxTemplateUserPOJO) throws Exception;

}