package com.tigiti.skill_managment_system.service.account;

import com.tigiti.skill_managment_system.enteties.*;
import com.tigiti.skill_managment_system.web.forms.CandidatForm;
import com.tigiti.skill_managment_system.web.forms.EnterpriseAdminForm;
import com.tigiti.skill_managment_system.web.forms.ExpertForm;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public AppUser findUserByName(String username);
    public AppRole findRoleByName(String roleName);
    public AppUser saveUser(AppUser appUser);
    public AppRole saveRole(AppRole appRole);
    public void addRoleToUser(String userName, String roleName);
    public EnterpriseAdmin saveEnterPriseAdmin(EnterpriseAdminForm enterpriseAdminForm);
    public Expert saveExpert(ExpertForm expertForm);
    public Candidat saveCandidat(CandidatForm candidatForm);
}
