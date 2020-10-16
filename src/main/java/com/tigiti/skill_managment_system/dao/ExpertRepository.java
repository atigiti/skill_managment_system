package com.tigiti.skill_managment_system.dao;

import com.tigiti.skill_managment_system.enteties.AppUser;
import com.tigiti.skill_managment_system.enteties.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin
public interface ExpertRepository extends JpaRepository<Expert, Long> {
    public Expert findExpertByMail(String mail);
    public Expert findExpertByAppUser(AppUser appUser);

}
