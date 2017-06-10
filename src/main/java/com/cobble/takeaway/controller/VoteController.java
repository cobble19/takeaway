package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
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
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.VoteItemPOJO;
import com.cobble.takeaway.pojo.VotePOJO;
import com.cobble.takeaway.pojo.VoteSearchPOJO;
import com.cobble.takeaway.service.Apply2Service;
import com.cobble.takeaway.service.VoteItemService;
import com.cobble.takeaway.service.VoteService;
import com.cobble.takeaway.util.CollectionUtilx;
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
			votePOJO  = voteService.findById(voteId);
			
			if (votePOJO == null) {
				throw new Exception("投票活动无效, voteId: " + voteId);
			}
			
			Long activityId = votePOJO.getActivityId();
			String apply2AttrModelIds = votePOJO.getApply2AttrModelIds();
			List<Long> apply2AttrModelIdList = CollectionUtilx.string2Longs(apply2AttrModelIds);
			
			List<VoteItemPOJO> voteItemPOJOs = voteItemService.findsByVoteId(voteId);
			votePOJO.setVoteItemPOJOs(voteItemPOJOs);
			
			Apply2SearchPOJO apply2SearchPOJO = new Apply2SearchPOJO();
			apply2SearchPOJO.setActivityId(activityId);
			List<Apply2POJO> apply2POJOs = apply2Service.finds2ByActivityId(apply2SearchPOJO);
			if (CollectionUtils.isNotEmpty(apply2POJOs) && CollectionUtils.isNotEmpty(apply2AttrModelIdList)) {
				for (Apply2POJO apply2POJO : apply2POJOs) {
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
					// apply2 set into voteItem
					if (CollectionUtils.isNotEmpty(voteItemPOJOs)) {
						for (VoteItemPOJO voteItemPOJO : voteItemPOJOs) {
							if (voteItemPOJO.getApply2Id().longValue() == apply2POJO.getApply2Id().longValue()) {
								voteItemPOJO.setApply2POJO(apply2POJO);
								break;
							}
						}
					}
					
				}
			}
			
			if (CollectionUtils.isNotEmpty(apply2POJOs) && CollectionUtils.isNotEmpty(voteItemPOJOs)) {
				for (Apply2POJO apply2POJO : apply2POJOs) {
					// apply2 set into voteItem
					for (VoteItemPOJO voteItemPOJO : voteItemPOJOs) {
						if (voteItemPOJO.getApply2Id().longValue() == apply2POJO.getApply2Id().longValue()) {
							voteItemPOJO.setApply2POJO(apply2POJO);
							break;
						}
					}
				}
			}
			
			
			
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
