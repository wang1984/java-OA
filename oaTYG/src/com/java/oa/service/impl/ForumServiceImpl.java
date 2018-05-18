package com.java.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.oa.dao.ForumDao;
import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.Forum;
import com.java.oa.service.ForumService;
import com.java.oa.service.base.impl.BaseServiceImpl;
@Service("forumService")
public class ForumServiceImpl extends BaseServiceImpl<Forum> implements ForumService{
	
	@Resource(name="forumDao")
	private ForumDao forumDao;
	@Override
	public BaseDao getBaseDao() {
		return this.forumDao;
	}
	
	public void save(Forum forum){
		this.forumDao.save(forum);
	}
	
	public List<Forum> findAll(){
		return this.forumDao.findAll();
	}
	
	public void moveUp(Long id) {
		this.forumDao.moveUp(id);
	}
	public void moveDown(Long id) {
	    this.forumDao.moveDown(id);
	}
	
	
	 
}
