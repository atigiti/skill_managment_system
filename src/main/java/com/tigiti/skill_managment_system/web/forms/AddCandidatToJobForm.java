package com.tigiti.skill_managment_system.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AddCandidatToJobForm {
    //private String idCandidat;
    private String idJob;
    // Si le Candidat n'existe pas dans le système
    // Send Mail to subscribe
    private String candidatMail;
}
