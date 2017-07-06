package com.hiwatch.watch.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.hiwatch.watch.entity.MailParam;

@Component("mailBiz")
public class MailBiz {
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private SimpleMailMessage simpleMailMessage;
	
	public void mainSend(final MailParam mailParam) throws Exception{
		simpleMailMessage.setFrom(simpleMailMessage.getFrom());
		simpleMailMessage.setTo(mailParam.getTo());
		simpleMailMessage.setSubject(mailParam.getSubject());
		simpleMailMessage.setText(mailParam.getContent());
		
		mailSender.send(simpleMailMessage);
	}
	
}
