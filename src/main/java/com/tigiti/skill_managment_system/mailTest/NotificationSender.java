package com.tigiti.skill_managment_system.mailTest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationSender {

    private JavaMailSender javaMailSender ;

    @Autowired
    public NotificationSender(JavaMailSender javaMailSender ){
        this.javaMailSender = javaMailSender;

    }

    // create Message
    public void sendNotification(User user) throws MailException {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmailAdress());
        mailMessage.setFrom("tigitidev@gmail.com");
        mailMessage.setSubject("Subject Example from my Apps");
        mailMessage.setText("hello world, devvvvvvv");

        javaMailSender.send(mailMessage);
    }
}
