package com.tigiti.skill_managment_system.dao;

import com.tigiti.skill_managment_system.enteties.Job;
import com.tigiti.skill_managment_system.enteties.RankingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin
public interface RankingResultRepository extends JpaRepository<RankingResult, Long> {
    public List<RankingResult> findByJob(Job job);
}
