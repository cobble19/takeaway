package com.cobble.takeaway.controller.weixin;

import com.cobble.takeaway.controller.BaseController;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.weixin.WxMsgEventLogPOJO;
import com.cobble.takeaway.pojo.weixin.WxMsgEventLogSearchPOJO;
import com.cobble.takeaway.service.weixin.WxMsgEventLogService;
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
public class WxMsgEventLogController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxMsgEventLogController.class);
	
	@Autowired
	private WxMsgEventLogService wxMsgEventLogService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	

	@RequestMapping(value = "/web/unified/wxMsgEventLog/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4WebUnified(WxMsgEventLogPOJO wxMsgEventLogPOJO, Model model,
									 HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxMsgEventLogPOJO == null) {
				throw new Exception("wxMsgEventLogPOJO can't is NULL.");
			}
			int result = -1;
			if (wxMsgEventLogPOJO.getWxMsgEventLogId() != null) {
				result = wxMsgEventLogService.update(wxMsgEventLogPOJO);
			} else {
				result = wxMsgEventLogService.insert(wxMsgEventLogPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/unified/wxMsgEventLog_detail.jsp?wxMsgEventLogId=" + wxMsgEventLogPOJO.getWxMsgEventLogId();
//		url = "/web/unified/usercenter#wxMsgEventLog";
		url = "/page/unified/wx_msg_event_log_single.jsp";
		redirectStrategy.sendRedirect(request, response, url);
		
//		return ret;
		return null;
	}


	@RequestMapping(value = "/web/unified/wxMsgEventLog/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ModelAndView update(@RequestParam(value="wxMsgEventLogId") Long wxMsgEventLogId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			WxMsgEventLogPOJO wxMsgEventLogPOJO = wxMsgEventLogService.findById(wxMsgEventLogId);
			
			ret.addObject("wxMsgEventLogPOJO", wxMsgEventLogPOJO);
			ret.setViewName("/page/unified/wx_msg_event_log_update");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}

	
	@RequestMapping(value = "/api/unified/wxMsgEventLog/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(WxMsgEventLogPOJO wxMsgEventLogPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxMsgEventLogPOJO == null) {
				throw new Exception("wxMsgEventLogPOJO can't is NULL.");
			}
			int result = -1;
			if (wxMsgEventLogPOJO.getWxMsgEventLogId() != null) {
				result = wxMsgEventLogService.update(wxMsgEventLogPOJO);
			} else {
				result = wxMsgEventLogService.insert(wxMsgEventLogPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMsgEventLog/{wxMsgEventLogId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public WxMsgEventLogPOJO query(@PathVariable("wxMsgEventLogId") Long wxMsgEventLogId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WxMsgEventLogPOJO ret = new WxMsgEventLogPOJO();
		try {
			ret = wxMsgEventLogService.findById(wxMsgEventLogId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMsgEventLog/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxMsgEventLogPOJO> query(WxMsgEventLogSearchPOJO wxMsgEventLogSearchPOJO) throws Exception {
		DataTablesPOJO<WxMsgEventLogPOJO> ret = new DataTablesPOJO<WxMsgEventLogPOJO>();
		try {
			List<WxMsgEventLogPOJO> wxMsgEventLogPOJOs = wxMsgEventLogService.finds(wxMsgEventLogSearchPOJO);
			ret.setData(wxMsgEventLogPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/wxMsgEventLog/wxMsgEventLogByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxMsgEventLogPOJO> queryByUserId(WxMsgEventLogSearchPOJO wxMsgEventLogSearchPOJO) throws Exception {
		DataTablesPOJO<WxMsgEventLogPOJO> ret = new DataTablesPOJO<WxMsgEventLogPOJO>();
		try {
//			wxMsgEventLogSearchPOJO.setUserId(UserUtil.getCurrentUserId());
			wxMsgEventLogSearchPOJO.setPaginationFlage(false);
			List<WxMsgEventLogPOJO> wxMsgEventLogPOJOs = wxMsgEventLogService.finds(wxMsgEventLogSearchPOJO);
			ret.setData(wxMsgEventLogPOJOs);
		} catch (Exception e) {
			logger.error("wxMsgEventLogByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/wxMsgEventLog", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxMsgEventLogPOJO> findWxMsgEventLog(WxMsgEventLogSearchPOJO wxMsgEventLogSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<WxMsgEventLogPOJO> ret = new ExtjsPOJO<WxMsgEventLogPOJO>();
		List<WxMsgEventLogPOJO> wxMsgEventLogPOJOList = new ArrayList<WxMsgEventLogPOJO>();
		wxMsgEventLogPOJOList = wxMsgEventLogService.finds(wxMsgEventLogSearchPOJO);
		int total = wxMsgEventLogService.getCount(wxMsgEventLogSearchPOJO);
		
		ret.setGridModelList(wxMsgEventLogPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("wxMsgEventLogPOJOList", wxMsgEventLogPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMsgEventLog/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(WxMsgEventLogPOJO wxMsgEventLogPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMsgEventLogService.insert(wxMsgEventLogPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMsgEventLog/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(WxMsgEventLogPOJO wxMsgEventLogPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMsgEventLogService.update(wxMsgEventLogPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMsgEventLog/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMsgEventLogService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
