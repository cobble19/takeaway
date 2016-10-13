package com.cobble.takeaway.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobble.takeaway.pojo.weixin.api.AuthorizationInfoApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.FuncInfoApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.FuncscopeCategoryApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxAuthorizerAccessTokenApiPOJO;
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

	public static String convertToJson(Object object) throws JsonGenerationException, JsonMappingException, IOException {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper.writeValueAsString(object);
	}
	
	public static void main(String[] argv) throws Exception {
		String str = "{\"a\": \"b\"}";
		
		int index = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx483bd8288ebe84b4&redirect_uri=http%3A%2F%2Fwww.deweiyizhan.com%2Fweb%2Fwx%2Foauth2%2Fthird%2Fweb%2FauthCode&response_type=code&scope=snsapi_userinfo&state=kGZUWL&component_appid=wx2bec8614a6c47443#wechat_redirect".indexOf("authorize");
		logger.info("index： {}", index);
		
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
