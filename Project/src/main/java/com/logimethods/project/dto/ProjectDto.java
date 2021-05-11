package com.logimethods.project.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectDto {
	private Long id;
	
	@NotEmpty(message = "Must not be empty")
	private String name;
	
	private String description;
	private String url;
	
	@NotNull(message = "Must not be empty")
	@Positive(message = "Must be a positive integer")
	private Long customerId;

	public ProjectDto() {
		this(null, null, null, null, null);
	}

	public ProjectDto(Long id, String name, String description, String url, Long customerId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.url = url;
		this.customerId = customerId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
}
