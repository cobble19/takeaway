package com.cobble.takeaway.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailUtil {
	private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	
	public static InternetAddress[] parseAddrs(String addrs) {
		InternetAddress[] ret = null;
		try {
			if (StringUtils.isBlank(addrs)) {
				ret = null;
				return ret;
			}
			if (StringUtils.contains(addrs, ";")) {
				List<InternetAddress> v = new ArrayList<InternetAddress>();
				String[] addrArr = StringUtils.split(addrs, ";");
				if (ArrayUtils.isNotEmpty(addrArr)) {
					for (int i = 0; i < addrArr.length; i++) {
						InternetAddress temp = new InternetAddress(addrArr[i]);
						v.add(temp);
					}
					ret = new InternetAddress[v.size()];
					v.toArray(ret);
					return ret;
				}
			}
			ret = InternetAddress.parse(addrs);
		} catch (Exception e) {
			logger.error("Exception: {}", e);
		}
		return ret;
	}
	
	public static InternetAddress parseAddr(String addr) {
		InternetAddress ret = null;
		try {
			ret = new InternetAddress(addr);
		} catch (Exception e) {
			logger.error("Exception: {}", e);
		}
		return ret;
	}
	
	public static String sendEailAsync(String from, String tos, String ccs, String bccs
			, String subject, String content, String contentType, String filePaths) {
		String ret = "";
		logger.info("sendEailAsync start...");
		
		EmailThread emailThread = new EmailUtil().new EmailThread(from, tos, ccs, bccs, subject, content, contentType, filePaths);
		emailThread.start();
		
		logger.info("sendEailAsync end...");
		return ret;
	}
	
	public static String sendEailAsync(String from, String tos
			, String subject, String content) {
		String ret = "";
		logger.info("sendEailAsync start...");
		
		EmailUtil.sendEailAsync(from, tos, null, null, subject, content, null, null);
		
		logger.info("sendEailAsync end...");
		return ret;
	}
	
	class EmailThread extends Thread {
		String from;
		String tos;
		String ccs;
		String bccs;
		String subject;
		String content;
		String contentType;
		String filePaths;
		public EmailThread(String from, String tos, String ccs, String bccs
				, String subject, String content, String contentType, String filePaths) {
			this.from = from;
			this.tos = tos;
			this.ccs = ccs;
			this.bccs = bccs;
			this.subject = subject;
			this.content = content;
			this.contentType = contentType;
			this.filePaths = filePaths;
		}

		@Override
		public void run() {
			EmailUtil.sendEmail(from, tos, ccs, bccs, subject, content, contentType, filePaths);
		}
		
	}
	
	public static String sendEmail(String from, String tos, String ccs, String bccs
			, String subject, String content, String contentType, String filePaths) {
		String ret = "";
		try {
			logger.info("from: {}, tos: {}, ccs: {}, bccs: {}, subject: {}, content: {}, contentType: {}, filePaths: {}",
					from, tos, ccs, bccs, subject, content, contentType, filePaths);
			
			InternetAddress fromAddr = null;
			try {
				fromAddr = parseAddr(from);
			} catch (Exception e) {
				logger.error("Exception: {}", e);
			}
			
			InternetAddress[] toAddrs = parseAddrs(tos);
			InternetAddress[] ccAddrs = parseAddrs(ccs);
			InternetAddress[] bccAddrs = parseAddrs(bccs);
			// Get system properties
			Properties properties = System.getProperties();
			String host = "mda.webex.com";
			// Setup mail server
			/*properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");*/
			properties.put("mail.smtp.host", host);
			/*properties.put("mail.smtp.port", "25");*/

			// Get the default Session object.
			Session session = Session.getDefaultInstance(properties);

			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(fromAddr);

			// Set To: header field of the header.
			message.addRecipients(Message.RecipientType.TO, toAddrs);
			
			message.addRecipients(Message.RecipientType.CC, ccAddrs);
			message.addRecipients(Message.RecipientType.BCC, bccAddrs);

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
//			message.setText("This is actual message");

			// Create a multipart message
			Multipart multipart = new MimeMultipart();

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			if (StringUtils.isBlank(contentType)) {
				messageBodyPart.setText(content);
			} else {
				messageBodyPart.setContent(content, contentType);
			}

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			String[] filePathArr = null;
			if (StringUtils.isNotBlank(filePaths)) {
				filePathArr = StringUtils.split(filePaths, ",");
			}
			if (ArrayUtils.isNotEmpty(filePathArr)) {
				for (int i = 0; i < filePathArr.length; i++) {
					String filePath = filePathArr[i];
					messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(filePath);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(filePath);
					multipart.addBodyPart(messageBodyPart);
				}
			}

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);
			logger.info("Sent message successfully....");
			
		} catch (AddressException e) {
			logger.error("AddressException: {}", e);
		} catch (MessagingException e) {
			logger.error("MessagingException: {}", e);
		} catch (Exception e) {
			logger.error("Exception: {}", e);
		}
		
		
		return ret;
	}
	
	public static void main(String[] argv) {
//		EmailUtil eu = new EmailUtil();
		EmailUtil.sendEailAsync("bange@cisco.com", "abc123@cisco.com;bange@cisco.com", "bange@cisco.com", "bange@cisco.com", "Async email", "<a href='www.baidu.com'>baidu</a>", "text/html", null);
		
//		EmailUtil.sendEmail("bange@cisco.com", "abc123@cisco.com;bange@cisco.com", "bange@cisco.com", "bange@cisco.com", "Sync email", "abc", "text/html", null);

		/*try {
			InternetAddress[] addrs = InternetAddress.parse("ba@cisco.com,a1@cisco.com,b1@cisco.com");
			logger.info(addrs + "");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
}
