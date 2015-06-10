package com.cobble.takeaway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.dao.PrivilegeMapper;
import com.cobble.takeaway.dao.RelPrivilegeRoleMapper;
import com.cobble.takeaway.pojo.PrivilegePOJO;
import com.cobble.takeaway.pojo.PrivilegeSearchPOJO;
import com.cobble.takeaway.pojo.RelPrivilegeRolePOJO;
import com.cobble.takeaway.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
	
	@Autowired
	private PrivilegeMapper privilegeMapper;
	@Autowired
	private RelPrivilegeRoleMapper relPrivilegeRoleMapper;

	@Override
	public int insert(PrivilegePOJO privilegePOJO) throws Exception {
		int ret = 0;
		ret = privilegeMapper.insert(privilegePOJO);
		if (!CollectionUtils.isEmpty(privilegePOJO.getRoleId())) {
			for (Integer roleId : privilegePOJO.getRoleId()) {
				RelPrivilegeRolePOJO relPrivilegeRolePOJO = new RelPrivilegeRolePOJO();
				relPrivilegeRolePOJO.setPrivilegeId(privilegePOJO.getPrivilegeId());
				relPrivilegeRolePOJO.setRoleId(roleId);
				relPrivilegeRoleMapper.insert(relPrivilegeRolePOJO);
			}
		}
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

	@Override
	public List<PrivilegePOJO> findAll() throws Exception {
		List<PrivilegePOJO> ret = null;
		ret = privilegeMapper.findAll();
		return ret;
	}

	@Override
	public List<PrivilegePOJO> findByRoleId(Integer roleId) throws Exception {
		List<PrivilegePOJO> ret = null;
		ret = privilegeMapper.findByRoleId(roleId);
		return ret;
	}

}
