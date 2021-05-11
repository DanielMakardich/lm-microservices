package com.logimethods.project.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logimethods.project.dto.ContributorDto;
import com.logimethods.project.dto.mapper.ContributorMapper;
import com.logimethods.project.exception.InvalidInputException;
import com.logimethods.project.exception.NotFoundException;
import com.logimethods.project.model.Contributor;
import com.logimethods.project.model.Project;
import com.logimethods.project.repository.ContributorRepository;
import com.logimethods.project.repository.ProjectRepository;

@Service
public class ContributorServiceImpl implements ContributorService {

	@Autowired
	private ContributorRepository contributorRepo;
	
	@Autowired
	private ProjectRepository projectRepo;

	@Override
	@Transactional
	public List<ContributorDto> listContributors() {
		List<Contributor> contributors = contributorRepo.findAll();
		
		return contributors.stream()
			.map(c -> ContributorMapper.toContributorDto(c))
			.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public ContributorDto getContributorById(long id) {
		Contributor contrib = contributorRepo
			.findById(id)
			.orElseThrow(() -> new NotFoundException(String.format("Contributor not found for id '%d'", id)));
		
		ContributorDto dto = ContributorMapper.toContributorDto(contrib);
		
		return dto;
	}

	@Override
	@Transactional
	public ContributorDto createContributor(ContributorDto dto) {
		Project project = projectRepo
				.findById(dto.getProjectId())
				.orElseThrow(() -> new InvalidInputException(String.format("Cannot associate contributor with non-existing project id '%d'", dto.getProjectId())));
		
		Contributor contrib = new Contributor(dto.getEmployeeId(), project);
		contrib = contributorRepo.save(contrib);
		dto.setId(contrib.getId());
		
		return dto;
	}

	@Override
	@Transactional
	public ContributorDto updateContributor(long id, ContributorDto dto) {
		Contributor contrib = contributorRepo
			.findById(id)
			.orElse(new Contributor());

		Project project = projectRepo
			.findById(dto.getProjectId())
			.orElseThrow(() -> new InvalidInputException(String.format("Cannot associate contributor with non-existing project id '%d'", dto.getProjectId())));
		
		contrib.setId(id);
		contrib.setEmployeeId(dto.getEmployeeId());
		contrib.setProject(project);
		
		contrib = contributorRepo.save(contrib);
		dto.setId(contrib.getId());
		
		return dto;
	}

	@Override
	@Transactional
	public void deleteContributor(long id) {
		if(!contributorRepo.existsById(id)) {
			throw new NotFoundException(String.format("Contributor not found for id '%d'", id));
		}
		contributorRepo.deleteById(id);
	}
}
