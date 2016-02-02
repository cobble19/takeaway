package com.cobble.takeaway.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.dao.WxAttrMapper;
import com.cobble.takeaway.pojo.RelWxAttrPOJO;
import com.cobble.takeaway.pojo.WxAttrPOJO;
import com.cobble.takeaway.pojo.WxAttrSearchPOJO;
import com.cobble.takeaway.pojo.WxSecPOJO;
import com.cobble.takeaway.service.WxAttrService;

@Service
public class WxAttrServiceImpl implements WxAttrService {
	
	@Autowired
	private WxAttrMapper wxAttrMapper;

	@Override
	public int insert(WxAttrPOJO wxAttrPOJO) throws Exception {
		int ret = 0;
		ret = wxAttrMapper.insert(wxAttrPOJO);
		return ret;
	}

	@Override
	public int update(WxAttrPOJO wxAttrPOJO) throws Exception {
		int ret = 0;
		ret = wxAttrMapper.update(wxAttrPOJO);
		return ret;
	}

	@Override
	public List<WxAttrPOJO> finds(
			WxAttrSearchPOJO wxAttrSearchPOJO) throws Exception {
		List<WxAttrPOJO> ret = null;
		ret = wxAttrMapper.finds(wxAttrSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxAttrSearchPOJO wxAttrSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxAttrMapper.getCount(wxAttrSearchPOJO);
		return ret;
	}

	@Override
	public WxAttrPOJO findById(Long id) throws Exception {
		WxAttrPOJO ret = null;
		ret = wxAttrMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wxAttrMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxAttrMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public List<WxAttrPOJO> findsByIds(Long userId, Long wxTemplateId)
			throws Exception {
		List<WxAttrPOJO> ret = null;
		WxAttrSearchPOJO wxAttrSearchPOJO = new WxAttrSearchPOJO();
		wxAttrSearchPOJO.setUserId(userId);
		wxAttrSearchPOJO.setWxTemplateId(wxTemplateId);
		
		ret = wxAttrMapper.findsByIds(wxAttrSearchPOJO);
		return ret;
	}
	
	@Override
	public List<WxAttrPOJO> findsByIds(Long userId, Long wxTemplateId, Integer orderNo, Integer wxSecOrderNo)
			throws Exception {
		List<WxAttrPOJO> ret = null;
		WxAttrSearchPOJO wxAttrSearchPOJO = new WxAttrSearchPOJO();
		wxAttrSearchPOJO.setUserId(userId);
		wxAttrSearchPOJO.setWxTemplateId(wxTemplateId);
		wxAttrSearchPOJO.setOrderNo(orderNo);
		wxAttrSearchPOJO.setWxSecOrderNo(wxSecOrderNo);
		
		ret = wxAttrMapper.findsByIds(wxAttrSearchPOJO);
		return ret;
	}

	@Override
	public List<WxSecPOJO> findWxSecsByIds(Long userId, Long wxTemplateId)
			throws Exception {
		List<WxSecPOJO> ret = new ArrayList<WxSecPOJO>();
		List<WxAttrPOJO> wxAttrPOJOs = null;
		WxAttrSearchPOJO wxAttrSearchPOJO = new WxAttrSearchPOJO();
		wxAttrSearchPOJO.setUserId(userId);
		wxAttrSearchPOJO.setWxTemplateId(wxTemplateId);
		
		wxAttrPOJOs = wxAttrMapper.findsByIds(wxAttrSearchPOJO);
		
		Map<Integer, List<WxAttrPOJO>> wxSecMap = new HashMap<Integer, List<WxAttrPOJO>>();
		if (!CollectionUtils.isEmpty(wxAttrPOJOs)) {
			for (WxAttrPOJO wxAttrPOJO : wxAttrPOJOs) {
				int wxSecOrderNo = wxAttrPOJO.getWxSecOrderNo();
				List<WxAttrPOJO> wxAttrPOJOs2 = wxSecMap.get(wxSecOrderNo);
				if (CollectionUtils.isEmpty(wxAttrPOJOs2)) {
					wxAttrPOJOs2 = new ArrayList<WxAttrPOJO>();
				}
				wxAttrPOJOs2.add(wxAttrPOJO);
				wxSecMap.put(wxSecOrderNo, wxAttrPOJOs2);
			}

			Integer[] keys = new Integer[0];
			keys = wxSecMap.keySet().toArray(keys);
			Arrays.sort(keys);
			for (int i = 0; i < keys.length; i++) {
				List<WxAttrPOJO> wxAttrPOJOs2 = wxSecMap.get(keys[i]);
				WxSecPOJO wxSecPOJO = new WxSecPOJO();
				wxSecPOJO.setWxSecOrderNo(keys[i]);
				wxSecPOJO.setWxAttrPOJOs(wxAttrPOJOs2);
				ret.add(wxSecPOJO);
			}
		}
		
		return ret;
	}

	@Override
	public int insertRelWxAttr(RelWxAttrPOJO relWxAttrPOJO) throws Exception {
		int ret = 0;
		ret = wxAttrMapper.insertRelWxAttr(relWxAttrPOJO);
		return ret;
	}


}
