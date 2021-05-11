package com.logimethods.project.service;

import java.util.List;

import com.logimethods.project.dto.ContributorDto;

public interface ContributorService {
	List<ContributorDto> listContributors();
	ContributorDto getContributorById(long id);
	ContributorDto createContributor(ContributorDto dto);
	ContributorDto updateContributor(long id, ContributorDto dto);
	void deleteContributor(long id);
	
}
