package com.tigiti.skill_managment_system.web.frontControllers;

import com.tigiti.skill_managment_system.enteties.*;
import com.tigiti.skill_managment_system.service.account.AccountService;
import com.tigiti.skill_managment_system.service.expert.ExpertService;
import com.tigiti.skill_managment_system.web.forms.AcquiredNoteForm;
import com.tigiti.skill_managment_system.web.forms.EvaluationForm;
import com.tigiti.skill_managment_system.web.forms.ExpertForm;
import com.tigiti.skill_managment_system.web.forms.RequirementNoteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.RequestingUserName;
import java.util.List;

@RestController
public class ExpertController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ExpertService expertService;

    @GetMapping("/getNotTestedRequiredSkills")
    public List<RequirementNote> getNotTestedRequirementSkill(){
        return null;
    }

    @GetMapping("/getSkillForRequiredNote/{codeSkill}/{idExpert}")
    public RequirementNote getSkillForRequiredNote(@PathVariable("codeSkill") String codeSkill, @PathVariable("idExpert") String idExpert){
        RequirementNote req = expertService.getSkillForRequiredNote(codeSkill, idExpert);
        return req;
    }

    @GetMapping("/getSkillForAcquiredNote/{codeSkill}")
    public Skill getSkillBuId(@PathVariable("codeSkill") String codeSkill){
            return expertService.getSkillForAcquiredNote(codeSkill);
    }

    @GetMapping("/getSkillOfRequirementNote/{idRequirement}")
    public Skill getSkillOfRequirementNote(@PathVariable("idRequirement") String idRequirement){
        Skill skill = expertService.getSkillOfRequirementNote(idRequirement);
        return null;
    }

    @GetMapping("/getAcquiredNotes/{idSkill}/{idExpert}")
    public List<AcquiredNote> getAcuiredNotes(@PathVariable("idSkill") String idSkill, @PathVariable("idExpert") String idExpert){
        return expertService.getAcuiredNotes(idSkill, idExpert);
    }

    @GetMapping("/getCandidatForAcquiredNote/{idAcquiredNote}")
    public Candidat getCandidatForAcquiredNote(@PathVariable("idAcquiredNote") String idAcquiedNote){
        return expertService.getCandidatForAcquiredNote(idAcquiedNote);
    }

    @PostMapping("/setRequirementNote")
    public void setRequiredNote(@RequestBody RequirementNoteForm requiredNoteForm){
        // insere juste la note le record déjà crée
        expertService.setRequiredNote(requiredNoteForm);
    }

    @GetMapping("/getNotTestedAcquiredSkill")
    public List<Skill> getNotTestedAcquiredSkill(@RequestBody Expert expert){
        return null;
    }

    @PostMapping("/setEvaluation")
    public void setEvaluation(@RequestBody EvaluationForm evaluationForm){
        // Insere juste la date le record déjà crée
        expertService.setEvaluation(evaluationForm);
    }

    @PostMapping("/setAcquiredNote")
    public void setAcquiredNote(@RequestBody AcquiredNoteForm acquiredNoteForm){
        // inserer juste la note le record déjà crée
        expertService.setAcquiredNote(acquiredNoteForm);
    }

    @PostMapping("/registerExpert")
    public Expert registerExpert(@RequestBody ExpertForm expertForm){
        return accountService.saveExpert(expertForm);
    }


    @GetMapping("/getExpertDetails/{username}")
    public Expert getExpert(@PathVariable("username") String username){
        Expert expert = expertService.getExpert(username);
        expert.getRequirementNotes().clear();
        expert.getEvaluations().clear();
        expert.setAppUser(null);
        System.out.println(expert.getFirstName());
        return expert;
    }


    @GetMapping("/getSkillOfEvaluation/{idEvaluation}")
    public Skill getSkillOfEvaluation(@PathVariable("idEvaluation") String idEvaluation){
        System.out.println("effffffffffffffffffff");
        return expertService.getSkillOfEvaluation(idEvaluation);
    }
    @GetMapping("/getEvaluationsOfExpert/{idExpert}")
    public List<Evaluation> getEvaluationsOfExpert(@PathVariable("idExpert") String idExpert){
        return expertService.getEvaluationsOfExpert(idExpert);
    }
}
