package com.java.oa.service;

import java.util.List;

import com.java.oa.domain.Forum;
import com.java.oa.service.base.BaseService;

public interface ForumService extends BaseService<Forum>{

	void moveDown(Long id);

	void moveUp(Long id);

	public void save(Forum forum);
	
	public List<Forum> findAll();
}
