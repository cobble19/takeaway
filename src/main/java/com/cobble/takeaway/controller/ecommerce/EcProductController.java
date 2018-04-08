package com.cobble.takeaway.controller.ecommerce;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.controller.BaseController;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcProductPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcProductSearchPOJO;
import com.cobble.takeaway.service.ecommerce.EcProductService;

@Controller
public class EcProductController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(EcProductController.class);
	
	@Autowired
	private EcProductService ecProductService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	

	@RequestMapping(value = "/web/unified/ecProduct/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4WebUnified(EcProductPOJO ecProductPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (ecProductPOJO == null) {
				throw new Exception("ecProductPOJO can't is NULL.");
			}
			int result = -1;
			if (ecProductPOJO.getProductId() != null) {
				result = ecProductService.update(ecProductPOJO);
			} else {
				result = ecProductService.insert(ecProductPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/unified/ecProduct_detail.jsp?ecProductId=" + ecProductPOJO.getProductId();
//		url = "/web/unified/usercenter#ecProduct";
		url = "/page/unified/ec_product_single.jsp";
		redirectStrategy.sendRedirect(request, response, url);
		
//		return ret;
		return null;
	}


	@RequestMapping(value = "/web/unified/ecProduct/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ModelAndView update(@RequestParam(value="productId") Long productId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			EcProductPOJO ecProductPOJO = ecProductService.findById(productId);
			
			ret.addObject("ecProductPOJO", ecProductPOJO);
			ret.setViewName("/page/unified/ec_product_update");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}

	
	@RequestMapping(value = "/api/unified/ecProduct/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(EcProductPOJO ecProductPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (ecProductPOJO == null) {
				throw new Exception("ecProductPOJO can't is NULL.");
			}
			int result = -1;
			if (ecProductPOJO.getProductId() != null) {
				result = ecProductService.update(ecProductPOJO);
			} else {
				result = ecProductService.insert(ecProductPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/ecProduct/{productId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public EcProductPOJO query(@PathVariable("productId") Long productId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		EcProductPOJO ret = new EcProductPOJO();
		try {
			ret = ecProductService.findById(productId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/ecProduct/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<EcProductPOJO> query(EcProductSearchPOJO ecProductSearchPOJO) throws Exception {
		DataTablesPOJO<EcProductPOJO> ret = new DataTablesPOJO<EcProductPOJO>();
		try {
			List<EcProductPOJO> ecProductPOJOs = ecProductService.finds(ecProductSearchPOJO);
			ret.setData(ecProductPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/ecProduct/ecProductByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<EcProductPOJO> queryByUserId(EcProductSearchPOJO ecProductSearchPOJO) throws Exception {
		DataTablesPOJO<EcProductPOJO> ret = new DataTablesPOJO<EcProductPOJO>();
		try {
//			ecProductSearchPOJO.setUserId(UserUtil.getCurrentUserId());
			ecProductSearchPOJO.setPaginationFlage(false);
			List<EcProductPOJO> ecProductPOJOs = ecProductService.finds(ecProductSearchPOJO);
			ret.setData(ecProductPOJOs);
		} catch (Exception e) {
			logger.error("ecProductByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/ecProduct", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<EcProductPOJO> findEcProduct(EcProductSearchPOJO ecProductSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<EcProductPOJO> ret = new ExtjsPOJO<EcProductPOJO>();
		List<EcProductPOJO> ecProductPOJOList = new ArrayList<EcProductPOJO>();
		ecProductPOJOList = ecProductService.finds(ecProductSearchPOJO);
		int total = ecProductService.getCount(ecProductSearchPOJO);
		
		ret.setGridModelList(ecProductPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("ecProductPOJOList", ecProductPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecProduct/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(EcProductPOJO ecProductPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecProductService.insert(ecProductPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecProduct/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(EcProductPOJO ecProductPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecProductService.update(ecProductPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecProduct/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecProductService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
