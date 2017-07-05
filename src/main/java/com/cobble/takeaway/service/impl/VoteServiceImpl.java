package com.cobble.takeaway.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.dao.VoteMapper;
import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.Apply2AttrPOJO;
import com.cobble.takeaway.pojo.Apply2POJO;
import com.cobble.takeaway.pojo.Apply2SearchPOJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.RelVoteUserPOJO;
import com.cobble.takeaway.pojo.RelVoteUserSearchPOJO;
import com.cobble.takeaway.pojo.VoteItemPOJO;
import com.cobble.takeaway.pojo.VoteItemSearchPOJO;
import com.cobble.takeaway.pojo.VotePOJO;
import com.cobble.takeaway.pojo.VoteSearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserPOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserSearchPOJO;
import com.cobble.takeaway.service.ActivityService;
import com.cobble.takeaway.service.Apply2Service;
import com.cobble.takeaway.service.RelVoteUserService;
import com.cobble.takeaway.service.VoteItemService;
import com.cobble.takeaway.service.VoteService;
import com.cobble.takeaway.service.WxPersonUserService;
import com.cobble.takeaway.util.CollectionUtilx;
import com.cobble.takeaway.util.UserUtil;

@Service
public class VoteServiceImpl implements VoteService {
	private final static Logger logger = LoggerFactory.getLogger(VoteServiceImpl.class);
	
	@Autowired
	private VoteMapper voteMapper;
	@Autowired
	private ActivityService activityService;

	@Autowired
	private VoteItemService voteItemService;
	@Autowired
	private Apply2Service apply2Service;
	@Autowired
	private RelVoteUserService relVoteUserService;
	@Autowired
	private WxPersonUserService wxPersonUserService;

	@Override
	public int insert(VotePOJO votePOJO) throws Exception {
		int ret = 0;
		Long userId = UserUtil.getCurrentUserId();
		ActivityPOJO activityPOJO = new ActivityPOJO();
		activityPOJO.setTitle(votePOJO.getTitle());
		activityPOJO.setContent(votePOJO.getContent());
//		activityPOJO.setStartDateTime(startDateTime);
//		activityPOJO.setEndDateTime(endDateTime);
		activityPOJO.setTypeCode(1);
		activityPOJO.setPublishType(1);
		activityPOJO.setNeedSubscribe(0);
		activityPOJO.setUserIdEnterprise(votePOJO.getUserId());
		
		activityService.insert(activityPOJO, userId);
		votePOJO.setActivityId(activityPOJO.getActivityId());

		ret = voteMapper.insert(votePOJO);
		return ret;
	}

	@Override
	public int update(VotePOJO votePOJO) throws Exception {
		int ret = 0;
		ret = voteMapper.update(votePOJO);
		return ret;
	}

	@Override
	public List<VotePOJO> finds(
			VoteSearchPOJO voteSearchPOJO) throws Exception {
		List<VotePOJO> ret = null;
		ret = voteMapper.finds(voteSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(VoteSearchPOJO voteSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = voteMapper.getCount(voteSearchPOJO);
		return ret;
	}

	@Override
	public VotePOJO findById(Long id) throws Exception {
		VotePOJO ret = null;
		ret = voteMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = voteMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += voteMapper.deleteById(id);
			}
		}
		return ret;
	}
	
	// 根据
	public Map findCurrentVoteItem(VoteItemSearchPOJO voteItemSearchPOJO, VotePOJO votePOJO) throws Exception {
		Map ret = new HashMap();
		Apply2POJO apply2POJORet = null;
		VoteItemPOJO voteItemPOJORet = null;
		WxPersonUserPOJO wxPersonUserPOJORet = null;
		try {
			Long voteId = votePOJO.getVoteId(); 
			Long activityId = votePOJO.getActivityId();
			String apply2AttrModelIdsStr = votePOJO.getApply2AttrModelIds();
			Integer period = votePOJO.getPeriod();
			Integer numOfPeriod = votePOJO.getNumOfPeriod();
			
			Long voteItemId = voteItemSearchPOJO.getVoteItemId();
			Long userId = voteItemSearchPOJO.getUserId();
			Integer start = voteItemSearchPOJO.getStart();
			Integer limit = voteItemSearchPOJO.getLimit();
			String sort = voteItemSearchPOJO.getSort();
			String orderBy = voteItemSearchPOJO.getOrderBy();
			Boolean paginationFlag = voteItemSearchPOJO.getPaginationFlage();
			
			// 显示哪些报名属性
			List<Long> apply2AttrModelIdList = CollectionUtilx.string2Longs(apply2AttrModelIdsStr);
			
			if (voteId == null) {
				throw new Exception("voteId can't is NULL.");
			}
			int result = -1;
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			
			// 获取wxPersonUser
			WxPersonUserSearchPOJO wxPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
			wxPersonUserSearchPOJO.setUserId(userId);
			wxPersonUserSearchPOJO.setPaginationFlage(false);
			List<WxPersonUserPOJO> wxPersonUserPOJOs = wxPersonUserService.finds(wxPersonUserSearchPOJO);
			
			if (CollectionUtils.isNotEmpty(wxPersonUserPOJOs)) {
				wxPersonUserPOJORet = wxPersonUserPOJOs.get(0);
			}
			
			// 获取Apply2
			Apply2SearchPOJO apply2SearchPOJO = new Apply2SearchPOJO();
			apply2SearchPOJO.setActivityId(activityId);
			apply2SearchPOJO.setUserId(userId);
//			apply2SearchPOJO.setApply2Ids(apply2Ids);
			apply2SearchPOJO.setPaginationFlage(false);
			// 获取apply2和apply2Attr
			List<Apply2POJO> apply2POJOs = apply2Service.finds2ByActivityId(apply2SearchPOJO);
			if (CollectionUtils.isNotEmpty(apply2POJOs)) {
				apply2POJORet = apply2POJOs.get(0);
				
				if (CollectionUtils.isNotEmpty(apply2AttrModelIdList)) {
					List<Apply2AttrPOJO> apply2AttrPOJOs = apply2POJORet.getApply2AttrPOJOs();
					if (CollectionUtils.isNotEmpty(apply2AttrPOJOs)) {
						Iterator<Apply2AttrPOJO> it = apply2AttrPOJOs.iterator();
						while (it.hasNext()) {
							Apply2AttrPOJO apply2AttrPOJO = it.next();
							if (!apply2AttrModelIdList.contains(apply2AttrPOJO.getApply2AttrModelId())) {
								it.remove();
							}
						}
					}
				}
			}
			
			if (apply2POJORet != null) {
				// 获取voteItem
				VoteItemSearchPOJO voteItemSearchPOJO1 = new VoteItemSearchPOJO();
				voteItemSearchPOJO1.setVoteId(voteId);
//				voteItemSearchPOJO1.setVoteItemId(voteItemId);
//				voteItemSearchPOJO1.setStart(start);
//				voteItemSearchPOJO1.setLimit(limit);
				voteItemSearchPOJO1.setPaginationFlage(false);
//				voteItemSearchPOJO1.setSort(sort);
//				voteItemSearchPOJO1.setOrderBy(orderBy);
				voteItemSearchPOJO1.setApply2Id(apply2POJORet.getApply2Id());
				List<VoteItemPOJO> voteItemPOJOsTemp = voteItemService.finds(voteItemSearchPOJO1);
				if (CollectionUtils.isNotEmpty(voteItemPOJOsTemp)) {
					voteItemPOJORet = voteItemPOJOsTemp.get(0);
					voteItemPOJORet.setWxPersonUserPOJO(wxPersonUserPOJORet);
				}
			}
			
			
//			ret.put("votePOJO", votePOJO);
			ret.put("apply2POJO", apply2POJORet);
			ret.put("voteItemPOJO", voteItemPOJORet);
//			ret.put("wxPersonPOJORet", wxPersonUserPOJORet);
			
		} catch (Exception e) {
			logger.error("error.", e);
			throw e;
		}
		
		
		return ret;
	}
		
	// 根据 voteItemSearchPOJO and votePOJO获取voteItems
	public DataTablesPOJO<VoteItemPOJO> findVoteItems(VoteItemSearchPOJO voteItemSearchPOJO, VotePOJO votePOJO) throws Exception {
		DataTablesPOJO<VoteItemPOJO> ret = new DataTablesPOJO<VoteItemPOJO>();
//		Apply2POJO apply2POJORet = null;
//		VoteItemPOJO voteItemPOJORet = null;
		try {
			Long voteId = votePOJO.getVoteId(); 
			Long activityId = votePOJO.getActivityId();
			String apply2AttrModelIdsStr = votePOJO.getApply2AttrModelIds();
			Integer period = votePOJO.getPeriod();
			Integer numOfPeriod = votePOJO.getNumOfPeriod();
			
			Long voteItemId = voteItemSearchPOJO.getVoteItemId();
			Long userId = voteItemSearchPOJO.getUserId();
			Integer start = voteItemSearchPOJO.getStart();
			Integer limit = voteItemSearchPOJO.getLimit();
			String sort = voteItemSearchPOJO.getSort();
			String orderBy = voteItemSearchPOJO.getOrderBy();
			Boolean paginationFlag = voteItemSearchPOJO.getPaginationFlage();
			Integer approveFlag = voteItemSearchPOJO.getApproveFlag();
			
			// 显示哪些报名属性
			List<Long> apply2AttrModelIdList = CollectionUtilx.string2Longs(apply2AttrModelIdsStr);
			
			if (voteId == null) {
				throw new Exception("voteId can't is NULL.");
			}
			int result = -1;
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			
			
			// 获取voteItem
//			List<VoteItemPOJO> voteItemPOJOsTemp = voteItemService.findsByVoteId(voteId);
			VoteItemSearchPOJO voteItemSearchPOJO1 = new VoteItemSearchPOJO();
			voteItemSearchPOJO1.setVoteId(voteId);
			voteItemSearchPOJO1.setVoteItemId(voteItemId);
			voteItemSearchPOJO1.setStart(start);
			voteItemSearchPOJO1.setLimit(limit);
			voteItemSearchPOJO1.setPaginationFlage(paginationFlag);
			voteItemSearchPOJO1.setSort(sort);
			voteItemSearchPOJO1.setOrderBy(orderBy);
			voteItemSearchPOJO1.setApproveFlag(approveFlag);
			List<VoteItemPOJO> voteItemPOJOsTemp = voteItemService.finds(voteItemSearchPOJO1);
			
			List<VoteItemPOJO> voteItemPOJOs = new ArrayList<VoteItemPOJO>();
			
			if (CollectionUtils.isNotEmpty(voteItemPOJOsTemp) && voteItemId != null) {
				Iterator<VoteItemPOJO> it = voteItemPOJOsTemp.iterator();
				while (it.hasNext()) {
					VoteItemPOJO voteItemPOJO = it.next();
					try {
						if (voteItemId.longValue() == voteItemPOJO.getVoteItemId().longValue()) {
							voteItemPOJOs.add(voteItemPOJO);
							break;
						}
					} catch (Exception e) {
						logger.error("voteItemId is exception: {}" + e);
					}
				}
			} else {
				voteItemPOJOs = voteItemPOJOsTemp;
			}
			
			if (CollectionUtils.isEmpty(voteItemPOJOs)) {
				return null;
			}
			
			// 获取每个voteItemPOJO对应的apply2/apply2attr
			List<Long> apply2Ids = new ArrayList<Long>();
			for (VoteItemPOJO voteItemPOJO : voteItemPOJOs) {
				apply2Ids.add(voteItemPOJO.getApply2Id());
			}
			
			// 获取当前用户的投票的voteItem

			if (numOfPeriod == null) {
				numOfPeriod = 1;
			}
			
			RelVoteUserSearchPOJO relVoteUserSearchPOJO = new RelVoteUserSearchPOJO();
			relVoteUserSearchPOJO.setUserId(userId);
			relVoteUserSearchPOJO.setVoteId(voteId);
			if (period != null && period.intValue() > 0) {

				Date curDateTime = new Date();
				
				Date startDateTime = DateUtils.truncate(curDateTime, Calendar.DATE);
				Date endDateTime = DateUtils.addSeconds(startDateTime, period.intValue() * 24 * 60 * 60 - 1);
				
				relVoteUserSearchPOJO.setStartDateTime(startDateTime);
				relVoteUserSearchPOJO.setEndDateTime(endDateTime);
			}
//			Integer totalHasAddVote = relVoteUserService.getCount(relVoteUserSearchPOJO);
			// 当前用户投票情况
			List<RelVoteUserPOJO> relVoteUserPOJOs = relVoteUserService.finds(relVoteUserSearchPOJO);
			
			// 获取apply2
			Apply2SearchPOJO apply2SearchPOJO = new Apply2SearchPOJO();
			apply2SearchPOJO.setActivityId(activityId);
			apply2SearchPOJO.setApply2Ids(apply2Ids);
			apply2SearchPOJO.setPaginationFlage(false);
			// 获取apply2和apply2Attr
			List<Apply2POJO> apply2POJOs = apply2Service.finds2ByActivityId(apply2SearchPOJO);
			
			// userIds
			List<Long> userIds = new ArrayList<Long>();
			List<WxPersonUserPOJO> wxPersonUserPOJOs = new ArrayList<WxPersonUserPOJO>();
			Map<Long, WxPersonUserPOJO> wxPersonUserPOJOMap = new HashMap<Long, WxPersonUserPOJO>();
			
			if (CollectionUtils.isNotEmpty(apply2POJOs)) {
				// all userIds
				for (Apply2POJO apply2POJO : apply2POJOs) {
					userIds.add(apply2POJO.getUserId());
				}

				// 等下把所有的wxPersonUser查出来, 不用每次查询数据库
				WxPersonUserSearchPOJO wxPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
				wxPersonUserSearchPOJO.setUserIds(userIds);
				wxPersonUserSearchPOJO.setPaginationFlage(false);
				wxPersonUserPOJOs = wxPersonUserService.finds(wxPersonUserSearchPOJO);
				if (CollectionUtils.isNotEmpty(wxPersonUserPOJOs)) {
					for (WxPersonUserPOJO wxPersonUserPOJO : wxPersonUserPOJOs) {
						if (wxPersonUserPOJO.getUserId() != null) {
							wxPersonUserPOJOMap.put(wxPersonUserPOJO.getUserId().longValue(), wxPersonUserPOJO);
						}
					}
				}
				
				for (Apply2POJO apply2POJO : apply2POJOs) {
					// 获取当前登录用户的apply2POJO
//					if (apply2POJO.getUserId() != null && userId != null) {
//						if (apply2POJO.getUserId() == userId.longValue()) {
//							apply2POJORet = apply2POJO;
//						}
//					}
					// 过滤掉不需要显示的apply2attrmodel
					if (CollectionUtils.isNotEmpty(apply2AttrModelIdList)) {
						List<Apply2AttrPOJO> apply2AttrPOJOs = apply2POJO.getApply2AttrPOJOs();
						if (CollectionUtils.isNotEmpty(apply2AttrPOJOs)) {
							Iterator<Apply2AttrPOJO> it = apply2AttrPOJOs.iterator();
							while (it.hasNext()) {
								Apply2AttrPOJO apply2AttrPOJO = it.next();
								if (!apply2AttrModelIdList.contains(apply2AttrPOJO.getApply2AttrModelId())) {
									it.remove();
								}
							}
						}
					}
					// apply2 set into voteItem, 用所有的voteItemPOJOsTemp
					if (CollectionUtils.isNotEmpty(voteItemPOJOsTemp)) {
						for (VoteItemPOJO voteItemPOJO : voteItemPOJOsTemp) {
							
							// 是否被当前用户所投票
							if (CollectionUtils.isNotEmpty(relVoteUserPOJOs)) {
								for (RelVoteUserPOJO relVoteUserPOJO : relVoteUserPOJOs) {
									if (relVoteUserPOJO.getVoteItemId() != null && voteItemPOJO.getVoteItemId() != null) {
										if (relVoteUserPOJO.getVoteItemId().longValue() == voteItemPOJO.getVoteItemId().longValue()) {
											voteItemPOJO.setBeenVoted(true);
											break;
										}
									}
								}
							}
							
							if (voteItemPOJO.getApply2Id() != null && apply2POJO.getApply2Id() != null) {
								if (voteItemPOJO.getApply2Id().longValue() == apply2POJO.getApply2Id().longValue()
										&& apply2POJO.getUserId() != null) {
									// get wxPersonUserPOJO
									WxPersonUserPOJO wxPersonUserPOJO = wxPersonUserPOJOMap.get(apply2POJO.getUserId().longValue());
									
									voteItemPOJO.setApply2POJO(apply2POJO);
									voteItemPOJO.setWxPersonUserPOJO(wxPersonUserPOJO);
									// 当前登录用户
									/*if (apply2POJO.getUserId() != null && userId != null) {
										if (apply2POJO.getUserId() == userId.longValue()) {
											apply2POJORet = apply2POJO;
//											apply2POJORet.setVoteItemPOJO(voteItemPOJO); 
											voteItemPOJORet = voteItemPOJO;
										}
									}*/
									
									break;
								}
							}
						}
					}
					
				}
			}
			
			/*if (CollectionUtils.isNotEmpty(apply2POJOs) && CollectionUtils.isNotEmpty(voteItemPOJOs)) {
				for (Apply2POJO apply2POJO : apply2POJOs) {
					// apply2 set into voteItem
					for (VoteItemPOJO voteItemPOJO : voteItemPOJOs) {
						if (voteItemPOJO.getApply2Id().longValue() == apply2POJO.getApply2Id().longValue()) {
							voteItemPOJO.setApply2POJO(apply2POJO);
							break;
						}
					}
				}
			}*/

			// get current wxPersonUserPOJO
//			WxPersonUserSearchPOJO wxPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
//			wxPersonUserSearchPOJO.setUserId(userId);
//			List<WxPersonUserPOJO> wxPersonUserPOJOs = wxPersonUserService.finds(wxPersonUserSearchPOJO);
			/*if (CollectionUtils.isNotEmpty(wxPersonUserPOJOs)) {
				WxPersonUserPOJO wxPersonUserPOJO = wxPersonUserPOJOMap.get(userId.longValue());
				ret.put("wxPersonUserPOJO", wxPersonUserPOJO);
			}*/
			
			/*ret.put("votePOJO", votePOJO);
			ret.put("apply2POJO", apply2POJORet);
			ret.put("voteItemPOJO", voteItemPOJORet);*/
			
//			ret = voteItemPOJOs;
			ret.setData(voteItemPOJOs);
			ret.setSuccess(true);
			ret.setRecordsTotal(voteItemPOJOs.size());
			ret.setStart(start);
			ret.setLimit(limit);
		} catch (Exception e) {
			logger.error("error.", e);
			throw e;
		}
		
		
		return ret;
	}

	/**
	 * 分页, 用voteSearchPOJO的参数
	 * , 这个分页参数不是用来查询vote的
	 * , 而是用来查询voteItem的
	 * @param voteSearchPOJO
	 * @return
	 * @throws Exception
	 */
	public Map listVoteById4UnifiedBootstrap(VoteSearchPOJO voteSearchPOJO) throws Exception {
		Map ret = new HashMap();
		VotePOJO votePOJO = new VotePOJO();
		Apply2POJO apply2POJORet = null;
		VoteItemPOJO voteItemPOJORet = null;
		try {
			Long voteId = voteSearchPOJO.getVoteId(); 
			Long activityId = voteSearchPOJO.getActivityId();
			String activityTitle = voteSearchPOJO.getActivityTitle();
			Long voteItemId = voteSearchPOJO.getVoteItemId();
			Long userId = voteSearchPOJO.getUserId();
			Integer start = voteSearchPOJO.getStart();
			Integer limit = voteSearchPOJO.getLimit();
			
			if (voteId == null) {
				throw new Exception("voteId can't is NULL.");
			}
			int result = -1;
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			// 获取vote
			votePOJO = this.findById(voteId);
			
			if (votePOJO == null) {
				throw new Exception("投票活动无效, voteId: " + voteId);
			}
			
			// 确定activityId
			Long activityIdFromVote = votePOJO.getActivityId();
			if (activityId.longValue() != activityIdFromVote.longValue()) {
				activityId = activityIdFromVote;
			}
			
			// 显示哪些报名属性
			String apply2AttrModelIds = votePOJO.getApply2AttrModelIds();
			List<Long> apply2AttrModelIdList = CollectionUtilx.string2Longs(apply2AttrModelIds);
			
			// 获取voteItem
//			List<VoteItemPOJO> voteItemPOJOsTemp = voteItemService.findsByVoteId(voteId);
			VoteItemSearchPOJO voteItemSearchPOJO = new VoteItemSearchPOJO();
			voteItemSearchPOJO.setVoteId(voteId);
			voteItemSearchPOJO.setVoteItemId(voteItemId);
			voteItemSearchPOJO.setStart(start);
			voteItemSearchPOJO.setLimit(limit);
			List<VoteItemPOJO> voteItemPOJOsTemp = voteItemService.finds(voteItemSearchPOJO);
			
			List<VoteItemPOJO> voteItemPOJOs = new ArrayList<VoteItemPOJO>();
			
			if (CollectionUtils.isNotEmpty(voteItemPOJOsTemp) && voteItemId != null) {
				Iterator<VoteItemPOJO> it = voteItemPOJOsTemp.iterator();
				while (it.hasNext()) {
					VoteItemPOJO voteItemPOJO = it.next();
					if (voteItemId.longValue() == voteItemPOJO.getVoteItemId().longValue()) {
						voteItemPOJOs.add(voteItemPOJO);
						break;
					}
				}
			} else {
				voteItemPOJOs = voteItemPOJOsTemp;
			}
			
			votePOJO.setVoteItemPOJOs(voteItemPOJOs);
			
			// 获取当前用户的投票的voteItem

			Integer period = votePOJO.getPeriod();
			Integer numOfPeriod = votePOJO.getNumOfPeriod();
			if (numOfPeriod == null) {
				numOfPeriod = 1;
			}
			
			RelVoteUserSearchPOJO relVoteUserSearchPOJO = new RelVoteUserSearchPOJO();
			relVoteUserSearchPOJO.setUserId(userId);
			relVoteUserSearchPOJO.setVoteId(voteId);
			if (period != null && period.intValue() > 0) {

				Date curDateTime = new Date();
				
				Date startDateTime = DateUtils.truncate(curDateTime, Calendar.DATE);
				Date endDateTime = DateUtils.addSeconds(startDateTime, period.intValue() * 24 * 60 * 60 - 1);
				
				relVoteUserSearchPOJO.setStartDateTime(startDateTime);
				relVoteUserSearchPOJO.setEndDateTime(endDateTime);
			}
//			Integer totalHasAddVote = relVoteUserService.getCount(relVoteUserSearchPOJO);
			// 当前用户投票情况
			List<RelVoteUserPOJO> relVoteUserPOJOs = relVoteUserService.finds(relVoteUserSearchPOJO);
			
			// 获取apply2
			Apply2SearchPOJO apply2SearchPOJO = new Apply2SearchPOJO();
			apply2SearchPOJO.setActivityId(activityId);
			List<Apply2POJO> apply2POJOs = apply2Service.finds2ByActivityId(apply2SearchPOJO);
			
			// userIds
			List<Long> userIds = new ArrayList<Long>();
			List<WxPersonUserPOJO> wxPersonUserPOJOs = new ArrayList<WxPersonUserPOJO>();
			Map<Long, WxPersonUserPOJO> wxPersonUserPOJOMap = new HashMap<Long, WxPersonUserPOJO>();
			
			if (CollectionUtils.isNotEmpty(apply2POJOs)) {
				// all userIds
				for (Apply2POJO apply2POJO : apply2POJOs) {
					userIds.add(apply2POJO.getUserId());
				}

				// 等下把所有的wxPersonUser查出来, 不用每次查询数据库
				WxPersonUserSearchPOJO wxPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
				wxPersonUserSearchPOJO.setUserIds(userIds);
				wxPersonUserSearchPOJO.setPaginationFlage(false);
				wxPersonUserPOJOs = wxPersonUserService.finds(wxPersonUserSearchPOJO);
				if (CollectionUtils.isNotEmpty(wxPersonUserPOJOs)) {
					for (WxPersonUserPOJO wxPersonUserPOJO : wxPersonUserPOJOs) {
						wxPersonUserPOJOMap.put(wxPersonUserPOJO.getUserId().longValue(), wxPersonUserPOJO);
					}
				}
				
				for (Apply2POJO apply2POJO : apply2POJOs) {
					// 获取当前登录用户的apply2POJO
					if (apply2POJO.getUserId() != null && userId != null) {
						if (apply2POJO.getUserId() == userId.longValue()) {
							apply2POJORet = apply2POJO;
						}
					}
					// 过滤掉不需要显示的apply2attrmodel
					if (CollectionUtils.isNotEmpty(apply2AttrModelIdList)) {
						List<Apply2AttrPOJO> apply2AttrPOJOs = apply2POJO.getApply2AttrPOJOs();
						if (CollectionUtils.isNotEmpty(apply2AttrPOJOs)) {
							Iterator<Apply2AttrPOJO> it = apply2AttrPOJOs.iterator();
							while (it.hasNext()) {
								Apply2AttrPOJO apply2AttrPOJO = it.next();
								if (!apply2AttrModelIdList.contains(apply2AttrPOJO.getApply2AttrModelId())) {
									it.remove();
								}
							}
						}
					}
					// apply2 set into voteItem, 用所有的voteItemPOJOsTemp
					if (CollectionUtils.isNotEmpty(voteItemPOJOsTemp)) {
						for (VoteItemPOJO voteItemPOJO : voteItemPOJOsTemp) {
							
							if (CollectionUtils.isNotEmpty(relVoteUserPOJOs)) {
								for (RelVoteUserPOJO relVoteUserPOJO : relVoteUserPOJOs) {
									if (relVoteUserPOJO.getVoteItemId().longValue() == voteItemPOJO.getVoteItemId().longValue()) {
										voteItemPOJO.setBeenVoted(true);
										break;
									}
								}
							}
							
							if (voteItemPOJO.getApply2Id().longValue() == apply2POJO.getApply2Id().longValue()) {
								// get wxPersonUserPOJO
								WxPersonUserPOJO wxPersonUserPOJO = wxPersonUserPOJOMap.get(apply2POJO.getUserId().longValue());
								
								voteItemPOJO.setApply2POJO(apply2POJO);
								voteItemPOJO.setWxPersonUserPOJO(wxPersonUserPOJO);
								// 当前登录用户
								if (apply2POJO.getUserId() != null && userId != null) {
									if (apply2POJO.getUserId() == userId.longValue()) {
										apply2POJORet = apply2POJO;
//										apply2POJORet.setVoteItemPOJO(voteItemPOJO); 
										voteItemPOJORet = voteItemPOJO;
									}
								}
								
								break;
							}
						}
					}
					
				}
			}
			
			/*if (CollectionUtils.isNotEmpty(apply2POJOs) && CollectionUtils.isNotEmpty(voteItemPOJOs)) {
				for (Apply2POJO apply2POJO : apply2POJOs) {
					// apply2 set into voteItem
					for (VoteItemPOJO voteItemPOJO : voteItemPOJOs) {
						if (voteItemPOJO.getApply2Id().longValue() == apply2POJO.getApply2Id().longValue()) {
							voteItemPOJO.setApply2POJO(apply2POJO);
							break;
						}
					}
				}
			}*/

			// get current wxPersonUserPOJO
//			WxPersonUserSearchPOJO wxPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
//			wxPersonUserSearchPOJO.setUserId(userId);
//			List<WxPersonUserPOJO> wxPersonUserPOJOs = wxPersonUserService.finds(wxPersonUserSearchPOJO);
			if (CollectionUtils.isNotEmpty(wxPersonUserPOJOs)) {
				WxPersonUserPOJO wxPersonUserPOJO = wxPersonUserPOJOMap.get(userId.longValue());
				ret.put("wxPersonUserPOJO", wxPersonUserPOJO);
			}
			
			ret.put("votePOJO", votePOJO);
			ret.put("apply2POJO", apply2POJORet);
			ret.put("voteItemPOJO", voteItemPOJORet);
		} catch (Exception e) {
			logger.error("error.", e);
			throw e;
		}
		
		
		return ret;
	}
	

	public Map listVoteById4UnifiedBootstrap(Long voteId, 
			Long activityId,
			String activityTitle,
			Long voteItemId,
			Long userId) throws Exception {
		Map ret = new HashMap();
		VotePOJO votePOJO = new VotePOJO();
		Apply2POJO apply2POJORet = null;
		VoteItemPOJO voteItemPOJORet = null;
		try {
			if (voteId == null) {
				throw new Exception("voteId can't is NULL.");
			}
			int result = -1;
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			// 获取vote
			votePOJO  = this.findById(voteId);
			
			if (votePOJO == null) {
				throw new Exception("投票活动无效, voteId: " + voteId);
			}
			
			Long activityIdFromVote = votePOJO.getActivityId();
			
			if (activityId.longValue() != activityIdFromVote.longValue()) {
				activityId = activityIdFromVote;
			}
			
			String apply2AttrModelIds = votePOJO.getApply2AttrModelIds();
			List<Long> apply2AttrModelIdList = CollectionUtilx.string2Longs(apply2AttrModelIds);
			
			// 获取voteItem
			List<VoteItemPOJO> voteItemPOJOsTemp = voteItemService.findsByVoteId(voteId);
			
			List<VoteItemPOJO> voteItemPOJOs = new ArrayList<VoteItemPOJO>();
			
			if (CollectionUtils.isNotEmpty(voteItemPOJOsTemp) && voteItemId != null) {
				Iterator<VoteItemPOJO> it = voteItemPOJOsTemp.iterator();
				while (it.hasNext()) {
					VoteItemPOJO voteItemPOJO = it.next();
					if (voteItemId.longValue() == voteItemPOJO.getVoteItemId().longValue()) {
						voteItemPOJOs.add(voteItemPOJO);
						break;
					}
				}
			} else {
				voteItemPOJOs = voteItemPOJOsTemp;
			}
			
			votePOJO.setVoteItemPOJOs(voteItemPOJOs);
			
			// 获取当前用户的投票的voteItem

			Integer period = votePOJO.getPeriod();
			Integer numOfPeriod = votePOJO.getNumOfPeriod();
			if (numOfPeriod == null) {
				numOfPeriod = 1;
			}
			
			RelVoteUserSearchPOJO relVoteUserSearchPOJO = new RelVoteUserSearchPOJO();
			relVoteUserSearchPOJO.setUserId(userId);
			relVoteUserSearchPOJO.setVoteId(voteId);
			if (period != null && period.intValue() > 0) {

				Date curDateTime = new Date();
				
				Date startDateTime = DateUtils.truncate(curDateTime, Calendar.DATE);
				Date endDateTime = DateUtils.addSeconds(startDateTime, period.intValue() * 24 * 60 * 60 - 1);
				
				relVoteUserSearchPOJO.setStartDateTime(startDateTime);
				relVoteUserSearchPOJO.setEndDateTime(endDateTime);
			}
//			Integer totalHasAddVote = relVoteUserService.getCount(relVoteUserSearchPOJO);
			// 当前用户投票情况
			List<RelVoteUserPOJO> relVoteUserPOJOs = relVoteUserService.finds(relVoteUserSearchPOJO);
			
			// 获取apply2
			Apply2SearchPOJO apply2SearchPOJO = new Apply2SearchPOJO();
			apply2SearchPOJO.setActivityId(activityId);
			List<Apply2POJO> apply2POJOs = apply2Service.finds2ByActivityId(apply2SearchPOJO);
			
			// userIds
			List<Long> userIds = new ArrayList<Long>();
			List<WxPersonUserPOJO> wxPersonUserPOJOs = new ArrayList<WxPersonUserPOJO>();
			Map<Long, WxPersonUserPOJO> wxPersonUserPOJOMap = new HashMap<Long, WxPersonUserPOJO>();
			
			if (CollectionUtils.isNotEmpty(apply2POJOs)) {
				// all userIds
				for (Apply2POJO apply2POJO : apply2POJOs) {
					userIds.add(apply2POJO.getUserId());
				}

				// 等下把所有的wxPersonUser查出来, 不用每次查询数据库
				WxPersonUserSearchPOJO wxPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
				wxPersonUserSearchPOJO.setUserIds(userIds);
				wxPersonUserSearchPOJO.setPaginationFlage(false);
				wxPersonUserPOJOs = wxPersonUserService.finds(wxPersonUserSearchPOJO);
				if (CollectionUtils.isNotEmpty(wxPersonUserPOJOs)) {
					for (WxPersonUserPOJO wxPersonUserPOJO : wxPersonUserPOJOs) {
						wxPersonUserPOJOMap.put(wxPersonUserPOJO.getUserId().longValue(), wxPersonUserPOJO);
					}
				}
				
				for (Apply2POJO apply2POJO : apply2POJOs) {
					// 获取当前登录用户的apply2POJO
					if (apply2POJO.getUserId() != null && userId != null) {
						if (apply2POJO.getUserId() == userId.longValue()) {
							apply2POJORet = apply2POJO;
						}
					}
					// 过滤掉不需要显示的apply2attrmodel
					if (CollectionUtils.isNotEmpty(apply2AttrModelIdList)) {
						List<Apply2AttrPOJO> apply2AttrPOJOs = apply2POJO.getApply2AttrPOJOs();
						if (CollectionUtils.isNotEmpty(apply2AttrPOJOs)) {
							Iterator<Apply2AttrPOJO> it = apply2AttrPOJOs.iterator();
							while (it.hasNext()) {
								Apply2AttrPOJO apply2AttrPOJO = it.next();
								if (!apply2AttrModelIdList.contains(apply2AttrPOJO.getApply2AttrModelId())) {
									it.remove();
								}
							}
						}
					}
					// apply2 set into voteItem, 用所有的voteItemPOJOsTemp
					if (CollectionUtils.isNotEmpty(voteItemPOJOsTemp)) {
						for (VoteItemPOJO voteItemPOJO : voteItemPOJOsTemp) {
							
							if (CollectionUtils.isNotEmpty(relVoteUserPOJOs)) {
								for (RelVoteUserPOJO relVoteUserPOJO : relVoteUserPOJOs) {
									if (relVoteUserPOJO.getVoteItemId().longValue() == voteItemPOJO.getVoteItemId().longValue()) {
										voteItemPOJO.setBeenVoted(true);
										break;
									}
								}
							}
							
							if (voteItemPOJO.getApply2Id().longValue() == apply2POJO.getApply2Id().longValue()) {
								// get wxPersonUserPOJO
								WxPersonUserPOJO wxPersonUserPOJO = wxPersonUserPOJOMap.get(apply2POJO.getUserId().longValue());
								
								voteItemPOJO.setApply2POJO(apply2POJO);
								voteItemPOJO.setWxPersonUserPOJO(wxPersonUserPOJO);
								// 当前登录用户
								if (apply2POJO.getUserId() != null && userId != null) {
									if (apply2POJO.getUserId() == userId.longValue()) {
										apply2POJORet = apply2POJO;
//										apply2POJORet.setVoteItemPOJO(voteItemPOJO); 
										voteItemPOJORet = voteItemPOJO;
									}
								}
								
								break;
							}
						}
					}
					
				}
			}
			
			/*if (CollectionUtils.isNotEmpty(apply2POJOs) && CollectionUtils.isNotEmpty(voteItemPOJOs)) {
				for (Apply2POJO apply2POJO : apply2POJOs) {
					// apply2 set into voteItem
					for (VoteItemPOJO voteItemPOJO : voteItemPOJOs) {
						if (voteItemPOJO.getApply2Id().longValue() == apply2POJO.getApply2Id().longValue()) {
							voteItemPOJO.setApply2POJO(apply2POJO);
							break;
						}
					}
				}
			}*/

			// get current wxPersonUserPOJO
//			WxPersonUserSearchPOJO wxPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
//			wxPersonUserSearchPOJO.setUserId(userId);
//			List<WxPersonUserPOJO> wxPersonUserPOJOs = wxPersonUserService.finds(wxPersonUserSearchPOJO);
			if (CollectionUtils.isNotEmpty(wxPersonUserPOJOs)) {
				WxPersonUserPOJO wxPersonUserPOJO = wxPersonUserPOJOMap.get(userId.longValue());
				ret.put("wxPersonUserPOJO", wxPersonUserPOJO);
			}
			
			ret.put("votePOJO", votePOJO);
			ret.put("apply2POJO", apply2POJORet);
			ret.put("voteItemPOJO", voteItemPOJORet);
		} catch (Exception e) {
			logger.error("error.", e);
			throw e;
		}
		
		
		return ret;
	}


}
