package com.tigiti.skill_managment_system.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Data @AllArgsConstructor @NoArgsConstructor
public class EvaluationForm {
    private String idEvaluation;
    //private String idCandidat;
    private String idSkill;
    private Date date;
    private String message;
}
