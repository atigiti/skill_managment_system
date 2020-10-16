package com.tigiti.skill_managment_system.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data @AllArgsConstructor @NoArgsConstructor
public class RequirementNoteForm {
    private String idSkill;
    private String idExpert;
    private String requiredNote_1;
    private String requiredNote_2;
}
