package com.logimethods.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logimethods.project.model.Contributor;

public interface ContributorRepository extends JpaRepository<Contributor, Long> {
	List<Contributor> findByProjectId(long projectId);
}
