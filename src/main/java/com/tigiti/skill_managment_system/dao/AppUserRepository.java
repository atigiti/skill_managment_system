package com.tigiti.skill_managment_system.dao;

import com.tigiti.skill_managment_system.enteties.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findAppUserByUserName(String username);

}
