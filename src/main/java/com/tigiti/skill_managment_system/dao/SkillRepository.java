package com.tigiti.skill_managment_system.dao;

import com.tigiti.skill_managment_system.enteties.Evaluation;
import com.tigiti.skill_managment_system.enteties.Job;
import com.tigiti.skill_managment_system.enteties.RequirementNote;
import com.tigiti.skill_managment_system.enteties.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin
public interface SkillRepository extends JpaRepository<Skill, Long> {
    public List<Skill> findByJob(Job job);
    public Skill findSkillByEvaluations(Evaluation evaluation);
    public Skill findSkillByCodeSkill(String codeSkill);
    public Skill findSkillByRequiredNote(RequirementNote requirementNote);
}
