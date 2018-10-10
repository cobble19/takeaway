package com.cobble.takeaway.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketApiPOJO;

public class XmlUtils {
    private static Logger logger = LoggerFactory.getLogger(XmlUtils.class);
    // get xml text/attribute/element


    public static NodeList getNodeList(String xmlStr, String xpathStr) throws Exception {
        Object result = getElement(xmlStr, xpathStr, XPathConstants.NODESET);
        NodeList nodeList = (NodeList) result;

        return nodeList;
    }
    public static Node getNode(String xmlStr, String xpathStr) throws Exception {
        Object result = getElement(xmlStr, xpathStr, XPathConstants.NODE);
        Node ret = (Node) result;

        return ret;
    }
    public static String getNodeString(String xmlStr, String xpathStr) throws Exception {
        Object result = getElement(xmlStr, xpathStr, XPathConstants.STRING);
        String ret = (String) result;

        return ret;
    }
    public static Double getNodeNumber(String xmlStr, String xpathStr) throws Exception {
        Object result = getElement(xmlStr, xpathStr, XPathConstants.NUMBER);
        Double ret = (Double) result;

        return ret;
    }
    public static Boolean getNodeBoolean(String xmlStr, String xpathStr) throws Exception {
        Object result = getElement(xmlStr, xpathStr, XPathConstants.BOOLEAN);
        Boolean ret = (Boolean) result;

        return ret;
    }

    public static Object getElement(String xmlStr, String xpathStr, QName qname) throws Exception {
        Document doc = getDoc(xmlStr);
        XPathFactory fac = XPathFactory.newInstance();
        XPath xpath = fac.newXPath();
        XPathExpression expression = xpath.compile(xpathStr);
        Object result = expression.evaluate(doc, qname);
        return result;
    }

    public static Document getDoc(String xmlStr) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        dbf.setXIncludeAware(false);
        dbf.setExpandEntityReferences(false);

        DocumentBuilder builder = dbf.newDocumentBuilder();

        StringReader sr = new StringReader(xmlStr);
        InputSource is = new InputSource();
        is.setCharacterStream(sr);
        Document doc = builder.parse(is);

		/*InputStream is = new ByteArrayInputStream(xmlStr.getBytes("UFT-8"));
		Document doc = builder.parse(is);*/

        return doc;
    }

    public static NodeList getNodeList(Document doc, String xpathStr) throws Exception {
        Object result = getElement(doc, xpathStr, XPathConstants.NODESET);
        NodeList nodeList = (NodeList) result;

        return nodeList;
    }
    public static Node getNode(Document doc, String xpathStr) throws Exception {
        Object result = getElement(doc, xpathStr, XPathConstants.NODE);
        Node ret = (Node) result;

        return ret;
    }
    public static String getNodeString(Document doc, String xpathStr) throws Exception {
        Object result = getElement(doc, xpathStr, XPathConstants.STRING);
        String ret = (String) result;

        return ret;
    }
    public static Double getNodeNumber(Document doc, String xpathStr) throws Exception {
        Object result = getElement(doc, xpathStr, XPathConstants.NUMBER);
        Double ret = (Double) result;

        return ret;
    }
    public static Boolean getNodeBoolean(Document doc, String xpathStr) throws Exception {
        Object result = getElement(doc, xpathStr, XPathConstants.BOOLEAN);
        Boolean ret = (Boolean) result;

        return ret;
    }

    public static Object getElement(Document doc, String xpathStr, QName qname) throws Exception {
        XPathFactory fac = XPathFactory.newInstance();
        XPath xpath = fac.newXPath();
        XPathExpression expression = xpath.compile(xpathStr);
        Object result = expression.evaluate(doc, qname);
        return result;
    }

    //XML <-> Object
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

            XMLInputFactory xif = XMLInputFactory.newFactory();
            xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
//			xif.setProperty(XMLInputFactory.SUPPORT_DTD, true);
            xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            XMLStreamReader xsr = xif.createXMLStreamReader(new StringReader(xml));
            Unmarshaller unmarshaller = context.createUnmarshaller();

            t = (T) unmarshaller.unmarshal(xsr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return t;
    }
    public static void main(String[] args) throws Exception {
        WxComVerifyTicketApiPOJO wxComVerifyTicketPOJO = new WxComVerifyTicketApiPOJO();
        wxComVerifyTicketPOJO.setAppId("appid1233");
        wxComVerifyTicketPOJO.setComponentVerifyTicket("abc");
        wxComVerifyTicketPOJO.setCreateTime("1233456");
        wxComVerifyTicketPOJO.setInfoType("1");
        String result = XmlUtils.convertToXml(wxComVerifyTicketPOJO, true);
        logger.info(result);
        wxComVerifyTicketPOJO = XmlUtils.convertToJavaBean(result, WxComVerifyTicketApiPOJO.class);
		
		/*Document doc = XmlUtils.getDoc(result);
		String text = XmlUtils.getNodeString(doc, "//xml/AppId");
		logger.info(text);*/

        Object text = XmlUtils.getNodeNumber("<xml><AppId id=\"id1\" show=\"12.56123\">appid123456</AppId></xml>", "/xml/AppId/@show");
        logger.info(text + "");
    }
}
