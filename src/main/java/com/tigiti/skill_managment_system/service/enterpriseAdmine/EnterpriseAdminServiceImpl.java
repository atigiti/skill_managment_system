package com.tigiti.skill_managment_system.service.enterpriseAdmine;

import com.tigiti.skill_managment_system.dao.*;
import com.tigiti.skill_managment_system.enteties.*;
import com.tigiti.skill_managment_system.service.mailService.MailService;
import com.tigiti.skill_managment_system.web.forms.AddCandidatToJobForm;
import com.tigiti.skill_managment_system.web.forms.AddJobForm;
import com.tigiti.skill_managment_system.web.forms.AddSkillsToJobForm;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnterpriseAdminServiceImpl  implements EnterpriseAdminService{
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private ExpertRepository expertRepository;
    @Autowired
    private RequirementNoteRepository requirementNoteRepository;
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private RankingResultRepository rankingResultRepository;
    @Autowired
    private EnterpriseAdminRepository enterpriseAdminRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private CandidatSkillRepository candidatSkillRepository;
    @Autowired
    private AcquiredNoteRepository acquiredNoteRepository;
    @Autowired
    private CandidatNotSubsctribedRepository candidatNotSubsctribedRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    @Qualifier("NotifyExpertForSkillEval")
    private MailService expertSkillNotifier;
    @Autowired
    @Qualifier("NotifyExpertForCandidatsEval")
    private MailService expertCandidatNotifier;



    public Job addJob(AddJobForm jobForm) {
        Job job = jobForm.getJob();
        job.setJobCode(RandomString.make(15));
        AppUser appUser = appUserRepository.findAppUserByUserName(jobForm.getUserNameEnterpriseAdmin());
        EnterpriseAdmin enterpriseAdmin = enterpriseAdminRepository.findEnterpriseAdminByAppUser(appUser);
        enterpriseAdmin.getJobs().add(job);
        enterpriseAdminRepository.save(enterpriseAdmin);
        job.setEnterpriseAdmin(enterpriseAdmin);
        return jobRepository.save(job);

    }

    @Override
    public Job addSkillToJob(List<AddSkillsToJobForm> addSkillsToJobForms) {
        addSkillsToJobForms.stream().forEach(addSkillsToJobForm -> {

            System.out.println("*****************"+addSkillsToJobForm.getExpertForAcquiredMails());
            Job job = jobRepository.findById(addSkillsToJobForm.getIdJob()).get();
            Skill skill = new Skill();
            skill.setSkillTitle(addSkillsToJobForm.getSkillTitle());
            skill.setCodeSkill(RandomString.make(15));
            skill.setRequiredNote(addSkillsToJobForm.getRequiredNote());
            // Si l'Admin definit la note requise
            skill.setRequiredNote(addSkillsToJobForm.getRequiredNote());
            skill.setJob(job);
            skillRepository.save(skill);

            // les experts aui vont tester le niveau requis de Skill
            List<Expert> experts = new ArrayList<>();

            addSkillsToJobForm.getExpertForRequiredMails().stream().forEach(  mail -> {
                experts.add(expertRepository.findExpertByMail(mail));
            });




            // Les Experts qui vont test le niveau acquis des candidat
            List<Expert> expertAcquied = new ArrayList<>();
            addSkillsToJobForm.getExpertForAcquiredMails().stream().forEach(  mail -> {
                expertAcquied.add(expertRepository.findExpertByMail(mail));
            });


            // Creation de Evaluation Records
            List<Evaluation> evaluations = new ArrayList<>();
            expertAcquied.stream()
                    .forEach(expert -> {
                        if(expert != null){
                            Evaluation evaluation = new Evaluation();
                            evaluation.setSkill(skill);
                            evaluation.setExpert(expert);
                            evaluationRepository.save(evaluation);
                            evaluations.add(evaluation);

                            expert.getEvaluations().add(evaluation);
                            skill.getEvaluations().add(evaluation);
                            // update Expert
                            expertRepository.save(expert);
                            // update Skill
                            skillRepository.save(skill);

                            // Notifier Expert pour tester le nivau Requis
                            expertCandidatNotifier.sendMail(job,skill, expert.getMail(),null);
                        }

                    });

            // Creer AcquiredNote Records et le Candidat Skill
            List<Candidat> candidats = new ArrayList<>();
            List<RankingResult> rankingResults = rankingResultRepository.findByJob(job);
            rankingResults.stream()
                    .forEach(rankingResult -> {
                        Candidat candidat = rankingResult.getCandidat();
                        candidats.add(candidat);
                        CandidatSkill candidatSkill = new CandidatSkill();
                        candidatSkill.setCandidat(candidat);
                        candidatSkill.setSkill(skill);
                        candidatSkillRepository.save(candidatSkill);
                        candidat.getCandidatSkills().add(candidatSkill);
                        skill.getCandidatSkills().add(candidatSkill);
                        skillRepository.save(skill);
                        candidatRepository.save(candidat);

                    });


            evaluations.stream()
                    .forEach(evaluation -> {
                        candidats.stream()
                                .forEach(candidat -> {
                                    AcquiredNote acquiredNote = new AcquiredNote();
                                    acquiredNote.setCandidat(candidat);
                                    acquiredNote.setEvaluation(evaluation);
                                    acquiredNoteRepository.save(acquiredNote);
                                    candidat.getAcquiredNotes().add(acquiredNote);
                                    evaluation.getAcquiredNotes().add(acquiredNote);
                                    candidatRepository.save(candidat);
                                    evaluationRepository.save(evaluation);

                                });
                    });


            // Creation de RequirementNote Records
            experts.stream()
                    .forEach(expert ->  {
                        if(expert != null){
                            RequirementNote requirementNote = new RequirementNote();
                            requirementNote.setSkill(skill);
                            requirementNote.setExpertForSkillEvaluation(expert);

                            // save
                            requirementNoteRepository.save(requirementNote);
                            // ajouter la record requirementNote to Expert
                            expert.getRequirementNotes().add(requirementNote);
                            //  // ajouter la record requirementNote to Skill
                            skill.getRequirementNotes().add(requirementNote);
                            // update Expert
                            expertRepository.save(expert);
                            // update Skill
                            skillRepository.save(skill);

                            // Notify Expert to Evaluate the Skill
                            System.out.println("************************************************");
                            expertSkillNotifier.sendMail(job,skill, expert.getMail(),null);
                            System.out.println("-------------------------------------------------");
                        }

                    });

            // add Skill To Job Record
            job.getSkills().add(skill);

        });


        return  null;
    }

    @Override
    public List<RankingResult> getRankingResults(Job job) {
        return null;
    }

    @Override
    public Job addCandidatToJob(List<AddCandidatToJobForm> candidatToJobForms) {

        candidatToJobForms.stream()
                .forEach(rec -> {
                    System.out.println("-------------Add Candidat---------------");
                    System.out.println(rec.getCandidatMail());
                    Job job = jobRepository.findById(Long.valueOf(rec.getIdJob())).get();

                    // Si le Candidat n'existe pas dans le système
                    // Send Mail to subscribe
                   /* if(rec.getCandidatMail() != null){
                        // Insere le record dans la table CandidatNotSubsctribed
                        // Send Mail with the Job Code
                       // newCandidatNotifier.sendMail(job,rec.getCandidatMail());
                    }*/
                    // Candidat Déjà existe dans le system

                      //  Candidat candidat = candidatRepository.findById(Long.valueOf(rec.getIdCandidat())).get();
                        Candidat candidat = candidatRepository.findCandidatByMail(rec.getCandidatMail());
                        if(candidat == null ){
                            System.out.println("candidat doesn't existe");
                            candidatNotSubsctribedRepository.save(new CandidatNotSubsctribed(null,job, rec.getCandidatMail()));
                        }
                        else{
                            System.out.println("----------------> candidat existe"+ candidat.getFirstName() );
                            RankingResult rankingResult = new RankingResult();
                            rankingResult.setCandidat(candidat);
                            rankingResult.setJob(job);
                            rankingResultRepository.save(rankingResult);
                            // Ajouter le Ranking record to the candidat
                            candidat.getRankingResults().add(rankingResult);
                            candidatRepository.save(candidat);
                            // Ajouter le Ranking record to the job
                            job.getRankingResults().add(rankingResult);
                            // Notify Candidat
                            // candidatNotifier.sendMail(job, candidat.getMail());
                        }


                });


        return null;
    }

    @Override
    public void notifyExpertForCandidatEvaluation(Skill skill) {

    }

    @Override
    public void notifyExpertForSkillEvaluation(RequirementNote requirementNote) {

    }

    @Override
    public void sendJobOffer(Candidat candidat, Job job) {

    }

    @Override
    public EnterpriseAdmin getEnterpriseAdmin(String username) {
        AppUser appUser = appUserRepository.findAppUserByUserName(username);
        EnterpriseAdmin enterpriseAdmin = enterpriseAdminRepository.findEnterpriseAdminByAppUser(appUser);
        enterpriseAdmin.getJobs().clear();
        return enterpriseAdmin;
    }
}
