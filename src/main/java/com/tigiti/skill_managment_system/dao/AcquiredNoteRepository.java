package com.tigiti.skill_managment_system.dao;

import com.tigiti.skill_managment_system.enteties.AcquiredNote;
import com.tigiti.skill_managment_system.enteties.Candidat;
import com.tigiti.skill_managment_system.enteties.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin
public interface AcquiredNoteRepository extends JpaRepository<AcquiredNote, Long> {
    public List<AcquiredNote> findAcquiredNotesByEvaluation(Evaluation evaluation);
    public AcquiredNote findAcquiredNotesByCandidatAndEvaluation(Candidat candidat, Evaluation evaluation);

}
