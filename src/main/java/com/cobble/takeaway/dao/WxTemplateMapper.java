package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.RelWxIndexMapPOJO;
import com.cobble.takeaway.pojo.RelWxTemplateUserPOJO;
import com.cobble.takeaway.pojo.RelWxTemplateUserSearchPOJO;
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
	
	int updateRelWxTemplateUser4Display(RelWxTemplateUserPOJO relWxTemplateUserPOJO) throws Exception;
	
	int updateRelWxTemplateUser4WxStaticPage(RelWxTemplateUserPOJO relWxTemplateUserPOJO) throws Exception;
	int updateRelWxIndexMap4WxStaticPage(RelWxIndexMapPOJO relWxIndexMapPOJO) throws Exception;
	
	
	List<WxTemplatePOJO> findsByUserId4UC(WxTemplateSearchPOJO wxTemplateSearchPOJO) throws Exception;
	

	List<RelWxTemplateUserPOJO> findRelWxTemplateUsers(RelWxTemplateUserSearchPOJO relWxTemplateUserSearchPOJO) throws Exception;
	int insertRelWxTemplateUser(RelWxTemplateUserPOJO relWxTemplateUserPOJO) throws Exception;
	

}