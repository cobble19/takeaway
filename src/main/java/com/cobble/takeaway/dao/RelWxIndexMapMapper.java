package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.RelWxIndexMapPOJO;
import com.cobble.takeaway.pojo.RelWxIndexMapSearchPOJO;


public interface RelWxIndexMapMapper {
	int insert(RelWxIndexMapPOJO relWxIndexMapPOJO) throws Exception;
	int update(RelWxIndexMapPOJO relWxIndexMapPOJO) throws Exception;
	List<RelWxIndexMapPOJO> finds(RelWxIndexMapSearchPOJO relWxIndexMapSearchPOJO) throws Exception;
	int getCount(RelWxIndexMapSearchPOJO relWxIndexMapSearchPOJO) throws Exception;
	RelWxIndexMapPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;

}