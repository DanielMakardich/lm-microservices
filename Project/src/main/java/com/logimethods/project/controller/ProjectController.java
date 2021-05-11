package com.logimethods.project.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logimethods.project.controller.assemblers.ProjectModelAssembler;
import com.logimethods.project.dto.ContributorDto;
import com.logimethods.project.dto.ProjectDto;
import com.logimethods.project.service.ProjectService;

@RestController
@RequestMapping("/v1/projects")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectModelAssembler projectAssembler;
	
	@GetMapping()
	public CollectionModel<EntityModel<ProjectDto>> listProjects() {
		List<EntityModel<ProjectDto>> dtoList = projectService.listProjects().stream()
			.map(projectAssembler::toModel)
			.collect(Collectors.toList()); 
		
		return CollectionModel.of(dtoList,
			linkTo(methodOn(ProjectController.class).listProjects()).withSelfRel()
		);
	}
	
	@GetMapping("/{id}")
	public EntityModel<ProjectDto> getProjectById(@PathVariable long id) {
		
		ProjectDto dto = projectService.getProjectById(id);
		
		return projectAssembler.toModel(dto);
	}
	
	@PostMapping()
	public ProjectDto createProject(@RequestBody @Valid ProjectDto dto) {
		// TODO: return 201 (CREATED)
		return projectService.createProject(dto);
	}
	
	@PutMapping("/{id}")
	public ProjectDto updateProject(@PathVariable long id, @RequestBody @Valid ProjectDto dto) {
		return projectService.updateProject(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProject(@PathVariable long id) {
		projectService.deleteProject(id);
	}
	
	@GetMapping("/{id}/contributors")
	public List<ContributorDto> listContributors(@PathVariable long id) {
		return projectService.listContributorsForProject(id);
	}
	
	@PostMapping("/{id}/contributors")
	public ContributorDto createContributor(@PathVariable long id, @RequestBody @Valid ContributorDto dto) {
		return projectService.createContributorForProject(id, dto);
	}
}
