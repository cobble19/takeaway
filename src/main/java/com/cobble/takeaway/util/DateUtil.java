package com.cobble.takeaway.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	public static String toStr(Date date, String pattern) {
		String ret = "";
		if (date == null) {
			return ret;
		}
		if (StringUtils.isBlank(pattern)) {
			pattern = "yyyyMMddHHmmss";
		}

		DateFormat df = new SimpleDateFormat(pattern);
		try {
			ret = df.format(date);
		} catch (Exception e) {
			logger.error("Format date: {}, Exception: {}", date, e);
		}
		
		return ret;
	}
	
	public static Date toDate(String dateStr, String pattern) {
		Date ret = null;
		if (StringUtils.isBlank(dateStr)) {
			return ret;
		}
		if (StringUtils.isBlank(pattern)) {
			pattern = "yyyyMMddHHmmss";
		}

		DateFormat df = new SimpleDateFormat(pattern);
		try {
			ret = df.parse(dateStr);
		} catch (Exception e) {
			logger.error("Parse date: {}, Exception: {}", dateStr, e);
		}
		
		return ret;
	}
}
