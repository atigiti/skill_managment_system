package com.tigiti.skill_managment_system.service.account;

import com.tigiti.skill_managment_system.dao.*;
import com.tigiti.skill_managment_system.enteties.*;
import com.tigiti.skill_managment_system.web.forms.CandidatForm;
import com.tigiti.skill_managment_system.web.forms.EnterpriseAdminForm;
import com.tigiti.skill_managment_system.web.forms.ExpertForm;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EnterpriseAdminRepository enterpriseAdminRepository;
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private ExpertRepository expertRepository;


    @Override
    public AppUser findUserByName(String username) {
        return appUserRepository.findAppUserByUserName(username);
    }

    @Override
    public AppRole findRoleByName(String roleName) {
        return appRoleRepository.findAppRoleByRoleName(roleName);
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        // Tester si username already exists
        AppUser user = this.findUserByName(appUser.getUserName());
        if(user != null)
            throw new RuntimeException("User Already Exist");

        // il faut crypter le password avant l'inserer dans la base de données
        String pass = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(pass);
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole saveRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser appUser = appUserRepository.findAppUserByUserName(userName);
        AppRole appRole = appRoleRepository.findAppRoleByRoleName(roleName);
        if(appUser != null){
            if(appRole != null){
                appUser.getAppRoles().add(appRole);
            }
        }
    }

    @Override
    public EnterpriseAdmin saveEnterPriseAdmin(EnterpriseAdminForm enterpriseAdminForm) {
        AppUser appUser = new AppUser();
        appUser.setUserName(enterpriseAdminForm.getUserName());
        appUser.setPassword(enterpriseAdminForm.getPassword());
        this.saveUser(appUser);
        this.addRoleToUser(appUser.getUserName(), "EnterpriseAdmin");

        EnterpriseAdmin enterpriseAdmin = new EnterpriseAdmin();
        enterpriseAdmin.setEnterpriseAdminFirstName(enterpriseAdminForm.getEnterpriseAdminFirstName());
        enterpriseAdmin.setEnterpriseAdminLastName(enterpriseAdminForm.getEnterpriseAdminLastName());
        enterpriseAdmin.setEnterpriseMail(enterpriseAdminForm.getEnterpriseMail());
        enterpriseAdmin.setTele(enterpriseAdminForm.getTele());
        enterpriseAdmin.setEnterpriseName(enterpriseAdminForm.getEnterpriseName());
        enterpriseAdmin.setAppUser(appUser);
        return enterpriseAdminRepository.save(enterpriseAdmin);
    }

    @Override
    public Expert saveExpert(ExpertForm expertForm) {
        AppUser appUser = new AppUser();
        appUser.setUserName(expertForm.getUserName());
        appUser.setPassword(expertForm.getPassword());
        this.saveUser(appUser);
        this.addRoleToUser(appUser.getUserName(), "Expert");

        Expert expert = new Expert();
        expert.setFirstName(expertForm.getFirstName());
        expert.setLastName(expertForm.getLastName());
        expert.setMail(expertForm.getMail());
        expert.setTele(expertForm.getTele());
        expert.setDateNaissance(expertForm.getDateNassance());
        expert.setSexe(expertForm.getSexe());
        expert.setAppUser(appUser);

        return expertRepository.save(expert);
    }

    @Override
    public Candidat saveCandidat(CandidatForm candidatForm) {
        AppUser appUser = new AppUser();
        appUser.setUserName(candidatForm.getUserName());
        appUser.setPassword(candidatForm.getPassword());
        this.saveUser(appUser);
        this.addRoleToUser(appUser.getUserName(), "Candidat");

        Candidat candidat = new Candidat();
        candidat.setFirstName(candidatForm.getFirstName());
        candidat.setLastName(candidatForm.getLastName());
        candidat.setMail(candidatForm.getMail());
        candidat.setTele(candidatForm.getTele());
        candidat.setDateNaissance(candidatForm.getDateNassance());
        candidat.setSexe(candidatForm.getSexe());
        candidat.setCandidatCode(RandomString.make(10));
        candidat.setAppUser(appUser);
        return candidatRepository.save(candidat);
    }

}
