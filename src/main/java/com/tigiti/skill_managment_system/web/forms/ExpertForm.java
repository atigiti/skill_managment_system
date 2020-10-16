package com.tigiti.skill_managment_system.web.forms;

import com.tigiti.skill_managment_system.enteties.AppUser;
import com.tigiti.skill_managment_system.enteties.Expert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class ExpertForm {
    //for Expert Object
    private String firstName;
    private String lastName;
    private String mail;
    private String tele;
    private Date dateNassance;
    private String sexe;
    // For User Object
    private String userName;
    private String password;

}
