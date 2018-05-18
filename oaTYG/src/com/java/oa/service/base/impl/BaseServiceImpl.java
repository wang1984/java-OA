package com.java.oa.service.base.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.PageBean;
import com.java.oa.service.base.BaseService;
import com.java.oa.util.QueryHelper;
@SuppressWarnings("unchecked") 
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T>{
    
	public abstract BaseDao getBaseDao();
	
	@Override
	public List<T> findAll() {
		return this.getBaseDao().findAll(); 
	}
	
	@Override
	public void delete(Long id) {
		this.getBaseDao().delete(id);
	}
	
	@Override
	public void save(T t) {
		this.getBaseDao().save(t);
	}

	@Override
	public T getById(Long id) {
		return (T) this.getBaseDao().getById(id);
	}
	
	@Override
	public void update(T t) {
		this.getBaseDao().update(t); 
	}
	
	@Override
	public List<T> getByIds(Long[] ids) { 
		return this.getBaseDao().getByIds(ids);
	} 
	
	//版本2 
    public PageBean getPageBean(int pageNum, String hql, Object[] args){
		return  this.getBaseDao().getPageBean(pageNum,hql,args);
	}
    
    //最终版
    public PageBean getPageBean(int pageNum, QueryHelper queryHelper){
		return  this.getBaseDao().getPageBean(pageNum,queryHelper);
	}
}
