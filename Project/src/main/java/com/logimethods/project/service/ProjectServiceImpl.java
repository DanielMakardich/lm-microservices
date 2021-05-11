package com.logimethods.project.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logimethods.project.dto.ContributorDto;
import com.logimethods.project.dto.ProjectDto;
import com.logimethods.project.dto.mapper.ProjectMapper;
import com.logimethods.project.exception.NotFoundException;
import com.logimethods.project.model.Contributor;
import com.logimethods.project.model.Project;
import com.logimethods.project.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepo;
	
	@Override
	@Transactional
	public List<ProjectDto> listProjects() {
		List<Project> projects = projectRepo.findAll();
		
		return projects.stream()
			.map(p -> ProjectMapper.toProjectDto(p))
			.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public ProjectDto getProjectById(long id) {
		Project project = getExistingProjectModelById(id);
		ProjectDto dto = ProjectMapper.toProjectDto(project); 

		return dto;
	}

	@Override
	@Transactional
	public ProjectDto createProject(ProjectDto dto) {
		Project project = new Project(
			dto.getName(),
			dto.getDescription(),
			dto.getUrl(),
			dto.getCustomerId()
		);

		project = projectRepo.save(project);
		dto.setId(project.getId());

		return dto;
	}

	@Override
	@Transactional
	public ProjectDto updateProject(long id, ProjectDto dto) {
		Project project = projectRepo
			.findById(id)
			.orElse(new Project());

		project.setId(id);
		project.setName(dto.getName());
		project.setDescription(dto.getDescription());
		project.setUrl(dto.getUrl());
		project.setCustomerId(dto.getCustomerId());

		project = projectRepo.save(project);
		dto.setId(project.getId());
		
		return dto;
	}
	
	@Override
	@Transactional
	public void deleteProject(long id) {

		if (!projectRepo.existsById(id)) {
			throw new NotFoundException(String.format("Project not found for id '%d'", id));
		}

		projectRepo.deleteById(id);
	}

	@Override
	@Transactional
	public List<ContributorDto> listContributorsForProject(long id) {
		Project project = getExistingProjectModelById(id);
		List<Contributor> contributors = project.getContributors(); 
		
		return contributors.stream()
			.map(c -> new ContributorDto(c.getId(), c.getEmployeeId(), c.getProject().getId()))
			.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public ContributorDto createContributorForProject(long id, ContributorDto dto) {
		Project project = getExistingProjectModelById(id);
		Contributor contrib = new Contributor(dto.getEmployeeId(), project);
		project.addContributor(contrib);
		project = projectRepo.save(project);
		dto.setId(project.getId());
		return dto;
	}

	private Project getExistingProjectModelById(long id) {	
		return projectRepo
			.findById(id)
			.orElseThrow(() -> new NotFoundException(String.format("Project not found for id '%d'", id)));
	}

}
