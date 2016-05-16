package demo;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class Program {

	public static void main(String[] args) throws Exception {

		//
		// 第三方回复公众平台
		//

		// 需要加密的明文
//		String encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
//		String token = "pamtest";
//		String timestamp = "1409304348";
//		String nonce = "xxxxxx";
//		String appId = "wxb11529c136998cb6";
//		String replyMsg = " 中文<xml><ToUserName><![CDATA[oia2TjjewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";


		String encodingAesKey = "encAESKey0123A1B2C3D4E5h6i7j8k9l0O1P2Q3R4S5";
		String token = "token_dwyz";
		String timestamp = "1463109718";
		String nonce = "865605744";
		String appId = "wx2bec8614a6c47443";
		String msgSignature="4d1e3ff3bec6e0cd0443b63431e9b0c2fbbce23d";
//		String replyMsg = "<xml><AppId><![CDATA[wx2bec8614a6c47443]]></AppId><Encrypt><![CDATA[zABNOO10ICfuTrZegTkBSO72qjhwq4Q06FxJLBZwwxKt3z+SRMHRreFVpBqpR8O2NgkfizS4dA95yr79osHX4BnOVvDBxWOkbrisZTys3vlGT22IAJuEpIchfH0xqrOCuaT3s/PpbrLAW1ps2fZ1CuxDeV3mPJkjZc0TpMN+w9Q8ZpxxzDtVu/IztH5fX457JKHINXEhhIi7DgRPlvHiO7mJGXv96xRrfL1mNRAF6vJs1y9iVlFouXlfQJZLRj8g3x9hZae7E31vCTmMsjuOpEwGhynDuXnJCCR+BLyM/PCuC5XL9X3i7Is57OTB6MSupQHF8I/1PgRCOhGUU7L2Efu0tM+eS5sxtz0oRG8MkyAsTquCg4vXO+qyMdzhGx/4w2EcJnImhnAhnR59+tu2bVAIRpbrawuxglNHafNwh0PbDl3iiyFtbqnGR7XlhJtitGFBO1tMEmf6JoerqQ+uYQ==]]></Encrypt></xml>";

		
		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
//		String mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);
//		System.out.println("加密后: " + mingwen);
//
//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		DocumentBuilder db = dbf.newDocumentBuilder();
//		StringReader sr = new StringReader(mingwen);
//		InputSource is = new InputSource(sr);
//		Document document = db.parse(is);
//
//		Element root = document.getDocumentElement();
//		NodeList nodelist1 = root.getElementsByTagName("Encrypt");
//		NodeList nodelist2 = root.getElementsByTagName("MsgSignature");

//		String encrypt = nodelist1.item(0).getTextContent();
//		String msgSignature = nodelist2.item(0).getTextContent();

		String encrypt = "XyzXuUyU4ncYb2Q9H3C3Hs8MBJIOc2yPtg2s7KAR021x45pIQjTbF8nKZj8c2iHidhoejuSdXclP5Nf9jQppruGfjEPAq45aSO6HHBgwx/ZddV2/S+riuOlhpqOA5G3BYRq0YU+EB9Ae3tf1n1AIBGeWNdxlUJhJqhdHRFcFXfg5Ahz1UW3f6H2ZAiFF9scirrTNdi+BTaV6xVD8Fjal+8E7I8qoVWjCS7C7yZENxXaDc9utecqslfw6dphILkgM9tyDAE8JWQJ0VOnBUh1TN6/1qMV28m4SgtMueEvS6oHzqlgTOgtpF+5wXYQvFDek9e1tI0OGR7De/5/qKMzfeUuZuw692ukRLl7nScjtus9OlICZy2sj92pv3WNjb8ZAfO8O8jdYI6mj5f/e+8cqdokegz6gqJoyXtNel9P7MGv7w2f7WMs/75EG0392Wy3/XBQTJ91pkQD5TsAGKgzVkQ==";
		String format = "<xml><ToUserName><![CDATA[%1$s]]></ToUserName><Encrypt><![CDATA[%2$s]]></Encrypt></xml>";
		String fromXML = String.format(format, appId, encrypt);
		

//		String format = "<xml><ToUserName><![CDATA[]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
//		String fromXML = String.format(format, "zABNOO10ICfuTrZegTkBSO72qjhwq4Q06FxJLBZwwxKt3z+SRMHRreFVpBqpR8O2NgkfizS4dA95yr79osHX4BnOVvDBxWOkbrisZTys3vlGT22IAJuEpIchfH0xqrOCuaT3s/PpbrLAW1ps2fZ1CuxDeV3mPJkjZc0TpMN+w9Q8ZpxxzDtVu/IztH5fX457JKHINXEhhIi7DgRPlvHiO7mJGXv96xRrfL1mNRAF6vJs1y9iVlFouXlfQJZLRj8g3x9hZae7E31vCTmMsjuOpEwGhynDuXnJCCR+BLyM/PCuC5XL9X3i7Is57OTB6MSupQHF8I/1PgRCOhGUU7L2Efu0tM+eS5sxtz0oRG8MkyAsTquCg4vXO+qyMdzhGx/4w2EcJnImhnAhnR59+tu2bVAIRpbrawuxglNHafNwh0PbDl3iiyFtbqnGR7XlhJtitGFBO1tMEmf6JoerqQ+uYQ==");

		//
		// 公众平台发送消息给第三方，第三方处理
		//

		// 第三方收到公众号平台发送的消息
		String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, "<xml><ToUserName><![CDATA[wx2bec8614a6c47443]]></ToUserName><Encrypt><![CDATA[KhW6PmkSreM7jam5wL8nErjm7IC9swaWVLNlm58bs/yePDnH0V8EnvMmRdCyPydoUTiJluN/3N36VHi/DkAZtYEm7lOLj8FayplwctOAbRPKI+RQm2ZRyW/++L1kQRg1PvXa6BtvMseS1wR1mtAozQt0PTga+HfQoyfBCLAzjwk930/P7KiKAzJD+V6+wTFt8yo6K/Q4TxsRuhZFiozxobiA1l3pj4ZSxrkBN78y7WeGk+/WBnKR/a0vG+7cM5YmeNRyuiN7Ul54+95Qf0qutgsXMk6eLeuQyde+b3T0D/XDFI2S9OO96RO1cjJ552GhOHZwOOetuRI6LsT5Ut2TL816iuMr2tTOsBapmvdXEF5thtob65Gnu/IRKfi0Z2BCsy1JY9LXKNWr6OF8iIEX4oigukcoDZ5jBFeUmZ8cd6vXFrNFJOqcsOBvVzr6W/zxLbbB9s2xHpoRoi7TD8RAWw==]]></Encrypt></xml>");
		System.out.println("解密后明文: " + result2);
		
		//pc.verifyUrl(null, null, null, null);
	}
}
