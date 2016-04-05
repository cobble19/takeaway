package com.cobble.takeaway.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;

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
		Map map = new HashMap();
		map.put("a", "b");
		System.out.println(map);
		str = JsonUtils.convertToJson(map);

		System.out.println(str);
		Map map1 = JsonUtils.convertToJavaBean(str, HashMap.class);
		System.out.println(map1);
	}
}
