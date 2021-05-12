package com.logimethods.project.controller.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.logimethods.project.controller.ContributorController;
import com.logimethods.project.dto.ContributorDto;

@Component
public class ContributorModelAssembler implements RepresentationModelAssembler<ContributorDto, EntityModel<ContributorDto>> {

	@Override
	public EntityModel<ContributorDto> toModel(ContributorDto dto) {
		return EntityModel.of(dto,
				linkTo(methodOn(ContributorController.class).getContributorById(dto.getId())).withSelfRel(),
				linkTo(methodOn(ContributorController.class).listContributors()).withRel("all")
		);
	}

}
