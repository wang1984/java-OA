package com.java.oa.service;

import java.util.List;

import com.java.oa.domain.Forum;
import com.java.oa.domain.PageBean;
import com.java.oa.domain.Topic;
import com.java.oa.service.base.BaseService;

public interface TopicService extends BaseService<Topic>{
	 
	
	 List<Topic> findByForum(Forum forum) ;
	
	 PageBean getPageBeanByForum(int pageNum, Forum forum);
    
	 void save(Topic topic) ;
}
