package com.cobble.takeaway.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.HtmlConvertedPOJO;
import com.cobble.takeaway.pojo.ListParamPOJO;
import com.cobble.takeaway.pojo.RelWxAttrPOJO;
import com.cobble.takeaway.pojo.RelWxLinkPOJO;
import com.cobble.takeaway.pojo.RelWxTemplateUserPOJO;
import com.cobble.takeaway.pojo.RelWxTemplateUserSearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.pojo.WxAttrPOJO;
import com.cobble.takeaway.pojo.WxLinkPOJO;
import com.cobble.takeaway.pojo.WxLinkSearchPOJO;
import com.cobble.takeaway.pojo.WxSecPOJO;
import com.cobble.takeaway.pojo.WxTemplatePOJO;
import com.cobble.takeaway.service.UserService;
import com.cobble.takeaway.service.WxAttrService;
import com.cobble.takeaway.service.WxLinkService;
import com.cobble.takeaway.service.WxTemplateService;
import com.cobble.takeaway.util.FileUtil;
import com.cobble.takeaway.util.HttpRequestUtil;
import com.cobble.takeaway.util.JsonUtils;
import com.cobble.takeaway.util.UserUtil;
import com.fasterxml.jackson.core.type.TypeReference;

@Controller
public class WxLinkController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxLinkController.class);
	
	@Autowired
	private WxAttrService wxAttrService;
	
	@Autowired
	private WxLinkService wxLinkService;
	@Autowired
	private WxTemplateService wxTemplateService;
	@Autowired
	private UserService userService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/web/media/wxAttrs/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO addWxAttrs4Web(@RequestBody String body, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			
			List<WxAttrPOJO> wxAttrPOJOs = (List<WxAttrPOJO>) JsonUtils.convertToJavaBeanList(body, new TypeReference<List<WxAttrPOJO>>() { });
			if (CollectionUtils.isEmpty(wxAttrPOJOs)) {
				throw new Exception("wxAttrPOJOs can't is NULL.");
			}
			
			String base = HttpRequestUtil.getBase(request);
			
			Long userId = UserUtil.getCurrentUser().getUserId();
			
			for (WxAttrPOJO wxAttrPOJO : wxAttrPOJOs) {
				if (userId.longValue() != wxAttrPOJO.getUserId()) {
					throw new Exception("User is not yourself");
				}
				
				int result = -1;
				WxAttrPOJO temp = null;
				List<WxAttrPOJO> wxAttrPOJOTemps = wxAttrService.findsByIds(wxAttrPOJO.getUserId(), wxAttrPOJO.getWxTemplateId()
						, wxAttrPOJO.getOrderNo(), wxAttrPOJO.getWxSecOrderNo());
				if (!CollectionUtils.isEmpty(wxAttrPOJOTemps)) {
					temp = wxAttrPOJOTemps.get(0);
				}
				
				if (temp == null) {
					wxAttrService.insert(wxAttrPOJO);
					RelWxAttrPOJO relWxAttrPOJO = new RelWxAttrPOJO();
					relWxAttrPOJO.setWxAttrId(wxAttrPOJO.getWxAttrId());
					relWxAttrPOJO.setUserId(userId);
					relWxAttrPOJO.setWxTemplateId(wxAttrPOJO.getWxTemplateId());
					wxAttrService.insertRelWxAttr(relWxAttrPOJO);
				} else {
					wxAttrPOJO.setWxAttrId(temp.getWxAttrId());
					wxAttrService.update(wxAttrPOJO);
				}
			}
			
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/media/wxAttr/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO addWxAttr4Web(WxAttrPOJO wxAttrPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxAttrPOJO == null) {
				throw new Exception("wxAttrPOJO can't is NULL.");
			}
			
			String base = HttpRequestUtil.getBase(request);
			
			Long userId = UserUtil.getCurrentUser().getUserId();
			
			if (userId.longValue() != wxAttrPOJO.getUserId()) {
				throw new Exception("User is not yourself");
			}
			
			int result = -1;
			WxAttrPOJO temp = null;
			List<WxAttrPOJO> wxAttrPOJOs = wxAttrService.findsByIds(wxAttrPOJO.getUserId(), wxAttrPOJO.getWxTemplateId()
					, wxAttrPOJO.getOrderNo(), wxAttrPOJO.getWxSecOrderNo());
			if (!CollectionUtils.isEmpty(wxAttrPOJOs)) {
				temp = wxAttrPOJOs.get(0);
			}
			
			if (temp == null) {
				wxAttrService.insert(wxAttrPOJO);
				RelWxAttrPOJO relWxAttrPOJO = new RelWxAttrPOJO();
				relWxAttrPOJO.setWxAttrId(wxAttrPOJO.getWxAttrId());
				relWxAttrPOJO.setUserId(userId);
				relWxAttrPOJO.setWxTemplateId(wxAttrPOJO.getWxTemplateId());
				wxAttrService.insertRelWxAttr(relWxAttrPOJO);
			} else {
				wxAttrPOJO.setWxAttrId(temp.getWxAttrId());
				wxAttrService.update(wxAttrPOJO);
			}
			
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/media/wxAttr"/*, produces = {MediaType.APPLICATION_JSON_VALUE}*/)
	public ModelAndView wxSec(@RequestParam(value = "wxTemplateId") Long wxTemplateId,
			@RequestParam(value = "userId") Long userId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			
			List<WxSecPOJO> wxSecPOJOs = wxAttrService.findWxSecsByIds(userId, wxTemplateId);
			
			logger.info("wxSecPOJOs: " + wxSecPOJOs);
			
			String base = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort()
					+ request.getServletContext().getContextPath();
			
			if (!CollectionUtils.isEmpty(wxSecPOJOs)) {
				for (int i = 0; i < wxSecPOJOs.size(); i++) {
					WxSecPOJO temp = wxSecPOJOs.get(i);
					List<WxAttrPOJO> wxAttrPOJOs = temp.getWxAttrPOJOs();
					if (!CollectionUtils.isEmpty(wxAttrPOJOs)) {
						for (WxAttrPOJO wxAttrPOJO : wxAttrPOJOs) {
							String wxAttrData = wxAttrPOJO.getWxAttrData();
							if (StringUtils.isNotBlank(wxAttrData) && wxAttrData.startsWith("files/images")) {
								if (!UrlUtils.isAbsoluteUrl(wxAttrData)) {
									String imgSrc = base + "/" + wxAttrData;
									wxAttrPOJO.setWxAttrData(imgSrc);
								}
							}
						}
					}
					
					ret.addObject("wxSecPOJO" + temp.getWxSecOrderNo(), temp);
				}
			}
			
			ret.setViewName("/page/media/wx_attr");
		} catch (Exception e) {
			logger.error("WX LINK INDEX error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/media/wxLink"/*, produces = {MediaType.APPLICATION_JSON_VALUE}*/)
	public ModelAndView wxIndexLink(/*@RequestParam(value = "wxTemplateId") Long wxTemplateId,
			@RequestParam(value = "userId") Long userId*/WxLinkPOJO wxLinkPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			/*WxLinkPOJO wxLinkPOJO = new WxLinkPOJO();
			wxLinkPOJO.setWxTemplateId(wxTemplateId);
			wxLinkPOJO.setUserId(userId);*/
			List<WxLinkPOJO> wxLinkPOJOs = wxLinkService.findsByIds(wxLinkPOJO);
			/*ret.addObject("wxLinkPOJOs", wxLinkPOJOs);*/
			
			String base = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort()
					+ request.getServletContext().getContextPath();
			
			if (!CollectionUtils.isEmpty(wxLinkPOJOs)) {
				for (int i = 0; i < wxLinkPOJOs.size(); i++) {
					WxLinkPOJO temp = wxLinkPOJOs.get(i);
					if (!UrlUtils.isAbsoluteUrl(temp.getImgSrc())) {
						String imgSrc = base + "/" + temp.getImgSrc();
						temp.setImgSrc(imgSrc);
					}
					ret.addObject("wxLinkPOJO" + temp.getOrderNo(), temp);
				}
			}
			
			ret.setViewName("/page/media/wx_link_index");
		} catch (Exception e) {
			logger.error("WX LINK INDEX error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/wx/{wxIndexCode}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public HtmlConvertedPOJO wxIndexStatic(@PathVariable(value = "wxIndexCode") String wxIndexCode,
			/*@RequestParam(value = "userId", required = false) Long userId,*/ Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HtmlConvertedPOJO ret = new HtmlConvertedPOJO();
		try {
//			Long userId = UserUtil.getCurrentUser().getUserId();
			UserPOJO userPOJO = userService.findUserByIndexCode(wxIndexCode);
			
			/*WxTemplateSearchPOJO wxTemplateSearchPOJO = new WxTemplateSearchPOJO();
			wxTemplateSearchPOJO.setUserId(userPOJO.getUserId());
			
			WxTemplatePOJO wxTemplatePOJO = null;
			List<WxTemplatePOJO> wxTemplatePOJOs = wxTemplateService.findsByUserId(wxTemplateSearchPOJO);
			
			if (!CollectionUtils.isEmpty(wxTemplatePOJOs)) {
				for (WxTemplatePOJO temp : wxTemplatePOJOs) {
					if (temp.getRelWxTemplateUserPOJO().getDisplayFlag() == 1) {
						wxTemplatePOJO = temp;
					}
				}
			}
			
			String html = wxTemplatePOJO.getRelWxTemplateUserPOJO().getWxStaticPage();*/
			
			String html = userPOJO.getRelWxIndexMapPOJO().getWxStaticPage();
			
			String base = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort()
					+ request.getServletContext().getContextPath();
			if (!UrlUtils.isAbsoluteUrl(html)) {
				html = base + "/" + html;
			}
			
			redirectStrategy.sendRedirect(request, response, html);
			
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("wx index static page error: {}", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/media/wxLink/toHtml", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public HtmlConvertedPOJO wxLink2Html(@RequestParam(value = "wxTemplateId", required = true) Long wxTemplateId,
			/*@RequestParam(value = "userId", required = false) Long userId,*/ Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HtmlConvertedPOJO ret = new HtmlConvertedPOJO();
		try {
			Long userId = UserUtil.getCurrentUser().getUserId();
			
			WxTemplatePOJO wxTemplatePOJO = wxTemplateService.findById(wxTemplateId);
			String fromUrl = wxTemplatePOJO.getWxPage();
			int indexOf = fromUrl.indexOf("/");
			if (indexOf < 0) {
				indexOf = 0;
			}
			String tempUrl = fromUrl.replace("/", "_").replace("\\", "_");
			String toFilePath = "wx_index" + "_" + userId + "_" + wxTemplateId + "_" + tempUrl + ".html";
			
			String dir = messageSource.getMessage("files.directory", null, null);
			String toFileFullPath = dir + File.separator + "htmls" + File.separator + toFilePath;
			toFileFullPath = toFileFullPath.replace("/", File.separator).replace("\\", File.separator);
			
			String base = request.getScheme() + "://" + /*request.getServerName()*/ "127.0.0.1"
					+ ":" + request.getServerPort()
					+ request.getServletContext().getContextPath();
			if (!UrlUtils.isAbsoluteUrl(fromUrl)) {
				fromUrl = base + "/" + fromUrl;
			}
			
			fromUrl += "?wxTemplateId=" + wxTemplateId + "&userId=" + userId;
			
			ret = FileUtil.url2Html(fromUrl, toFileFullPath, request.getServerName(), UserUtil.getCurrentUser().getNickname());
			
			String htmlPath = "files/htmls" + "/" + toFilePath;
			htmlPath = htmlPath.replace("\\", "/");
			ret.setHtmlPath(htmlPath);
			
			ret.setSuccess(true);
			
			// insert or update RelWxTemplateUser

			RelWxTemplateUserPOJO relWxTemplateUserPOJO = new RelWxTemplateUserPOJO();
			relWxTemplateUserPOJO.setUserId(userId);
			relWxTemplateUserPOJO.setWxTemplateId(wxTemplateId);
			relWxTemplateUserPOJO.setWxStaticPage(htmlPath);
			
			List<RelWxTemplateUserPOJO> relWxTemplateUserPOJOs = wxTemplateService.findRelWxTemplateUsers(userId, wxTemplateId);
			if (CollectionUtils.isEmpty(relWxTemplateUserPOJOs)) {
				wxTemplateService.insertRelWxTemplateUser(relWxTemplateUserPOJO);
			} else {
				wxTemplateService.updateRelWxTemplateUser4WxStaticPage(relWxTemplateUserPOJO);
			}
			
			// for discuss with zzd, user rel_index_map_code TABLE
			/*RelWxIndexMapPOJO relWxIndexMapPOJO = new RelWxIndexMapPOJO();
			relWxIndexMapPOJO.setUserId(userId);
			relWxIndexMapPOJO.setWxTemplateId(wxTemplateId);
			relWxIndexMapPOJO.setWxStaticPage(htmlPath);
			wxTemplateService.updateRelWxIndexMap4WxStaticPage(relWxIndexMapPOJO);*/
			
		} catch (Exception e) {
			logger.error("toHtml error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/media/toHtml", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	@Deprecated
	public HtmlConvertedPOJO toHtml(@RequestParam(value = "fromFullUrl", required = true) String fromFullUrl,
			@RequestParam(value = "toFilePath", required = true) String toFilePath, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HtmlConvertedPOJO ret = new HtmlConvertedPOJO();
		try {
			String dir = messageSource.getMessage("files.directory", null, null);
			String toFileFullPath = dir + File.separator + "htmls" + File.separator + toFilePath;
			
			ret = FileUtil.url2Html(fromFullUrl, toFileFullPath);
			
			String htmlPath = "files/htmls" + "/" + toFilePath;
			htmlPath = htmlPath.replace("\\", "/");
			ret.setHtmlPath(htmlPath);
			
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("toHtml error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/media/wxLink/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4Web(WxLinkPOJO wxLinkPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxLinkPOJO == null) {
				throw new Exception("wxLinkPOJO can't is NULL.");
			}
			
			String imgSrc = wxLinkPOJO.getImgSrc();
			String base = HttpRequestUtil.getBase(request);
			if (imgSrc.indexOf(base) > -1) {
				imgSrc = imgSrc.substring(base.length() + 1);
				wxLinkPOJO.setImgSrc(imgSrc);
			}
			
			
			Long userId = UserUtil.getCurrentUser().getUserId();
			wxLinkPOJO.setUserId(userId);
			
			int result = -1;
			/*result = wxLinkService.getCountByKey(wxLinkPOJO);
			if (result > 0) {
				result = wxLinkService.updateByKey(wxLinkPOJO);
			} else {
				result = wxLinkService.insert(wxLinkPOJO);
			}*/
			WxLinkPOJO temp = null;
			List<WxLinkPOJO> wxLinkPOJOs = wxLinkService.findsByIds(wxLinkPOJO);
			if (!CollectionUtils.isEmpty(wxLinkPOJOs)) {
				temp = wxLinkPOJOs.get(0);
			}
			
			if (temp == null) {
				wxLinkService.insert(wxLinkPOJO);
				RelWxLinkPOJO relWxLinkPOJO = new RelWxLinkPOJO();
				relWxLinkPOJO.setUserId(userId);
				relWxLinkPOJO.setWxLinkId(wxLinkPOJO.getWxLinkId());
				relWxLinkPOJO.setWxTemplateId(wxLinkPOJO.getWxTemplateId());
				wxLinkService.insertRelWxLink(relWxLinkPOJO);
			} else {
				wxLinkPOJO.setWxLinkId(temp.getWxLinkId());
				wxLinkService.update(wxLinkPOJO);
			}
			
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/media/wxLink/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxLinkPOJO> listWxLink(WxLinkPOJO wxLinkPOJO, Model model) throws Exception {
		ExtjsPOJO<WxLinkPOJO> ret = new ExtjsPOJO<WxLinkPOJO>();
		List<WxLinkPOJO> wxLinkPOJOList = new ArrayList<WxLinkPOJO>();
		/*wxLinkPOJOList = wxLinkService.finds(wxLinkSearchPOJO);
		int total = wxLinkService.getCount(wxLinkSearchPOJO);*/
		
		Long userId = UserUtil.getCurrentUser().getUserId();
		wxLinkPOJO.setUserId(userId);
		wxLinkPOJOList = wxLinkService.findsByIds(wxLinkPOJO);
		int total = CollectionUtils.isEmpty(wxLinkPOJOList) ? 0 : wxLinkPOJOList.size();
		
		ret.setGridModelList(wxLinkPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		/*model.addAttribute("wxLinkPOJOList", wxLinkPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxLink", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxLinkPOJO> findWxLink(WxLinkSearchPOJO wxLinkSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<WxLinkPOJO> ret = new ExtjsPOJO<WxLinkPOJO>();
		List<WxLinkPOJO> wxLinkPOJOList = new ArrayList<WxLinkPOJO>();
		wxLinkPOJOList = wxLinkService.finds(wxLinkSearchPOJO);
		int total = wxLinkService.getCount(wxLinkSearchPOJO);
		
		ret.setGridModelList(wxLinkPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("wxLinkPOJOList", wxLinkPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxLink/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(WxLinkPOJO wxLinkPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxLinkService.insert(wxLinkPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxLink/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(WxLinkPOJO wxLinkPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxLinkService.update(wxLinkPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxLink/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxLinkService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
