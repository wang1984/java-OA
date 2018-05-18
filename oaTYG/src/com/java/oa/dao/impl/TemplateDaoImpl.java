package com.java.oa.dao.impl;

import java.io.File;

import org.springframework.stereotype.Repository;

import com.java.oa.dao.TemplateDao;
import com.java.oa.dao.base.impl.BaseDaoImpl;
import com.java.oa.domain.Template;

@Repository("templateDao")
public class TemplateDaoImpl extends BaseDaoImpl<Template> implements TemplateDao {

	@Override
	public void delete(Long id) {
		// 删除数据库记录
		Template template = getById(id);
		getSession().delete(template);

		// 删除文件
		File file = new File(template.getPath());
		if (file.exists()) {
			file.delete();
		}
	}

}
 