package com.tigiti.skill_managment_system.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Component
public class Expert implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExpert;
    private String firstName;
    private String lastName;
    private String mail;
    private String tele;
    private Date dateNaissance;
    private String sexe;
    @OneToMany(mappedBy = "expertForSkillEvaluation",fetch = FetchType.LAZY)
    private Collection<RequirementNote> requirementNotes = new ArrayList<>();
    @OneToMany(mappedBy = "expert",fetch = FetchType.LAZY)
    private Collection<Evaluation> evaluations = new ArrayList<>();
    @OneToOne
    private AppUser appUser;

}
