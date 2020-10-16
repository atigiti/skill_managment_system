package com.tigiti.skill_managment_system.enteties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Component
public class RequirementNote implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requirementNoteId;
  //  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(  fetch = FetchType.LAZY)
    private Skill skill;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Expert expertForSkillEvaluation;
    private String requiredNoteOfExpert_1;
    private String requiredNoteOfExpert_2;
}
