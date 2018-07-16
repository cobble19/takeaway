package com.cobble.takeaway.controller.ecommerce;

import com.cobble.takeaway.controller.BaseController;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcCartPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcCartSearchPOJO;
import com.cobble.takeaway.service.ecommerce.EcCartService;
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
public class EcCartController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(EcCartController.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private EcCartService ecCartService;

	/// below is DB

	@RequestMapping(value = "/web/unified/ecCart/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4WebUnified(EcCartPOJO ecCartPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (ecCartPOJO == null) {
				throw new Exception("ecCartPOJO can't is NULL.");
			}
			int result = -1;
			if (ecCartPOJO.getEcCartId() != null) {
				result = ecCartService.update(ecCartPOJO);
			} else {
				result = ecCartService.insert(ecCartPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/unified/ecCart_detail.jsp?ecCartId=" + ecCartPOJO.getEcCartId();
//		url = "/web/unified/usercenter#ecCart";
		url = "/page/unified/ec_cart_single.jsp";
		redirectStrategy.sendRedirect(request, response, url);
		
//		return ret;
		return null;
	}


	@RequestMapping(value = "/web/unified/ecCart/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ModelAndView update(@RequestParam(value="ecCartId") Long ecCartId, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			EcCartPOJO ecCartPOJO = ecCartService.findById(ecCartId);
			
			ret.addObject("ecCartPOJO", ecCartPOJO);
			ret.setViewName("/page/unified/ec_cart_update");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}

	
	@RequestMapping(value = "/api/unified/ecCart/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(EcCartPOJO ecCartPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (ecCartPOJO == null) {
				throw new Exception("ecCartPOJO can't is NULL.");
			}
			int result = -1;
			if (ecCartPOJO.getEcCartId() != null) {
				result = ecCartService.update(ecCartPOJO);
			} else {
				result = ecCartService.insert(ecCartPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/ecCart/{ecCartId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public EcCartPOJO query(@PathVariable("rderId") Long ecCartId, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		EcCartPOJO ret = new EcCartPOJO();
		try {
			ret = ecCartService.findById(ecCartId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/ecCart/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<EcCartPOJO> query(EcCartSearchPOJO ecCartSearchPOJO) throws Exception {
		DataTablesPOJO<EcCartPOJO> ret = new DataTablesPOJO<EcCartPOJO>();
		try {
			List<EcCartPOJO> ecCartPOJOs = ecCartService.finds(ecCartSearchPOJO);
			ret.setData(ecCartPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/ecCart/ecCartByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<EcCartPOJO> queryByUserId(EcCartSearchPOJO ecCartSearchPOJO) throws Exception {
		DataTablesPOJO<EcCartPOJO> ret = new DataTablesPOJO<EcCartPOJO>();
		try {
//			ecCartSearchPOJO.setUserId(UserUtil.getCurrentUserId());
			ecCartSearchPOJO.setPaginationFlage(false);
			List<EcCartPOJO> ecCartPOJOs = ecCartService.finds(ecCartSearchPOJO);
			ret.setData(ecCartPOJOs);
		} catch (Exception e) {
			logger.error("ecCartByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/ecCart", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<EcCartPOJO> findEcCart(EcCartSearchPOJO ecCartSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<EcCartPOJO> ret = new ExtjsPOJO<EcCartPOJO>();
		List<EcCartPOJO> ecCartPOJOList = new ArrayList<EcCartPOJO>();
		ecCartPOJOList = ecCartService.finds(ecCartSearchPOJO);
		int total = ecCartService.getCount(ecCartSearchPOJO);
		
		ret.setGridModelList(ecCartPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("ecCartPOJOList", ecCartPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecCart/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(EcCartPOJO ecCartPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecCartService.insert(ecCartPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecCart/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(EcCartPOJO ecCartPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecCartService.update(ecCartPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecCart/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecCartService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
