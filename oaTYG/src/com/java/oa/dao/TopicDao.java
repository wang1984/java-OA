package com.java.oa.dao;

import java.util.List;

import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.Forum;
import com.java.oa.domain.PageBean;
import com.java.oa.domain.Topic;

public interface TopicDao extends BaseDao<Topic> {

	 List<Topic> findByForum(Forum forum) ;
	
	 PageBean getPageBeanByForum(int pageNum, Forum forum);
	 
	 void save(Topic topic,Forum forum) ;
}
