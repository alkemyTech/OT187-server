package com.alkemy.ong.service;

import com.alkemy.ong.utility.EmailUtility;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;


    @Override
    public void contactEmail(String email) throws IOException {
        send(email, EmailUtility.EMAIL_SUBJECT_CONTACT, EmailUtility.EMAIL_TEMPLATE_CONTACT);
    }

    public void registerEmail(String send) throws IOException {
        send(send, EmailUtility.EMAIL_SUBJECT_REGISTER, EmailUtility.EMAIL_TEMPLATE_REGISTER);
    }

    @Override
    public void send(String send, String subject_email, String template) throws IOException {

        String apiKey = env.getProperty("EMAIL_API_KEY");

        Email from = new Email(EmailUtility.EMAIL_FROM);
        Email to = new Email(send);
        String subject = subject_email;
        Content content = new Content(EmailUtility.EMAIL_TYPE, getEmailFromResources(template));
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint(EmailUtility.EMAIL_ENDPOINT);
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {

        }
    }

    private String getEmailFromResources(String template) throws IOException {
        String email = "";
        String line;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            InputStream inputStream = classLoader.getResourceAsStream(template);
            assert inputStream != null;
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            while ((line = reader.readLine()) != null) {
                email = email.concat(line);
            }
        } catch (IOException e) {

        }
        return email;

    }


}
