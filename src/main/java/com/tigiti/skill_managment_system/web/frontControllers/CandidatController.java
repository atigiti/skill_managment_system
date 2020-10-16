package com.tigiti.skill_managment_system.web.frontControllers;

import com.tigiti.skill_managment_system.enteties.AcquiredNote;
import com.tigiti.skill_managment_system.enteties.Candidat;
import com.tigiti.skill_managment_system.enteties.Job;
import com.tigiti.skill_managment_system.service.account.AccountService;
import com.tigiti.skill_managment_system.service.cadidat.CandidatService;
import com.tigiti.skill_managment_system.web.forms.CandidatForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CandidatController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CandidatService candidatService;

    @GetMapping("/getProposedJob")
    public Job getProposedJob(@RequestBody String codeJob){
        return null;
    }

    @GetMapping("/getAcquiredNote")
    public List<AcquiredNote> getAcquiredNote(@RequestBody Candidat candidat){
        return null;
    }

    @PostMapping("/registerCandidat")
    public Candidat registerCandidat(@RequestBody CandidatForm candidatForm){
       return  accountService.saveCandidat(candidatForm);
    }

    @GetMapping("/getCandidatDetails/{username}")
    public Candidat getCandidat(@PathVariable("username") String username){
        Candidat candidat = candidatService.getCandidat(username);
        candidat.getCandidatSkills().clear();
        candidat.getRankingResults().clear();
        candidat.getAcquiredNotes().clear();
        candidat.setAppUser(null);
        System.out.println(candidat);
        return candidat;
    }
}
