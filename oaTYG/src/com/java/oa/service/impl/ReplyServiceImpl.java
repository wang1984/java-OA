package com.java.oa.service.impl;



import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.oa.dao.ReplyDao;
import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.Forum;
import com.java.oa.domain.PageBean;
import com.java.oa.domain.Reply;
import com.java.oa.domain.Topic;
import com.java.oa.domain.User;
import com.java.oa.service.ReplyService;
import com.java.oa.service.base.impl.BaseServiceImpl;

@Service("replyService")
@Transactional
@SuppressWarnings("unchecked")
public class ReplyServiceImpl extends BaseServiceImpl<Reply> implements ReplyService {
	
	@Resource(name="replyDao")
	private ReplyDao replyDao;
	 
	@Override
	public BaseDao getBaseDao() {
		return this.replyDao;
	}

	@Override
	public List<Reply> findByTopic(Topic topic) {
		 return this.replyDao.findByTopic(topic);
	}
    
	@Override
	@Transactional
	public void save(Reply reply) {
		
		// 1，设置属性并保存
		reply.setDeleted(false); // 默认为未删除
		reply.setPostTime(new Date()); // 当前时间
		
		// 2，更新相关的信息
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();

		forum.setArticleCount(forum.getArticleCount() + 1); // 版块的文章数量（主题+回复）
		topic.setReplyCount(topic.getReplyCount() + 1); // 主题的回复数量
		topic.setLastUpdateTime(reply.getPostTime()); // 主题的最后更新时间（主题发表的时间或最后回复的时间）
		topic.setLastReply(reply); // 主题的最后发表的回复
		
		
		 this.replyDao.save(reply,topic,forum);
	}
    
	//版本1
	@Override
	public PageBean getPageBeanByTopic(int pageNum, Topic topic) {
		 
		return  this.replyDao.getPageBeanByTopic(pageNum,topic);
	}
	 
}
