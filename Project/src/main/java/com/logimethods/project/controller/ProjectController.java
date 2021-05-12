package com.logimethods.project.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logimethods.project.controller.assemblers.ContributorModelAssembler;
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
	
	@Autowired
	private ContributorModelAssembler contributorAssembler;
	
	@GetMapping()
	public CollectionModel<EntityModel<ProjectDto>> listProjects() {
		List<ProjectDto> dtoList = projectService.listProjects();
		
		return projectAssembler.toCollectionModel(dtoList)
			.add(linkTo(methodOn(ProjectController.class).listProjects()).withSelfRel());
	}
	
	@GetMapping("/{id}")
	public EntityModel<ProjectDto> getProjectById(@PathVariable long id) {
		ProjectDto dto = projectService.getProjectById(id);
		
		return projectAssembler.toModel(dto);
	}
	
	@PostMapping()
	public ResponseEntity<?> createProject(@RequestBody @Valid ProjectDto dto) {
		EntityModel<ProjectDto> entityModel = projectAssembler.toModel(projectService.createProject(dto));
		
		return ResponseEntity
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entityModel);
	}
	
	@PutMapping("/{id}")
	public EntityModel<ProjectDto> updateProject(@PathVariable long id, @RequestBody @Valid ProjectDto dto) {	
		return projectAssembler.toModel(projectService.updateProject(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable long id) {
		projectService.deleteProject(id);

		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}/contributors")
	public CollectionModel<EntityModel<ContributorDto>> listContributors(@PathVariable long id) {
		List<ContributorDto> dtoList = projectService.listContributorsForProject(id); 
		
		return contributorAssembler.toCollectionModel(dtoList)
				.add(linkTo(methodOn(ProjectController.class).listContributors(id)).withSelfRel());
	}
	
	@PostMapping("/{id}/contributors")
	public ResponseEntity<?> createContributor(@PathVariable long id, @RequestBody @Valid ContributorDto dto) {
		EntityModel<ContributorDto> entityModel = contributorAssembler.toModel(projectService.createContributorForProject(id, dto));
		
		return ResponseEntity
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entityModel);
	}
}
