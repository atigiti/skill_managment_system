package com.tigiti.skill_managment_system.enteties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Component
public class Skill implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSkill;
    private String skillTitle;
    private boolean fullyRequirementTested;
    private boolean fullyAcuiredTested;
    private String requiredNote;
    private String codeSkill;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Job job;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private Collection<RequirementNote> requirementNotes = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private Collection<Evaluation> evaluations = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private Collection<CandidatSkill> candidatSkills = new ArrayList<>();

}
