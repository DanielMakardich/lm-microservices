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
import com.logimethods.project.dto.ContributorDto;
import com.logimethods.project.service.ContributorService;

@RestController
@RequestMapping("/v1/contributors")
public class ContributorController {

	@Autowired
	private ContributorService contributorService;
	
	@Autowired
	private ContributorModelAssembler contributorAssembler;
	
	@GetMapping()
	public CollectionModel<EntityModel<ContributorDto>> listContributors() {
		List<ContributorDto> dtoList = contributorService.listContributors();

		return contributorAssembler.toCollectionModel(dtoList)
			.add(linkTo(methodOn(ContributorController.class).listContributors()).withSelfRel());
	}
	
	@GetMapping("/{id}")
	public EntityModel<ContributorDto> getContributorById(@PathVariable long id) {
		ContributorDto dto = contributorService.getContributorById(id); 
		
		return contributorAssembler.toModel(dto);
	}
	
	@PostMapping()
	public ResponseEntity<?> createContributor(@RequestBody @Valid ContributorDto dto) {
		EntityModel<ContributorDto> entityModel = contributorAssembler.toModel(contributorService.createContributor(dto));
		
		return ResponseEntity
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entityModel);
	}
	
	@PutMapping("/{id}")
	public EntityModel<ContributorDto> updateContributor(@PathVariable long id, @RequestBody @Valid ContributorDto dto) {
		return contributorAssembler.toModel(contributorService.updateContributor(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteContributor(@PathVariable long id) {
		contributorService.deleteContributor(id);
		
		return ResponseEntity.noContent().build();
	}
}
