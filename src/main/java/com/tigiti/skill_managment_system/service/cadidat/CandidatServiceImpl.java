package com.tigiti.skill_managment_system.service.cadidat;

import com.tigiti.skill_managment_system.dao.AppUserRepository;
import com.tigiti.skill_managment_system.dao.CandidatRepository;
import com.tigiti.skill_managment_system.enteties.AcquiredNote;
import com.tigiti.skill_managment_system.enteties.AppUser;
import com.tigiti.skill_managment_system.enteties.Candidat;
import com.tigiti.skill_managment_system.enteties.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CandidatServiceImpl implements CandidatService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private CandidatRepository candidatRepository;
    @Override
    public Job getProposedJob(String codeJob) {
        return null;
    }

    @Override
    public List<AcquiredNote> getAcquiredNote(Long idCandidat, Long idJob) {
        return null;
    }

    @Override
    public Candidat getCandidat(String username) {
        AppUser appUser = appUserRepository.findAppUserByUserName(username);
        Candidat candidat = candidatRepository.findCandidatByAppUser(appUser);
        return candidat;
    }


}
