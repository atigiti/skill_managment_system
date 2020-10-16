package com.tigiti.skill_managment_system.service.enterpriseAdmine;

import com.tigiti.skill_managment_system.enteties.*;
import com.tigiti.skill_managment_system.web.forms.AddCandidatToJobForm;
import com.tigiti.skill_managment_system.web.forms.AddJobForm;
import com.tigiti.skill_managment_system.web.forms.AddSkillsToJobForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnterpriseAdminService {
    public Job addJob(AddJobForm jobForm);
    public Job addSkillToJob(List<AddSkillsToJobForm> addSkillsToJobForm);
    public List<RankingResult> getRankingResults(Job job);
    public Job addCandidatToJob(List<AddCandidatToJobForm> candidatToJobForms);
    /*public void notifyCandidat(String candidatMail,Job job);
    public void notifyNewCandidat(String candidatMail,Job job);*/
    public void notifyExpertForCandidatEvaluation(Skill skill);
    public void notifyExpertForSkillEvaluation(RequirementNote requirementNote);
    public void sendJobOffer(Candidat candidat, Job job);
    public EnterpriseAdmin getEnterpriseAdmin(String username);
}
