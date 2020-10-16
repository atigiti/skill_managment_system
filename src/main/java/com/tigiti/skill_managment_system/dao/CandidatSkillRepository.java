package com.tigiti.skill_managment_system.dao;

import com.tigiti.skill_managment_system.enteties.CandidatSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin
public interface CandidatSkillRepository extends JpaRepository<CandidatSkill, Long> {
}
