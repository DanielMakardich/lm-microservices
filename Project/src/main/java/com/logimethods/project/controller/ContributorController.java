package com.logimethods.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logimethods.project.dto.ContributorDto;
import com.logimethods.project.service.ContributorService;

@RestController
@RequestMapping("/v1/contributors")
public class ContributorController {

	@Autowired
	private ContributorService contributorService;
	
	@GetMapping()
	public List<ContributorDto> listContributors() {
		return contributorService.listContributors();
	}
	
	@GetMapping("/{id}")
	public ContributorDto getContributorById(@PathVariable long id) {
		return contributorService.getContributorById(id);
	}
	
	@PostMapping()
	public ContributorDto createContributor(@RequestBody @Valid ContributorDto dto) {
		return contributorService.createContributor(dto);
	}
	
	@PutMapping("/{id}")
	public ContributorDto updateContributor(@PathVariable long id, @RequestBody @Valid ContributorDto dto) {
		return contributorService.updateContributor(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteContributor(@PathVariable long id) {
		contributorService.deleteContributor(id);
	}
}
