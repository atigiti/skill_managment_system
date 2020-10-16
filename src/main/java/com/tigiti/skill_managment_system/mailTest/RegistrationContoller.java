package com.tigiti.skill_managment_system.mailTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationContoller {
    @Autowired
    private NotificationSender notificationSender;

    @RequestMapping("/signup")
    public String signup(){
        return "Please Sign Up";
    }

    @RequestMapping("/signup-succes")
    public String signupSuccess(){
        // Send Notification

        User user = new User();
        user.setFirstName("Abdeallah");
        user.setLastName("tigiti");
        user.setEmailAdress("tigitiabdeallah@gmail.com");

        try{
            notificationSender.sendNotification(user);
        }
        catch (MailException e){
            System.out.println(e.getMessage());
        }
        return "Thank you for regestration";
    }

}
