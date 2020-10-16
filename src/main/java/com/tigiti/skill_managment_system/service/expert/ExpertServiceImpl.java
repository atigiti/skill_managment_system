package com.tigiti.skill_managment_system.service.expert;

import com.tigiti.skill_managment_system.dao.*;
import com.tigiti.skill_managment_system.enteties.*;
import com.tigiti.skill_managment_system.service.mailService.MailService;
import com.tigiti.skill_managment_system.web.forms.AcquiredNoteForm;
import com.tigiti.skill_managment_system.web.forms.EvaluationForm;
import com.tigiti.skill_managment_system.web.forms.RequirementNoteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ExpertServiceImpl implements ExpertService{
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private AcquiredNoteRepository acquiredNoteRepository;
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CandidatNotSubsctribedRepository candidatNotSubsctribedRepository;
    @Autowired
    private ExpertRepository expertRepository;
    @Autowired
    private RequirementNoteRepository requirementNoteRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    @Qualifier("NotifyNewCandidat")
    private MailService newCandidatNotifier;
    @Autowired
    @Qualifier("NotifyCandidat")
    private MailService candidatNotifier;
    @Override
    public List<RequirementNote> getNotTestRequirementSkill(Long idExpert) {
        return null;
    }

    @Override
    public List<Skill> getNotTestAcquiredSkill(Long idExpert) {
        return null;
    }

    @Override
    public Evaluation setEvaluation(EvaluationForm evaluationForm) {
        Evaluation evaluation = evaluationRepository.findById(Long.valueOf(evaluationForm.getIdEvaluation())).get();
        evaluation.setEvaluationDate(evaluationForm.getDate());
        evaluationRepository.save(evaluation);
        // Recupere le Job
        Skill skill = skillRepository.findSkillByEvaluations(evaluation);
        Job job = jobRepository.findJobBySkills(skill);

        // Notifier les Candidat n'est pas encore inscrit dans la platforme
        List<CandidatNotSubsctribed> candidatNotSubsctribeds = new ArrayList<>();
        candidatNotSubsctribeds = candidatNotSubsctribedRepository.findCandidatNotSubsctribedByJob(job);
        candidatNotSubsctribeds.stream()
                .forEach(cns -> {
                    newCandidatNotifier.sendMail(job,skill, cns.getCandidatMail(), java.util.Optional.ofNullable(evaluationForm.getMessage()));
                });


        // List Acuired Note
        List<AcquiredNote> acquiredNotes = acquiredNoteRepository.findAcquiredNotesByEvaluation(evaluation);
        // Notify Candidat
        acquiredNotes.stream()
                .forEach(acquiredNote -> {
                    Candidat candidat = acquiredNote.getCandidat();
                    // Notifier le candidat
                    candidatNotifier.sendMail(job,skill, candidat.getMail(), java.util.Optional.ofNullable(evaluationForm.getMessage()));
                });

        return evaluation;
    }

    @Override
    public void setAcquiredNote(AcquiredNoteForm acquiredNoteForm) {
        /*Candidat candidat = candidatRepository.findById(Long.valueOf(acquiredNoteForm.getIdCandidat())).get();
        Evaluation evaluation = evaluationRepository.findById(Long.valueOf(acquiredNoteForm.getIdEvaluation())).get();
        AcquiredNote acquiredNote = acquiredNoteRepository.findAcquiredNotesByCandidatAndEvaluation(candidat, evaluation);*/
        AcquiredNote acquiredNote = acquiredNoteRepository.findById(Long.valueOf(acquiredNoteForm.getIdAcquiredNote())).get();
        acquiredNote.setAcquiredNote_1(acquiredNoteForm.getAcquiredNote_1());
        acquiredNote.setAcquiredNote_2(acquiredNoteForm.getAcquiredNote_2());
        acquiredNote.setExpertComment(acquiredNoteForm.getExpertComment());
        acquiredNoteRepository.save(acquiredNote);
    }

    @Override
    public void setRequiredNote(RequirementNoteForm requiredNoteForm) {
        Skill skill = skillRepository.findById(Long.valueOf(requiredNoteForm.getIdSkill())).get();
        Expert expert = expertRepository.findById(Long.valueOf(requiredNoteForm.getIdExpert())).get();
        RequirementNote requirementNote = requirementNoteRepository.findRequirementNoteBySkillAndExpertForSkillEvaluation(skill, expert);
        requirementNote.setRequiredNoteOfExpert_1(requiredNoteForm.getRequiredNote_1());
        requirementNote.setRequiredNoteOfExpert_2(requiredNoteForm.getRequiredNote_2());
        //System.out.println(requirementNote.getRequiredNoteOfExpert());
        requirementNoteRepository.save(requirementNote);
    }

    @Override
    public Expert getExpert(String username){
        AppUser appUser = appUserRepository.findAppUserByUserName(username);
        Expert expert = expertRepository.findExpertByAppUser(appUser);
        return expert;
    }

    @Override
    public RequirementNote getSkillForRequiredNote(String codeSkill, String idExpert) {
        Skill skill = skillRepository.findSkillByCodeSkill(codeSkill);
        if (skill != null){
            Expert expert = expertRepository.findById(Long.valueOf(idExpert)).get();
            if(expert != null){
                RequirementNote requirementNote = requirementNoteRepository.findRequirementNoteBySkillAndExpertForSkillEvaluation(skill, expert);
                return requirementNote;
            }
            return null;
        }

        else {
            return null;
        }
    }

    @Override
    public Skill getSkillOfRequirementNote(String idRequirementNote) {
        RequirementNote requirementNote = requirementNoteRepository.findById(Long.valueOf(idRequirementNote)).get();
        if(requirementNote != null){
            Skill skill = skillRepository.findSkillByRequiredNote(requirementNote);
        }
       /*  skill.getRequirementNotes().clear();
       skill.getCandidatSkills().clear();
       skill.getEvaluations().clear();
       skill.setJob(null);*/

        return null;
    }

    @Override
    public Skill getSkillForAcquiredNote(String codeSkill) {
        return skillRepository.findSkillByCodeSkill(codeSkill);
    }

    @Override
    public List<AcquiredNote> getAcuiredNotes(String idSkill, String idExpert) {
        List<AcquiredNote> acquiredNotes = new ArrayList<>();
        Skill skill = skillRepository.findById(Long.valueOf(idSkill)).get();
        Expert expert = expertRepository.findById(Long.valueOf(idExpert)).get();
        Evaluation evaluation = evaluationRepository.findEvaluationBySkillAndExpert(skill,expert);
        acquiredNotes = acquiredNoteRepository.findAcquiredNotesByEvaluation(evaluation);
        acquiredNotes.stream().forEach(acquiredNote -> {
            System.out.println(acquiredNote.getCandidat().getFirstName());
        });
        return acquiredNotes;
    }

    @Override
    public Candidat getCandidatForAcquiredNote(String idAcquiredNote) {
        AcquiredNote acquiredNote = acquiredNoteRepository.findById(Long.valueOf(idAcquiredNote)).get();
        Candidat candidat = candidatRepository.findByAcquiredNotes(acquiredNote);
        Candidat c = new Candidat();
        c.setIdCandidat(candidat.getIdCandidat());
        c.setFirstName(candidat.getFirstName());
        c.setLastName(candidat.getLastName());
        c.setMail(candidat.getMail());
        c.setTele(candidat.getTele());
        return c;
    }

    @Override
    public Skill getSkillOfEvaluation(String idEvaluation) {
        Evaluation evaluation = evaluationRepository.findById(Long.valueOf(idEvaluation)).get();
        Skill skill = skillRepository.findSkillByEvaluations(evaluation);
        System.out.println(skill.getSkillTitle());
        Skill skill1 = new Skill();
        skill1.setIdSkill(skill.getIdSkill());
        skill1.setSkillTitle(skill.getSkillTitle());
        skill1.setCodeSkill(skill.getCodeSkill());
        return skill1;
    }

    @Override
    public List<Evaluation> getEvaluationsOfExpert(String idExpert) {
        Expert expert = expertRepository.findById(Long.valueOf(idExpert)).get();
        List<Evaluation> evaluations = evaluationRepository.findEvaluationByExpert(expert);
        return evaluations;
    }


}
