package com.java.oa.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.oa.cfg.Configuration;
import com.java.oa.dao.TopicDao;
import com.java.oa.dao.base.impl.BaseDaoImpl;
import com.java.oa.domain.Forum;
import com.java.oa.domain.PageBean;
import com.java.oa.domain.Topic;

@Repository("topicDao")
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements TopicDao {
	
	//有了分页后此方法废弃
	public List<Topic> findByForum(Forum forum) {
		return getSession().createQuery(//
				// 排序：最新状态的排到前面，置顶帖在最上面。  如果t.type是2就是2，如果不是2 就是0
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.list();
	}
	
	@Override
	public void save(Topic topic,Forum forum) {
		
		getSession().save(topic); // 保存
		getSession().update(forum);
	}
	
	//版本1
	@Override
	public PageBean getPageBeanByForum(int pageNum, Forum forum) {
		// 获取pageSize信息
		int pageSize = Configuration.getPageSize();
		// 查询一页的数据列表
		List list = getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();

		// 查询总记录数
		Long count = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Topic t WHERE t.forum=?")//
				.setParameter(0, forum)//
				.uniqueResult();

    	return new PageBean(pageNum, pageSize, count.intValue(), list);
		
	}
	

	



	
}
 