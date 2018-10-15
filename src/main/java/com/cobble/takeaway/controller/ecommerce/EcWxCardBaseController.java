package com.cobble.takeaway.controller.ecommerce;

import com.cobble.takeaway.controller.BaseController;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardBasePOJO;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardBaseSearchPOJO;
import com.cobble.takeaway.service.ecommerce.EcWxCardBaseService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EcWxCardBaseController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(EcWxCardBaseController.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private EcWxCardBaseService ecWxCardBaseService;

	
	/////////////////////////below is DB, above is call wx pay

	@RequestMapping(value = "/web/unified/ecWxCardBase/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4WebUnified(EcWxCardBasePOJO ecWxCardBasePOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (ecWxCardBasePOJO == null) {
				throw new Exception("ecWxCardBasePOJO can't is NULL.");
			}
			int result = -1;
			if (ecWxCardBasePOJO.getEcWxCardBaseId() != null) {
				result = ecWxCardBaseService.update(ecWxCardBasePOJO);
			} else {
				result = ecWxCardBaseService.insert(ecWxCardBasePOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/unified/ecWxCardBase_detail.jsp?ecWxCardBaseId=" + ecWxCardBasePOJO.getEcWxCardBaseId();
//		url = "/web/unified/usercenter#ecWxCardBase";
		url = "/page/unified/ec_wx_card_base_single.jsp";
		redirectStrategy.sendRedirect(request, response, url);
		
//		return ret;
		return null;
	}


	@RequestMapping(value = "/web/unified/ecWxCardBase/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ModelAndView update(@RequestParam(value="ecWxCardBaseId") Long ecWxCardBaseId, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			EcWxCardBasePOJO ecWxCardBasePOJO = ecWxCardBaseService.findById(ecWxCardBaseId);
			
			ret.addObject("ecWxCardBasePOJO", ecWxCardBasePOJO);
			ret.setViewName("/page/unified/ec_wx_card_base_update");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}

	
	@RequestMapping(value = "/api/unified/ecWxCardBase/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(EcWxCardBasePOJO ecWxCardBasePOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (ecWxCardBasePOJO == null) {
				throw new Exception("ecWxCardBasePOJO can't is NULL.");
			}
			int result = -1;
			if (ecWxCardBasePOJO.getEcWxCardBaseId() != null) {
				result = ecWxCardBaseService.update(ecWxCardBasePOJO);
			} else {
				result = ecWxCardBaseService.insert(ecWxCardBasePOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/ecWxCardBase/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<EcWxCardBasePOJO> query(EcWxCardBaseSearchPOJO ecWxCardBaseSearchPOJO) throws Exception {
		DataTablesPOJO<EcWxCardBasePOJO> ret = new DataTablesPOJO<EcWxCardBasePOJO>();
		try {
			List<EcWxCardBasePOJO> ecWxCardBasePOJOs = ecWxCardBaseService.finds(ecWxCardBaseSearchPOJO);
			ret.setData(ecWxCardBasePOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/ecWxCardBase/ecWxCardBaseByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<EcWxCardBasePOJO> queryByUserId(EcWxCardBaseSearchPOJO ecWxCardBaseSearchPOJO) throws Exception {
		DataTablesPOJO<EcWxCardBasePOJO> ret = new DataTablesPOJO<EcWxCardBasePOJO>();
		try {
//			ecWxCardBaseSearchPOJO.setUserId(UserUtil.getCurrentUserId());
			ecWxCardBaseSearchPOJO.setPaginationFlage(false);
			List<EcWxCardBasePOJO> ecWxCardBasePOJOs = ecWxCardBaseService.finds(ecWxCardBaseSearchPOJO);
			ret.setData(ecWxCardBasePOJOs);
		} catch (Exception e) {
			logger.error("ecWxCardBaseByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/ecWxCardBase", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<EcWxCardBasePOJO> findEcWxCardBase(EcWxCardBaseSearchPOJO ecWxCardBaseSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<EcWxCardBasePOJO> ret = new ExtjsPOJO<EcWxCardBasePOJO>();
		List<EcWxCardBasePOJO> ecWxCardBasePOJOList = new ArrayList<EcWxCardBasePOJO>();
		ecWxCardBasePOJOList = ecWxCardBaseService.finds(ecWxCardBaseSearchPOJO);
		int total = ecWxCardBaseService.getCount(ecWxCardBaseSearchPOJO);
		
		ret.setGridModelList(ecWxCardBasePOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("ecWxCardBasePOJOList", ecWxCardBasePOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecWxCardBase/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(EcWxCardBasePOJO ecWxCardBasePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecWxCardBaseService.insert(ecWxCardBasePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecWxCardBase/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(EcWxCardBasePOJO ecWxCardBasePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecWxCardBaseService.update(ecWxCardBasePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecWxCardBase/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecWxCardBaseService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
