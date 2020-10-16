package com.tigiti.skill_managment_system.dao;

import com.tigiti.skill_managment_system.enteties.Evaluation;
import com.tigiti.skill_managment_system.enteties.Expert;
import com.tigiti.skill_managment_system.enteties.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    public Evaluation findEvaluationBySkillAndExpert(Skill skill, Expert expert);
    public List<Evaluation> findEvaluationByExpert(Expert expert);
}
