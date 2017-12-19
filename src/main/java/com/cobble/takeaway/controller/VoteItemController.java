package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.RelVoteUserPOJO;
import com.cobble.takeaway.pojo.RelVoteUserSearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.VoteItemPOJO;
import com.cobble.takeaway.pojo.VoteItemSearchPOJO;
import com.cobble.takeaway.pojo.VotePOJO;
import com.cobble.takeaway.service.RelVoteUserService;
import com.cobble.takeaway.service.VoteItemService;
import com.cobble.takeaway.service.VoteService;
import com.cobble.takeaway.util.DateUtil;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class VoteItemController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(VoteItemController.class);
	
	@Autowired
	private VoteItemService voteItemService;
	@Autowired
	private RelVoteUserService relVoteUserService;
	@Autowired
	private VoteService voteService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@RequestMapping(value = "/api/media/voteItem/existUser4VoteItem", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO existUser4VoteItem(@RequestParam("voteId") Long voteId
			, @RequestParam(value="voteItemId", required=false) Long voteItemId
			, @RequestParam(value="userId", required=false) Long userId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (voteId == null) {
				throw new Exception("voteId can't is NULL.");
			}
			if (userId == null) {
				userId = UserUtil.getCurrentUserId();
			}
			int result = -1;
			
			// 获取判断投票的规则
			VotePOJO votePOJO = voteService.findById(voteId); 
			Integer period = votePOJO.getPeriod();
			Integer numOfPeriod = votePOJO.getNumOfPeriod();
			if (numOfPeriod == null) {
				numOfPeriod = 1;
			}
			
			// Check whether user have addVote to this voteItem
			RelVoteUserSearchPOJO relVoteUserSearchPOJO = new RelVoteUserSearchPOJO();
			relVoteUserSearchPOJO.setUserId(userId);
			relVoteUserSearchPOJO.setVoteId(voteId);
			relVoteUserSearchPOJO.setVoteItemId(voteItemId);
			if (period != null && period.intValue() > 0) {

				Date curDateTime = new Date();
				
				Date startDateTime = DateUtils.truncate(curDateTime, Calendar.DATE);
				Date endDateTime = DateUtils.addSeconds(startDateTime, period.intValue() * 24 * 60 * 60 - 1);
				startDateTime = DateUtils.addDays(startDateTime, (period - 1) * (-1));
				
				relVoteUserSearchPOJO.setStartDateTime(startDateTime);
				relVoteUserSearchPOJO.setEndDateTime(endDateTime);
			}
//			Integer totalHasAddVote = relVoteUserService.getCount(relVoteUserSearchPOJO);
			List<RelVoteUserPOJO> relVoteUserPOJOs = relVoteUserService.finds(relVoteUserSearchPOJO);
			
			if (CollectionUtils.isNotEmpty(relVoteUserPOJOs)) {
				if (relVoteUserPOJOs.size() >= numOfPeriod) {
					ret.setSuccess(true);
					ret.setDesc("已经投过票");
					return ret;
				}
				if (voteItemId != null) {
					for (RelVoteUserPOJO relVoteUserPOJO : relVoteUserPOJOs) {
						if (voteItemId.longValue() == relVoteUserPOJO.getVoteItemId().longValue()) {
							ret.setSuccess(true);
							ret.setDesc("已经投过票");
							return ret;
						} else {
							ret.setSuccess(false);
							ret.setDesc("还没有投票");
						}
					}
				} else {	// voteItemId is null
					ret.setSuccess(false);
					ret.setDesc("还没有投票");
				}
			} else {	// relVoteUserPOJOs is empty
				ret.setSuccess(false);
				ret.setDesc("还没有投票");
			}
			
			
			/*if (totalHasAddVote > 0) {
				ret.setSuccess(true);
				ret.setDesc("已经投过票");
			} else {
				ret.setSuccess(false);
				ret.setDesc("还没有投票");
			}*/
			
//			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/media/voteItem/addVote", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO addVote(@RequestParam("ids") Long[] ids, @RequestParam("voteId") Long voteId
			, @RequestParam(value="userId", required=false) Long userId
			, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (ids == null || ids.length < 1) {
				throw new Exception("ids can't is NULL.");
			}

			if (userId == null) {
				userId = UserUtil.getCurrentUserId();
			}
			int result = -1;
			for (int i = 0; i < ids.length; i++) {
				VoteItemPOJO voteItemPOJO = new VoteItemPOJO();
				Long voteItemId = ids[i];
				
				// Check whether user have addVote to this voteItem
				RelVoteUserSearchPOJO relVoteUserSearchPOJO = new RelVoteUserSearchPOJO();
				relVoteUserSearchPOJO.setUserId(userId);
				relVoteUserSearchPOJO.setVoteId(voteId);
				relVoteUserSearchPOJO.setVoteItemId(voteItemId);
//				Integer totalHasAddVote = relVoteUserService.getCount(relVoteUserSearchPOJO);
//				if (totalHasAddVote <= 0) {
					voteItemPOJO.setVoteItemId(voteItemId);
					voteItemService.increaseTotalNumPlusOne(voteItemPOJO);
					
					// insert REL
					RelVoteUserPOJO relVoteUserPOJO = new RelVoteUserPOJO();
					relVoteUserPOJO.setUserId(userId);
					relVoteUserPOJO.setVoteId(voteId);
					relVoteUserPOJO.setVoteItemId(voteItemId);
					relVoteUserService.insert(relVoteUserPOJO);
//				}
			}
			
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/voteItem/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebPI4Unified(VoteItemPOJO voteItemPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (voteItemPOJO == null) {
				throw new Exception("voteItemPOJO can't is NULL.");
			}
			int result = -1;
			if (voteItemPOJO.getVoteItemId() != null) {
				result = voteItemService.update(voteItemPOJO);
			} else {
				result = voteItemService.insert(voteItemPOJO, UserUtil.getCurrentUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/media/voteItem/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebPI(VoteItemPOJO voteItemPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (voteItemPOJO == null) {
				throw new Exception("voteItemPOJO can't is NULL.");
			}
			int result = -1;
			if (voteItemPOJO.getVoteItemId() != null) {
				result = voteItemService.update(voteItemPOJO);
			} else {
				result = voteItemService.insert(voteItemPOJO, UserUtil.getCurrentUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	/*@RequestMapping(value = "/web/enterprise/voteItem/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(VoteItemPOJO voteItemPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (voteItemPOJO == null) {
				throw new Exception("voteItemPOJO can't is NULL.");
			}
			int result = -1;
			if (voteItemPOJO.getVoteItemId() != null) {
				result = voteItemService.update(voteItemPOJO);
			} else {
				result = voteItemService.insert(voteItemPOJO, UserUtil.getCurrentUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/enterprise/voteItem_detail.jsp?voteItemId=" + voteItemPOJO.getVoteItemId();
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}*/
	
	@RequestMapping(value = "/api/media/voteItem/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<VoteItemPOJO> query(VoteItemSearchPOJO voteItemSearchPOJO) throws Exception {
		DataTablesPOJO<VoteItemPOJO> ret = new DataTablesPOJO<VoteItemPOJO>();
		try {
			List<VoteItemPOJO> voteItemPOJOs = voteItemService.finds(voteItemSearchPOJO);
			ret.setData(voteItemPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/media/voteItem/voteItemByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<VoteItemPOJO> queryByUserId(VoteItemSearchPOJO voteItemSearchPOJO) throws Exception {
		DataTablesPOJO<VoteItemPOJO> ret = new DataTablesPOJO<VoteItemPOJO>();
		voteItemSearchPOJO.setUserId(UserUtil.getCurrentUserId());
		try {
			List<VoteItemPOJO> voteItemPOJOs = voteItemService.finds(voteItemSearchPOJO);
			ret.setData(voteItemPOJOs);
		} catch (Exception e) {
			logger.error("voteItemByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/voteItem", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<VoteItemPOJO> findVoteItem(VoteItemSearchPOJO voteItemSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<VoteItemPOJO> ret = new ExtjsPOJO<VoteItemPOJO>();
		List<VoteItemPOJO> voteItemPOJOList = new ArrayList<VoteItemPOJO>();
		voteItemPOJOList = voteItemService.finds(voteItemSearchPOJO);
		int total = voteItemService.getCount(voteItemSearchPOJO);
		
		ret.setGridModelList(voteItemPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("voteItemPOJOList", voteItemPOJOList);
		model.addAttribute("success", true);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/voteItem/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(VoteItemPOJO voteItemPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = voteItemService.insert(voteItemPOJO, UserUtil.getCurrentUserId());
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/voteItem/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(VoteItemPOJO voteItemPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = voteItemService.update(voteItemPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/voteItem/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = voteItemService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
