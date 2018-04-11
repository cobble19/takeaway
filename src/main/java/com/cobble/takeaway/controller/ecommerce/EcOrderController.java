package com.cobble.takeaway.controller.ecommerce;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.controller.BaseController;
import com.cobble.takeaway.controller.Oauth2Controller;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderCallWxPayParamPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderSearchPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcProductPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxJsSdkConfigRespApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderQueryReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayUnifiedOrderReqApiPOJO;
import com.cobble.takeaway.service.WxPayService;
import com.cobble.takeaway.service.ecommerce.EcOrderService;
import com.cobble.takeaway.service.ecommerce.EcProductService;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderService;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.ConfigurationUtil;
import com.cobble.takeaway.util.HttpRequestUtil;
import com.cobble.takeaway.util.UserUtil;
import com.github.wxpay.sdk.MyWXPayConfigImpl;
import com.github.wxpay.sdk.WXPayConstants;

@Controller
public class EcOrderController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(EcOrderController.class);
	
	@Autowired
	private EcOrderService ecOrderService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Autowired
	private WpOrderService wpOrderService;
	@Autowired
	private WxPayService wxPayService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private Oauth2Controller oauth2Controller;
	@Autowired
	private EcProductService ecProductService;

	private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

	@RequestMapping(value = "/web/ecommerce/ecorder/ecproduct/callwxpay", method = {RequestMethod.GET})
	public ModelAndView ecOrderUnifiedOrder(@RequestParam(value="authorizerAppId", required = false) String authorizerAppId
			, @RequestParam(value="productId") Long productId
			, @RequestParam(value="unitPrice") Integer unitPrice
			, @RequestParam(value="quantity") Integer quantity
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		
		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		String queryString = request.getQueryString();
		String url = request.getRequestURL() + "";
		if (StringUtils.isNotBlank(queryString)) {
			queryString = queryString.split("#")[0];
			url += "?" + queryString;
		}
		HttpSession session = request.getSession();
		WxJsSdkConfigRespApiPOJO wxJsSdkConfigRespApiPOJO = new WxJsSdkConfigRespApiPOJO();
		EcProductPOJO ecProductPOJO = new EcProductPOJO();
		try {
			String openId = (String) session.getAttribute(CommonConstant.PROXY_OPEN_ID);
			// 检查是否有库存
			ecProductPOJO = ecProductService.findById(productId);

			ret.setViewName("/page/ecommerce/ec_product_call_wx_pay");
			if (ecProductPOJO == null) {
				ret.addObject("success", false);
				ret.addObject("errMessage", "商品不存在");
				return ret;
			} else {
				if (ecProductPOJO.getQuantityStock() < quantity) {
					ret.addObject("success", false);
					ret.addObject("errMessage", "商品库存不足: " + ecProductPOJO.getQuantityStock());
					return ret;
				} else {
					// ecProduct derease 1
					ecProductService.decreaseStock(productId, quantity);
				}
			}
			
			// 添加ec order
			EcOrderPOJO ecOrderPOJO = new EcOrderPOJO();
			ecOrderPOJO.setOpenId(openId);
			ecOrderPOJO.setProductId(productId);
			ecOrderPOJO.setQuantity(quantity);
			ecOrderPOJO.setUnitPrice(unitPrice);
			Long userId = UserUtil.getCurrentUserId();
			ecOrderPOJO.setUserId(userId);
			ecOrderService.insert(ecOrderPOJO);
			
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
//				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			String appId = authorizerAppId;
			String jsSdkTicket = oauth2Controller.getWxJsSdkTicket(authorizerAppId);
			Long timestamp = System.currentTimeMillis() / 1000;
			String nonceStr = RandomStringUtils.randomAlphanumeric(6);
			//注意这里参数名必须全部小写，且必须有序
	        String string1 = "jsapi_ticket=" + jsSdkTicket +
	                  "&noncestr=" + nonceStr +
	                  "&timestamp=" + timestamp +
	                  "&url=" + url;

			String signature = "";
	        try {
	            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	            crypt.reset();
	            crypt.update(string1.getBytes("UTF-8"));
	            signature = byteToHex(crypt.digest());

		        logger.info("string1: {}, signature: {}", string1, signature);
	        } catch (NoSuchAlgorithmException e) {
	            logger.error("MessageDigest exception: ", e);
	        } catch (UnsupportedEncodingException e) {
	            logger.error("MessageDigest exception: ", e);
	        }
			List<String> jsApiList = Arrays.asList(StringUtils.split(messageSource.getMessage("WX.jssdk.jsApiList", null, null), ","));
			
			
			wxJsSdkConfigRespApiPOJO.setAppId(appId);
			wxJsSdkConfigRespApiPOJO.setNonceStr(nonceStr);
			wxJsSdkConfigRespApiPOJO.setTimestamp(timestamp);
			wxJsSdkConfigRespApiPOJO.setSignature(signature);
			wxJsSdkConfigRespApiPOJO.setJsApiList(jsApiList);
			
			wxJsSdkConfigRespApiPOJO.setTicket(jsSdkTicket);
			wxJsSdkConfigRespApiPOJO.setUrl(url);
			
			logger.info("wxJsSdkConfigRespApiPOJO: " + wxJsSdkConfigRespApiPOJO);
			
			
			// 调用wx unified order api获取prepay_id
//			Map unifiedOrderReqMap = new HashMap();
			WxPayUnifiedOrderReqApiPOJO wxPayUnifiedOrderReqApiPOJO = new WxPayUnifiedOrderReqApiPOJO();
			String mchId = MyWXPayConfigImpl.getInstance().getMchID();
			String body = ecProductPOJO.getProductName();
			String outTradeNo = wpOrderService.getNextOutTradeNo();
			String totalFee = (unitPrice * quantity) + "";
			String spbillCreateIp = HttpRequestUtil.getIpAddr(request);
//			String notifyUrl = HttpRequestUtil.getBase(request) + "/api/wxpay/notify";
			String notifyUrl = ConfigurationUtil.getPropertiesConfig().getString("WXPAY.notifyUrl", "http://www.deweiyizhan.com/api/wxpay/notify");
			String tradeType = "JSAPI";
			timestamp = System.currentTimeMillis() / 1000;
			nonceStr = RandomStringUtils.randomAlphanumeric(6);
			wxPayUnifiedOrderReqApiPOJO.setAppId(appId);
			wxPayUnifiedOrderReqApiPOJO.setMchId(mchId);
			wxPayUnifiedOrderReqApiPOJO.setNonceStr(nonceStr);
			wxPayUnifiedOrderReqApiPOJO.setDeviceInfo("WEB");
			wxPayUnifiedOrderReqApiPOJO.setBody(body);
			wxPayUnifiedOrderReqApiPOJO.setAttach("attach");
			wxPayUnifiedOrderReqApiPOJO.setOutTradeNo(outTradeNo);
			wxPayUnifiedOrderReqApiPOJO.setTotalFee(totalFee);
			wxPayUnifiedOrderReqApiPOJO.setSpbillCreateIp(spbillCreateIp);
			wxPayUnifiedOrderReqApiPOJO.setNotifyUrl(notifyUrl);
			wxPayUnifiedOrderReqApiPOJO.setTradeType(tradeType);
			wxPayUnifiedOrderReqApiPOJO.setOpenId(openId);
			
			wxPayUnifiedOrderReqApiPOJO.setSignType(WXPayConstants.MD5);

			Map unifiedOrderRespMap = wxPayService.unifiedOrder(wxPayUnifiedOrderReqApiPOJO);
			try {
				ret.addObject("success", true);
				
				// 创建weixinpay order
				WpOrderPOJO wpOrderPOJO = new WpOrderPOJO();
				org.apache.commons.beanutils.BeanUtils.copyProperties(wpOrderPOJO, wxPayUnifiedOrderReqApiPOJO);
				wpOrderPOJO.setEcOrderId(ecOrderPOJO.getOrderId());
				wpOrderPOJO.setCreateDateTime(new Date());
				wpOrderService.insert(wpOrderPOJO);
			} catch (Exception e) {
				logger.error("wpOrderService insert exception: ", e);
			}
			
			// orderquery
			WxPayOrderQueryReqApiPOJO wxPayOrderQueryReqApiPOJO = new WxPayOrderQueryReqApiPOJO();
			wxPayOrderQueryReqApiPOJO.setAppId(appId);
			wxPayOrderQueryReqApiPOJO.setMchId(mchId);
			wxPayOrderQueryReqApiPOJO.setOutTradeNo(outTradeNo);
			wxPayOrderQueryReqApiPOJO.setNonceStr(nonceStr + 1);
			wxPayOrderQueryReqApiPOJO.setSignType(WXPayConstants.MD5);
			Map orderQueryRespMap = wxPayService.orderQuery(wxPayOrderQueryReqApiPOJO);
			// end orderquery
			
			Map<String, String> jsPayMap = new HashMap<String, String>();
			jsPayMap.put("appId", appId);     //公众号名称，由商户传入     
			jsPayMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");        //时间戳，自1970年以来的秒数     
			jsPayMap.put("nonceStr", nonceStr + "1"); //随机串     
			jsPayMap.put("package", "prepay_id=" + unifiedOrderRespMap.get("prepay_id"));     
			jsPayMap.put("signType", WXPayConstants.MD5);        //微信签名方式：
			jsPayMap = wxPayService.appendSign(jsPayMap);
			jsPayMap.put("prepayId", unifiedOrderRespMap.get("prepay_id") + "");  
			jsPayMap.put("packageUo", "prepay_id=" + unifiedOrderRespMap.get("prepay_id")); 
			
			ret.addObject("orderQueryRespMap", orderQueryRespMap);
			ret.addObject("unifiedOrderRespMap", unifiedOrderRespMap);
			ret.addObject("jsPayMap", jsPayMap);
		} catch (Exception e) {
			logger.error("insert error.", e);
		}

		ret.addObject("wxJsSdkConfigRespApiPOJO", wxJsSdkConfigRespApiPOJO);
		return ret;
	}

	@RequestMapping(value = "/api/ecommerce/ecorder/ecproduct/callwxpay", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Map ecOrderUnifiedOrderApi(EcOrderCallWxPayParamPOJO ecOrderCallWxPayParamPOJO
			, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Model ret = model;
		
		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		String queryString = request.getQueryString();
		String url = request.getRequestURL() + "";
		if (StringUtils.isNotBlank(queryString)) {
			queryString = queryString.split("#")[0];
			url += "?" + queryString;
		}
		HttpSession session = request.getSession();
		
		String authorizerAppId = ecOrderCallWxPayParamPOJO.getAuthorizerAppId();
		Long productId = ecOrderCallWxPayParamPOJO.getProductId();
		Integer unitPrice = ecOrderCallWxPayParamPOJO.getUnitPrice();
		Integer quantity = ecOrderCallWxPayParamPOJO.getQuantity();
		
		EcProductPOJO ecProductPOJO = new EcProductPOJO();
		try {
			String openId = (String) session.getAttribute(CommonConstant.PROXY_OPEN_ID);
			// 检查是否有库存
			ecProductPOJO = ecProductService.findById(productId);

			if (ecProductPOJO == null) {
				ret.addAttribute("success", false);
				ret.addAttribute("errMessage", "商品不存在");
				return ret.asMap();
			} else {
				if (ecProductPOJO.getQuantityStock() < quantity) {
					ret.addAttribute("success", false);
					ret.addAttribute("errMessage", "商品库存不足: " + ecProductPOJO.getQuantityStock());
					return ret.asMap();
				} else {
					// ecProduct derease 1
					ecProductService.decreaseStock(productId, quantity);
				}
			}
			
			// 添加ec order
			EcOrderPOJO ecOrderPOJO = new EcOrderPOJO();
			ecOrderPOJO.setOpenId(openId);
			ecOrderPOJO.setProductId(productId);
			ecOrderPOJO.setQuantity(quantity);
			ecOrderPOJO.setUnitPrice(unitPrice);
			Long userId = UserUtil.getCurrentUserId();
			ecOrderPOJO.setUserId(userId);
			ecOrderService.insert(ecOrderPOJO);
			
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
//				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			String appId = authorizerAppId;
			String nonceStr = RandomStringUtils.randomAlphanumeric(6);
			
			// 调用wx unified order api获取prepay_id
//			Map unifiedOrderReqMap = new HashMap();
			WxPayUnifiedOrderReqApiPOJO wxPayUnifiedOrderReqApiPOJO = new WxPayUnifiedOrderReqApiPOJO();
			String mchId = MyWXPayConfigImpl.getInstance().getMchID();
			String body = ecProductPOJO.getProductName();
			String outTradeNo = wpOrderService.getNextOutTradeNo();
			String totalFee = (unitPrice * quantity) + "";
			String spbillCreateIp = HttpRequestUtil.getIpAddr(request);
//			String notifyUrl = HttpRequestUtil.getBase(request) + "/api/wxpay/notify";
			String notifyUrl = ConfigurationUtil.getPropertiesConfig().getString("WXPAY.notifyUrl", "http://www.deweiyizhan.com/api/wxpay/notify");
			String tradeType = "JSAPI";
			nonceStr = RandomStringUtils.randomAlphanumeric(6);
			wxPayUnifiedOrderReqApiPOJO.setAppId(appId);
			wxPayUnifiedOrderReqApiPOJO.setMchId(mchId);
			wxPayUnifiedOrderReqApiPOJO.setNonceStr(nonceStr);
			wxPayUnifiedOrderReqApiPOJO.setDeviceInfo("WEB");
			wxPayUnifiedOrderReqApiPOJO.setBody(body);
			wxPayUnifiedOrderReqApiPOJO.setAttach("attach");
			wxPayUnifiedOrderReqApiPOJO.setOutTradeNo(outTradeNo);
			wxPayUnifiedOrderReqApiPOJO.setTotalFee(totalFee);
			wxPayUnifiedOrderReqApiPOJO.setSpbillCreateIp(spbillCreateIp);
			wxPayUnifiedOrderReqApiPOJO.setNotifyUrl(notifyUrl);
			wxPayUnifiedOrderReqApiPOJO.setTradeType(tradeType);
			wxPayUnifiedOrderReqApiPOJO.setOpenId(openId);
			
			wxPayUnifiedOrderReqApiPOJO.setSignType(WXPayConstants.MD5);

			Map unifiedOrderRespMap = wxPayService.unifiedOrder(wxPayUnifiedOrderReqApiPOJO);
			try {
				ret.addAttribute("success", true);
				
				// 创建weixinpay order
				WpOrderPOJO wpOrderPOJO = new WpOrderPOJO();
				org.apache.commons.beanutils.BeanUtils.copyProperties(wpOrderPOJO, wxPayUnifiedOrderReqApiPOJO);
				wpOrderPOJO.setEcOrderId(ecOrderPOJO.getOrderId());
				wpOrderPOJO.setCreateDateTime(new Date());
				wpOrderService.insert(wpOrderPOJO);
			} catch (Exception e) {
				logger.error("wpOrderService insert exception: ", e);
			}
			
			// orderquery 查询微信支付订单
			WxPayOrderQueryReqApiPOJO wxPayOrderQueryReqApiPOJO = new WxPayOrderQueryReqApiPOJO();
			wxPayOrderQueryReqApiPOJO.setAppId(appId);
			wxPayOrderQueryReqApiPOJO.setMchId(mchId);
			wxPayOrderQueryReqApiPOJO.setOutTradeNo(outTradeNo);
			wxPayOrderQueryReqApiPOJO.setNonceStr(nonceStr + 1);
			wxPayOrderQueryReqApiPOJO.setSignType(WXPayConstants.MD5);
			Map orderQueryRespMap = wxPayService.orderQuery(wxPayOrderQueryReqApiPOJO);
			// end orderquery
			
			Map<String, String> jsPayMap = new HashMap<String, String>();
			jsPayMap.put("appId", appId);     //公众号名称，由商户传入     
			jsPayMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");        //时间戳，自1970年以来的秒数     
			jsPayMap.put("nonceStr", nonceStr + "1"); //随机串     
			jsPayMap.put("package", "prepay_id=" + unifiedOrderRespMap.get("prepay_id"));     
			jsPayMap.put("signType", WXPayConstants.MD5);        //微信签名方式：
			jsPayMap = wxPayService.appendSign(jsPayMap);
			jsPayMap.put("prepayId", unifiedOrderRespMap.get("prepay_id") + "");  
			jsPayMap.put("packageUo", "prepay_id=" + unifiedOrderRespMap.get("prepay_id")); 
			
			ret.addAttribute("orderQueryRespMap", orderQueryRespMap);
			ret.addAttribute("unifiedOrderRespMap", unifiedOrderRespMap);
			ret.addAttribute("jsPayMap", jsPayMap);
		} catch (Exception e) {
			logger.error("insert error.", e);
		}

		return ret.asMap();
	}

	@RequestMapping(value = "/web/ecommerce/ecorder/ecproduct/choose", method = {RequestMethod.GET})
	public ModelAndView ecOrderChoosePay(
			@RequestParam(value="authorizerAppId", required = false) String authorizerAppId
			,@RequestParam(value="productId") Long productId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		
		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		String queryString = request.getQueryString();
		String url = request.getRequestURL() + "";
		if (StringUtils.isNotBlank(queryString)) {
			queryString = queryString.split("#")[0];
			url += "?" + queryString;
		}
		HttpSession session = request.getSession();
		WxJsSdkConfigRespApiPOJO wxJsSdkConfigRespApiPOJO = new WxJsSdkConfigRespApiPOJO();
		EcProductPOJO ecProductPOJO = new EcProductPOJO();
		try {
			ret.setViewName("/page/ecommerce/ec_product_choose");
			// get ecProductPOJO
			ecProductPOJO = ecProductService.findById(productId);

			if (ecProductPOJO == null) {
				ret.addObject("success", false);
				ret.addObject("errMessage", "商品不存在");
				return ret;
			} else {
				if (ecProductPOJO.getQuantityStock() < 1) {
					ret.addObject("success", false);
					ret.addObject("errMessage", "商品库存不足: " + ecProductPOJO.getQuantityStock());
					return ret;
				}
			}
			
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
//				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			String appId = authorizerAppId;
			String jsSdkTicket = oauth2Controller.getWxJsSdkTicket(authorizerAppId);
			Long timestamp = System.currentTimeMillis() / 1000;
			String nonceStr = RandomStringUtils.randomAlphanumeric(6);
			//注意这里参数名必须全部小写，且必须有序
	        String string1 = "jsapi_ticket=" + jsSdkTicket +
	                  "&noncestr=" + nonceStr +
	                  "&timestamp=" + timestamp +
	                  "&url=" + url;

			String signature = "";
	        try {
	            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	            crypt.reset();
	            crypt.update(string1.getBytes("UTF-8"));
	            signature = byteToHex(crypt.digest());

		        logger.info("string1: {}, signature: {}", string1, signature);
	        } catch (NoSuchAlgorithmException e) {
	            logger.error("MessageDigest exception: ", e);
	        } catch (UnsupportedEncodingException e) {
	            logger.error("MessageDigest exception: ", e);
	        }
			List<String> jsApiList = Arrays.asList(StringUtils.split(messageSource.getMessage("WX.jssdk.jsApiList", null, null), ","));
			
			
			wxJsSdkConfigRespApiPOJO.setAppId(appId);
			wxJsSdkConfigRespApiPOJO.setNonceStr(nonceStr);
			wxJsSdkConfigRespApiPOJO.setTimestamp(timestamp);
			wxJsSdkConfigRespApiPOJO.setSignature(signature);
			wxJsSdkConfigRespApiPOJO.setJsApiList(jsApiList);
			
			wxJsSdkConfigRespApiPOJO.setTicket(jsSdkTicket);
			wxJsSdkConfigRespApiPOJO.setUrl(url);
			
			logger.info("wxJsSdkConfigRespApiPOJO: " + wxJsSdkConfigRespApiPOJO);
			
			
		} catch (Exception e) {
			logger.error("insert error.", e);
		}

		ret.addObject("wxJsSdkConfigRespApiPOJO", wxJsSdkConfigRespApiPOJO);
		ret.addObject("ecProductPOJO", ecProductPOJO);
		return ret;
	}
	
	/////////////////////////below is DB, above is call wx pay

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