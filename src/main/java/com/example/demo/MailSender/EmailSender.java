package com.example.demo.MailSender;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);

}