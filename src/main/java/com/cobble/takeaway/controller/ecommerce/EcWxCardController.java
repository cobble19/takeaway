package com.cobble.takeaway.controller.ecommerce;

import com.cobble.takeaway.controller.BaseController;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardSearchPOJO;
import com.cobble.takeaway.service.ecommerce.EcWxCardService;
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
public class EcWxCardController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(EcWxCardController.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private EcWxCardService ecWxCardService;

	
	/////////////////////////below is DB, above is call wx pay

	@RequestMapping(value = "/web/unified/ecWxCard/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4WebUnified(EcWxCardPOJO ecWxCardPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (ecWxCardPOJO == null) {
				throw new Exception("ecWxCardPOJO can't is NULL.");
			}
			int result = -1;
			if (ecWxCardPOJO.getEcWxCardId() != null) {
				result = ecWxCardService.update(ecWxCardPOJO);
			} else {
				result = ecWxCardService.insert(ecWxCardPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/unified/ecWxCard_detail.jsp?ecWxCardId=" + ecWxCardPOJO.getEcWxCardId();
//		url = "/web/unified/usercenter#ecWxCard";
		url = "/page/unified/ec_wx_card_single.jsp";
		redirectStrategy.sendRedirect(request, response, url);
		
//		return ret;
		return null;
	}


	@RequestMapping(value = "/web/unified/ecWxCard/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ModelAndView update(@RequestParam(value="ecWxCardId") Long ecWxCardId, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			EcWxCardPOJO ecWxCardPOJO = ecWxCardService.findById(ecWxCardId);
			
			ret.addObject("ecWxCardPOJO", ecWxCardPOJO);
			ret.setViewName("/page/unified/ec_wx_card_update");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}

	
	@RequestMapping(value = "/api/unified/ecWxCard/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(EcWxCardPOJO ecWxCardPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (ecWxCardPOJO == null) {
				throw new Exception("ecWxCardPOJO can't is NULL.");
			}
			int result = -1;
			if (ecWxCardPOJO.getEcWxCardId() != null) {
				result = ecWxCardService.update(ecWxCardPOJO);
			} else {
				result = ecWxCardService.insert(ecWxCardPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/ecWxCard/{orderId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public EcWxCardPOJO query(@PathVariable("rderId") Long orderId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		EcWxCardPOJO ret = new EcWxCardPOJO();
		try {
			ret = ecWxCardService.findById(orderId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/ecWxCard/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<EcWxCardPOJO> query(EcWxCardSearchPOJO ecWxCardSearchPOJO) throws Exception {
		DataTablesPOJO<EcWxCardPOJO> ret = new DataTablesPOJO<EcWxCardPOJO>();
		try {
			List<EcWxCardPOJO> ecWxCardPOJOs = ecWxCardService.finds(ecWxCardSearchPOJO);
			ret.setData(ecWxCardPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/ecWxCard/ecWxCardByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<EcWxCardPOJO> queryByUserId(EcWxCardSearchPOJO ecWxCardSearchPOJO) throws Exception {
		DataTablesPOJO<EcWxCardPOJO> ret = new DataTablesPOJO<EcWxCardPOJO>();
		try {
//			ecWxCardSearchPOJO.setUserId(UserUtil.getCurrentUserId());
			ecWxCardSearchPOJO.setPaginationFlage(false);
			List<EcWxCardPOJO> ecWxCardPOJOs = ecWxCardService.finds(ecWxCardSearchPOJO);
			ret.setData(ecWxCardPOJOs);
		} catch (Exception e) {
			logger.error("ecWxCardByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/ecWxCard", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<EcWxCardPOJO> findEcWxCard(EcWxCardSearchPOJO ecWxCardSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<EcWxCardPOJO> ret = new ExtjsPOJO<EcWxCardPOJO>();
		List<EcWxCardPOJO> ecWxCardPOJOList = new ArrayList<EcWxCardPOJO>();
		ecWxCardPOJOList = ecWxCardService.finds(ecWxCardSearchPOJO);
		int total = ecWxCardService.getCount(ecWxCardSearchPOJO);
		
		ret.setGridModelList(ecWxCardPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("ecWxCardPOJOList", ecWxCardPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecWxCard/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(EcWxCardPOJO ecWxCardPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecWxCardService.insert(ecWxCardPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecWxCard/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(EcWxCardPOJO ecWxCardPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecWxCardService.update(ecWxCardPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecWxCard/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecWxCardService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
