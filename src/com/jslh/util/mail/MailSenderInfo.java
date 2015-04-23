package com.jslh.util.mail;

import java.util.Properties;

import com.jslh.util.PropertiesUtil;

public class MailSenderInfo {

	// 发送邮件的服务器的IP和端口
	private String mailServerHost;
	private String mailServerPort;
	// 邮件接收者的地址
	private String toAddress;
	// 登陆邮件发送服务器的用户名和密码
	private String userName;
	private String password;
	// 是否需要身份验证
	private boolean validate = false;
	// 邮件主题
	private String subject;
	// 邮件的文本内容
	private String content;
	// 邮件附件的文件名
	private String[] attachFileNames;

	// 对部分字段使用从配置文件中读取配置进行初始化
	public MailSenderInfo() {
		this.mailServerHost = PropertiesUtil.getConfigValue("mail.smtp.host");
		this.mailServerPort = PropertiesUtil.getConfigValue("mail.smtp.port");
		this.validate = Boolean.getBoolean(PropertiesUtil.getConfigValue("mail.smtp.auth"));
		this.userName = PropertiesUtil.getConfigValue("mail.username");
		this.password = PropertiesUtil.getConfigValue("mail.password");
	}

	// 对部分字段使用从配置文件中读取配置进行初始化
	public MailSenderInfo(String mailServerHost, String mailServerPort,
			boolean validate) {
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.validate = validate;
	}

	/**
	 * 获得邮件会话属性
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	private String generateServerHost(String userName) {
		String serverHost = "";
		if (null != userName && userName.indexOf("@") != -1) {
			serverHost = "smtp." + userName.split("@")[1];
		}
		return serverHost;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;

		this.mailServerHost = generateServerHost(userName);

	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}
}