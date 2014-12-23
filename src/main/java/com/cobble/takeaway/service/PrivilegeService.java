package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.PrivilegePOJO;
import com.cobble.takeaway.pojo.PrivilegeSearchPOJO;

public interface PrivilegeService {
	int insert(PrivilegePOJO privilegePOJO) throws Exception;
	int update(PrivilegePOJO privilegePOJO) throws Exception;
	List<PrivilegePOJO> finds(PrivilegeSearchPOJO privilegeSearchPOJO) throws Exception;
	List<PrivilegePOJO> findAll() throws Exception;
	
	int getCount(PrivilegeSearchPOJO privilegeSearchPOJO) throws Exception;
	PrivilegePOJO findById(Integer id) throws Exception;
	int delete(Integer id) throws Exception;
	int delete(Integer[] ids) throws Exception;
}
