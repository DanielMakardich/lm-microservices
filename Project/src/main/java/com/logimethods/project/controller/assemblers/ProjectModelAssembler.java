package com.logimethods.project.controller.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.logimethods.project.controller.ProjectController;
import com.logimethods.project.dto.ProjectDto;

@Component
public class ProjectModelAssembler implements RepresentationModelAssembler<ProjectDto, EntityModel<ProjectDto>> {

	@Override
	public EntityModel<ProjectDto> toModel(ProjectDto dto) {
		return EntityModel.of(dto,
			linkTo(methodOn(ProjectController.class).getProjectById(dto.getId())).withSelfRel(),
			linkTo(methodOn(ProjectController.class).listProjects()).withRel("all"),
			linkTo(methodOn(ProjectController.class).listContributors(dto.getId())).withRel("contributors")
		);
	}

}
