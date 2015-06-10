package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.PrivilegePOJO;
import com.cobble.takeaway.pojo.PrivilegeSearchPOJO;


public interface PrivilegeMapper {
	int insert(PrivilegePOJO privilegePOJO) throws Exception;
	int update(PrivilegePOJO privilegePOJO) throws Exception;
	List<PrivilegePOJO> findAll() throws Exception;
	List<PrivilegePOJO> findByRoleId(Integer roleId) throws Exception;
	List<PrivilegePOJO> finds(PrivilegeSearchPOJO privilegeSearchPOJO) throws Exception;
	int getCount(PrivilegeSearchPOJO privilegeSearchPOJO) throws Exception;
	PrivilegePOJO findById(Integer id) throws Exception;
	int deleteById(Integer id) throws Exception;
}