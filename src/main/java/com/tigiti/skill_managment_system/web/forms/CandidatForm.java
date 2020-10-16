package com.tigiti.skill_managment_system.web.forms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class CandidatForm {
    // For Candidat Object
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
