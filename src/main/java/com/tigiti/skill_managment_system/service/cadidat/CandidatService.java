package com.tigiti.skill_managment_system.service.cadidat;

import com.tigiti.skill_managment_system.enteties.AcquiredNote;
import com.tigiti.skill_managment_system.enteties.Candidat;
import com.tigiti.skill_managment_system.enteties.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidatService {
    public Job getProposedJob(String codeJob);
    public List<AcquiredNote> getAcquiredNote(Long idCandidat, Long idJob);
    public Candidat getCandidat(String username);
}
