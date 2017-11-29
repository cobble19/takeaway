package com.cobble.takeaway.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.util.CacheUtil;

@Controller
public class ShortLinkController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(ShortLinkController.class);
	
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
    @RequestMapping(value = "/web/unified/t/{shortLink}", method = {RequestMethod.GET})
	public ModelAndView userProfile(Model model, @PathVariable(value = "shortLink") String shortLink
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();

		try {
			String url = CacheUtil.getInstance().get(shortLink);
			if (StringUtils.isNotBlank(url)) {
				redirectStrategy.sendRedirect(request, response, url);
				return null;
			} else {
				ret.setViewName("/errorPage/short_link_error");
			}
		} catch (Exception e) {
			logger.error("Can't get Authorizer: {}", e);
		}

		return ret;
	}
    
}
