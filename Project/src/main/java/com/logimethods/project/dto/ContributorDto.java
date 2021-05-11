package com.logimethods.project.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContributorDto {
	private Long id;
	
	@NotNull(message = "Must not be empty")
	@Positive(message = "Must be a positive integer")
	private Long employeeId;
	
	@NotNull(message = "Must not be empty")
	@Positive(message = "Must be a positive integer")
	private Long projectId;

	public ContributorDto() {
		this(null);
	}

	public ContributorDto(Long employeeId) {
		this(null, employeeId);
	}
	
	public ContributorDto(Long id, Long employeeId) {
		this(id, employeeId, null);
	}

	public ContributorDto(Long id, Long employeeId, Long projectId) {
		this.id = id;
		this.employeeId = employeeId;
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "ContributorDto [id=" + id + ", employeeId=" + employeeId + ", projectId=" + projectId + "]";
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}