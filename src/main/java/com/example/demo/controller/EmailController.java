package com.example.demo.controller;

import com.example.demo.MailSender.EmailSender;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
@Slf4j
public class EmailController {
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;
    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @RequestMapping("/api")
    public String send() {
        try {
            Context context = new Context();
            context.setVariable("header", "Nowy artykuł na CodeCouple");
            context.setVariable("title", "#8 Spring Boot – email - szablon i wysyłanie");
            context.setVariable("description", "Tutaj jakis opis...");
            String body = templateEngine.process("template", context);
            emailSender.sendEmail("michalzma@gmail.com", "CodeCouple Newsletter", body);
        }
        catch (MailException e){
            log.info("asaaasasaasasasasasasa "+e.getMessage());
        }
        return "template";
    }
}