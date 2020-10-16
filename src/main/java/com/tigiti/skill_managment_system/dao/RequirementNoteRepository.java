package com.tigiti.skill_managment_system.dao;

import com.tigiti.skill_managment_system.enteties.Expert;
import com.tigiti.skill_managment_system.enteties.RequirementNote;
import com.tigiti.skill_managment_system.enteties.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin
public interface RequirementNoteRepository extends JpaRepository<RequirementNote, Long> {
    public RequirementNote findRequirementNoteBySkillAndExpertForSkillEvaluation(Skill skill, Expert expert);
}
