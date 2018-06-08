package com.cobble.takeaway.controller.ecommerce;

import com.cobble.takeaway.controller.BaseController;
import com.cobble.takeaway.controller.Oauth2Controller;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderCallWxPayParamPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderSearchPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcProductPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoSearchPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxCardMgrCardApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxCardMgrGetCardListRespApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxJsSdkConfigCardChoosePOJO;
import com.cobble.takeaway.pojo.weixin.api.WxJsSdkConfigRespApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderRespPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderRespSearchPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderSearchPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderQueryReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayUnifiedOrderReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayUnifiedOrderRespApiPOJO;
import com.cobble.takeaway.service.WxAuthorizerInfoService;
import com.cobble.takeaway.service.WxPayService;
import com.cobble.takeaway.service.ecommerce.EcOrderService;
import com.cobble.takeaway.service.ecommerce.EcProductService;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderRespService;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderService;
import com.cobble.takeaway.util.*;
import com.github.wxpay.sdk.MyWXPayConfigImpl;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.github.wxpay.sdk.WxCardSign;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Controller
public class EcOrderController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(EcOrderController.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private EcOrderService ecOrderService;
	@Autowired
	private WpOrderService wpOrderService;
	@Autowired
	private WpOrderRespService wpOrderRespService;
	@Autowired
	private WxPayService wxPayService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private Oauth2Controller oauth2Controller;
	@Autowired
	private EcProductService ecProductService;
	@Autowired
	private WxAuthorizerInfoService wxAuthorizerInfoService;

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

	@RequestMapping(value = "/web/weixin/wxmycard", method = {RequestMethod.GET})
	public ModelAndView wxMyCard(
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
		WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = new WxAuthorizerInfoPOJO();
		Boolean subscribeFlag = false;
		try {
			ret.setViewName("/page/weixin/wx_my_card");

			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
//				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			String openId = (String) session.getAttribute(CommonConstant.PROXY_OPEN_ID);

			// check whether subscribe
//			if (ecProductPOJO.getNeedSubscribe() != null && ecProductPOJO.getNeedSubscribe() == CommonConstant.WX_NEED_SUBSCRIBE) {
//				subscribeFlag = oauth2Controller.getSubscribeFlag(authorizerAppId, openId);
//				if (!subscribeFlag) {
//					String qrCodeUrl = "/web/wx/oauth2/third/authorizer/qrcode?authorizerAppId=" + authorizerAppId;
//					redirectStrategy.sendRedirect(request, response, qrCodeUrl);
//					return null;
//				}
//			}
			// 获得是否关注
			subscribeFlag = oauth2Controller.getSubscribeFlag(authorizerAppId, openId);
			// 获得是否关注, 然后在jsp页面用参数来控制显示公众号的二维码
			WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
			wxAuthorizerInfoSearchPOJO.setAuthorizerAppId(authorizerAppId);
			List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
			if (!CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {
				wxAuthorizerInfoPOJO = wxAuthorizerInfoPOJOs.get(0);
			}

//			String appId = authorizerAppId;

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


			wxJsSdkConfigRespApiPOJO.setAppId(authorizerAppId);
			wxJsSdkConfigRespApiPOJO.setNonceStr(nonceStr);
			wxJsSdkConfigRespApiPOJO.setTimestamp(timestamp);
			wxJsSdkConfigRespApiPOJO.setSignature(signature);
			wxJsSdkConfigRespApiPOJO.setJsApiList(jsApiList);

			wxJsSdkConfigRespApiPOJO.setTicket(jsSdkTicket);
			wxJsSdkConfigRespApiPOJO.setUrl(url);

			logger.info("wxJsSdkConfigRespApiPOJO: " + wxJsSdkConfigRespApiPOJO);

			// get ecProductPOJO
			ecProductPOJO = ecProductService.findById(productId);

			ret.addObject("wxJsSdkConfigRespApiPOJO", wxJsSdkConfigRespApiPOJO);
			ret.addObject("wxAuthorizerInfoPOJO", wxAuthorizerInfoPOJO);
			ret.addObject("subscribeFlag", subscribeFlag);

			if (ecProductPOJO == null) {
				ret.addObject("success", false);
				ret.addObject("errMessage", "商品不存在");
				ret.addObject("ecProductPOJO", new EcProductPOJO());
				return ret;
			} else {
				ret.addObject("ecProductPOJO", ecProductPOJO);
// 获取购买的商品个数
				WpOrderSearchPOJO wpOrderSearchPOJO = new WpOrderSearchPOJO();
				wpOrderSearchPOJO.setEcProductId(productId);
				wpOrderSearchPOJO.setOpenId(openId);
				wpOrderSearchPOJO.setRespReturnCode("SUCCESS");
				wpOrderSearchPOJO.setRespResultCode("SUCCESS");
				wpOrderSearchPOJO.setPaginationFlage(false);
				// 已成功购买商品(卡券)个数
				int orderCount = wpOrderService.getCount(wpOrderSearchPOJO);
				ret.addObject("orderCount", orderCount);
				// 今天已购买商品(卡券)个数
				int orderCountToday = 0;
				Date curDateTime = new Date();
				Date startDateTime = DateUtils.truncate(curDateTime, Calendar.DATE);
				Date endDateTime = DateUtils.addMilliseconds(startDateTime, 1 * 24 * 60 * 60 * 1000 - 1);
				wpOrderSearchPOJO = new WpOrderSearchPOJO();
				wpOrderSearchPOJO.setEcProductId(productId);
				wpOrderSearchPOJO.setPaginationFlage(false);
				wpOrderSearchPOJO.setStartDateTime(startDateTime);
				wpOrderSearchPOJO.setEndDateTime(endDateTime);
				int orderCountTodayTotal = wpOrderService.getCount(wpOrderSearchPOJO);
				int orderCountTodayClose = wpOrderService.getCountWithClose(wpOrderSearchPOJO);
				orderCountToday = orderCountTodayTotal - orderCountTodayClose;
				ret.addObject("orderCountToday", orderCountToday);

				// 通过微信卡券接口得到卡券的库存
				String cardId = ecProductPOJO.getWxCardId();
				int wxCardStock = 0;
				int getLimit = 0;
				try {
					Map wxCardDetailMap = oauth2Controller.getWxCardDetail(authorizerAppId, cardId);
					Map cardMap = (Map) wxCardDetailMap.get("card");
					Map cashMap = (Map) cardMap.get("cash");
					Map baseInfoMap = (Map) cashMap.get("base_info");
					getLimit = (Integer) baseInfoMap.get("get_limit");
					Map skuMap = (Map) baseInfoMap.get("sku");
					wxCardStock = (Integer) skuMap.get("quantity");
                    /*EcProductPOJO ecProductPOJO4Update = new EcProductPOJO();
                    ecProductPOJO4Update.setProductId(productId);
                    ecProductPOJO4Update.setWxCardStock(wxCardStock);
                    ecProductPOJO4Update.setWxCardLimitNumEveryone(getLimit);
                    ecProductService.update(ecProductPOJO4Update);*/

					// 数据库订单数量
					ecProductPOJO.setWxCardStock(wxCardStock);
					ret.addObject("wxCardLimit", getLimit);

					// 通过微信api获取卡券数量
					WxCardMgrGetCardListRespApiPOJO wxCardMgrGetCardListRespApiPOJO = oauth2Controller.getWxCardList(authorizerAppId, openId, cardId);
					int wxCardCount = 0;
					if (wxCardMgrGetCardListRespApiPOJO != null) {
						List<WxCardMgrCardApiPOJO> wxCardMgrCardApiPOJOs = wxCardMgrGetCardListRespApiPOJO.getWxCardMgrCardApiPOJOs();
						if (CollectionUtils.isNotEmpty(wxCardMgrCardApiPOJOs)) {
							wxCardCount = wxCardMgrCardApiPOJOs.size();

							List<Map> wxCards = new ArrayList<Map>();
							for (int i = 0; i < wxCardMgrCardApiPOJOs.size(); i++) {
								WxCardMgrCardApiPOJO wxCardMgrCardApiPOJO = wxCardMgrCardApiPOJOs.get(i);
								String cardId2 = wxCardMgrCardApiPOJO.getCardId();
								String code = wxCardMgrCardApiPOJO.getCode();

								Map wxCardsMap = new HashMap();
								wxCardsMap.put("cardId", cardId2);
								wxCardsMap.put("code", code);
								wxCards.add(wxCardsMap);
							}
							ret.addObject("wxCards", JsonUtils.convertToJson(wxCards));
						}
					}
					ret.addObject("wxCardCount", wxCardCount);

					// wx card
					WxJsSdkConfigCardChoosePOJO wxJsSdkConfigCardChoosePOJO = new WxJsSdkConfigCardChoosePOJO();
//					wxJsSdkConfigCardChoosePOJO.setCardId(cardId);
//              wxJsSdkConfigCardChoosePOJO.setCardType("");
					wxJsSdkConfigCardChoosePOJO.setNonceStr(nonceStr + "cardchoose" + RandomStringUtils.randomAlphabetic(3));
//              wxJsSdkConfigCardChoosePOJO.setShopId("");
//              wxJsSdkConfigCardChoosePOJO.setSignType(WXPayConstants.SHA1);
//              long timestamp = System.currentTimeMillis() / 1000;
					wxJsSdkConfigCardChoosePOJO.setTimestamp(timestamp);
					String wxCardTicket = oauth2Controller.getWxCardSdkTicket(authorizerAppId);
					String cardSign = "";
					WxCardSign wxCardSign = new WxCardSign();
					wxCardSign.addData(wxCardTicket);
					wxCardSign.addData(authorizerAppId);
//					wxCardSign.addData("location_id");
					wxCardSign.addData(wxJsSdkConfigCardChoosePOJO.getTimestamp() + "");
					wxCardSign.addData(wxJsSdkConfigCardChoosePOJO.getNonceStr());
//					wxCardSign.addData(wxJsSdkConfigCardChoosePOJO.getCardId());
					wxCardSign.addData(wxJsSdkConfigCardChoosePOJO.getCardType());

					cardSign = wxCardSign.getSignature();
					wxJsSdkConfigCardChoosePOJO.setCardSign(cardSign);
					ret.addObject("wxJsSdkConfigCardChoosePOJO", wxJsSdkConfigCardChoosePOJO);
				} catch (Exception e1) {
					logger.error("get getWxCardDetail exception: ", e1);
					ret.addObject("wxCardLimit", 0);
					ret.addObject("wxCardCount", 0);
				}

				if (ecProductPOJO.getQuantityStock() < 1) {
					ret.addObject("success", false);
					ret.addObject("errMessage", "商品已全部售罄: " + ecProductPOJO.getQuantityStock());
					return ret;
				}
			}

		} catch (Exception e) {
			logger.error("insert error.", e);
		}

//		ret.addObject("wxJsSdkConfigRespApiPOJO", wxJsSdkConfigRespApiPOJO);
//		ret.addObject("ecProductPOJO", ecProductPOJO);
//		ret.addObject("wxAuthorizerInfoPOJO", wxAuthorizerInfoPOJO);
//		ret.addObject("subscribeFlag", subscribeFlag);
		return ret;
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

	public Map getExistEcOrderUnifiedOrderMap(EcOrderCallWxPayParamPOJO ecOrderCallWxPayParamPOJO
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("ecOrderCallWxPayParamPOJO: {}", ecOrderCallWxPayParamPOJO);

		Map ret = new HashMap();

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
			if (StringUtils.isBlank(openId)) {
				logger.error("openId is null");
				return null;
			}

			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
			}

			// 因为是已经存在的订单, 不需要检查库存
			// 获取最后一条没有支付成功, 并且没有被close的订单
			int orderTimeout = ConfigurationUtil.getPropertiesConfig().getInt("WXPAY.orderTimeout", 30 * 60);
			WpOrderSearchPOJO wpOrderSearchPOJO = new WpOrderSearchPOJO();
			wpOrderSearchPOJO.setPaginationFlage(false);
			wpOrderSearchPOJO.setAppId(authorizerAppId);
			wpOrderSearchPOJO.setEcProductId(productId);
			wpOrderSearchPOJO.setOpenId(openId);

			Calendar cal = Calendar.getInstance();
			Date curDate = cal.getTime();
			cal.add(Calendar.SECOND, -2 * orderTimeout );
			Date startDateTime = cal.getTime();
			wpOrderSearchPOJO.setStartDateTime(startDateTime);
			List<WpOrderPOJO> wpOrderPOJOs = wpOrderService.finds4Close(wpOrderSearchPOJO);
			if (CollectionUtils.isEmpty(wpOrderPOJOs)) {
				logger.error("wpOrderPOJOs is null");
				return null;
			}
			WpOrderPOJO wpOrderPOJO = wpOrderPOJOs.get(0);

			String appId = authorizerAppId;
			String nonceStr = wpOrderPOJO.getNonceStr();

			// 调用wx unified order api获取prepay_id
//			Map unifiedOrderReqMap = new HashMap();
//			WxPayUnifiedOrderReqApiPOJO wxPayUnifiedOrderReqApiPOJO = new WxPayUnifiedOrderReqApiPOJO();
			String mchId = MyWXPayConfigImpl.getInstance().getMchID();
//			String body = ecProductPOJO.getProductName();
			String outTradeNo = wpOrderPOJO.getOutTradeNo();
//			String totalFee = (unitPrice * quantity) + "";
//			String spbillCreateIp = HttpRequestUtil.getIpAddr(request);
//			String notifyUrl = ConfigurationUtil.getPropertiesConfig().getString("WXPAY.notifyUrl", "http://www.deweiyizhan.com/api/wxpay/notify");
//			String tradeType = "JSAPI";
//			wxPayUnifiedOrderReqApiPOJO.setAppId(appId);
//			wxPayUnifiedOrderReqApiPOJO.setMchId(mchId);
//			wxPayUnifiedOrderReqApiPOJO.setNonceStr(nonceStr);
//			wxPayUnifiedOrderReqApiPOJO.setDeviceInfo("WEB");
//			wxPayUnifiedOrderReqApiPOJO.setBody(body);
//			wxPayUnifiedOrderReqApiPOJO.setAttach("attach");
//			wxPayUnifiedOrderReqApiPOJO.setOutTradeNo(outTradeNo);
//			wxPayUnifiedOrderReqApiPOJO.setTotalFee(totalFee);
//			wxPayUnifiedOrderReqApiPOJO.setSpbillCreateIp(spbillCreateIp);
//			wxPayUnifiedOrderReqApiPOJO.setNotifyUrl(notifyUrl);
//			wxPayUnifiedOrderReqApiPOJO.setTradeType(tradeType);
//			wxPayUnifiedOrderReqApiPOJO.setOpenId(openId);
//
//			wxPayUnifiedOrderReqApiPOJO.setSignType(WXPayConstants.MD5);
//
//			Map unifiedOrderRespMap = wxPayService.unifiedOrder(wxPayUnifiedOrderReqApiPOJO);

			WpOrderRespPOJO wpOrderRespPOJO = null;
			try {
				WpOrderRespSearchPOJO wpOrderRespSearchPOJO = new WpOrderRespSearchPOJO();
				wpOrderRespSearchPOJO.setAppId(authorizerAppId);
				wpOrderRespSearchPOJO.setOutTradeNo(outTradeNo);
				wpOrderRespSearchPOJO.setOpenId(openId);
				wpOrderRespSearchPOJO.setMchId(mchId);	// 可以不用
				wpOrderRespSearchPOJO.setEcOrderId(wpOrderPOJO.getEcOrderId()); // 可以不用
				wpOrderRespSearchPOJO.setEcProductId(productId);	// 可以不用
				wpOrderRespSearchPOJO.setStartDateTime(startDateTime);

				List<WpOrderRespPOJO> wpOrderRespPOJOs = wpOrderRespService.finds(wpOrderRespSearchPOJO);
				if (CollectionUtils.isEmpty(wpOrderRespPOJOs)) {
					logger.error("wpOrderRespPOJOs is null");
					return null;
				}
				wpOrderRespPOJO = wpOrderRespPOJOs.get(0);
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
			String returnCode = (String) orderQueryRespMap.get("return_code");
			String resultCode = (String) orderQueryRespMap.get("result_code");
			String tradeState = (String) orderQueryRespMap.get("trade_state");

			if (CommonConstant.WXPAY_SUCCESS.equals(returnCode)
					&& CommonConstant.WXPAY_SUCCESS.equals(resultCode)
					&& CommonConstant.WXPAY_ORDER_NOTPAY.equals(tradeState)) {
				Map<String, String> jsPayMap = new HashMap<String, String>();
				jsPayMap.put("appId", appId);     //公众号名称，由商户传入
				jsPayMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");        //时间戳，自1970年以来的秒数
				jsPayMap.put("nonceStr", nonceStr + "1"); //随机串
				jsPayMap.put("package", "prepay_id=" + wpOrderRespPOJO.getPrepayId());
				jsPayMap.put("signType", WXPayConstants.MD5);        //微信签名方式：
				jsPayMap = wxPayService.appendSign(jsPayMap);
				jsPayMap.put("prepayId", wpOrderRespPOJO.getPrepayId() + "");
				jsPayMap.put("packageUo", "prepay_id=" + wpOrderRespPOJO.getPrepayId());

				ret.put("success", true);

//				WxPayUnifiedOrderRespApiPOJO wxPayUnifiedOrderRespApiPOJO = new WxPayUnifiedOrderRespApiPOJO();
//				org.apache.commons.beanutils.BeanUtils.copyProperties(wxPayUnifiedOrderRespApiPOJO, wpOrderRespPOJO);
//				String unifiedOrderRespXml = XmlUtils.convertToXml(wxPayUnifiedOrderRespApiPOJO);
//				Map<String, String> unifiedOrderRespMap = WXPayUtil.xmlToMap(unifiedOrderRespXml);
				ret.put("orderQueryRespMap", orderQueryRespMap);
//				ret.put("unifiedOrderRespMap", unifiedOrderRespMap);
				ret.put("jsPayMap", jsPayMap);
				logger.info("use existed wp order resp info, wpOrderRespPOJO: {}", wpOrderRespPOJO);
			} else {
				logger.error("订单不是未支付的: {}", WXPayUtil.mapToXml(orderQueryRespMap));
				return null;
			}

		} catch (Exception e) {
			logger.error("insert error.", e);
		}

		return ret;
	}

	@RequestMapping(value = "/api/ecommerce/ecorder/ecproduct/callwxpay", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Map ecOrderUnifiedOrderApi(EcOrderCallWxPayParamPOJO ecOrderCallWxPayParamPOJO
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map ret = new HashMap();
		
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
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
			}

			String appId = authorizerAppId;

			ret = this.getExistEcOrderUnifiedOrderMap(ecOrderCallWxPayParamPOJO, request, response);
			if (MapUtils.isNotEmpty(ret)) {
				return ret;
			}
			ret = new HashMap();

			// 检查是否有库存
			ecProductPOJO = ecProductService.findById(productId);

			if (ecProductPOJO == null) {
				ret.put("success", false);
				ret.put("errMessage", "商品不存在");
				return ret;
			} else {
				// valid, 验证开始时间, 结束时间, 每人限额
				Date curDate = new Date();
				if (ecProductPOJO.getStartDateTime().after(curDate)) {
					ret.put("success", false);
					ret.put("errMessage", "商品还没有开始销售");
					ret.put("ecProductPOJO", ecProductPOJO);
					return ret;
				}

				if (ecProductPOJO.getEndDateTime().before(curDate)) {
					ret.put("success", false);
					ret.put("errMessage", "商品销售已经结束");
					ret.put("ecProductPOJO", ecProductPOJO);
					return ret;
				}
				// 获取成功购买的商品个数
				WpOrderSearchPOJO wpOrderSearchPOJO = new WpOrderSearchPOJO();
				wpOrderSearchPOJO.setEcProductId(productId);
				wpOrderSearchPOJO.setOpenId(openId);
				wpOrderSearchPOJO.setRespReturnCode("SUCCESS");
				wpOrderSearchPOJO.setRespResultCode("SUCCESS");
				wpOrderSearchPOJO.setPaginationFlage(false);
				// 已购买卡券个数
				int orderCount = wpOrderService.getCount(wpOrderSearchPOJO);
				if (orderCount + quantity > ecProductPOJO.getLimitNumEveryone()) {
					ret.put("success", false);
					ret.put("errMessage", "该商品每人只能购买" + ecProductPOJO.getLimitNumEveryone() + "个"
										+ ", 您已达购买上限，感谢您的惠顾");
					ret.put("ecProductPOJO", ecProductPOJO);
					return ret;
				}
				// 今天已购买商品(卡券)个数
				int orderCountToday = 0;
				Date curDateTime = new Date();
				Date startDateTime = DateUtils.truncate(curDateTime, Calendar.DATE);
				Date endDateTime = DateUtils.addMilliseconds(startDateTime, 1 * 24 * 60 * 60 * 1000 - 1);
				wpOrderSearchPOJO = new WpOrderSearchPOJO();
				wpOrderSearchPOJO.setEcProductId(productId);
				wpOrderSearchPOJO.setPaginationFlage(false);
				wpOrderSearchPOJO.setStartDateTime(startDateTime);
				wpOrderSearchPOJO.setEndDateTime(endDateTime);
				int orderCountTodayTotal = wpOrderService.getCount(wpOrderSearchPOJO);
				int orderCountTodayClose = wpOrderService.getCountWithClose(wpOrderSearchPOJO);
				orderCountToday = orderCountTodayTotal - orderCountTodayClose;
				ret.put("orderCountToday", orderCountToday);
				if (null != ecProductPOJO.getLimitNumDay() && orderCountToday >= ecProductPOJO.getLimitNumDay()) {
					ret.put("success", false);
					ret.put("errMessage", "该商品单日销售总量为" + ecProductPOJO.getLimitNumDay() + "个"
										+ ", 今日已全部售罄, 请您明天再来");
					ret.put("ecProductPOJO", ecProductPOJO);
					return ret;
				}

				// 通过微信卡券接口得到卡券的库存
				String cardId = ecProductPOJO.getWxCardId();
				Map wxCardDetailMap = oauth2Controller.getWxCardDetail(authorizerAppId, cardId);
				int wxCardStock = 0;
				try {
					Map cardMap = (Map) wxCardDetailMap.get("card");
					Map cashMap = (Map) cardMap.get("cash");
					Map baseInfoMap = (Map) cashMap.get("base_info");
					int getLimit = (Integer) baseInfoMap.get("get_limit");
					Map skuMap = (Map) baseInfoMap.get("sku");
					wxCardStock = (Integer) skuMap.get("quantity");
					EcProductPOJO ecProductPOJO4Update = new EcProductPOJO();
					ecProductPOJO4Update.setProductId(productId);
					ecProductPOJO4Update.setWxCardStock(wxCardStock);
					ecProductPOJO4Update.setWxCardLimitNumEveryone(getLimit);
					ecProductService.update(ecProductPOJO4Update);
					// 数据库订单数量
					if (orderCount + quantity > getLimit) {
						ret.put("success", false);
						ret.put("errMessage", "该商品每个人只能购买卡券" + getLimit + "个"
											+ ", 您已经购买了" + orderCount + "个");
						ret.put("ecProductPOJO", ecProductPOJO);
						return ret;
					}
					// 通过微信api获取卡券数量
					WxCardMgrGetCardListRespApiPOJO wxCardMgrGetCardListRespApiPOJO = oauth2Controller.getWxCardList(authorizerAppId, openId, cardId);
					int wxCardCount = 0;
					if (wxCardMgrGetCardListRespApiPOJO != null) {
						List<WxCardMgrCardApiPOJO> wxCardMgrCardApiPOJOs = wxCardMgrGetCardListRespApiPOJO.getWxCardMgrCardApiPOJOs();
						if (CollectionUtils.isNotEmpty(wxCardMgrCardApiPOJOs)) {
							wxCardCount = wxCardMgrCardApiPOJOs.size();
							if (wxCardCount + quantity > getLimit) {
								ret.put("success", false);
								ret.put("errMessage", "该商品每个人只能购买卡券" + getLimit + "个"
										+ ", 您已经拥有了" + wxCardCount + "个");
								ret.put("ecProductPOJO", ecProductPOJO);
								return ret;
							}
						}
					}
				} catch (Exception e) {
					logger.error("get getWxCardDetail exception: ", e);
				}

				// 验证库存
				if (ecProductPOJO.getQuantityStock() < quantity || wxCardStock < quantity) {
					ret.put("success", false);
					ret.put("errMessage", "商品已全部售罄");
					ret.put("ecProductPOJO", ecProductPOJO);
					return ret;
				} else {
					// ecProduct derease, 减少库存
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

			String nonceStr = RandomStringUtils.randomAlphanumeric(6);
			
			// 调用wx unified order api获取prepay_id
//			Map unifiedOrderReqMap = new HashMap();
			WxPayUnifiedOrderReqApiPOJO wxPayUnifiedOrderReqApiPOJO = new WxPayUnifiedOrderReqApiPOJO();
			String mchId = MyWXPayConfigImpl.getInstance().getMchID();
			String body = ecProductPOJO.getProductName();
			String outTradeNo = wpOrderService.getNextOutTradeNo();
			String totalFee = (unitPrice * quantity) + "";
			String spbillCreateIp = HttpRequestUtil.getIpAddr(request);
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

			WpOrderRespPOJO wpOrderRespPOJO = new WpOrderRespPOJO();
			try {
				ret.put("success", true);
				
				// 创建wx pay order
				WpOrderPOJO wpOrderPOJO = new WpOrderPOJO();
				org.apache.commons.beanutils.BeanUtils.copyProperties(wpOrderPOJO, wxPayUnifiedOrderReqApiPOJO);
				wpOrderPOJO.setEcOrderId(ecOrderPOJO.getOrderId());
				wpOrderPOJO.setEcProductId(productId);
				wpOrderPOJO.setCreateDateTime(new Date());
				wpOrderService.insert(wpOrderPOJO);
				// insert wx pay order resp
				String xmlWpOrderResp = WXPayUtil.mapToXml(unifiedOrderRespMap);
				WxPayUnifiedOrderRespApiPOJO wxPayUnifiedOrderRespApiPOJO = XmlUtils.convertToJavaBean(xmlWpOrderResp, WxPayUnifiedOrderRespApiPOJO.class);
				org.apache.commons.beanutils.BeanUtils.copyProperties(wpOrderRespPOJO, wxPayUnifiedOrderRespApiPOJO);
				wpOrderRespPOJO.setWpOrderId(wpOrderPOJO.getWpOrderId());
				wpOrderRespPOJO.setOutTradeNo(wpOrderPOJO.getOutTradeNo());
				wpOrderRespPOJO.setOpenId(wpOrderPOJO.getOpenId());
				wpOrderRespPOJO.setEcOrderId(wpOrderPOJO.getEcOrderId());
				wpOrderRespPOJO.setEcProductId(wpOrderPOJO.getEcProductId());
				wpOrderRespPOJO.setCreateDateTime(new Date());
				wpOrderRespPOJO.setLastModifiedDateTime(new Date());
				wpOrderRespService.insert(wpOrderRespPOJO);
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
			jsPayMap.put("package", "prepay_id=" + wpOrderRespPOJO.getPrepayId());
			jsPayMap.put("signType", WXPayConstants.MD5);        //微信签名方式：
			jsPayMap = wxPayService.appendSign(jsPayMap);
			jsPayMap.put("prepayId", wpOrderRespPOJO.getPrepayId() + "");
			jsPayMap.put("packageUo", "prepay_id=" + wpOrderRespPOJO.getPrepayId());
			
			ret.put("orderQueryRespMap", orderQueryRespMap);
			ret.put("unifiedOrderRespMap", unifiedOrderRespMap);
			ret.put("jsPayMap", jsPayMap);
		} catch (Exception e) {
			logger.error("insert error.", e);
		}

		return ret;
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
		WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = new WxAuthorizerInfoPOJO();
		Boolean subscribeFlag = false;
		try {
			ret.setViewName("/page/ecommerce/ec_product_choose");

			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
//				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			String openId = (String) session.getAttribute(CommonConstant.PROXY_OPEN_ID);
			
			// check whether subscribe
//			if (ecProductPOJO.getNeedSubscribe() != null && ecProductPOJO.getNeedSubscribe() == CommonConstant.WX_NEED_SUBSCRIBE) {
//				subscribeFlag = oauth2Controller.getSubscribeFlag(authorizerAppId, openId);
//				if (!subscribeFlag) {
//					String qrCodeUrl = "/web/wx/oauth2/third/authorizer/qrcode?authorizerAppId=" + authorizerAppId;
//					redirectStrategy.sendRedirect(request, response, qrCodeUrl);
//					return null;
//				}
//			}
			// 获得是否关注
			subscribeFlag = oauth2Controller.getSubscribeFlag(authorizerAppId, openId);
			// 获得是否关注, 然后在jsp页面用参数来控制显示公众号的二维码
			WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
			wxAuthorizerInfoSearchPOJO.setAuthorizerAppId(authorizerAppId);
			List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
			if (!CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {
				wxAuthorizerInfoPOJO = wxAuthorizerInfoPOJOs.get(0);
			}
			
//			String appId = authorizerAppId;

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
			
			
			wxJsSdkConfigRespApiPOJO.setAppId(authorizerAppId);
			wxJsSdkConfigRespApiPOJO.setNonceStr(nonceStr);
			wxJsSdkConfigRespApiPOJO.setTimestamp(timestamp);
			wxJsSdkConfigRespApiPOJO.setSignature(signature);
			wxJsSdkConfigRespApiPOJO.setJsApiList(jsApiList);
			
			wxJsSdkConfigRespApiPOJO.setTicket(jsSdkTicket);
			wxJsSdkConfigRespApiPOJO.setUrl(url);
			
			logger.info("wxJsSdkConfigRespApiPOJO: " + wxJsSdkConfigRespApiPOJO);
			
			// get ecProductPOJO
			ecProductPOJO = ecProductService.findById(productId);

			ret.addObject("wxJsSdkConfigRespApiPOJO", wxJsSdkConfigRespApiPOJO);
			ret.addObject("wxAuthorizerInfoPOJO", wxAuthorizerInfoPOJO);
			ret.addObject("subscribeFlag", subscribeFlag);
			
			if (ecProductPOJO == null) {
				ret.addObject("success", false);
				ret.addObject("errMessage", "商品不存在");
				ret.addObject("ecProductPOJO", new EcProductPOJO());
				return ret;
			} else {
				ret.addObject("ecProductPOJO", ecProductPOJO);
// 获取购买的商品个数
                WpOrderSearchPOJO wpOrderSearchPOJO = new WpOrderSearchPOJO();
                wpOrderSearchPOJO.setEcProductId(productId);
                wpOrderSearchPOJO.setOpenId(openId);
                wpOrderSearchPOJO.setRespReturnCode("SUCCESS");
                wpOrderSearchPOJO.setRespResultCode("SUCCESS");
                wpOrderSearchPOJO.setPaginationFlage(false);
                // 已成功购买商品(卡券)个数
                int orderCount = wpOrderService.getCount(wpOrderSearchPOJO);
                ret.addObject("orderCount", orderCount);
                // 今天已购买商品(卡券)个数
                int orderCountToday = 0;
				Date curDateTime = new Date();
				Date startDateTime = DateUtils.truncate(curDateTime, Calendar.DATE);
				Date endDateTime = DateUtils.addMilliseconds(startDateTime, 1 * 24 * 60 * 60 * 1000 - 1);
				wpOrderSearchPOJO = new WpOrderSearchPOJO();
				wpOrderSearchPOJO.setEcProductId(productId);
				wpOrderSearchPOJO.setPaginationFlage(false);
				wpOrderSearchPOJO.setStartDateTime(startDateTime);
				wpOrderSearchPOJO.setEndDateTime(endDateTime);
				int orderCountTodayTotal = wpOrderService.getCount(wpOrderSearchPOJO);
				int orderCountTodayClose = wpOrderService.getCountWithClose(wpOrderSearchPOJO);
				orderCountToday = orderCountTodayTotal - orderCountTodayClose;
				ret.addObject("orderCountToday", orderCountToday);

                // 通过微信卡券接口得到卡券的库存
                String cardId = ecProductPOJO.getWxCardId();
                int wxCardStock = 0;
                int getLimit = 0;
                try {
                    Map wxCardDetailMap = oauth2Controller.getWxCardDetail(authorizerAppId, cardId);
                    Map cardMap = (Map) wxCardDetailMap.get("card");
                    Map cashMap = (Map) cardMap.get("cash");
                    Map baseInfoMap = (Map) cashMap.get("base_info");
                    getLimit = (Integer) baseInfoMap.get("get_limit");
                    Map skuMap = (Map) baseInfoMap.get("sku");
                    wxCardStock = (Integer) skuMap.get("quantity");
                    /*EcProductPOJO ecProductPOJO4Update = new EcProductPOJO();
                    ecProductPOJO4Update.setProductId(productId);
                    ecProductPOJO4Update.setWxCardStock(wxCardStock);
                    ecProductPOJO4Update.setWxCardLimitNumEveryone(getLimit);
                    ecProductService.update(ecProductPOJO4Update);*/

                    // 数据库订单数量
                    ecProductPOJO.setWxCardStock(wxCardStock);
                    ret.addObject("wxCardLimit", getLimit);

                    // 通过微信api获取卡券数量
                    WxCardMgrGetCardListRespApiPOJO wxCardMgrGetCardListRespApiPOJO = oauth2Controller.getWxCardList(authorizerAppId, openId, cardId);
                    int wxCardCount = 0;
                    if (wxCardMgrGetCardListRespApiPOJO != null) {
                        List<WxCardMgrCardApiPOJO> wxCardMgrCardApiPOJOs = wxCardMgrGetCardListRespApiPOJO.getWxCardMgrCardApiPOJOs();
                        if (CollectionUtils.isNotEmpty(wxCardMgrCardApiPOJOs)) {
                            wxCardCount = wxCardMgrCardApiPOJOs.size();
                        }
                    }
                    ret.addObject("wxCardCount", wxCardCount);

                } catch (Exception e1) {
                    logger.error("get getWxCardDetail exception: ", e1);
                    ret.addObject("wxCardLimit", 0);
                    ret.addObject("wxCardCount", 0);
                }

				if (ecProductPOJO.getQuantityStock() < 1) {
					ret.addObject("success", false);
					ret.addObject("errMessage", "商品已全部售罄: " + ecProductPOJO.getQuantityStock());
					return ret;
				}
			}
			
		} catch (Exception e) {
			logger.error("insert error.", e);
		}

//		ret.addObject("wxJsSdkConfigRespApiPOJO", wxJsSdkConfigRespApiPOJO);
//		ret.addObject("ecProductPOJO", ecProductPOJO);
//		ret.addObject("wxAuthorizerInfoPOJO", wxAuthorizerInfoPOJO);
//		ret.addObject("subscribeFlag", subscribeFlag);
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
