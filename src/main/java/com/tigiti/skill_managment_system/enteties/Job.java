package com.tigiti.skill_managment_system.enteties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Component
public class Job implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJob;
    private String jobTitle;
    private String jobDescription;
    private String jobCode;
    @JsonIgnoreProperties
    @ManyToOne(fetch = FetchType.LAZY)
    private EnterpriseAdmin enterpriseAdmin;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private Collection<Skill> skills = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private Collection<RankingResult> rankingResults = new ArrayList<>();

}
