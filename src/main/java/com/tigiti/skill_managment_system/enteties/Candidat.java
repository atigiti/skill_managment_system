package com.tigiti.skill_managment_system.enteties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Component
public class Candidat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidat;
    private String firstName;
    private String lastName;
    private String mail;
    private String tele;
    private Date dateNaissance;
    private String sexe;
    private String candidatCode;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "candidat", fetch = FetchType.LAZY)
    private Collection<CandidatSkill> candidatSkills = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "candidat", fetch = FetchType.LAZY)
    private Collection<RankingResult> rankingResults = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "candidat", fetch = FetchType.LAZY)
    private Collection<AcquiredNote> acquiredNotes = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

}
