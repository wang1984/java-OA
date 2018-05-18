package com.java.oa.service;

import com.java.oa.domain.Template;
import com.java.oa.service.base.BaseService;

public interface TemplateService extends BaseService<Template>{
   void delete(Long id);
}
