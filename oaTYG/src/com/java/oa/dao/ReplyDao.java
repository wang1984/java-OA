package com.java.oa.dao;

import java.util.List;

import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.Forum;
import com.java.oa.domain.PageBean;
import com.java.oa.domain.Reply;
import com.java.oa.domain.Topic;

public interface ReplyDao extends BaseDao<Reply> {
	 
	 List<Reply> findByTopic(Topic topic) ;
	 void save(Reply reply,Topic topic,Forum forum);
	 
	 PageBean getPageBeanByTopic(int pageNum, Topic topic);
}
