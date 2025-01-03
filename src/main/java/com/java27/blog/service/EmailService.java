package com.java27.blog.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
	public class EmailService {
	@Value("${sendgrid.key}")
	String key;

		public void sendMail() throws IOException {
			Email from = new Email("monteforteluigi28@gmail.com");
			String subject = "Sending with SendGrid is Fun";
			Email to = new Email("luigimonteforte28@libero.it");
			Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
			Mail mail = new Mail(from, subject, to, content);

			SendGrid sg = new SendGrid(System.getenv("key"));
			Request request = new Request();
			try {
				request.setMethod(Method.POST);
				request.setEndpoint("mail/send");
				request.setBody(mail.build());
				Response response = sg.api(request);
				System.out.println(response.getStatusCode());
				System.out.println(response.getBody());
				System.out.println(response.getHeaders());
			} catch (IOException ex) {
				throw ex;
			}
		}
	}

