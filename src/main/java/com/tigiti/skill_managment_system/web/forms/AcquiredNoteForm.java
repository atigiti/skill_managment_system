package com.tigiti.skill_managment_system.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data @AllArgsConstructor @NoArgsConstructor
public class AcquiredNoteForm {
    /*private String idExpert;
    private String idEvaluation;
    private String idCandidat;*/
    private String idAcquiredNote;
    private String acquiredNote_1;
    private String acquiredNote_2;
    private String expertComment;
}
