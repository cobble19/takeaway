package com.cobble.takeaway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.InteractiveMapper;
import com.cobble.takeaway.dao.WxRespMsgMapper;
import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.InteractivePOJO;
import com.cobble.takeaway.pojo.InteractiveSearchPOJO;
import com.cobble.takeaway.pojo.RelInteractiveUserPOJO;
import com.cobble.takeaway.pojo.weixin.WxRespMsgPOJO;
import com.cobble.takeaway.pojo.weixin.WxRespMsgSearchPOJO;
import com.cobble.takeaway.service.ActivityService;
import com.cobble.takeaway.service.InteractiveService;
import com.cobble.takeaway.util.CommonConstant;

@Service
public class InteractiveServiceImpl implements InteractiveService {
	
	@Autowired
	private InteractiveMapper interactiveMapper;
	@Autowired
	private WxRespMsgMapper wxRespMsgMapper;
	@Autowired
	private ActivityService activityService;

	@Override
	public int insert(InteractivePOJO interactivePOJO, Long userId) throws Exception {
		int ret = 0;

		ActivityPOJO activityPOJO = new ActivityPOJO();
		activityPOJO.setTitle(interactivePOJO.getName());
		activityPOJO.setContent(interactivePOJO.getContent());
		activityPOJO.setStartDateTime(interactivePOJO.getStartDateTime());
		activityPOJO.setEndDateTime(interactivePOJO.getEndDateTime());
		activityPOJO.setTypeCode(1);
		activityPOJO.setPublishType(1);
		activityPOJO.setNeedSubscribe(0);
		activityPOJO.setUserIdEnterprise(userId);
		activityService.insert(activityPOJO, userId);
		
		interactivePOJO.setActivityId(activityPOJO.getActivityId());
		interactivePOJO.setUserId(userId);

		ret = interactiveMapper.insert(interactivePOJO);
		RelInteractiveUserPOJO relInteractiveUserPOJO = new RelInteractiveUserPOJO();
		relInteractiveUserPOJO.setInteractiveId(interactivePOJO.getInteractiveId());
		relInteractiveUserPOJO.setUserId(userId);
		interactiveMapper.insertRelInteractiveUser(relInteractiveUserPOJO);
		
		return ret;
	}

	@Override
	public int update(InteractivePOJO interactivePOJO) throws Exception {
		int ret = 0;
		ret = interactiveMapper.update(interactivePOJO);
		return ret;
	}

	@Override
	public List<InteractivePOJO> finds(
			InteractiveSearchPOJO interactiveSearchPOJO) throws Exception {
		List<InteractivePOJO> ret = null;
		ret = interactiveMapper.finds(interactiveSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(InteractiveSearchPOJO interactiveSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = interactiveMapper.getCount(interactiveSearchPOJO);
		return ret;
	}

	@Override
	public InteractivePOJO findById(Long id) throws Exception {
		InteractivePOJO ret = null;
		ret = interactiveMapper.findById(id);

		// 获取每个互动活动对应的回复关键字
		WxRespMsgSearchPOJO wxRespMsgSearchPOJO = new WxRespMsgSearchPOJO();
		if (ret != null) {
			List<Long> interactiveIds = new ArrayList<Long>();
			interactiveIds.add(ret.getInteractiveId());
			
			wxRespMsgSearchPOJO.setInteractiveIds(interactiveIds);

			List<WxRespMsgPOJO> wxRespMsgPOJOs = wxRespMsgMapper.finds(wxRespMsgSearchPOJO);
			if (CollectionUtils.isNotEmpty(wxRespMsgPOJOs)) {
				for (WxRespMsgPOJO wxRespMsgPOJO : wxRespMsgPOJOs) {
					if (wxRespMsgPOJO != null && wxRespMsgPOJO.getMsgSend() != null 
							&& wxRespMsgPOJO.getMsgSend().equalsIgnoreCase(ret.getInteractiveId() + "")) {
						ret.setWxRespMsgPOJO(wxRespMsgPOJO);
						break;
					}
				}
			}
		}
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = interactiveMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += interactiveMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public List<InteractivePOJO> findActives(
			InteractiveSearchPOJO interactiveSearchPOJO) throws Exception {
		List<InteractivePOJO> ret = interactiveMapper.findActives(interactiveSearchPOJO);
		return ret;
	}

	@Override
	public int getActiveCount(InteractiveSearchPOJO interactiveSearchPOJO)
			throws Exception {
		int ret = interactiveMapper.getActiveCount(interactiveSearchPOJO);
		return ret;
	}

	@Override
	public int updateStatus(InteractivePOJO interactivePOJO) throws Exception {
		int ret = interactiveMapper.updateStatus(interactivePOJO);
		return ret;
	}

	@Override
	public List<InteractivePOJO> find4Enterprises(
			InteractiveSearchPOJO interactiveSearchPOJO) throws Exception {
		List<InteractivePOJO> ret = null;
		ret = interactiveMapper.find4Enterprises(interactiveSearchPOJO);
		return ret;
	}

	@Override
	public List<InteractivePOJO> findFulls(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception {
		List<InteractivePOJO> ret = null;
		List<InteractivePOJO> interactivePOJOs = interactiveMapper.finds(interactiveSearchPOJO);
		
		// 获取每个互动活动对应的回复关键字
		WxRespMsgSearchPOJO wxRespMsgSearchPOJO = new WxRespMsgSearchPOJO();
		if (CollectionUtils.isNotEmpty(interactivePOJOs)) {
			List<Long> interactiveIds = new ArrayList<Long>();
			for (InteractivePOJO interactivePOJO : interactivePOJOs) {
				interactiveIds.add(interactivePOJO.getInteractiveId());
			}
			wxRespMsgSearchPOJO.setInteractiveIds(interactiveIds);

			List<WxRespMsgPOJO> wxRespMsgPOJOs = wxRespMsgMapper.finds(wxRespMsgSearchPOJO);
			if (CollectionUtils.isNotEmpty(interactivePOJOs) && CollectionUtils.isNotEmpty(wxRespMsgPOJOs)) {
				for (InteractivePOJO interactivePOJO : interactivePOJOs) {
					for (WxRespMsgPOJO wxRespMsgPOJO : wxRespMsgPOJOs) {
						if (wxRespMsgPOJO != null && wxRespMsgPOJO.getMsgSend() != null 
								&& wxRespMsgPOJO.getMsgSend().equalsIgnoreCase(interactivePOJO.getInteractiveId() + "")) {
							interactivePOJO.setWxRespMsgPOJO(wxRespMsgPOJO);
							break;
						}
					}
				}
			}
		}
		ret = interactivePOJOs;
		
		return ret;
	}

}
