package com.java.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.oa.dao.ForumDao;
import com.java.oa.dao.base.impl.BaseDaoImpl;
import com.java.oa.domain.Forum;

@Repository("forumDao")
public class ForumDaoImpl extends BaseDaoImpl<Forum> implements ForumDao {
	/**
	 * 在查询列表时要按position的值排序
	 */
	public List<Forum> findAll() {
		return getSession().createQuery(//
				"FROM Forum f ORDER BY f.position")//
				.list();
	}
 
	/**
	 * 重写save()方法，在里面要设置position的值
	 */
	public void save(Forum forum) {
		// 保存到数据库
		getSession().save(forum);//保存后就有了id值

		// 设置position的值（可以使用id的值）
		forum.setPosition( forum.getId().intValue() );//id是long型,position是 int型所以要将long 类型转成 int 
	}
	
	
	public void moveUp(Long id) {
		// 1，找出要交位置号的Forum对象
		//当前的
		Forum forum = getById(id); 
		//上一个(排序小于当前的最大值)
		Forum other = (Forum) getSession().createQuery(//
				"FROM Forum f WHERE f.position<? ORDER BY f.position DESC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)// 从结果列表中的哪个索引开始取数据
				.setMaxResults(1)// 最多取几条数据
				.uniqueResult();

		// 最上面不能上移
		if (other == null) {
			return;
		}

		// 2，交换position的值
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);

		// 3，保存到数据库中
		getSession().update(forum);
		getSession().update(other);
	}

	public void moveDown(Long id) {
		// 1，找出要交位置号的Forum对象
		//当前的
		Forum forum = getById(id);
		//下一个(排序大于当前的最小值)
		Forum other = (Forum) getSession().createQuery(// 
				"FROM Forum f WHERE f.position>? ORDER BY f.position ASC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)// 从结果列表中的哪个索引开始取数据
				.setMaxResults(1)// 最多取几条数据
				.uniqueResult();

		// 最下面不能下移
		if (other == null) {
			return;
		}

		// 2，交换position的值
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);

		// 3，保存到数据库中
		getSession().update(forum);
		getSession().update(other);
	}
}
 