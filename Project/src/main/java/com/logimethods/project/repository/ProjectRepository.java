package com.logimethods.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logimethods.project.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
}
