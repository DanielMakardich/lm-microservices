package com.logimethods.project.dto.mapper;

import com.logimethods.project.dto.ProjectDto;
import com.logimethods.project.model.Project;

public class ProjectMapper {
	public static ProjectDto toProjectDto(Project project) {	
		return new ProjectDto(
			project.getId(),
			project.getName(),
			project.getDescription(),
			project.getUrl(),
			project.getCustomerId()
		);
	}
}
