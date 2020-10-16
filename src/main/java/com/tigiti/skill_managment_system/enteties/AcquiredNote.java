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
public class AcquiredNote implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAcquiredNote;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Evaluation evaluation;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Candidat candidat;
    // calculable
    private String acquiredNote_1;
    private String acquiredNote_2;
    private String expertComment;
}
