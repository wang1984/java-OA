package com.java.oa.dao;

import java.util.List;

import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.Forum;

public interface ForumDao extends BaseDao<Forum> {
	public void save(Forum forum);
	public List<Forum> findAll();
	public void moveUp(Long id);
	public void moveDown(Long id); 
	
}
