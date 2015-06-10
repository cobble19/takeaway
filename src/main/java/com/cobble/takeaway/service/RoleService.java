package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.RolePOJO;
import com.cobble.takeaway.pojo.RoleSearchPOJO;

public interface RoleService {
	int insert(RolePOJO rolePOJO) throws Exception;
	int update(RolePOJO rolePOJO) throws Exception;
	List<RolePOJO> findAll() throws Exception;
	List<RolePOJO> findByPrivilegeId(Integer privilegeId) throws Exception;
	List<RolePOJO> findByUserId(Integer userId) throws Exception;
	List<RolePOJO> finds(RoleSearchPOJO roleSearchPOJO) throws Exception;
	int getCount(RoleSearchPOJO roleSearchPOJO) throws Exception;
	RolePOJO findById(Integer id) throws Exception;
	int delete(Integer id) throws Exception;
	int delete(Integer[] ids) throws Exception;
}
