package com.tigiti.skill_managment_system.service.mailService;

import com.tigiti.skill_managment_system.enteties.Job;
import com.tigiti.skill_managment_system.enteties.Skill;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("NotifyNewCandidat")
public class NotifyNewCandidat extends MailService {

    @Override
    public void sendMail(Job job, Skill skill, String mail, Optional<String> message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail);
        mailMessage.setFrom("tigitidev@gmail.com");
        mailMessage.setSubject("Convocation pour Passer un Test de Recrutement");
        mailMessage.setText("Vous êtes convoqué à passer un Stage pour le post "+job.getJobTitle()+"\n" +
                "Sur la platforme skillManagment.org \n" +
                " pour s'inscrire au test utiliser le code Suivant : "+ job.getJobCode() );
    }
}
