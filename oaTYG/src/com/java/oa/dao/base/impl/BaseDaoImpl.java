package com.java.oa.dao.base.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java.oa.cfg.Configuration;
import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.PageBean;
import com.java.oa.util.QueryHelper;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	private Class classt;
	
	//无参构造函数    反射获取泛型的类型（真实类型）
	//this 代表的是 BaseDaoImpl的子类
	public BaseDaoImpl(){
	    ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		this.classt = (Class)type.getActualTypeArguments()[0];
	}
 
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save(T entity) {
		 
		getSession().save(entity);
		 
	}

	@Override
	public void delete(Long id) {
		if (id == null) {
			return;
		}

		Object entity = getById(id);
		if (entity != null) {
			getSession().delete(entity);
		}
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
		 
	}

	@Override
	public T getById(Long id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(classt, id);
		}
	}
	
    /*
     clazz.getSimpleName() 简单名称（只有类名）
	 clazz.getName()       全限定名称（带着包名的）
    */
	@Override
	public List<T> getByIds(Long[] ids) {
		if(ids == null || ids.length == 0){
			return Collections.EMPTY_LIST;//当我们返回一个集合，而这个集合没有值，我们一般不是返回null，而是返回空的集合
		}
		return getSession().createQuery(
				// 注意空格！
				"FROM " + classt.getSimpleName() + " WHERE id IN (:ids)")//使用名字的形式 而不是 位置的形式
				.setParameterList("ids", ids)
				// 注意因为参数是一个数组所以一定要使用setParameterList("参数名","数组类型的参数")
				.list();
	}

	@Override
	public List<T> findAll() {
		// 注意空格！
       return getSession().createQuery("FROM " + classt.getSimpleName()).list();
	}
	
	//版本2
	// 公共的查询分页信息的方法
	public PageBean getPageBean(int pageNum, String hql, Object[] args) {
			System.out.println("------------> BaseDao中的公用分页方法getPageBean()");

			// 获取pageSize信息
			int pageSize = Configuration.getPageSize();

			// 查询一页的数据列表
			Query query = getSession().createQuery(hql);
			if (args != null && args.length > 0) { // 设置参数
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
				}
			}
			query.setFirstResult((pageNum - 1) * pageSize);
			query.setMaxResults(pageSize);
			List list = query.list(); // 查询

			// 查询总记录数
			query = getSession().createQuery("SELECT COUNT(*) " + hql); // 注意空格！
			if (args != null && args.length > 0) { // 设置参数
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
				}
			}
			Long count = (Long) query.uniqueResult(); // 查询

			return new PageBean(pageNum, pageSize, count.intValue(), list);
		}

	
	/**
	 * 公共的查询分页信息的方法（最终版）
	 * 
	 * @param pageNum
	 * @param queryHelper
	 *            查询语句 + 参数列表
	 * @return
	 */
	public PageBean getPageBean(int pageNum, QueryHelper queryHelper) {
		System.out.println("------------> DaoSupportImpl.getPageBean( int pageNum, QueryHelper queryHelper )");

		// 获取pageSize等信息
		int pageSize = Configuration.getPageSize();
		List<Object> parameters = queryHelper.getParameters();

		// 查询一页的数据列表
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		if (parameters != null && parameters.size() > 0) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		query.setFirstResult((pageNum - 1) * pageSize);
		query.setMaxResults(pageSize);
		List list = query.list(); // 查询

		// 查询总记录数
		query = getSession().createQuery(queryHelper.getQueryCountHql()); // 注意空格！
		if (parameters != null && parameters.size() > 0) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) query.uniqueResult(); // 查询

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}
	
}
