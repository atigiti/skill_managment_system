package com.tigiti.skill_managment_system;

import com.tigiti.skill_managment_system.dao.*;
import com.tigiti.skill_managment_system.enteties.*;
import com.tigiti.skill_managment_system.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class SkillManagmentSystemApplication implements CommandLineRunner {
    @Autowired
   private AccountService accountService;
    @Autowired
    private ExpertRepository expertRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private RequirementNoteRepository requirementNoteRepository;
    @Autowired
    private EnterpriseAdminRepository enterpriseAdminRepository;
    @Autowired
    RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(SkillManagmentSystemApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
     repositoryRestConfiguration.exposeIdsFor(AcquiredNote.class);
     repositoryRestConfiguration.exposeIdsFor(Candidat.class);
     repositoryRestConfiguration.exposeIdsFor(CandidatNotSubsctribed.class);
     repositoryRestConfiguration.exposeIdsFor(CandidatSkill.class);
     repositoryRestConfiguration.exposeIdsFor(EnterpriseAdmin.class);
     repositoryRestConfiguration.exposeIdsFor(Evaluation.class);
     repositoryRestConfiguration.exposeIdsFor(Expert.class);
     repositoryRestConfiguration.exposeIdsFor(Job.class);
     repositoryRestConfiguration.exposeIdsFor(RankingResult.class);
     repositoryRestConfiguration.exposeIdsFor(RequirementNote.class);
     repositoryRestConfiguration.exposeIdsFor(Skill.class);


    /* AppRole r1 = new AppRole();
     r1.setRoleName("Candidat");

     AppRole r2 = new AppRole();
     r2.setRoleName("Expert");

     AppRole r3 = new AppRole();
     r3.setRoleName("EnterpriseAdmin");

     accountService.saveRole(r1);
     accountService.saveRole(r2);
     accountService.saveRole(r3);

       // Enterprise Admin
       EnterpriseAdmin enterpriseAdmin = new EnterpriseAdmin();
        enterpriseAdmin.setEnterpriseAdminFirstName("F Admin");
        enterpriseAdmin.setEnterpriseAdminLastName("L Admin");
        enterpriseAdmin.setEnterpriseMail("admin@gmail.com");
        enterpriseAdmin.setTele("066121352");

        // AppUser For EnterpriseAdmin
        AppUser appUser = new AppUser();
        appUser.setUserName("admin");
        appUser.setPassword("admin");



       enterpriseAdmin.setAppUser(appUser);
        accountService.saveUser(appUser);
        accountService.saveRole(r2);
        accountService.saveRole(r2);
        accountService.addRoleToUser(appUser.getUserName(), r3.getRoleName());
        //accountService.addRoleToUser(appUser.getUserName(), r2.getRoleName());
        enterpriseAdminRepository.save(enterpriseAdmin);



        // Expert 1
        Expert expert = new Expert();
        expert.setFirstName("F Expert 1");
        expert.setLastName("L Expert 1");
        expert.setMail("expert1@gmail.com");
        expert.setTele("066149434");
        AppUser user = new AppUser();
        user.setUserName("expert1");
        user.setPassword("expert1");
        accountService.saveUser(user);
        expert.setAppUser(user);
        expertRepository.save(expert);
        accountService.addRoleToUser("expert1","Expert");

        // Expert 2
        Expert expert2 = new Expert();
        expert2.setFirstName("F Expert 2 ");
        expert2.setLastName("L Expert 2");
        expert2.setMail("expert2@gmail.com");
        expert2.setTele("066149434");
        AppUser user2 = new AppUser();
        user2.setUserName("expert2");
        user2.setPassword("expert2");
        expertRepository.save(expert2);
        expert2.setAppUser(user2);
        accountService.saveUser(user2);
        accountService.addRoleToUser("expert2","Expert");
*/

        // Ajouter des Job
       /* Job job = new Job();
        job.setJobTitle("RSHE");
        job.setJobDescription("it wase just a few words for test");
        job.setJobCode("THIS IS The Code");
        jobRepository.save(job);


        // Ajouter des Skills
        ArrayList<Skill> skills = new ArrayList<>();
        Stream.of("Java", "Python","Ruby","Big Data")
                .forEach(title ->  {
                    Skill skill = new Skill();
                    skill.setSkillTitle(title);
                    skill.setFullyAcuiredTested(false);
                    skill.setFullyRequirementTested(false);

                    // Creation de requirement Table
                    RequirementNote requirementNote = new RequirementNote();
                    requirementNote.setExpertForSkillEvaluation(expert);
                    requirementNote.setExpertForSkillEvaluation(expert2);
                    requirementNote.setSkill(skill);
                    skill.setJob(job);
                    skillRepository.save(skill);
                    requirementNoteRepository.save(requirementNote);

                    skills.add(skill);

                });

        job.setSkills(skills);
        jobRepository.save(job);*/


     // Roles For AppUser
        /*AppRole appRole1 = new AppRole();
        appRole1.setRoleName("Admin");

        AppRole appRole2 = new AppRole();
        appRole2.setRoleName("User");*/

    }

    @Bean
    public BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }
}
