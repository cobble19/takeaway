package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.RecommendPOJO;
import com.cobble.takeaway.pojo.RecommendSearchPOJO;


public interface RecommendMapper {
	int insert(RecommendPOJO recommendPOJO) throws Exception;
	int update(RecommendPOJO recommendPOJO) throws Exception;
	List<RecommendPOJO> finds(RecommendSearchPOJO recommendSearchPOJO) throws Exception;
	int getCount(RecommendSearchPOJO recommendSearchPOJO) throws Exception;
	RecommendPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
}