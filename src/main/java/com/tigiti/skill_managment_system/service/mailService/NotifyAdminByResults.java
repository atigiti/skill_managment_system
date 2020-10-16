package com.tigiti.skill_managment_system.service.mailService;

import com.tigiti.skill_managment_system.enteties.Job;
import com.tigiti.skill_managment_system.enteties.Skill;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("NotifyAdminByResults")
public class NotifyAdminByResults extends MailService {

    @Override
    public void sendMail(Job job, Skill skill, String mail, Optional<String> messaga) {

    }
}
