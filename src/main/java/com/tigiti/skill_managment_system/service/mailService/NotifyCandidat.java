package com.tigiti.skill_managment_system.service.mailService;

import com.tigiti.skill_managment_system.enteties.Job;
import com.tigiti.skill_managment_system.enteties.Skill;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("NotifyCandidat")
public class NotifyCandidat extends MailService{

    @Override
    public void sendMail(Job job,Skill skill, String mail, Optional<String> message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail);
        mailMessage.setFrom("tigitidev@gmail.com");
        mailMessage.setSubject("Convocation pour Passer un Test de Recrutement");
        if(message.isPresent()){
            mailMessage.setText(message.get()+"\n Job Title : "+job.getJobTitle()+" \n pour s'inscrire au test utiliser le code Suivant : "+ job.getJobCode() );
        }
        else{
            mailMessage.setText("Vous êtes convoqué à passer un Test pour le post "+job.getJobTitle()+"\n" +
                    "pour s'inscrire au test utiliser le code Suivant : "+ job.getJobCode() );
        }

        javaMailSender.send(mailMessage);
    }
}
