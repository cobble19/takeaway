package com.cobble.takeaway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.WxMenuMgrButtonMapper;
import com.cobble.takeaway.dao.WxMenuMgrCategoryMapper;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrButtonPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrButtonSearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategoryPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategorySearchPOJO;
import com.cobble.takeaway.service.WxMenuMgrCategoryService;
import com.cobble.takeaway.util.CommonConstant;

@Service
public class WxMenuMgrCategoryServiceImpl implements WxMenuMgrCategoryService {
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private WxMenuMgrCategoryMapper wxMenuMgrCategoryMapper;
	@Autowired
	private WxMenuMgrButtonMapper wxMenuMgrButtonMapper;

	@Override
	public int insert(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO) throws Exception {
		int ret = 0;
		ret = wxMenuMgrCategoryMapper.insert(wxMenuMgrCategoryPOJO);
		return ret;
	}

	@Override
	public int update(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO) throws Exception {
		int ret = 0;
		ret = wxMenuMgrCategoryMapper.update(wxMenuMgrCategoryPOJO);
		return ret;
	}

	@Override
	public List<WxMenuMgrCategoryPOJO> finds(
			WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO) throws Exception {
		List<WxMenuMgrCategoryPOJO> ret = null;
		ret = wxMenuMgrCategoryMapper.finds(wxMenuMgrCategorySearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxMenuMgrCategoryMapper.getCount(wxMenuMgrCategorySearchPOJO);
		return ret;
	}

	@Override
	public WxMenuMgrCategoryPOJO findById(Long id) throws Exception {
		WxMenuMgrCategoryPOJO ret = null;
		ret = wxMenuMgrCategoryMapper.findById(id);
		return ret;
	}

	@Override
	public int deleteById(Long id) throws Exception {
		int ret = 0;
		ret = wxMenuMgrCategoryMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxMenuMgrCategoryMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public List<WxMenuMgrCategoryPOJO> findFull(
			WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO)
			throws Exception {
		List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOs = wxMenuMgrCategoryMapper.finds(wxMenuMgrCategorySearchPOJO);
		
		WxMenuMgrButtonSearchPOJO wxMenuMgrButtonSearchPOJO = new WxMenuMgrButtonSearchPOJO();
		if (wxMenuMgrCategorySearchPOJO != null) {
			wxMenuMgrButtonSearchPOJO.setAuthorizerAppId(wxMenuMgrCategorySearchPOJO.getAuthorizerAppId());
			wxMenuMgrButtonSearchPOJO.setWxMenuMgrCategoryId(wxMenuMgrCategorySearchPOJO.getWxMenuMgrCategoryId());
		}
		wxMenuMgrButtonSearchPOJO.setPaginationFlage(false);
		List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs = wxMenuMgrButtonMapper.finds(wxMenuMgrButtonSearchPOJO);
		
		if (CollectionUtils.isNotEmpty(wxMenuMgrCategoryPOJOs) && CollectionUtils.isNotEmpty(wxMenuMgrButtonPOJOs)) {
			// level = 1
			for (WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO2 : wxMenuMgrCategoryPOJOs) {
				for (WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO2 : wxMenuMgrButtonPOJOs) {
					String authorizerAppIdCate = wxMenuMgrCategoryPOJO2.getAuthorizerAppId();
					String authorizerAppIdBtn = wxMenuMgrButtonPOJO2.getAuthorizerAppId();
					
					Long wxMenuMgrCategoryId0 = wxMenuMgrCategoryPOJO2.getWxMenuMgrCategoryId();
					Long wxMenuMgrCategoryId1 = wxMenuMgrButtonPOJO2.getWxMenuMgrCategoryId();
					
					int level = wxMenuMgrButtonPOJO2.getLevel();
					Long parentButtonId = wxMenuMgrButtonPOJO2.getParentButtonId();
					
					if (StringUtils.isBlank(authorizerAppIdCate)) {
						continue;
					}
					
					if (wxMenuMgrCategoryId0 == null) {
						continue;
					}
					
					if (authorizerAppIdCate.equalsIgnoreCase(authorizerAppIdBtn)
							&& wxMenuMgrCategoryId0.longValue() == wxMenuMgrCategoryId1) {
						if (CommonConstant.WX_MENU_LEVEL_1 == level) {
							List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs2 = wxMenuMgrCategoryPOJO2.getWxMenuMgrButtonPOJOs();
							if (CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs2)) {
								wxMenuMgrCategoryPOJO2.setWxMenuMgrButtonPOJOs(new ArrayList<WxMenuMgrButtonPOJO>());
							}
							wxMenuMgrCategoryPOJO2.getWxMenuMgrButtonPOJOs().add(wxMenuMgrButtonPOJO2);
						}
					}
					
				}
			}	// end level = 1
			// level = 2
			for (WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO1 : wxMenuMgrButtonPOJOs) {
				if (wxMenuMgrButtonPOJO1 != null) {
					if ("text".equalsIgnoreCase(wxMenuMgrButtonPOJO1.getType())) {
						wxMenuMgrButtonPOJO1.setType("click");
						wxMenuMgrButtonPOJO1.setBtnKey(wxMenuMgrButtonPOJO1.getValue());
					}
				}
				
				for (WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO2 : wxMenuMgrButtonPOJOs) {
					String authorizerAppIdBtn1 = wxMenuMgrButtonPOJO1.getAuthorizerAppId();
					String authorizerAppIdBtn2 = wxMenuMgrButtonPOJO2.getAuthorizerAppId();

					Long wxMenuMgrCategoryId0 = wxMenuMgrButtonPOJO1.getWxMenuMgrCategoryId();
					Long wxMenuMgrCategoryId1 = wxMenuMgrButtonPOJO2.getWxMenuMgrCategoryId();
					
					int level = wxMenuMgrButtonPOJO1.getLevel();
					Long buttonId = wxMenuMgrButtonPOJO1.getWxMenuMgrButtonId();
					Long parentButtonId = wxMenuMgrButtonPOJO2.getParentButtonId();
					
					if (StringUtils.isBlank(authorizerAppIdBtn1)) {
						continue;
					}
					if (wxMenuMgrCategoryId0 == null) {
						continue;
					}
					
					if (authorizerAppIdBtn1.equalsIgnoreCase(authorizerAppIdBtn2)
							&& buttonId.longValue() == parentButtonId && CommonConstant.WX_MENU_LEVEL_1 == level
							&& wxMenuMgrCategoryId0.longValue() == wxMenuMgrCategoryId1) {
						List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs2 = wxMenuMgrButtonPOJO1.getWxMenuMgrButtonPOJOs();
						if (CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs2)) {
							wxMenuMgrButtonPOJO1.setWxMenuMgrButtonPOJOs(new ArrayList<WxMenuMgrButtonPOJO>());
						}
						wxMenuMgrButtonPOJO1.getWxMenuMgrButtonPOJOs().add(wxMenuMgrButtonPOJO2);
					}
					
				}
			}	// end level = 2
		}
		
		return wxMenuMgrCategoryPOJOs;
	}


}
