package com.test;

import org.junit.Test;
import com.jslh.util.mail.MailSenderInfo;
import com.jslh.util.mail.SimpleMailSender;

public class MailTest {

	@Test
	public void sendMailConfirm() throws Exception{
		
		MailSenderInfo mailInfo = new MailSenderInfo();
		
		mailInfo.setValidate(true);
		mailInfo.setToAddress("wzydoublex@126.com");
		mailInfo.setSubject("平台验证邮件");
		
		mailInfo.setContent("发送的内容");
		
		SimpleMailSender.sendHtmlMail(mailInfo);
	}
}
