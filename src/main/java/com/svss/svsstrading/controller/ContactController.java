package com.svss.svsstrading.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
	
	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("/main")
	public String getMain() {
		return "main";
	}

	@PostMapping("/contact")
	public String submitContact(HttpServletRequest request) {

		String name = request.getParameter("Name");
		String email = request.getParameter("Email");
		String subject = request.getParameter("Subject");
		String content = request.getParameter("Message");

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("info@svsstrading.co.za");// <--company mail id-->//
		message.setTo("info@svsstrading.co.za");// <--personal mail id to receive-->//

		String mailSubject = name + " has sent a message";
		String mailContent = "Sender Name: " + name + "\n";
		mailContent += "Sender Email: " + email + "\n";
		mailContent += "Subject: " + subject + "\n";
		mailContent += "Message: " + content + "\n";

		message.setSubject(mailSubject);
		message.setText(mailContent);

		mailSender.send(message);
		return "message";
	}
}
