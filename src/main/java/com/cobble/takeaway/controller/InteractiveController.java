package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.InteractivePOJO;
import com.cobble.takeaway.pojo.InteractiveSearchPOJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.pojo.UserSearchPOJO;
import com.cobble.takeaway.service.InteractiveService;
import com.cobble.takeaway.service.UserService;
import com.cobble.takeaway.spring.security.MyUser;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class InteractiveController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(InteractiveController.class);
	
	@Autowired
	private InteractiveService interactiveService;
	@Autowired
	private UserService userService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@RequestMapping(value = "/web/enterprise/interactive/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(InteractivePOJO interactivePOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (interactivePOJO == null) {
				throw new Exception("interactivePOJO can't is NULL.");
			}
			int result = -1;
			if (interactivePOJO.getInteractiveId() != null && interactivePOJO.getInteractiveId() > 0l) {
				result = interactiveService.update(interactivePOJO);
			} else {
				result = interactiveService.insert(interactivePOJO, UserUtil.getCurrentUser().getUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/enterprise/interactive_detail.jsp?interactiveId=" + interactivePOJO.getInteractiveId();
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}

	@RequestMapping(value = "/web/enterprise/prize/provider", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public List<UserPOJO> prizeProvider() throws Exception {
		List<UserPOJO> ret = new ArrayList<UserPOJO>();
		try {
			UserSearchPOJO userSearchPOJO = new UserSearchPOJO();
			userSearchPOJO.setUserType(MyUser.ENTERPRISE);
			ret = userService.finds(userSearchPOJO);
		} catch (Exception e) {
			LOGGER.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/enterprise/interactive/{interactiveId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public InteractivePOJO query(@PathVariable("interactiveId") Long interactiveId) throws Exception {
		InteractivePOJO ret = new InteractivePOJO();
		try {
			ret = interactiveService.findById(interactiveId);
		} catch (Exception e) {
			LOGGER.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/enterprise/interactive/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<InteractivePOJO> queryList(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception {
		DataTablesPOJO<InteractivePOJO> ret = new DataTablesPOJO<InteractivePOJO>();
		try {
			List<InteractivePOJO> interactivePOJOs = interactiveService.finds(interactiveSearchPOJO);
			ret.setData(interactivePOJOs);
		} catch (Exception e) {
			LOGGER.error("list error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/enterprise/interactive/list/active", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<InteractivePOJO> queryListActive(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception {
		DataTablesPOJO<InteractivePOJO> ret = new DataTablesPOJO<InteractivePOJO>();
		try {
			List<InteractivePOJO> interactivePOJOs = interactiveService.findActives(interactiveSearchPOJO);
			ret.setData(interactivePOJOs);
		} catch (Exception e) {
			LOGGER.error("list error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/enterprise/interactive", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<InteractivePOJO> query(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception {
		DataTablesPOJO<InteractivePOJO> ret = new DataTablesPOJO<InteractivePOJO>();
		try {
			interactiveSearchPOJO.setUserId(UserUtil.getCurrentUser().getUserId());
			List<InteractivePOJO> interactivePOJOs = interactiveService.finds(interactiveSearchPOJO);
			ret.setData(interactivePOJOs);
		} catch (Exception e) {
			LOGGER.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/enterprise/interactiveByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<InteractivePOJO> queryByUserId(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception {
		DataTablesPOJO<InteractivePOJO> ret = new DataTablesPOJO<InteractivePOJO>();
		interactiveSearchPOJO.setUserId(UserUtil.getCurrentUser().getUserId());
		try {
			List<InteractivePOJO> interactivePOJOs = interactiveService.finds(interactiveSearchPOJO);
			ret.setData(interactivePOJOs);
		} catch (Exception e) {
			LOGGER.error("interactiveByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/interactive/all", method = {RequestMethod.GET})
	public ModelAndView findFoodSellersPure(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<InteractivePOJO> interactivePOJOList = new ArrayList<InteractivePOJO>();
		interactivePOJOList = interactiveService.finds(interactiveSearchPOJO);
//		int total = interactiveService.getCount(interactiveSearchPOJO);
		ret.addObject("interactivePOJOList", interactivePOJOList);
		ret.setViewName("page/interactive_all");
		return ret;
	}
	
	@RequestMapping(value = "/mgr/interactive", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<InteractivePOJO> findInteractive(InteractiveSearchPOJO interactiveSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<InteractivePOJO> ret = new ExtjsPOJO<InteractivePOJO>();
		List<InteractivePOJO> interactivePOJOList = new ArrayList<InteractivePOJO>();
		interactivePOJOList = interactiveService.finds(interactiveSearchPOJO);
		int total = interactiveService.getCount(interactiveSearchPOJO);
		
		ret.setGridModelList(interactivePOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("interactivePOJOList", interactivePOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/interactive/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(InteractivePOJO interactivePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = interactiveService.insert(interactivePOJO, UserUtil.getCurrentUser().getUserId());
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/interactive/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(InteractivePOJO interactivePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = interactiveService.update(interactivePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/interactive/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = interactiveService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
