package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.RolePOJO;
import com.cobble.takeaway.pojo.RoleSearchPOJO;


public interface RoleMapper {
	int insert(RolePOJO rolePOJO) throws Exception;
	int update(RolePOJO rolePOJO) throws Exception;
	List<RolePOJO> finds(RoleSearchPOJO roleSearchPOJO) throws Exception;
	int getCount(RoleSearchPOJO roleSearchPOJO) throws Exception;
	RolePOJO findById(Integer id) throws Exception;
	int deleteById(Integer id) throws Exception;
}