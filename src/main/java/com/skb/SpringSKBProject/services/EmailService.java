package com.skb.SpringSKBProject.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String toEmail, String subject, String message) {

//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//        mailMessage.setTo(toEmail);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(message);
//
//        mailMessage.setFrom("johndoe@example.com");
//
//        javaMailSender.send(mailMessage);
        log.info("Отправка email юзеру : " + toEmail + " c текстом : " + message);
    }
}