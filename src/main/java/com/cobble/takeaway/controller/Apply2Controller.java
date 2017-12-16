package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.Apply2AttrPOJO;
import com.cobble.takeaway.pojo.Apply2POJO;
import com.cobble.takeaway.pojo.Apply2SearchPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.RelActivityApply2POJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserPOJO;
import com.cobble.takeaway.service.ActivityService;
import com.cobble.takeaway.service.Apply2AttrService;
import com.cobble.takeaway.service.Apply2Service;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.HttpRequestUtil;
import com.cobble.takeaway.util.JsonUtils;
import com.cobble.takeaway.util.UserUtil;
import com.fasterxml.jackson.core.type.TypeReference;

@Controller
public class Apply2Controller extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(Apply2Controller.class);
	
	@Autowired
	private Apply2Service apply2Service;
	@Autowired
	private Apply2AttrService apply2AttrService;

	@Autowired
	private ActivityService activityService;
	

	@RequestMapping(value = "/api/apply2/existByUnionId", method = {RequestMethod.POST})
	@ResponseBody
	public StatusPOJO existApply2InActivity(Apply2SearchPOJO apply2SearchPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			HttpSession session = request.getSession();
			String unionId = UserUtil.getCurrentUser().getUnionId();
			apply2SearchPOJO.setUnionId(unionId);
			
			
			int result = apply2Service.findsApply2InActivityByUnionId(apply2SearchPOJO);
			if (result > 0) {
				ret.setSuccess(true);
				ret.setDesc("重复提交：" + unionId);
			} else {
				ret.setSuccess(false);
				ret.setDesc("可以提交：" + unionId);
			}
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			ret.setDesc(e.getMessage());
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/apply2/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO apply2Add(@RequestBody String body, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			List<Apply2AttrPOJO> apply2AttrPOJOs = (List<Apply2AttrPOJO>) JsonUtils.convertToJavaBeanList(body
													, new TypeReference<List<Apply2AttrPOJO>>() { });
			if (CollectionUtils.isEmpty(apply2AttrPOJOs)) {
				throw new Exception("apply2AttrPOJOs can't is NULL.");
			}
			HttpSession session = request.getSession();
			
			String base = HttpRequestUtil.getBase(request);
			Long userId = UserUtil.getCurrentUserId();
			String openId = UserUtil.getCurrentUser().getOpenId();
			String unionId = UserUtil.getCurrentUser().getUnionId();
			String proxyOpenId = UserUtil.getCurrentUser().getProxyOpenId();
			WxPersonUserPOJO wxPersonUserPOJO = (WxPersonUserPOJO) session.getAttribute(CommonConstant.CURRENT_WX_PERSON_USER);
			Long wxPersonUserId = null;
			if (wxPersonUserPOJO != null) {
				wxPersonUserId = wxPersonUserPOJO.getWxPersonUserId();
			}
			

			Apply2AttrPOJO apply2AttrPOJOTemp = apply2AttrPOJOs.get(0);
			Long activityId = apply2AttrPOJOTemp.getActivityId();
			ActivityPOJO activityPOJO = activityService.findById(activityId);
			if (activityPOJO == null) {
				ret.setSuccess(false);
				ret.setDesc("活动不存在!!");
				return ret;
			}
			
			if (activityPOJO != null && activityPOJO.getStartDateTime() != null
					&& activityPOJO.getEndDateTime() != null) {
				Date curDate = new Date();
				if (curDate.before(activityPOJO.getStartDateTime()) || curDate.after(activityPOJO.getEndDateTime())) {
					ret.setSuccess(false);
					ret.setDesc("活动不在有效期内");
					return ret;
				}
			}
			
			
			
			Long apply2Id = -9999L;
			for (int i = 0; i < apply2AttrPOJOs.size(); i++) {
				Apply2AttrPOJO apply2AttrPOJO = apply2AttrPOJOs.get(i);
				activityId = apply2AttrPOJO.getActivityId();
				if (0 == i) {
					Apply2POJO apply2pojo = new Apply2POJO();
					apply2pojo.setDescription("活动id：" + activityId + ", i: " + i);
					apply2pojo.setUserId(userId);
					apply2pojo.setProxyOpenId(proxyOpenId);
					apply2pojo.setOpenId(openId);
					apply2pojo.setUnionId(unionId);
					apply2pojo.setActivityId(activityId);
					apply2Service.insert(apply2pojo);
					
					apply2Id = apply2pojo.getApply2Id();
					
					RelActivityApply2POJO relActivityApply2POJO = new RelActivityApply2POJO();
					relActivityApply2POJO.setActivityId(activityId);
					relActivityApply2POJO.setApply2Id(apply2pojo.getApply2Id());
					apply2Service.insertRelActivityApply2(relActivityApply2POJO);
				}
				
				apply2AttrPOJO.setApply2Id(apply2Id);
				
				apply2AttrService.insert(apply2AttrPOJO);
			}
			
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			ret.setDesc(e.getMessage());
//			throw e;
		}
		
		return ret;
	}
	
	/*@RequestMapping(value = "/web/person/apply2InActivity/{activityId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<Apply2POJO> apply2InActivity(@PathVariable("activityId") Long activityId) throws Exception {
		DataTablesPOJO<Apply2POJO> ret = new DataTablesPOJO<Apply2POJO>();
		try {
			List<Apply2POJO> apply2POJOs = apply2Service.findsApply2InActivity(activityId);
			int count = apply2Service.getCountApply2InActivity(activityId);
			ret.setData(apply2POJOs);
			ret.setRecordsTotal(count);
			ret.setDraw(1);
			ret.setRecordsFiltered(count);
		} catch (Exception e) {
			logger.error("apply2InActivity error.", e);
			throw e;
		}
		
		return ret;
	}*/

	/*@RequestMapping(value = "/web/person/apply2/exist", method = {RequestMethod.POST})
	@ResponseBody
	public StatusPOJO existApply2InActivity(Apply2SearchPOJO apply2SearchPOJO) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			Boolean result = apply2Service.existApply2InActivity(apply2SearchPOJO);
			if (result) {
				ret.setSuccess(true);
				ret.setDesc("存在手机号：" + apply2SearchPOJO.getPhone());
			} else {
				ret.setSuccess(false);
				ret.setDesc("不存在手机号：" + apply2SearchPOJO.getPhone());
			}
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}*/
	
	@RequestMapping(value = "/web/person/apply2/add", method = {RequestMethod.POST})
	@ResponseBody
	public StatusPOJO apply2(Apply2POJO apply2POJO) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = apply2Service.insert(apply2POJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/apply2/all", method = {RequestMethod.GET})
	public ModelAndView findFoodSellersPure(Apply2SearchPOJO apply2SearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<Apply2POJO> apply2POJOList = new ArrayList<Apply2POJO>();
		apply2POJOList = apply2Service.finds(apply2SearchPOJO);
//		int total = apply2Service.getCount(apply2SearchPOJO);
		ret.addObject("apply2POJOList", apply2POJOList);
		ret.setViewName("page/apply2_all");
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply2", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<Apply2POJO> findApply2(Apply2SearchPOJO apply2SearchPOJO, Model model) throws Exception {
		ExtjsPOJO<Apply2POJO> ret = new ExtjsPOJO<Apply2POJO>();
		List<Apply2POJO> apply2POJOList = new ArrayList<Apply2POJO>();
		apply2POJOList = apply2Service.finds(apply2SearchPOJO);
		int total = apply2Service.getCount(apply2SearchPOJO);
		/*Apply2POJO apply2POJO = new Apply2POJO();
		apply2POJO.setApply2Id(1);
		apply2POJO.setName("hefei");
		apply2POJO.setDescription("描述信息。。。");
		apply2POJOList.add(apply2POJO);*/
		
		ret.setGridModelList(apply2POJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		/*model.addAttribute("apply2POJOList", apply2POJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply2/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(Apply2POJO apply2POJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = apply2Service.insert(apply2POJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply2/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(Apply2POJO apply2POJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = apply2Service.update(apply2POJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply2/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = apply2Service.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
