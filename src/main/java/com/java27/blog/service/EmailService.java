package com.java27.blog.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class EmailService {
    @Value("${send-email.email-from}")
    private String emailFrom;

    @Value("${send-email.secret-key}")
     private String secretKey;

    public void sendEmail(String emailTo, String link) throws IOException {
        Email from = new Email(emailFrom);
        String subject = "Registrazione";
        Email to = new Email(emailTo);
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java" + link);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(secretKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            log.info("Email inviata con status " + (response.getStatusCode()));
        } catch (IOException ex) {
            log.error("Error: " + ex.getMessage());
            throw ex;
        }
    }
}
