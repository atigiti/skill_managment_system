package com.tigiti.skill_managment_system.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class EnterpriseAdminForm {
    // For Enterprise Admin
    private String enterpriseAdminFirstName;
    private String enterpriseAdminLastName;
    private String enterpriseName;
    private String enterpriseMail;
    private String tele;

    // For User Object
    private String userName;
    private String password;
}
