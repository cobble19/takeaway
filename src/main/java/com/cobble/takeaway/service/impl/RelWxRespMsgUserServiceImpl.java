package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.RelWxRespMsgUserMapper;
import com.cobble.takeaway.pojo.weixin.RelWxRespMsgUserPOJO;
import com.cobble.takeaway.pojo.weixin.RelWxRespMsgUserSearchPOJO;
import com.cobble.takeaway.service.RelWxRespMsgUserService;

@Service
public class RelWxRespMsgUserServiceImpl implements RelWxRespMsgUserService {
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private RelWxRespMsgUserMapper relWxRespMsgUserMapper;

	@Override
	public int insert(RelWxRespMsgUserPOJO relWxRespMsgUserPOJO) throws Exception {
		int ret = 0;
		ret = relWxRespMsgUserMapper.insert(relWxRespMsgUserPOJO);
		return ret;
	}

	@Override
	public int update(RelWxRespMsgUserPOJO relWxRespMsgUserPOJO) throws Exception {
		int ret = 0;
		ret = relWxRespMsgUserMapper.update(relWxRespMsgUserPOJO);
		return ret;
	}

	@Override
	public List<RelWxRespMsgUserPOJO> finds(
			RelWxRespMsgUserSearchPOJO relWxRespMsgUserSearchPOJO) throws Exception {
		List<RelWxRespMsgUserPOJO> ret = null;
		ret = relWxRespMsgUserMapper.finds(relWxRespMsgUserSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(RelWxRespMsgUserSearchPOJO relWxRespMsgUserSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = relWxRespMsgUserMapper.getCount(relWxRespMsgUserSearchPOJO);
		return ret;
	}

	@Override
	public RelWxRespMsgUserPOJO findById(Long id) throws Exception {
		RelWxRespMsgUserPOJO ret = null;
		ret = relWxRespMsgUserMapper.findById(id);
		return ret;
	}

	@Override
	public int deleteById(Long id) throws Exception {
		int ret = 0;
		ret = relWxRespMsgUserMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += relWxRespMsgUserMapper.deleteById(id);
			}
		}
		return ret;
	}


}
