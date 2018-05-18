package com.java.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.oa.dao.DepartmentDao;
import com.java.oa.dao.RoleDao;
import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.Department;
import com.java.oa.domain.Role;
import com.java.oa.service.DepartmentService;
import com.java.oa.service.RoleService;
import com.java.oa.service.base.impl.BaseServiceImpl;

@Service("departmentService")

public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
 
	@Override
	public BaseDao getBaseDao() {
		return this.departmentDao;
	}
	 
	// 默认显示顶级部门列表 
	public List<Department> findChildren(Long parentId) {
		return  departmentDao.findChildren(parentId);
	}
	
	// 显示指定部门的子部门列表
	public List<Department> findTopList() {
		return  departmentDao.findTopList(); 
	}

}
