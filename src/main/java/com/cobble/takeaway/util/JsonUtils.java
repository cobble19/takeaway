package com.cobble.takeaway.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrMenuInfoRespApiPOJO;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	static ObjectMapper objectMapper;

	public static List<?> convertToJavaBeanList(String json, TypeReference typeRef) throws JsonParseException, JsonMappingException, IOException {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper.readValue(json, typeRef);
	}
	
	/*public static <T> List<T> convertToJavaBeanList(String json, Class<T> valueType) throws JsonParseException, JsonMappingException, IOException {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper.readValue(json, new TypeReference<List<T>>() { });
	}*/
	
	public static <T> T convertToJavaBean(String json, Class<T> valueType) throws JsonParseException, JsonMappingException, IOException {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		return (T) objectMapper.readValue(json, valueType);
	}
	
	/**
	 * deal DataTablesPOJO.java
	 * @param <ItemT>
	 * @param json
	 * @param valueType
	 * @param itemClass
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T, ItemT> DataTablesPOJO<ItemT> convertToJavaBeanDT(String json, Class<ItemT> itemClass) throws JsonParseException, JsonMappingException, IOException {
		DataTablesPOJO dataTablesPOJO = JsonUtils.convertToJavaBean(json, DataTablesPOJO.class);
		List<ItemT> list = new ArrayList<ItemT>();
		if (dataTablesPOJO != null && dataTablesPOJO.getData() != null) {
			List data = dataTablesPOJO.getData();
			Map d = (Map) data.get(0);
			String s = JsonUtils.convertToJson(d);
			ItemT wx = JsonUtils.convertToJavaBean(s, itemClass);
			list.add(wx);
//			logger.info("data: {}\n, d: {}\n, wx: {}", data, d, wx);
		}
		dataTablesPOJO.setData(list);
		return dataTablesPOJO;
	}

	public static String convertToJson(Object object) throws JsonGenerationException, JsonMappingException, IOException {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper.writeValueAsString(object);
	}
	
	public static void main(String[] argv) throws Exception {
		String str = "{\"a\": \"b\"}";
		
		/*str = "{\"button\":[{\"name\":\"扫码\",\"sub_button\":"
				+ "[{\"type\":\"scancode_waitmsg\",\"name\":\"扫码带提示\",\"key\":\"rselfmenu_0_0\",\"sub_button\":[]}"
				+ ",{\"type\":\"scancode_push\",\"name\":\"扫码推事件\",\"key\":\"rselfmenu_0_1\",\"sub_button\":[]}]}"
				+ ",{\"name\":\"发图\",\"sub_button\":[{\"type\":\"pic_sysphoto\",\"name\":\"系统拍照发图\",\"key\":\"rselfmenu_1_0\""
				+ ",\"sub_button\":[]},{\"type\":\"pic_photo_or_album\",\"name\":\"拍照或者相册发图\""
				+ ",\"key\":\"rselfmenu_1_1\",\"sub_button\":[]},{\"type\":\"pic_weixin\",\"name\":\"微信相册发图\""
				+ ",\"key\":\"rselfmenu_1_2\",\"sub_button\":[]}]},{\"name\":\"发送位置\",\"type\":\"location_select\""
				+ ",\"key\":\"rselfmenu_2_0\"},{\"type\":\"media_id\",\"name\":\"图片\",\"media_id\":\"MEDIA_ID1\"}"
				+ ",{\"type\":\"view_limited\",\"name\":\"图文消息\",\"media_id\":\"MEDIA_ID2\"}]}";
		
		WxMenuMgrReqApiPOJO wxMenuMgrReqApiPOJO = JsonUtils.convertToJavaBean(str, WxMenuMgrReqApiPOJO.class);
		logger.info("wxMenuMgrReqApiPOJO: {}", wxMenuMgrReqApiPOJO);
		str = JsonUtils.convertToJson(wxMenuMgrReqApiPOJO);
		logger.info("str: {}", str);
		logger.info("str: {}", str.replaceAll("\"\\w+\":null,*|\"\\w+\":\\[\\],*", "").replaceAll(",}", "}"));
		
		wxMenuMgrReqApiPOJO = JsonUtils.convertToJavaBean(str, WxMenuMgrReqApiPOJO.class);
		logger.info("wxMenuMgrMenuRespApiPOJO: {}", wxMenuMgrReqApiPOJO);
		str = JsonUtils.convertToJson(wxMenuMgrReqApiPOJO);
		logger.info("str: {}", str);
		logger.info("str: {}", str.replaceAll("\"\\w+\":null,*|\"\\w+\":\\[\\],*", "").replaceAll(",}", "}"));*/
		
		/*str = "{\"menu\":{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\""
				+ ",\"sub_button\":[]}],\"menuid\":208396938},\"conditionalmenu\":"
				+ "[{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\""
				+ ",\"sub_button\":[]},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\""
				+ ",\"url\":\"http://www.soso.com/\",\"sub_button\":[]},{\"type\":\"view\",\"name\":\"视频\""
				+ ",\"url\":\"http://v.qq.com/\",\"sub_button\":[]},{\"type\":\"click\",\"name\":\"赞一下我们\""
				+ ",\"key\":\"V1001_GOOD\",\"sub_button\":[]}]}],\"matchrule\":{\"group_id\":2,\"sex\":1"
				+ ",\"country\":\"中国\",\"province\":\"广东\",\"city\":\"广州\",\"client_platform_type\":2}"
				+ ",\"menuid\":208396993}]}";
		
		WxMenuMgrRespApiPOJO wxMenuMgrMenuRespApiPOJO = JsonUtils.convertToJavaBean(str, WxMenuMgrRespApiPOJO.class);
		logger.info("wxMenuMgrMenuRespApiPOJO: {}", wxMenuMgrMenuRespApiPOJO);
		str = JsonUtils.convertToJson(wxMenuMgrMenuRespApiPOJO);
		logger.info("str: {}", str);
		logger.info("str: {}", str.replaceAll("\"\\w+\":null,*|\"\\w+\":\\[\\],*", "").replaceAll(",}", "}"));
		
		wxMenuMgrMenuRespApiPOJO = JsonUtils.convertToJavaBean(str, WxMenuMgrRespApiPOJO.class);
		logger.info("wxMenuMgrMenuRespApiPOJO: {}", wxMenuMgrMenuRespApiPOJO);
		str = JsonUtils.convertToJson(wxMenuMgrMenuRespApiPOJO);
		logger.info("str: {}", str);
		logger.info("str: {}", str.replaceAll("\"\\w+\":null,*|\"\\w+\":\\[\\],*", "").replaceAll(",}", "}"));*/
		
		/*str = "{\"is_menu_open\":1,\"selfmenu_info\":{\"button\":[{\"name\":\"button\""
				+ ",\"sub_button\":{\"list\":[{\"type\":\"view\",\"name\":\"view_url\",\"url\":\"http://www.qq.com\"}"
				+ ",{\"type\":\"news\",\"name\":\"news\",\"value\":\"KQb_w_Tiz-nSdVLoTV35Psmty8hGBulGhEdbb9SKs-o\""
				+ ",\"news_info\":{\"list\":[{\"title\":\"MULTI_NEWS\",\"author\":\"JIMZHENG\",\"digest\":\"text\""
				+ ",\"show_cover\":0,\"cover_url\":\"http://mmbiz.qpic.cn/mmbiz/GE7et87vE9vicuCibqXsX9GPPLuE"
				+ "tBfXfK0HKuBIa1A1cypS0uY1wickv70iaY1gf3I1DTszuJoS3lAVLvhTcm9sDA/0\",\"content_url\":"
				+ "\"http://mp.weixin.qq.com/s?__biz=MjM5ODUwNTM3Ng==&mid=204013432&idx=1&sn=80ce6d9abcb832237bf86c87e50fda15#rd\""
				+ ",\"source_url\":\"\"},{\"title\":\"MULTI_NEWS1\",\"author\":\"JIMZHENG\",\"digest\":\"MULTI_NEWS1\""
				+ ",\"show_cover\":1,\"cover_url\":\"http://mmbiz.qpic.cn/mmbiz/GE7et87vE9vicuCibqXsX9GPPLuEtBfXfKnmnpX"
				+ "YgWmQD5gXUrEApIYBCgvh2yHsu3ic3anDUGtUCHwjiaEC5bicd7A/0\",\"content_url\":"
				+ "\"http://mp.weixin.qq.com/s?__biz=MjM5ODUwNTM3Ng==&mid=204013432&idx=2&sn=82"
				+ "26843afb14ecdecb08d9ce46bc1d37#rd\",\"source_url\":\"\"}]}}"
				+ ",{\"type\":\"video\",\"name\":\"video\",\"value\":\"http://61.182.130.30/vweixinp.tc.qq.com/1007_114b"
				+ "cede9a2244eeb5ab7f76d951df5f.f10.mp4?vkey=77A42D0C2015FBB0A3653D29C571B5F4BBF1D243FBEF17F09C24FF1F2"
				+ "F22E30881BD350E360BC53F&sha=0&save=1\"},{\"type\":\"voice\",\"name\":\"voice\",\"value\":\"nTXe3ag"
				+ "hlQ4XYHa0AQPWiQQbFW9RVtaYTLPC1PCQx11qc9UB6CiUPFjdkeEtJicn\"}]}},{\"type\":\"text\",\"name\":\"text\""
				+ ",\"value\":\"This is text!\"},{\"type\":\"img\",\"name\":\"photo\",\"value\":\"ax5Whs5dsoomJLEpp"
				+ "AvftBUuH7CgXCZGFbFJifmbUjnQk_ierMHY99Y5d2Cv14RD\"}]}}";
		
		WxMenuMgrMenuInfoRespApiPOJO wxMenuMgrMenuInfoRespApiPOJO = JsonUtils.convertToJavaBean(str, WxMenuMgrMenuInfoRespApiPOJO.class);
		logger.info("wxMenuMgrMenuInfoRespApiPOJO: {}", wxMenuMgrMenuInfoRespApiPOJO);
		str = JsonUtils.convertToJson(wxMenuMgrMenuInfoRespApiPOJO);
		logger.info("str: {}", str);
		logger.info("str: {}", str.replaceAll("\"\\w+\":null,*|\"\\w+\":\\[\\],*", "").replaceAll(",}", "}"));*/
		
		str = "{\"draw\":null,\"recordsTotal\":1,\"recordsFiltered\":null,\"data\":"
				+ "[{\"errcode\":null,\"errmsg\":null,\"is_menu_open\":1,\"selfmenu_info\":"
				+ "{\"errcode\":null,\"errmsg\":null,\"button\":[{\"type\":\"media_id\",\"name\":\"media_id\""
				+ ",\"media_id\":\"WQnZQIcVKS-5L9S9WBAnI4Zvi0xgN-rVTnDXb5tFTVo\"},{\"type\":\"click\",\"name\":\"今日歌曲\""
				+ ",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":{\"list\":[{\"type\":\"view\""
				+ ",\"name\":\"加入会员\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ "wx483bd8288ebe84b4&redirect_uri=http%3A%2F%2Fwww.deweiyizhan.com%2Fweb%2Fwx%2Foauth2%2Fthird%2Fwe"
				+ "b%2FauthCode&response_type=code&scope=snsapi_userinfo&state=kGZUWL&component_appid=wx2bec8614a6c47"
				+ "443#wechat_redirect\"},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\""
				+ ",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}}]}}],\"error\":null,\"success\":true,\"dt_RowClass\":null"
				+ ",\"dt_RowData\":null,\"dt_RowAttr\":null,\"dt_RowId\":null}";
		DataTablesPOJO<WxMenuMgrMenuInfoRespApiPOJO> temp = new DataTablesPOJO<WxMenuMgrMenuInfoRespApiPOJO>();
		DataTablesPOJO<WxMenuMgrMenuInfoRespApiPOJO> dataTablesPOJO = JsonUtils.convertToJavaBeanDT(str, WxMenuMgrMenuInfoRespApiPOJO.class);
		if (dataTablesPOJO != null && dataTablesPOJO.getData() != null) {
			List<WxMenuMgrMenuInfoRespApiPOJO> data = dataTablesPOJO.getData();
			/*Map d = (Map) data.get(0);
			String s = JsonUtils.convertToJson(d);
			WxMenuMgrMenuInfoRespApiPOJO wx = JsonUtils.convertToJavaBean(s, WxMenuMgrMenuInfoRespApiPOJO.class);
			data.add(wx);
			logger.info("data: {}\n, d: {}\n, wx: {}", data, d, wx);*/
		}
		logger.info("wxMenuMgrMenuInfoRespApiPOJO: {}", dataTablesPOJO);
		str = JsonUtils.convertToJson(dataTablesPOJO);
		logger.info("str: {}", str);
		logger.info("str: {}", str.replaceAll("\"\\w+\":null,*|\"\\w+\":\\[\\],*", "").replaceAll(",}", "}"));
		
		/*int index = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx483bd8288ebe84b4&redirect_uri=http%3A%2F%2Fwww.deweiyizhan.com%2Fweb%2Fwx%2Foauth2%2Fthird%2Fweb%2FauthCode&response_type=code&scope=snsapi_userinfo&state=kGZUWL&component_appid=wx2bec8614a6c47443#wechat_redirect".indexOf("authorize");
		logger.info("index： {}", index);*/
		
		/*Map map = new HashMap();
		map.put("a", "b");
		System.out.println(map);
		str = JsonUtils.convertToJson(map);

		System.out.println(str);
		Map map1 = JsonUtils.convertToJavaBean(str, HashMap.class);
		System.out.println(map1);*/
		
		
//		WxAuthorizerAccessTokenApiPOJO wxAuthorizerAccessTokenPOJO = new WxAuthorizerAccessTokenApiPOJO();
//		AuthorizationInfoApiPOJO ai = new AuthorizationInfoApiPOJO();
//		List<FuncInfoApiPOJO> fis = new ArrayList<FuncInfoApiPOJO>();
//		FuncInfoApiPOJO fi = new FuncInfoApiPOJO();
//		FuncscopeCategoryApiPOJO funcscopeCategoryPOJO = new FuncscopeCategoryApiPOJO();
//		funcscopeCategoryPOJO.setId(1);
//		fi.setFuncscopeCategoryPOJO(funcscopeCategoryPOJO);
//		fis.add(fi);
//		ai.setFuncInfoPOJOList(fis);;
//		wxAuthorizerAccessTokenPOJO.setAuthorizationInfoPOJO(ai);
//		String result = JsonUtils.convertToJson(wxAuthorizerAccessTokenPOJO);
//		logger.info("result: {}", result);
		
//		String wxThirdAuthorizerToken = "{\"authorization_info\":{\"authorizer_appid\":\"wxe0037de41e16f816\",\"authorizer_access_token\":\"F8R2aQwTsRSH-GAd3jLAau7ZIIYa1VjYj2tLH14aKzfRjiheqBSHu6C-2GiR6J6nzKVmffOGtONn7aqeNtR6_-vM7HiE3vPoZM6qe2YKxAcD3jW-e5rGtqlMQkJii7FFXGLbAMDGPU\",\"expires_in\":7200,\"authorizer_refresh_token\":\"refreshtoken@@@duDVOVUScFHsbP8DxIYWA_73-nj339-5DXyJ4aS6ePo\",\"func_info\":[{\"funcscope_category\":{\"id\":1}},{\"funcscope_category\":{\"id\":15}},{\"funcscope_category\":{\"id\":4}},{\"funcscope_category\":{\"id\":7}},{\"funcscope_category\":{\"id\":2}},{\"funcscope_category\":{\"id\":3}},{\"funcscope_category\":{\"id\":11}},{\"funcscope_category\":{\"id\":6}},{\"funcscope_category\":{\"id\":5}},{\"funcscope_category\":{\"id\":8}},{\"funcscope_category\":{\"id\":13}},{\"funcscope_category\":{\"id\":10}},{\"funcscope_category\":{\"id\":12}}]}}";
//		wxAuthorizerAccessTokenPOJO = JsonUtils.convertToJavaBean(result, WxAuthorizerAccessTokenApiPOJO.class);
//		logger.info("wxAuthorizerAccessTokenPOJO: {}", wxAuthorizerAccessTokenPOJO);
	}
}
