package com.tigiti.skill_managment_system.dao;

import com.tigiti.skill_managment_system.enteties.AcquiredNote;
import com.tigiti.skill_managment_system.enteties.AppUser;
import com.tigiti.skill_managment_system.enteties.Candidat;
import com.tigiti.skill_managment_system.enteties.RankingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin
public interface CandidatRepository extends JpaRepository<Candidat, Long> {
    public Candidat findByAcquiredNotes(AcquiredNote acquiredNote);
    public Candidat findCandidatByRankingResults(RankingResult rankingResult);
    public Candidat findCandidatByMail(String mail);
    public Candidat findCandidatByAppUser(AppUser appUser);
}
