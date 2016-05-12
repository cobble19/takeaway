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
		String encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
		String token = "pamtest";
		String timestamp = "1409304348";
		String nonce = "xxxxxx";
		String appId = "wxb11529c136998cb6";
		String replyMsg = " 中文<xml><ToUserName><![CDATA[oia2TjjewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";


//		String encodingAesKey = "encAESKey0123A1B2C3D4E5h6i7j8k9l0O1P2Q3R4S5";
//		String token = "token_dwyz";
//		String timestamp = "1463073712";
//		String nonce = "1178790654";
//		String appId = "wx2bec8614a6c47443";
//		String msgSignature="da5ceb71f4a1094e9e5769ea0f3741c46613cb5a";
//		String replyMsg = "<xml><AppId><![CDATA[wx2bec8614a6c47443]]></AppId><Encrypt><![CDATA[zABNOO10ICfuTrZegTkBSO72qjhwq4Q06FxJLBZwwxKt3z+SRMHRreFVpBqpR8O2NgkfizS4dA95yr79osHX4BnOVvDBxWOkbrisZTys3vlGT22IAJuEpIchfH0xqrOCuaT3s/PpbrLAW1ps2fZ1CuxDeV3mPJkjZc0TpMN+w9Q8ZpxxzDtVu/IztH5fX457JKHINXEhhIi7DgRPlvHiO7mJGXv96xRrfL1mNRAF6vJs1y9iVlFouXlfQJZLRj8g3x9hZae7E31vCTmMsjuOpEwGhynDuXnJCCR+BLyM/PCuC5XL9X3i7Is57OTB6MSupQHF8I/1PgRCOhGUU7L2Efu0tM+eS5sxtz0oRG8MkyAsTquCg4vXO+qyMdzhGx/4w2EcJnImhnAhnR59+tu2bVAIRpbrawuxglNHafNwh0PbDl3iiyFtbqnGR7XlhJtitGFBO1tMEmf6JoerqQ+uYQ==]]></Encrypt></xml>";

		
		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
		String mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);
		System.out.println("加密后: " + mingwen);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(mingwen);
		InputSource is = new InputSource(sr);
		Document document = db.parse(is);

		Element root = document.getDocumentElement();
		NodeList nodelist1 = root.getElementsByTagName("Encrypt");
		NodeList nodelist2 = root.getElementsByTagName("MsgSignature");

		String encrypt = nodelist1.item(0).getTextContent();
		String msgSignature = nodelist2.item(0).getTextContent();

		String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
		String fromXML = String.format(format, encrypt);
		

//		String format = "<xml><ToUserName><![CDATA[]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
//		String fromXML = String.format(format, "zABNOO10ICfuTrZegTkBSO72qjhwq4Q06FxJLBZwwxKt3z+SRMHRreFVpBqpR8O2NgkfizS4dA95yr79osHX4BnOVvDBxWOkbrisZTys3vlGT22IAJuEpIchfH0xqrOCuaT3s/PpbrLAW1ps2fZ1CuxDeV3mPJkjZc0TpMN+w9Q8ZpxxzDtVu/IztH5fX457JKHINXEhhIi7DgRPlvHiO7mJGXv96xRrfL1mNRAF6vJs1y9iVlFouXlfQJZLRj8g3x9hZae7E31vCTmMsjuOpEwGhynDuXnJCCR+BLyM/PCuC5XL9X3i7Is57OTB6MSupQHF8I/1PgRCOhGUU7L2Efu0tM+eS5sxtz0oRG8MkyAsTquCg4vXO+qyMdzhGx/4w2EcJnImhnAhnR59+tu2bVAIRpbrawuxglNHafNwh0PbDl3iiyFtbqnGR7XlhJtitGFBO1tMEmf6JoerqQ+uYQ==");

		//
		// 公众平台发送消息给第三方，第三方处理
		//

		// 第三方收到公众号平台发送的消息
		String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
		System.out.println("解密后明文: " + result2);
		
		//pc.verifyUrl(null, null, null, null);
	}
}
