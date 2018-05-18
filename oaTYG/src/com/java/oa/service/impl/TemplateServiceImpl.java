package com.java.oa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.oa.dao.TemplateDao;
import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.Template;
import com.java.oa.service.TemplateService;
import com.java.oa.service.base.impl.BaseServiceImpl;
@Service("templateService")
public class TemplateServiceImpl extends BaseServiceImpl<Template> implements TemplateService{
	
	@Resource(name="templateDao")
	private TemplateDao templateDao;
	
	@Override
	public BaseDao getBaseDao() {
		return this.templateDao;
	} 
	
	@Override
	@Transactional
	public void delete(Long id) {
		 this.templateDao.delete(id);
	}
}
