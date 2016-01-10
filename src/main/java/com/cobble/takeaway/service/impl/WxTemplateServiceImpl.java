package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.WxTemplateMapper;
import com.cobble.takeaway.pojo.RelWxIndexMapPOJO;
import com.cobble.takeaway.pojo.RelWxTemplateUserPOJO;
import com.cobble.takeaway.pojo.WxTemplatePOJO;
import com.cobble.takeaway.pojo.WxTemplateSearchPOJO;
import com.cobble.takeaway.service.WxTemplateService;

@Service
public class WxTemplateServiceImpl implements WxTemplateService {
	
	@Autowired
	private WxTemplateMapper wxTemplateMapper;

	@Override
	public int insert(WxTemplatePOJO wxTemplatePOJO) throws Exception {
		int ret = 0;
		ret = wxTemplateMapper.insert(wxTemplatePOJO);
		return ret;
	}

	@Override
	public int update(WxTemplatePOJO wxTemplatePOJO) throws Exception {
		int ret = 0;
		ret = wxTemplateMapper.update(wxTemplatePOJO);
		return ret;
	}

	@Override
	public List<WxTemplatePOJO> finds(
			WxTemplateSearchPOJO wxTemplateSearchPOJO) throws Exception {
		List<WxTemplatePOJO> ret = null;
		ret = wxTemplateMapper.finds(wxTemplateSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxTemplateSearchPOJO wxTemplateSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxTemplateMapper.getCount(wxTemplateSearchPOJO);
		return ret;
	}

	@Override
	public WxTemplatePOJO findById(Long id) throws Exception {
		WxTemplatePOJO ret = null;
		ret = wxTemplateMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wxTemplateMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxTemplateMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public List<WxTemplatePOJO> findsByUserId(
			WxTemplateSearchPOJO wxTemplateSearchPOJO) throws Exception {
		List<WxTemplatePOJO> ret = null;
		ret = wxTemplateMapper.findsByUserId(wxTemplateSearchPOJO);
		return ret;
	}

	@Override
	public int updateRelWxTemplateUser4Status(
			RelWxTemplateUserPOJO relWxTemplateUserPOJO) throws Exception {
		int ret = 0;
		ret = wxTemplateMapper.updateRelWxTemplateUser4Status(relWxTemplateUserPOJO);
		return ret;
	}

	@Override
	public int updateRelWxTemplateUser4WxStaticPage(
			RelWxTemplateUserPOJO relWxTemplateUserPOJO) throws Exception {
		int ret = 0;
		ret = wxTemplateMapper.updateRelWxTemplateUser4WxStaticPage(relWxTemplateUserPOJO);
		return ret;
	}

	@Override
	public List<WxTemplatePOJO> findsByUserId4UC(
			WxTemplateSearchPOJO wxTemplateSearchPOJO) throws Exception {
		List<WxTemplatePOJO> ret = null;
		ret = wxTemplateMapper.findsByUserId4UC(wxTemplateSearchPOJO);
		return ret;
	}

	@Override
	public int updateRelWxTemplateUser4Display(
			RelWxTemplateUserPOJO relWxTemplateUserPOJO) throws Exception {
		int ret = 0;
		ret = wxTemplateMapper.updateRelWxTemplateUser4Display(relWxTemplateUserPOJO);
		return ret;
	}

	@Override
	public int updateRelWxIndexMap4WxStaticPage(
			RelWxIndexMapPOJO relWxIndexMapPOJO) throws Exception {
		int ret = 0;
		ret = wxTemplateMapper.updateRelWxIndexMap4WxStaticPage(relWxIndexMapPOJO);
		return ret;
	}

}
