package com.tigiti.skill_managment_system.dao;

import com.tigiti.skill_managment_system.enteties.Job;
import com.tigiti.skill_managment_system.enteties.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin
public interface JobRepository extends JpaRepository<Job, Long> {
    public Job findJobBySkills(Skill skill);
    public Job findJobByJobCode(String codeJob);
}
