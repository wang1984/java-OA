package com.java.oa.service;

import java.util.List;

import com.java.oa.domain.PageBean;
import com.java.oa.domain.Reply;
import com.java.oa.domain.Topic;
import com.java.oa.service.base.BaseService;

public interface ReplyService extends BaseService<Reply>{

	 List<Reply> findByTopic(Topic topic) ;
	 void save(Reply reply);
	 PageBean getPageBeanByTopic(int pageNum, Topic topic);

}
