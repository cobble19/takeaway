package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.Apply2AttrPOJO;
import com.cobble.takeaway.pojo.Apply2POJO;
import com.cobble.takeaway.pojo.Apply2SearchPOJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.RelVoteUserPOJO;
import com.cobble.takeaway.pojo.RelVoteUserSearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.VoteItemPOJO;
import com.cobble.takeaway.pojo.VoteItemSearchPOJO;
import com.cobble.takeaway.pojo.VotePOJO;
import com.cobble.takeaway.pojo.VoteSearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserPOJO;
import com.cobble.takeaway.service.Apply2Service;
import com.cobble.takeaway.service.RelVoteUserService;
import com.cobble.takeaway.service.VoteItemService;
import com.cobble.takeaway.service.VoteService;
import com.cobble.takeaway.util.CollectionUtilx;
import com.cobble.takeaway.util.HttpRequestUtil;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class VoteController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(VoteController.class);
	
	@Autowired
	private VoteService voteService;
	@Autowired
	private VoteItemService voteItemService;
	@Autowired
	private Apply2Service apply2Service;
	@Autowired
	private RelVoteUserService relVoteUserService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	

	@RequestMapping(value = "/web/unified/vote/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO addUnified4Web(VotePOJO votePOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (votePOJO == null) {
				throw new Exception("votePOJO can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			votePOJO.setUserId(userId);
			if (votePOJO.getVoteId() != null) {
				result = voteService.update(votePOJO);
			} else {
				result = voteService.insert(votePOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/web/media/voteDetail?voteId=" + votePOJO.getVoteId();
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}


	@RequestMapping(value = "/api/unified/vote/publishType", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO publishType4Unified(VotePOJO votePOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (votePOJO.getVoteId() == null || votePOJO.getPublishType() == null) {
				throw new Exception("voteId/publishType can't is NULL. voteId: " + votePOJO.getVoteId() + ", publishType: " + votePOJO.getPublishType());
			}

			int result = -1;
			/*Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			result = voteService.update(votePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/media/vote/publishType", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO publishType(VotePOJO votePOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (votePOJO.getVoteId() == null || votePOJO.getPublishType() == null) {
				throw new Exception("voteId/publishType can't is NULL. voteId: " + votePOJO.getVoteId() + ", publishType: " + votePOJO.getPublishType());
			}

			int result = -1;
			/*Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			result = voteService.update(votePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/unified/voteDetail")
	public ModelAndView voteDetail4Unified(@RequestParam(value="voteId") Long voteId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		VotePOJO votePOJO = new VotePOJO();
		try {
			if (voteId == null) {
				throw new Exception("voteId can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			votePOJO  = voteService.findById(voteId);
			List<VoteItemPOJO> voteItemPOJOs = voteItemService.findsByVoteId(voteId);
			if (votePOJO != null) {
				votePOJO.setVoteItemPOJOs(voteItemPOJOs);
			}
			
			ret.addObject("votePOJO", votePOJO);
			ret.setViewName("/page/unified/vote_detail");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		
		return ret;
	}
	@RequestMapping(value = "/web/media/voteDetail")
	public ModelAndView voteDetail(@RequestParam(value="voteId") Long voteId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		VotePOJO votePOJO = new VotePOJO();
		try {
			if (voteId == null) {
				throw new Exception("voteId can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			votePOJO  = voteService.findById(voteId);
			List<VoteItemPOJO> voteItemPOJOs = voteItemService.findsByVoteId(voteId);
			if (votePOJO != null) {
				votePOJO.setVoteItemPOJOs(voteItemPOJOs);
			}
			
			ret.addObject("votePOJO", votePOJO);
			ret.setViewName("/page/media/vote_detail");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		
		return ret;
	}
	@RequestMapping(value = "/api/unified/vote/loadmore/query/{voteId}")
	@ResponseBody
	public DataTablesPOJO<VoteItemPOJO> apiListVoteById4LoadMore(@PathVariable(value="voteId") Long voteId, 
			VoteSearchPOJO voteSearchPOJO,
			Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataTablesPOJO<VoteItemPOJO> ret = new DataTablesPOJO<VoteItemPOJO>();
		VotePOJO votePOJO = null;
//		Apply2POJO apply2POJORet = null;
//		WxPersonUserPOJO wxPersonUserPOJO = null;
		try {
			if (voteId == null) {
				throw new Exception("voteId can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			

			Long activityId = voteSearchPOJO.getActivityId();
			String activityTitle = voteSearchPOJO.getActivityTitle();
			Long voteItemId = voteSearchPOJO.getVoteItemId();
			votePOJO = voteService.findById(voteId);

			if (votePOJO == null) {
				throw new Exception("投票活动无效, voteId: " + voteId);
			}

			/*Long voteId = votePOJO.getVoteId(); 
			Long activityId = votePOJO.getActivityId();*/
//			String apply2AttrModelIdsStr = votePOJO.getApply2AttrModelIds();
//			Integer period = votePOJO.getPeriod();
//			Integer numOfPeriod = votePOJO.getNumOfPeriod();
			
			/*Long voteItemId = voteSearchPOJO.getVoteItemId();
			Long userId = voteSearchPOJO.getUserId();*/
			Integer start = voteSearchPOJO.getStart();
			Integer limit = voteSearchPOJO.getLimit();
			String sort = voteSearchPOJO.getSort();
			String orderBy = voteSearchPOJO.getOrderBy();
			Boolean paginationFlag = voteSearchPOJO.getPaginationFlage();
			Integer orderNo = voteSearchPOJO.getOrderNo();
			
			final int APPROVED = 1;
			
			VoteItemSearchPOJO voteItemSearchPOJO = new VoteItemSearchPOJO();
			voteItemSearchPOJO.setVoteItemId(voteItemId);
			voteItemSearchPOJO.setUserId(userId);
			voteItemSearchPOJO.setStart(start);
			voteItemSearchPOJO.setLimit(limit);
			voteItemSearchPOJO.setSort(sort);
			voteItemSearchPOJO.setOrderBy(orderBy);
			voteItemSearchPOJO.setPaginationFlage(paginationFlag);
			voteItemSearchPOJO.setApproveFlag(APPROVED);
			voteItemSearchPOJO.setOrderNo(orderNo);
			
			DataTablesPOJO<VoteItemPOJO> dataTablesPOJO = voteService.findVoteItems(voteItemSearchPOJO , votePOJO);
			
			if (dataTablesPOJO != null && dataTablesPOJO.getData() != null) {
				List<VoteItemPOJO> voteItemPOJOs = dataTablesPOJO.getData();
				if (CollectionUtils.isNotEmpty(voteItemPOJOs)) {
					for (VoteItemPOJO voteItemPOJO : voteItemPOJOs) {
						String qs = request.getQueryString();
						String voteItemUrl = HttpRequestUtil.getBase(request) 
										+ "/web/unified/vote/loadmore/query/"
										+ voteId + "/" + voteItemPOJO.getOrderNo()
										+ "?" + qs;
						voteItemPOJO.setVoteItemUrl(voteItemUrl);
					}
				}
			}

//			votePOJO = (VotePOJO) map.get("votePOJO");
//			apply2POJORet = (Apply2POJO) map.get("apply2POJO");
//			wxPersonUserPOJO = (WxPersonUserPOJO) map.get("wxPersonUserPOJO");
			
			// clear orderNo
			voteSearchPOJO.setOrderNo(null);
			ret = dataTablesPOJO;
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		
		return ret;
	}
	@RequestMapping(value = "/web/unified/vote/loadmore/query/{voteId}")
	public ModelAndView weblistVoteById4loadMore(@PathVariable(value="voteId") Long voteId, 
			VoteSearchPOJO voteSearchPOJO,
			Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		VotePOJO votePOJO = null;
		Apply2POJO apply2POJORet = null;
		VoteItemPOJO voteItemPOJORet = null;
		WxPersonUserPOJO wxPersonUserPOJO = null;
		try {
			if (voteId == null) {
				throw new Exception("voteId can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/

			Long activityId = voteSearchPOJO.getActivityId();
			String activityTitle = voteSearchPOJO.getActivityTitle();
			Long voteItemId = voteSearchPOJO.getVoteItemId();
			votePOJO = voteService.findById(voteId);

			if (votePOJO == null) {
				throw new Exception("投票活动无效, voteId: " + voteId);
			}

			/*Long voteId = votePOJO.getVoteId(); 
			Long activityId = votePOJO.getActivityId();*/
//			String apply2AttrModelIdsStr = votePOJO.getApply2AttrModelIds();
//			Integer period = votePOJO.getPeriod();
//			Integer numOfPeriod = votePOJO.getNumOfPeriod();
			
			/*Long voteItemId = voteSearchPOJO.getVoteItemId();
			Long userId = voteSearchPOJO.getUserId();*/
			Integer start = voteSearchPOJO.getStart();
			Integer limit = voteSearchPOJO.getLimit();
			String sort = voteSearchPOJO.getSort();
			String orderBy = voteSearchPOJO.getOrderBy();
			Boolean paginationFlag = voteSearchPOJO.getPaginationFlage();
			Integer orderNo = voteSearchPOJO.getOrderNo();
			
			VoteItemSearchPOJO voteItemSearchPOJO = new VoteItemSearchPOJO();
			voteItemSearchPOJO.setVoteItemId(voteItemId);
			voteItemSearchPOJO.setUserId(userId);
			voteItemSearchPOJO.setStart(start);
			voteItemSearchPOJO.setLimit(limit);
			voteItemSearchPOJO.setSort(sort);
			voteItemSearchPOJO.setOrderBy(orderBy);
			voteItemSearchPOJO.setPaginationFlage(paginationFlag);
//			voteItemSearchPOJO.setOrderNo(orderNo);
			
//			Map map = voteService.listVoteById4UnifiedBootstrap(voteId, activityId, activityTitle, voteItemId, userId);
			Map map = voteService.findCurrentVoteItem(voteItemSearchPOJO, votePOJO);

//			votePOJO = (VotePOJO) map.get("votePOJO");
			apply2POJORet = (Apply2POJO) map.get("apply2POJO");
			voteItemPOJORet = (VoteItemPOJO) map.get("voteItemPOJO");
//			wxPersonUserPOJO = (WxPersonUserPOJO) map.get("wxPersonUserPOJO");

			if (voteItemPOJORet != null) {
				String qs = request.getQueryString();
				String voteItemUrl = HttpRequestUtil.getBase(request) 
								+ "/web/unified/vote/loadmore/query/"
								+ voteId + "/" + voteItemPOJORet.getOrderNo()
								+ "?" + qs;
				voteItemPOJORet.setVoteItemUrl(voteItemUrl);
			}
			
//			ret.addObject("wxPersonUserPOJO", wxPersonUserPOJO);
			ret.addObject("orderNo", orderNo);
			ret.addObject("votePOJO", votePOJO);
			ret.addObject("apply2POJO", apply2POJORet);
			ret.addObject("voteItemPOJO", voteItemPOJORet);
			ret.addObject("voteItemSearchPOJOX", voteItemSearchPOJO);
			ret.setViewName("/page/unified/vote_item_by_vote_id_bs_loadmore");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		
		return ret;
	}

	@RequestMapping(value = "/web/unified/vote/loadmore/query/{voteId}/{orderNo}")
	public ModelAndView weblistVoteById4loadMoreOrderNo(@PathVariable(value="voteId") Long voteId, 
			@PathVariable(value="orderNo") Integer orderNo, 
			VoteSearchPOJO voteSearchPOJO,
			Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		VotePOJO votePOJO = null;
		Apply2POJO apply2POJORet = null;
		VoteItemPOJO voteItemPOJORet = null;
		WxPersonUserPOJO wxPersonUserPOJO = null;
		try {
			if (voteId == null) {
				throw new Exception("voteId can't is NULL.");
			}
			voteSearchPOJO.setOrderNo(orderNo);
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/

			Long activityId = voteSearchPOJO.getActivityId();
			String activityTitle = voteSearchPOJO.getActivityTitle();
			Long voteItemId = voteSearchPOJO.getVoteItemId();
			votePOJO = voteService.findById(voteId);

			if (votePOJO == null) {
				throw new Exception("投票活动无效, voteId: " + voteId);
			}

			/*Long voteId = votePOJO.getVoteId(); 
			Long activityId = votePOJO.getActivityId();*/
//			String apply2AttrModelIdsStr = votePOJO.getApply2AttrModelIds();
//			Integer period = votePOJO.getPeriod();
//			Integer numOfPeriod = votePOJO.getNumOfPeriod();
			
			/*Long voteItemId = voteSearchPOJO.getVoteItemId();
			Long userId = voteSearchPOJO.getUserId();*/
			Integer start = voteSearchPOJO.getStart();
			Integer limit = voteSearchPOJO.getLimit();
			String sort = voteSearchPOJO.getSort();
			String orderBy = voteSearchPOJO.getOrderBy();
			Boolean paginationFlag = voteSearchPOJO.getPaginationFlage();
			orderNo = voteSearchPOJO.getOrderNo();
			
			VoteItemSearchPOJO voteItemSearchPOJO = new VoteItemSearchPOJO();
			voteItemSearchPOJO.setVoteItemId(voteItemId);
			voteItemSearchPOJO.setUserId(userId);
			voteItemSearchPOJO.setStart(start);
			voteItemSearchPOJO.setLimit(limit);
			voteItemSearchPOJO.setSort(sort);
			voteItemSearchPOJO.setOrderBy(orderBy);
			voteItemSearchPOJO.setPaginationFlage(paginationFlag);
//			voteItemSearchPOJO.setOrderNo(orderNo);
			
//			Map map = voteService.listVoteById4UnifiedBootstrap(voteId, activityId, activityTitle, voteItemId, userId);
			Map map = voteService.findCurrentVoteItem(voteItemSearchPOJO, votePOJO);

//			votePOJO = (VotePOJO) map.get("votePOJO");
			apply2POJORet = (Apply2POJO) map.get("apply2POJO");
			voteItemPOJORet = (VoteItemPOJO) map.get("voteItemPOJO");
//			wxPersonUserPOJO = (WxPersonUserPOJO) map.get("wxPersonUserPOJO");

			if (voteItemPOJORet != null) {
				String qs = request.getQueryString();
				String voteItemUrl = HttpRequestUtil.getBase(request) 
								+ "/web/unified/vote/loadmore/query/"
								+ voteId + "/" + voteItemPOJORet.getOrderNo()
								+ "?" + qs;
				voteItemPOJORet.setVoteItemUrl(voteItemUrl);
			}
			
//			ret.addObject("wxPersonUserPOJO", wxPersonUserPOJO);
			ret.addObject("orderNo", orderNo);
			ret.addObject("votePOJO", votePOJO);
			ret.addObject("apply2POJO", apply2POJORet);
			ret.addObject("voteItemPOJO", voteItemPOJORet);
			ret.addObject("voteItemSearchPOJOX", voteItemSearchPOJO);
			ret.setViewName("/page/unified/vote_item_by_vote_id_bs_loadmore_orderno");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		
		return ret;
	}
	// 通过activity的定制表单来实现
		@RequestMapping(value = "/web/unified/vote/bs/query/{voteId}")
		public ModelAndView listVoteById4UnifiedBootstrap(@PathVariable(value="voteId") Long voteId, 
				@RequestParam(value="activityId") Long activityId,
				@RequestParam(value="activityTitle") String activityTitle,
				@RequestParam(value="voteItemId", required=false) Long voteItemId,
				Model model, 
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView ret = new ModelAndView();
			VotePOJO votePOJO = null;
			Apply2POJO apply2POJORet = null;
			VoteItemPOJO voteItemPOJORet = null;
			WxPersonUserPOJO wxPersonUserPOJO = null;
			try {
				if (voteId == null) {
					throw new Exception("voteId can't is NULL.");
				}
				int result = -1;
				Long userId = UserUtil.getCurrentUserId();
				/*if (userId == null) {
					throw new Exception("userId can't is NULL.");
				}*/
				
				Map map = voteService.listVoteById4UnifiedBootstrap(voteId, activityId, activityTitle, voteItemId, userId);

				votePOJO = (VotePOJO) map.get("votePOJO");
				apply2POJORet = (Apply2POJO) map.get("apply2POJO");
				voteItemPOJORet = (VoteItemPOJO) map.get("voteItemPOJO");
				wxPersonUserPOJO = (WxPersonUserPOJO) map.get("wxPersonUserPOJO");
				
				if (votePOJO == null) {
					throw new Exception("投票活动无效, voteId: " + voteId);
				}
				
				ret.addObject("wxPersonUserPOJO", wxPersonUserPOJO);
				ret.addObject("votePOJO", votePOJO);
				ret.addObject("apply2POJO", apply2POJORet);
				ret.addObject("voteItemPOJO", voteItemPOJORet);
				ret.setViewName("/page/unified/vote_item_by_vote_id_bs");
			} catch (Exception e) {
				logger.error("insert error.", e);
				throw e;
			}
			
			
			return ret;
		}
		
	// 通过activity的定制表单来实现
	@RequestMapping(value = "/web/unified/vote/custom/query/{voteId}")
	public ModelAndView listVoteById4Unified4Custom(@PathVariable(value="voteId") Long voteId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		VotePOJO votePOJO = new VotePOJO();
		try {
			if (voteId == null) {
				throw new Exception("voteId can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			// 获取vote
			votePOJO  = voteService.findById(voteId);
			
			if (votePOJO == null) {
				throw new Exception("投票活动无效, voteId: " + voteId);
			}
			
			Long activityId = votePOJO.getActivityId();
			String apply2AttrModelIds = votePOJO.getApply2AttrModelIds();
			List<Long> apply2AttrModelIdList = CollectionUtilx.string2Longs(apply2AttrModelIds);
			
			// 获取voteItem
			List<VoteItemPOJO> voteItemPOJOs = voteItemService.findsByVoteId(voteId);
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
			List<RelVoteUserPOJO> relVoteUserPOJOs = relVoteUserService.finds(relVoteUserSearchPOJO);
			
			// 获取apply2
			Apply2SearchPOJO apply2SearchPOJO = new Apply2SearchPOJO();
			apply2SearchPOJO.setActivityId(activityId);
			List<Apply2POJO> apply2POJOs = apply2Service.finds2ByActivityId(apply2SearchPOJO);
			if (CollectionUtils.isNotEmpty(apply2POJOs)) {
				for (Apply2POJO apply2POJO : apply2POJOs) {
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
					// apply2 set into voteItem
					if (CollectionUtils.isNotEmpty(voteItemPOJOs)) {
						for (VoteItemPOJO voteItemPOJO : voteItemPOJOs) {
							
							if (CollectionUtils.isNotEmpty(relVoteUserPOJOs)) {
								for (RelVoteUserPOJO relVoteUserPOJO : relVoteUserPOJOs) {
									if (relVoteUserPOJO.getVoteItemId().longValue() == voteItemPOJO.getVoteItemId().longValue()) {
										voteItemPOJO.setBeenVoted(true);
										break;
									}
								}
							}
							
							if (voteItemPOJO.getApply2Id().longValue() == apply2POJO.getApply2Id().longValue()) {
								voteItemPOJO.setApply2POJO(apply2POJO);
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
			
			
			
			ret.addObject("votePOJO", votePOJO);
			ret.setViewName("/page/unified/vote_item_by_vote_id_custom");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		
		return ret;
	}

	@RequestMapping(value = "/web/unified/vote/query/{id}")
	public ModelAndView listVoteById4Unified(@PathVariable(value="id") Long id, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		VotePOJO votePOJO = new VotePOJO();
		try {
			if (id == null) {
				throw new Exception("id can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			votePOJO  = voteService.findById(id);
			List<VoteItemPOJO> voteItemPOJOs = voteItemService.findsByVoteId(id);
			if (votePOJO != null) {
				votePOJO.setVoteItemPOJOs(voteItemPOJOs);
			}
			
			ret.addObject("votePOJO", votePOJO);
			ret.setViewName("/page/unified/vote_item_by_vote_id");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		
		return ret;
	}
	@RequestMapping(value = "/web/media/vote/query/{id}")
	public ModelAndView listVoteById(@PathVariable(value="id") Long id, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		VotePOJO votePOJO = new VotePOJO();
		try {
			if (id == null) {
				throw new Exception("id can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			votePOJO  = voteService.findById(id);
			List<VoteItemPOJO> voteItemPOJOs = voteItemService.findsByVoteId(id);
			if (votePOJO != null) {
				votePOJO.setVoteItemPOJOs(voteItemPOJOs);
			}
			
			ret.addObject("votePOJO", votePOJO);
			ret.setViewName("/page/media/vote_item_by_vote_id");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		
		return ret;
	}
	
	/*@RequestMapping(value = "/api/media/vote/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(VotePOJO votePOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (votePOJO == null) {
				throw new Exception("votePOJO can't is NULL.");
			}

			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			votePOJO.setUserId(userId);
			if (votePOJO.getVoteId() != null) {
				result = voteService.update(votePOJO);
			} else {
				result = voteService.insert(votePOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}*/
	
	@RequestMapping(value = "/web/media/vote/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(VotePOJO votePOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (votePOJO == null) {
				throw new Exception("votePOJO can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			votePOJO.setUserId(userId);
			if (votePOJO.getVoteId() != null) {
				result = voteService.update(votePOJO);
			} else {
				result = voteService.insert(votePOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/web/media/voteDetail?voteId=" + votePOJO.getVoteId();
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}
	

	@RequestMapping(value = "/api/unified/vote/{voteId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public VotePOJO queryUnified(@PathVariable("voteId") Long voteId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		VotePOJO ret = new VotePOJO();
		try {
			ret = voteService.findById(voteId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/media/vote/{voteId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public VotePOJO query(@PathVariable("voteId") Long voteId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		VotePOJO ret = new VotePOJO();
		try {
			ret = voteService.findById(voteId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/vote/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<VotePOJO> query4Unified(VoteSearchPOJO voteSearchPOJO) throws Exception {
		DataTablesPOJO<VotePOJO> ret = new DataTablesPOJO<VotePOJO>();
		try {
			List<VotePOJO> votePOJOs = voteService.finds(voteSearchPOJO);
			ret.setData(votePOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}
	@RequestMapping(value = "/api/media/vote/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<VotePOJO> query(VoteSearchPOJO voteSearchPOJO) throws Exception {
		DataTablesPOJO<VotePOJO> ret = new DataTablesPOJO<VotePOJO>();
		try {
			List<VotePOJO> votePOJOs = voteService.finds(voteSearchPOJO);
			ret.setData(votePOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/vote/voteByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<VotePOJO> queryByUserId4Unified(VoteSearchPOJO voteSearchPOJO) throws Exception {
		DataTablesPOJO<VotePOJO> ret = new DataTablesPOJO<VotePOJO>();
		Long userId = voteSearchPOJO.getUserId();
		if (userId == null || userId <= 0) {
			userId = UserUtil.getCurrentUserId();
		}
		voteSearchPOJO.setUserId(userId);
		try {
			voteSearchPOJO.setPaginationFlage(false);
			List<VotePOJO> votePOJOs = voteService.finds(voteSearchPOJO);
			ret.setData(votePOJOs);
		} catch (Exception e) {
			logger.error("voteByUserId error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/media/vote/voteByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<VotePOJO> queryByUserId(VoteSearchPOJO voteSearchPOJO) throws Exception {
		DataTablesPOJO<VotePOJO> ret = new DataTablesPOJO<VotePOJO>();
		Long userId = voteSearchPOJO.getUserId();
		if (userId == null || userId <= 0) {
			userId = UserUtil.getCurrentUserId();
		}
		voteSearchPOJO.setUserId(userId);
		try {
			voteSearchPOJO.setPaginationFlage(false);
			List<VotePOJO> votePOJOs = voteService.finds(voteSearchPOJO);
			ret.setData(votePOJOs);
		} catch (Exception e) {
			logger.error("voteByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/vote", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<VotePOJO> findVote(VoteSearchPOJO voteSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<VotePOJO> ret = new ExtjsPOJO<VotePOJO>();
		List<VotePOJO> votePOJOList = new ArrayList<VotePOJO>();
		votePOJOList = voteService.finds(voteSearchPOJO);
		int total = voteService.getCount(voteSearchPOJO);
		
		ret.setGridModelList(votePOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("votePOJOList", votePOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/vote/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(VotePOJO votePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = voteService.insert(votePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/vote/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(VotePOJO votePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = voteService.update(votePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/vote/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = voteService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
