package com.logimethods.project.service;

import java.util.List;

import com.logimethods.project.dto.ContributorDto;
import com.logimethods.project.dto.ProjectDto;

public interface ProjectService {
	List<ProjectDto> listProjects();
	ProjectDto getProjectById(long id);
	ProjectDto createProject(ProjectDto dto);
	ProjectDto updateProject(long id, ProjectDto dto);
	void deleteProject(long id);
	List<ContributorDto> listContributorsForProject(long id);
	ContributorDto createContributorForProject(long id, ContributorDto dto);
}
