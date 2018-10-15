package com.cobble.takeaway.service.ecommerce.impl;

import com.cobble.takeaway.dao.ecommerce.EcWxCardBaseMapper;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardBasePOJO;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardBaseSearchPOJO;
import com.cobble.takeaway.service.ecommerce.EcWxCardBaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcWxCardBaseServiceImpl implements EcWxCardBaseService {
	private static final Logger logger = LoggerFactory.getLogger(EcWxCardBaseServiceImpl.class);
	
	@Autowired
	private EcWxCardBaseMapper ecWxCardBaseMapper;

	@Override
	public int insert(EcWxCardBasePOJO ecWxCardBasePOJO) throws Exception {
		int ret = 0;
		ret = ecWxCardBaseMapper.insert(ecWxCardBasePOJO);
		return ret;
	}

	@Override
	public int update(EcWxCardBasePOJO ecWxCardBasePOJO) throws Exception {
		int ret = 0;
		ret = ecWxCardBaseMapper.update(ecWxCardBasePOJO);
		return ret;
	}

	@Override
	public List<EcWxCardBasePOJO> finds(
			EcWxCardBaseSearchPOJO ecWxCardBaseSearchPOJO) throws Exception {
		List<EcWxCardBasePOJO> ret = null;
		ret = ecWxCardBaseMapper.finds(ecWxCardBaseSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(EcWxCardBaseSearchPOJO ecWxCardBaseSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = ecWxCardBaseMapper.getCount(ecWxCardBaseSearchPOJO);
		return ret;
	}

	@Override
	public EcWxCardBasePOJO findById(Long id) throws Exception {
		EcWxCardBasePOJO ret = null;
		ret = ecWxCardBaseMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = ecWxCardBaseMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += ecWxCardBaseMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public EcWxCardBasePOJO initEcWxCardBase(EcWxCardBasePOJO ecWxCardBasePOJO) throws Exception {
		EcWxCardBasePOJO ret = null;
		EcWxCardBaseSearchPOJO ecWxCardBaseSearchPOJO = new EcWxCardBaseSearchPOJO();
		String authorizerAppId = ecWxCardBasePOJO.getAuthorizerAppId();
		String cardId = ecWxCardBasePOJO.getCardId();
		String openId = ecWxCardBasePOJO.getOpenId();

		if (StringUtils.isBlank(authorizerAppId) || StringUtils.isBlank(cardId) || StringUtils.isBlank(openId)) {
			logger.error("authorizerAppId: {}, cardId: {}, openId: {} must not be empty", authorizerAppId, cardId, openId);
			return null;
		}

		ecWxCardBaseSearchPOJO.setAuthorizerAppId(authorizerAppId);
		ecWxCardBaseSearchPOJO.setCardId(cardId);
		ecWxCardBaseSearchPOJO.setOpenId(openId);

		List<EcWxCardBasePOJO> ecWxCardBasePOJOs = ecWxCardBaseMapper.finds(ecWxCardBaseSearchPOJO);
		if (CollectionUtils.isEmpty(ecWxCardBasePOJOs)) {
			int result = ecWxCardBaseMapper.insert(ecWxCardBasePOJO);
			logger.info("initEcWxCardBase成功: {}", ecWxCardBasePOJO);
			return ecWxCardBasePOJO;
		}
		// have one and only one
		ret = ecWxCardBasePOJOs.get(0);
		logger.info("initEcWxCardBase成功, 已经存在不需要初始化: {}", ret);
		return ret;
	}

}
