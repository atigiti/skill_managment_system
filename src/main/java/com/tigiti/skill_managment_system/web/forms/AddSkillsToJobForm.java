package com.tigiti.skill_managment_system.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data @AllArgsConstructor @NoArgsConstructor
public class AddSkillsToJobForm {
    private Long idJob;
    private String skillTitle;
    private String requiredNote;
    private List<String> expertForRequiredMails = new ArrayList<>();
    private List<String> expertForAcquiredMails = new ArrayList<>();

}
