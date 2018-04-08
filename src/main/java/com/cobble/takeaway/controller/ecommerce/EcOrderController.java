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
import com.cobble.takeaway.pojo.ecommerce.EcOrderPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderSearchPOJO;
import com.cobble.takeaway.service.ecommerce.EcOrderService;

@Controller
public class EcOrderController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(EcOrderController.class);
	
	@Autowired
	private EcOrderService ecOrderService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	

	@RequestMapping(value = "/web/unified/ecOrder/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4WebUnified(EcOrderPOJO ecOrderPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (ecOrderPOJO == null) {
				throw new Exception("ecOrderPOJO can't is NULL.");
			}
			int result = -1;
			if (ecOrderPOJO.getOrderId() != null) {
				result = ecOrderService.update(ecOrderPOJO);
			} else {
				result = ecOrderService.insert(ecOrderPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/unified/ecOrder_detail.jsp?ecOrderId=" + ecOrderPOJO.getOrderId();
//		url = "/web/unified/usercenter#ecOrder";
		url = "/page/unified/ec_order_single.jsp";
		redirectStrategy.sendRedirect(request, response, url);
		
//		return ret;
		return null;
	}


	@RequestMapping(value = "/web/unified/ecOrder/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ModelAndView update(@RequestParam(value="orderId") Long orderId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			EcOrderPOJO ecOrderPOJO = ecOrderService.findById(orderId);
			
			ret.addObject("ecOrderPOJO", ecOrderPOJO);
			ret.setViewName("/page/unified/ec_order_update");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}

	
	@RequestMapping(value = "/api/unified/ecOrder/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(EcOrderPOJO ecOrderPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (ecOrderPOJO == null) {
				throw new Exception("ecOrderPOJO can't is NULL.");
			}
			int result = -1;
			if (ecOrderPOJO.getOrderId() != null) {
				result = ecOrderService.update(ecOrderPOJO);
			} else {
				result = ecOrderService.insert(ecOrderPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/ecOrder/{orderId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public EcOrderPOJO query(@PathVariable("rderId") Long orderId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		EcOrderPOJO ret = new EcOrderPOJO();
		try {
			ret = ecOrderService.findById(orderId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/ecOrder/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<EcOrderPOJO> query(EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception {
		DataTablesPOJO<EcOrderPOJO> ret = new DataTablesPOJO<EcOrderPOJO>();
		try {
			List<EcOrderPOJO> ecOrderPOJOs = ecOrderService.finds(ecOrderSearchPOJO);
			ret.setData(ecOrderPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/ecOrder/ecOrderByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<EcOrderPOJO> queryByUserId(EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception {
		DataTablesPOJO<EcOrderPOJO> ret = new DataTablesPOJO<EcOrderPOJO>();
		try {
//			ecOrderSearchPOJO.setUserId(UserUtil.getCurrentUserId());
			ecOrderSearchPOJO.setPaginationFlage(false);
			List<EcOrderPOJO> ecOrderPOJOs = ecOrderService.finds(ecOrderSearchPOJO);
			ret.setData(ecOrderPOJOs);
		} catch (Exception e) {
			logger.error("ecOrderByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/ecOrder", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<EcOrderPOJO> findEcOrder(EcOrderSearchPOJO ecOrderSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<EcOrderPOJO> ret = new ExtjsPOJO<EcOrderPOJO>();
		List<EcOrderPOJO> ecOrderPOJOList = new ArrayList<EcOrderPOJO>();
		ecOrderPOJOList = ecOrderService.finds(ecOrderSearchPOJO);
		int total = ecOrderService.getCount(ecOrderSearchPOJO);
		
		ret.setGridModelList(ecOrderPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("ecOrderPOJOList", ecOrderPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecOrder/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(EcOrderPOJO ecOrderPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecOrderService.insert(ecOrderPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecOrder/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(EcOrderPOJO ecOrderPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecOrderService.update(ecOrderPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/ecOrder/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = ecOrderService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
