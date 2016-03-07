package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.Apply2AttrPOJO;
import com.cobble.takeaway.pojo.Apply2AttrSearchPOJO;

public interface Apply2AttrService {
	int insert(Apply2AttrPOJO apply2AttrPOJO) throws Exception;
	int update(Apply2AttrPOJO apply2AttrPOJO) throws Exception;
	List<Apply2AttrPOJO> finds(Apply2AttrSearchPOJO apply2AttrSearchPOJO) throws Exception;
	int getCount(Apply2AttrSearchPOJO apply2AttrSearchPOJO) throws Exception;
	Apply2AttrPOJO findById(Long id) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

}
