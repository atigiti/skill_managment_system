package com.tigiti.skill_managment_system.service.mailService;

import com.tigiti.skill_managment_system.enteties.Job;
import com.tigiti.skill_managment_system.enteties.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public abstract class MailService {
    @Autowired
    protected JavaMailSender javaMailSender;
    public abstract void sendMail(Job job, Skill skill, String mail, Optional<String> message);
}
