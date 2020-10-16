package com.tigiti.skill_managment_system.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Cette sert à stocker les emails des candidat qui n'ont pas encore inscrit dans la platforme
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class CandidatNotSubsctribed {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Job job;
    private String candidatMail;

}
