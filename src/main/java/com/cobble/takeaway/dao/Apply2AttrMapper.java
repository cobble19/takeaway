package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.Apply2AttrPOJO;
import com.cobble.takeaway.pojo.Apply2AttrSearchPOJO;


public interface Apply2AttrMapper {
	int insert(Apply2AttrPOJO apply2AttrPOJO) throws Exception;
	int update(Apply2AttrPOJO apply2AttrPOJO) throws Exception;
	List<Apply2AttrPOJO> finds(Apply2AttrSearchPOJO apply2AttrSearchPOJO) throws Exception;
	int getCount(Apply2AttrSearchPOJO apply2AttrSearchPOJO) throws Exception;
	Apply2AttrPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
}