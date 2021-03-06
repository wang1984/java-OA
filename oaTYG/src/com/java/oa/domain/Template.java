package com.java.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 申请模板
 */
public class Template {
	private Long id;
	private String name;
	private String processKey;//流程定义名称
	private String path; // 上传文件的路径
	private Set<Application> applications = new HashSet<Application>();
	
	
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
	public String getProcessKey() {
		return processKey;
	}
	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public Set<Application> getApplications() {
		return applications;
	}

	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}
	
}
