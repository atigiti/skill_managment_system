package com.tigiti.skill_managment_system.service.mailService;

import com.tigiti.skill_managment_system.enteties.Job;
import com.tigiti.skill_managment_system.enteties.Skill;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("NotifyExpertForSkillEval")
public class NotifyExpertForSkillEval extends MailService {


    @Override
    public void sendMail(Job job, Skill skill, String mail, Optional<String> message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail);
        mailMessage.setFrom("tigitidev@gmail.com");
        mailMessage.setSubject("Convocation pour Tester Le Niveau Requis ");
        mailMessage.setText("Vous êtes invité à Tester le Noveau Requis pour le Poste  "+job.getJobTitle()+"\n" +
                "Merci d'utiliser ce code Suivant : "+ job.getJobCode() +"\n"+
                "Skill Code : "+ skill.getCodeSkill());
        javaMailSender.send(mailMessage);
    }
}
