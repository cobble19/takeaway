package com.cobble.takeaway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.dao.RelUserRoleMapper;
import com.cobble.takeaway.dao.UserMapper;
import com.cobble.takeaway.pojo.RelUserRolePOJO;
import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.pojo.UserSearchPOJO;
import com.cobble.takeaway.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RelUserRoleMapper relUserRoleMapper;

	@Override
	public int insert(UserPOJO userPOJO) throws Exception {
		int ret = 0;
		ret = userMapper.insert(userPOJO);
		if (!CollectionUtils.isEmpty(userPOJO.getRoleId())) {
			for (Integer roleId : userPOJO.getRoleId()) {
				RelUserRolePOJO relUserRolePOJO = new RelUserRolePOJO();
				relUserRolePOJO.setUserId(userPOJO.getUserId());
				relUserRolePOJO.setRoleId(roleId);
				relUserRoleMapper.insert(relUserRolePOJO);
			}
		}
		return ret;
	}

	@Override
	public int update(UserPOJO userPOJO) throws Exception {
		int ret = 0;
		ret = userMapper.update(userPOJO);
		return ret;
	}

	@Override
	public List<UserPOJO> finds(
			UserSearchPOJO userSearchPOJO) throws Exception {
		List<UserPOJO> ret = null;
		ret = userMapper.finds(userSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(UserSearchPOJO userSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = userMapper.getCount(userSearchPOJO);
		return ret;
	}

	@Override
	public UserPOJO findById(Integer id) throws Exception {
		UserPOJO ret = null;
		ret = userMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Integer id) throws Exception {
		int ret = 0;
		ret = userMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Integer[] ids) throws Exception {
		int ret = 0;
		if (ids == null) {
			return ret;
		}
		for (Integer id: ids) {
			ret += userMapper.deleteById(id);
		}
		return ret;
	}

	@Override
	public UserPOJO findUserByName(String username) throws Exception {
		return userMapper.findUserByName(username);
	}


}
