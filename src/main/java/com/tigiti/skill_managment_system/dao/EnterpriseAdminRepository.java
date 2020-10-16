package com.tigiti.skill_managment_system.dao;

import com.tigiti.skill_managment_system.enteties.AppUser;
import com.tigiti.skill_managment_system.enteties.EnterpriseAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin
public interface EnterpriseAdminRepository extends JpaRepository<EnterpriseAdmin, Long> {
    public EnterpriseAdmin findEnterpriseAdminByAppUser(AppUser appUser);
}
