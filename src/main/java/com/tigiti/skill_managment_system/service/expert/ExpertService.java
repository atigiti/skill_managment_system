package com.tigiti.skill_managment_system.service.expert;

import com.tigiti.skill_managment_system.enteties.*;
import com.tigiti.skill_managment_system.web.forms.AcquiredNoteForm;
import com.tigiti.skill_managment_system.web.forms.EvaluationForm;
import com.tigiti.skill_managment_system.web.forms.RequirementNoteForm;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ExpertService {
    public List<RequirementNote> getNotTestRequirementSkill(Long idExpert);
    public List<Skill> getNotTestAcquiredSkill(Long idExpert);
    public Evaluation setEvaluation(EvaluationForm evaluationForm);
    public void setAcquiredNote(AcquiredNoteForm acquiredNoteForm);
    public void setRequiredNote(RequirementNoteForm requiredNoteForm);
    public Expert getExpert(String username);
    public RequirementNote getSkillForRequiredNote(String codeSkill, String idExpert);
    public Skill getSkillOfRequirementNote(String idRequirementNote);
    public Skill getSkillForAcquiredNote(String codeSkill);
    public List<AcquiredNote> getAcuiredNotes(String idSkill, String idExpert);
    public Candidat getCandidatForAcquiredNote(String idAcquiredNote);
    public Skill getSkillOfEvaluation(String idEvaluation);
    public List<Evaluation> getEvaluationsOfExpert(String idExpert);
}
