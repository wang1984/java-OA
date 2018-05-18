package com.java.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.oa.cfg.Configuration;
import com.java.oa.dao.ReplyDao;
import com.java.oa.dao.base.impl.BaseDaoImpl;
import com.java.oa.domain.Forum;
import com.java.oa.domain.PageBean;
import com.java.oa.domain.Reply;
import com.java.oa.domain.Topic;

@Repository("replyDao")
public class ReplyDaoImpl extends BaseDaoImpl<Reply> implements ReplyDao {
	public List<Reply> findByTopic(Topic topic) {
		return getSession().createQuery(//
				// 排序：最新的回复排到最后面
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
				.setParameter(0, topic)//
				.list();
	}

	@Override
	public void save(Reply reply,Topic topic,Forum forum) {
	 
		getSession().save(reply);

		getSession().update(topic);
		getSession().update(forum);
	}
    
	//版本1
	@Override
	public PageBean getPageBeanByTopic(int pageNum, Topic topic) {
		        // 获取pageSize信息
				int pageSize = Configuration.getPageSize();

				// 查询一页的数据列表
				List list = getSession().createQuery(//
						"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
						.setParameter(0, topic)//
						.setFirstResult((pageNum - 1) * pageSize)//
						.setMaxResults(pageSize)//
						.list();

				// 查询总记录数
				Long count = (Long) getSession().createQuery(//
						"SELECT COUNT(*) FROM Reply r WHERE r.topic=?")//
						.setParameter(0, topic)//
						.uniqueResult();

				return new PageBean(pageNum, pageSize, count.intValue(), list);
	} 


    
	
}
 