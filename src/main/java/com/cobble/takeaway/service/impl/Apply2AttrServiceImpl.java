package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.Apply2AttrMapper;
import com.cobble.takeaway.pojo.Apply2AttrPOJO;
import com.cobble.takeaway.pojo.Apply2AttrSearchPOJO;
import com.cobble.takeaway.service.Apply2AttrService;

@Service
public class Apply2AttrServiceImpl implements Apply2AttrService {
	
	@Autowired
	private Apply2AttrMapper apply2AttrMapper;

	@Override
	public int insert(Apply2AttrPOJO apply2AttrPOJO) throws Exception {
		int ret = 0;
		ret = apply2AttrMapper.insert(apply2AttrPOJO);
		return ret;
	}

	@Override
	public int update(Apply2AttrPOJO apply2AttrPOJO) throws Exception {
		int ret = 0;
		ret = apply2AttrMapper.update(apply2AttrPOJO);
		return ret;
	}

	@Override
	public List<Apply2AttrPOJO> finds(
			Apply2AttrSearchPOJO apply2AttrSearchPOJO) throws Exception {
		List<Apply2AttrPOJO> ret = null;
		ret = apply2AttrMapper.finds(apply2AttrSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(Apply2AttrSearchPOJO apply2AttrSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = apply2AttrMapper.getCount(apply2AttrSearchPOJO);
		return ret;
	}

	@Override
	public Apply2AttrPOJO findById(Long id) throws Exception {
		Apply2AttrPOJO ret = null;
		ret = apply2AttrMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = apply2AttrMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += apply2AttrMapper.deleteById(id);
			}
		}
		return ret;
	}

}
