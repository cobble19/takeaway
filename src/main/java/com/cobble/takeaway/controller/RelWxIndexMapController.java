package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.RelWxIndexMapPOJO;
import com.cobble.takeaway.pojo.RelWxIndexMapSearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.RelWxIndexMapService;
import com.cobble.takeaway.service.UserService;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class RelWxIndexMapController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(RelWxIndexMapController.class);
	
	@Autowired
	private RelWxIndexMapService relWxIndexMapService;
	@Autowired
	private UserService userService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@RequestMapping(value = "/api/unified/relWxIndexMap/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(RelWxIndexMapPOJO relWxIndexMapPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (relWxIndexMapPOJO == null) {
				throw new Exception("relWxIndexMapPOJO can't is NULL.");
			}

			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			if (relWxIndexMapPOJO.getRelWxIndexMapId() != null) {
				result = relWxIndexMapService.update(relWxIndexMapPOJO);
			} else {
				result = relWxIndexMapService.insert(relWxIndexMapPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/unified/relWxIndexMap/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(RelWxIndexMapPOJO relWxIndexMapPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (relWxIndexMapPOJO == null) {
				throw new Exception("relWxIndexMapPOJO can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			if (StringUtils.isBlank(relWxIndexMapPOJO.getWxIndexCode())) {
				throw new Exception("wxIndexCode can't is NULL.");
			}
			
			RelWxIndexMapSearchPOJO relWxIndexMapSearchPOJO = new RelWxIndexMapSearchPOJO();
			relWxIndexMapSearchPOJO.setWxIndexCode(relWxIndexMapPOJO.getWxIndexCode());
			List<RelWxIndexMapPOJO> relWxIndexMapPOJOs = relWxIndexMapService.finds(relWxIndexMapSearchPOJO);
			RelWxIndexMapPOJO temp = null;
			if (CollectionUtils.isNotEmpty(relWxIndexMapPOJOs)) {
				temp = relWxIndexMapPOJOs.get(0);
			}
			
			if (relWxIndexMapPOJO.getRelWxIndexMapId() != null) {
				if (temp != null && temp.getUserId().longValue() != relWxIndexMapPOJO.getUserId()) {
					throw new Exception("一个indexcode不能用户2个或多个用户.");
				}
				result = relWxIndexMapService.update(relWxIndexMapPOJO);
			} else {
				if (temp != null) {
					throw new Exception("一个indexcode不能用户2个或多个用户.");
				}
				result = relWxIndexMapService.insert(relWxIndexMapPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/web/unified/relWxIndexMapDetail?relWxIndexMapId=" + relWxIndexMapPOJO.getRelWxIndexMapId();

		url = "/web/unified/usercenter#rel_wx_index_map";
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}
	
	@RequestMapping(value = "/web/unified/relWxIndexMap/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public ModelAndView showupdate4Web(RelWxIndexMapPOJO relWxIndexMapPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			if (relWxIndexMapPOJO == null) {
				throw new Exception("relWxIndexMapPOJO can't is NULL.");
			}
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			RelWxIndexMapPOJO relWxIndexMapPOJO2 = relWxIndexMapService.findById(relWxIndexMapPOJO.getRelWxIndexMapId());
			ret.addObject("relWxIndexMapPOJO", relWxIndexMapPOJO2);
			ret.setViewName("/page/unified/rel_wx_index_map_update");
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/relWxIndexMap/{relWxIndexMapId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public RelWxIndexMapPOJO query(@PathVariable("relWxIndexMapId") Long relWxIndexMapId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelWxIndexMapPOJO ret = new RelWxIndexMapPOJO();
		try {
			ret = relWxIndexMapService.findById(relWxIndexMapId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/relWxIndexMap/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<RelWxIndexMapPOJO> query(RelWxIndexMapSearchPOJO relWxIndexMapSearchPOJO) throws Exception {
		DataTablesPOJO<RelWxIndexMapPOJO> ret = new DataTablesPOJO<RelWxIndexMapPOJO>();
		try {
			List<RelWxIndexMapPOJO> relWxIndexMapPOJOs = relWxIndexMapService.finds(relWxIndexMapSearchPOJO);
			ret.setData(relWxIndexMapPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/relWxIndexMap/relWxIndexMapByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<RelWxIndexMapPOJO> queryByUserId(RelWxIndexMapSearchPOJO relWxIndexMapSearchPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataTablesPOJO<RelWxIndexMapPOJO> ret = new DataTablesPOJO<RelWxIndexMapPOJO>();
		Long userId = relWxIndexMapSearchPOJO.getUserId();
		if (userId == null || userId <= 0) {
			userId = UserUtil.getCurrentUserId();
		}
		
		HttpSession session = request.getSession();
		
		relWxIndexMapSearchPOJO.setPaginationFlage(false);
		relWxIndexMapSearchPOJO.setUserId(userId);
		
		try {
			List<RelWxIndexMapPOJO> relWxIndexMapPOJOs = relWxIndexMapService.finds(relWxIndexMapSearchPOJO);
			ret.setData(relWxIndexMapPOJOs);
		} catch (Exception e) {
			logger.error("relWxIndexMapByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/relWxIndexMap", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<RelWxIndexMapPOJO> findRelWxIndexMap(RelWxIndexMapSearchPOJO relWxIndexMapSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<RelWxIndexMapPOJO> ret = new ExtjsPOJO<RelWxIndexMapPOJO>();
		List<RelWxIndexMapPOJO> relWxIndexMapPOJOList = new ArrayList<RelWxIndexMapPOJO>();
		relWxIndexMapPOJOList = relWxIndexMapService.finds(relWxIndexMapSearchPOJO);
		int total = relWxIndexMapService.getCount(relWxIndexMapSearchPOJO);
		
		ret.setGridModelList(relWxIndexMapPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("relWxIndexMapPOJOList", relWxIndexMapPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/relWxIndexMap/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(RelWxIndexMapPOJO relWxIndexMapPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = relWxIndexMapService.insert(relWxIndexMapPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/relWxIndexMap/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(RelWxIndexMapPOJO relWxIndexMapPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = relWxIndexMapService.update(relWxIndexMapPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/relWxIndexMap/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = relWxIndexMapService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
}
