package com.java.oa.service.base;

import java.io.Serializable;
import java.util.List;

import com.java.oa.domain.PageBean;
import com.java.oa.util.QueryHelper;

public interface BaseService<T> {
	List<T> findAll();
	 
	void delete(Long id);
 
	void save(T entity);

	T getById(Long id);
 
	void update(T entity);
	
	List<T> getByIds(Long[] ids); 
	
	//版本2 
	PageBean getPageBean(int pageNum, String hql, Object[] args);
	
	//最终版
	PageBean getPageBean(int pageNum, QueryHelper queryHelper);
}
