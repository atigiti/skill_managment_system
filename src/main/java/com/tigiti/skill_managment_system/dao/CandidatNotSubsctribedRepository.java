package com.tigiti.skill_managment_system.dao;

import com.tigiti.skill_managment_system.enteties.CandidatNotSubsctribed;
import com.tigiti.skill_managment_system.enteties.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatNotSubsctribedRepository extends JpaRepository<CandidatNotSubsctribed, Long> {
    public List<CandidatNotSubsctribed> findCandidatNotSubsctribedByJob(Job job);
}
