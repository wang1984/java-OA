package com.java.oa.dao;

import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.Template;

public interface TemplateDao extends BaseDao<Template> {
	void delete(Long id); 
}
