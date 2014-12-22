package com.cobble.takeaway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.PrivilegeMapper;
import com.cobble.takeaway.pojo.PrivilegePOJO;
import com.cobble.takeaway.pojo.PrivilegeSearchPOJO;
import com.cobble.takeaway.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
	
	@Autowired
	private PrivilegeMapper privilegeMapper;

	@Override
	public int insert(PrivilegePOJO privilegePOJO) throws Exception {
		int ret = 0;
		ret = privilegeMapper.insert(privilegePOJO);
		return ret;
	}

	@Override
	public int update(PrivilegePOJO privilegePOJO) throws Exception {
		int ret = 0;
		ret = privilegeMapper.update(privilegePOJO);
		return ret;
	}

	@Override
	public List<PrivilegePOJO> finds(
			PrivilegeSearchPOJO privilegeSearchPOJO) throws Exception {
		List<PrivilegePOJO> ret = null;
		ret = privilegeMapper.finds(privilegeSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(PrivilegeSearchPOJO privilegeSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = privilegeMapper.getCount(privilegeSearchPOJO);
		return ret;
	}

	@Override
	public PrivilegePOJO findById(Integer id) throws Exception {
		PrivilegePOJO ret = null;
		ret = privilegeMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Integer id) throws Exception {
		int ret = 0;
		ret = privilegeMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Integer[] ids) throws Exception {
		int ret = 0;
		if (ids == null) {
			return ret;
		}
		for (Integer id: ids) {
			ret += privilegeMapper.deleteById(id);
		}
		return ret;
	}

}
