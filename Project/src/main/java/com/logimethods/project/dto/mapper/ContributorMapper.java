package com.logimethods.project.dto.mapper;

import com.logimethods.project.dto.ContributorDto;
import com.logimethods.project.model.Contributor;
import com.logimethods.project.model.Project;

public class ContributorMapper {
	
	public static ContributorDto toContributorDto(Contributor contributor) {
		Project project = contributor.getProject();
		
		Long projectId = null;
		if(project != null) {
			projectId = project.getId();
		}
		
		return new ContributorDto(
			contributor.getId(),
			contributor.getEmployeeId(),
			projectId
		);
	}

}
