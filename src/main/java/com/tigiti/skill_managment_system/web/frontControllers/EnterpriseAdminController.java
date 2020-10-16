package com.tigiti.skill_managment_system.web.frontControllers;

import com.tigiti.skill_managment_system.enteties.*;
import com.tigiti.skill_managment_system.service.account.AccountService;
import com.tigiti.skill_managment_system.service.enterpriseAdmine.EnterpriseAdminService;
import com.tigiti.skill_managment_system.web.forms.AddCandidatToJobForm;
import com.tigiti.skill_managment_system.web.forms.AddJobForm;
import com.tigiti.skill_managment_system.web.forms.AddSkillsToJobForm;
import com.tigiti.skill_managment_system.web.forms.EnterpriseAdminForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnterpriseAdminController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private EnterpriseAdminService enterpriseAdminService;

    @PostMapping("/addJob")
    public Long addJob(@RequestBody AddJobForm jobForm){

        Job job = enterpriseAdminService.addJob(jobForm);
        return job.getIdJob();
    }

    @PostMapping("/addSkills")
    public void addSkillToJob(@RequestBody List<AddSkillsToJobForm> skills){
        enterpriseAdminService.addSkillToJob(skills);
    }

    @PostMapping("/addCandidatToJob")
    public void addCandidatToJob(@RequestBody  List<AddCandidatToJobForm> candidats){
        enterpriseAdminService.addCandidatToJob(candidats);
    }

    @GetMapping("/getRankingResult")
    public List<RankingResult> getRankingResult(@RequestBody Job job){
        return null;
    }

    @PostMapping("/registerEnterpriseAdmin")
    public EnterpriseAdmin registerEnterpriseAdmin(@RequestBody EnterpriseAdminForm enterpriseAdminForm){
        return accountService.saveEnterPriseAdmin(enterpriseAdminForm);
    }

    @GetMapping("/getEnterpriseAdminDetails/{username}")
    public EnterpriseAdmin getEnterpriseAdmin(@PathVariable String username){
        System.out.println(username);
        EnterpriseAdmin enterpriseAdmin = enterpriseAdminService.getEnterpriseAdmin(username);
        enterpriseAdmin.setAppUser(null);
        System.out.println(enterpriseAdmin);
        return enterpriseAdmin;
    }

}


