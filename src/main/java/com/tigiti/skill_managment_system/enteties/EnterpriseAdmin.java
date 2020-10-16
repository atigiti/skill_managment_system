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
public class EnterpriseAdmin implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enterpriseAdminId;
    private String enterpriseAdminFirstName;
    private String enterpriseAdminLastName;
    private String enterpriseName;
    private String enterpriseMail;
    private String tele;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy ="enterpriseAdmin",fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private Collection<Job> jobs= new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    private AppUser appUser;
}
