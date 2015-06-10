package com.cobble.takeaway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.dao.RelPrivilegeRoleMapper;
import com.cobble.takeaway.dao.RoleMapper;
import com.cobble.takeaway.pojo.RelPrivilegeRolePOJO;
import com.cobble.takeaway.pojo.RolePOJO;
import com.cobble.takeaway.pojo.RoleSearchPOJO;
import com.cobble.takeaway.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RelPrivilegeRoleMapper relPrivilegeRoleMapper;

	@Override
	public int insert(RolePOJO rolePOJO) throws Exception {
		int ret = 0;
		ret = roleMapper.insert(rolePOJO);
		if (!CollectionUtils.isEmpty(rolePOJO.getPrivilegeId())) {
			for (Integer privilegeId : rolePOJO.getPrivilegeId()) {
				RelPrivilegeRolePOJO relPrivilegeRolePOJO = new RelPrivilegeRolePOJO();
				relPrivilegeRolePOJO.setPrivilegeId(privilegeId);
				relPrivilegeRolePOJO.setRoleId(rolePOJO.getRoleId());
				relPrivilegeRoleMapper.insert(relPrivilegeRolePOJO);
			}
		}
		return ret;
	}

	@Override
	public int update(RolePOJO rolePOJO) throws Exception {
		int ret = 0;
		ret = roleMapper.update(rolePOJO);
		return ret;
	}

	@Override
	public List<RolePOJO> finds(
			RoleSearchPOJO roleSearchPOJO) throws Exception {
		List<RolePOJO> ret = null;
		ret = roleMapper.finds(roleSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(RoleSearchPOJO roleSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = roleMapper.getCount(roleSearchPOJO);
		return ret;
	}

	@Override
	public RolePOJO findById(Integer id) throws Exception {
		RolePOJO ret = null;
		ret = roleMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Integer id) throws Exception {
		int ret = 0;
		ret = roleMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Integer[] ids) throws Exception {
		int ret = 0;
		if (ids == null) {
			return ret;
		}
		for (Integer id: ids) {
			ret += roleMapper.deleteById(id);
		}
		return ret;
	}

	@Override
	public List<RolePOJO> findAll() throws Exception {
		return roleMapper.findAll();
	}

	@Override
	public List<RolePOJO> findByPrivilegeId(Integer privilegeId) throws Exception {
		return roleMapper.findByPrivilegeId(privilegeId);
	}

	@Override
	public List<RolePOJO> findByUserId(Integer userId) throws Exception {
		return roleMapper.findByUserId(userId);
	}

}
