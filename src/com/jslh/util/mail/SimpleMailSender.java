package com.jslh.util.mail;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SimpleMailSender {

	private static final String baseDir = SimpleMailSender.class
			.getClassLoader().getResource("").getPath();

	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 * @throws Exception
	 */
	public static void sendTextMail(MailSenderInfo mailInfo) throws Exception {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 创建邮件发送者地址
		Address from = new InternetAddress(mailInfo.getUserName());
		// 设置邮件消息的发送者
		mailMessage.setFrom(from);
		// 创建邮件的接收者地址，并设置到邮件消息中
		Address to = new InternetAddress(mailInfo.getToAddress());
		mailMessage.setRecipient(Message.RecipientType.TO, to);
		// 设置邮件消息的主题
		mailMessage.setSubject(mailInfo.getSubject());
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());
		// 设置邮件消息的主要内容
		String mailContent = mailInfo.getContent();
		mailMessage.setText(mailContent);

		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		// 添加附件必须设置邮件类型
		if (null != mailInfo.getAttachFileNames()
				&& mailInfo.getAttachFileNames().length > 0) {
			// 如果有附件
			Multipart multiPart = new MimeMultipart("mixed");
			mailMessage.setContent(multiPart);
			for (String fileName : mailInfo.getAttachFileNames()) {
				MimeBodyPart attch = new MimeBodyPart();
				multiPart.addBodyPart(attch);
				// 设置附件的名称 MimeUtility.encodeText防止中文文件名乱码
				attch.setFileName(MimeUtility.encodeText(fileName));
				// 设置数据源（即数据的来源）
				DataSource ds = new FileDataSource(baseDir + fileName);
				DataHandler dh = new DataHandler(ds);
				// 设置附件的句柄给这个附件对象
				attch.setDataHandler(dh);
			}
		}

		// 发送邮件
		Transport.send(mailMessage);

		// 如果有附件 邮件发送成功后 删除临时文件
		if (null != mailInfo.getAttachFileNames()
				&& mailInfo.getAttachFileNames().length > 0) {
			for (String fileName : mailInfo.getAttachFileNames()) {
				File file = new File(baseDir + fileName);
				if (file.isFile() && file.exists()) {
					file.delete();
				}
			}
		}
	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 * @throws Exception
	 */
	public static void sendHtmlMail(MailSenderInfo mailInfo) throws Exception {

		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 创建邮件发送者地址
		Address from = new InternetAddress(mailInfo.getUserName());
		// 设置邮件消息的发送者
		mailMessage.setFrom(from);
		// 创建邮件的接收者地址，并设置到邮件消息中
		Address to = new InternetAddress(mailInfo.getToAddress());
		// Message.RecipientType.TO属性表示接收者的类型为TO
		mailMessage.setRecipient(Message.RecipientType.TO, to);
		// 设置邮件消息的主题
		mailMessage.setSubject(mailInfo.getSubject());
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart multiPart = new MimeMultipart("mixed");
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart html = new MimeBodyPart();
		// 设置HTML内容
		html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
		multiPart.addBodyPart(html);
		// 将MiniMultipart对象设置为邮件内容
		mailMessage.setContent(multiPart);

		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		// 添加附件必须设置邮件类型
		if (null != mailInfo.getAttachFileNames()
				&& mailInfo.getAttachFileNames().length > 0) {
			// 如果有附件
			for (String fileName : mailInfo.getAttachFileNames()) {
				MimeBodyPart attch = new MimeBodyPart();
				multiPart.addBodyPart(attch);
				// 设置附件的名称 MimeUtility.encodeText防止中文文件名乱码
				attch.setFileName(MimeUtility.encodeText(fileName));
				// 设置数据源（即数据的来源）
				DataSource ds = new FileDataSource(baseDir + fileName);
				DataHandler dh = new DataHandler(ds);
				// 设置附件的句柄给这个附件对象
				attch.setDataHandler(dh);
			}
		}

		// 发送邮件
		Transport.send(mailMessage);

		// 如果有附件 邮件发送成功后 删除临时文件
		if (null != mailInfo.getAttachFileNames()
				&& mailInfo.getAttachFileNames().length > 0) {
			for (String fileName : mailInfo.getAttachFileNames()) {
				File file = new File(baseDir + fileName);
				if (file.isFile() && file.exists()) {
					file.delete();
				}
			}
		}

	}
}
