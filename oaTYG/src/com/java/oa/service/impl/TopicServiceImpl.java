package com.java.oa.service.impl;



import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.oa.cfg.Configuration;
import com.java.oa.dao.TopicDao;
import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.Forum;
import com.java.oa.domain.PageBean;
import com.java.oa.domain.Reply;
import com.java.oa.domain.Topic;
import com.java.oa.service.TopicService;
import com.java.oa.service.base.impl.BaseServiceImpl;

@Service("topicService")
@Transactional
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends BaseServiceImpl<Topic> implements TopicService {

	@Resource(name="topicDao")
	private TopicDao topicDao;
	 
	@Override
	public BaseDao getBaseDao() {
		return this.topicDao;
	}

	@Override
	public List<Topic> findByForum(Forum forum) {
	 
		 return this.topicDao.findByForum(forum);
	}

	@Override
	@Transactional
	public void save(Topic topic) {
		// 1，设置属性并保存
		topic.setType(Topic.TYPE_NORMAL); // 普通帖
		topic.setReplyCount(0);
		topic.setLastReply(null);
		topic.setPostTime(new Date()); // 当前时间
		topic.setLastUpdateTime(topic.getPostTime()); // 默认为主题的发表时间
		
		// 2，更新相关的信息
		Forum forum = topic.getForum();
		forum.setTopicCount(forum.getTopicCount() + 1); // 主题数量
		forum.setArticleCount(forum.getArticleCount() + 1); // 文章数量（主题+回复）
		forum.setLastTopic(topic); // 最后发表的主题
		
		 this.topicDao.save(topic,forum);
	}
    
	
	@Override
	public PageBean getPageBeanByForum(int pageNum, Forum forum) {
			
			return	this.topicDao.getPageBeanByForum(pageNum,forum);
				
	}

}
