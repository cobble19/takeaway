package com.cobble.takeaway.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketApiPOJO;

public class XmlUtils {
	private static Logger logger = LoggerFactory.getLogger(XmlUtils.class);
	
	public static String convertToXml(Object obj) {
		return convertToXml(obj, "UTF-8", false, true);
	}
	
	public static String convertToXml(Object obj, Boolean formatted) {
		return convertToXml(obj, "UTF-8", formatted, true);
	}

	public static String convertToXml(Object obj, String encoding, Boolean formatted, Boolean fragment) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formatted);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, fragment);// whether remove xml head

			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return result;
	}

	public static <T> T convertToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return t;
	}
	public static void main(String[] args) {
		WxComVerifyTicketApiPOJO wxComVerifyTicketPOJO = new WxComVerifyTicketApiPOJO();
		wxComVerifyTicketPOJO.setAppId("appid1233");
		wxComVerifyTicketPOJO.setComponentVerifyTicket("abc");
		wxComVerifyTicketPOJO.setCreateTime("1233456");
		wxComVerifyTicketPOJO.setInfoType("1");
		String result = XmlUtils.convertToXml(wxComVerifyTicketPOJO, true);
		logger.info(result);
	}
}
