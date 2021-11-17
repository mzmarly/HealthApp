package com.example.demo.MailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderImpl implements EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    private final String pathToAttachment="C:/Users/micha/Downloads/HealthApp/src/main/java/logo.jpg";
    @Override
    public void sendEmail(String to, String title, String content) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
//            helper.setReplyTo("newsletter@codecouple.pl");
//            helper.setFrom("newsletter@codecouple.pl");
            helper.setSubject(title);
            helper.setText(content, true);
            FileSystemResource file  = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("logo.jpg", file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
    }

}