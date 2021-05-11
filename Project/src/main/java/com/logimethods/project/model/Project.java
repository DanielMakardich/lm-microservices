package com.logimethods.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String url;
	
	@Column(name = "customer_id")
	private Long customerId;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contributor> contributors;

	public Project() {
		this(null, null, null, null);
	}

	public Project(String name, String description, String url, Long customerId) {
		this(name, description, url, customerId, null);
	}

	public Project(String name, String description, String url, Long customerId, List<Contributor> contributors) {
		this.name = name;
		this.description = description;
		this.url = url;
		this.customerId = customerId;
		this.contributors = contributors;
	}
	
	public void addContributor(Contributor contributor) {
		if(contributors == null) {
			contributors = new ArrayList<>();
		}

		contributors.add(contributor);
	}
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description=" + description + ", url=" + url
				+ ", customerId=" + customerId + ", contributors=" + contributors + "]";
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
	public List<Contributor> getContributors() {
		return contributors;
	}
	public void setContributors(List<Contributor> contributors) {
		this.contributors = contributors;
	}
	
	
}
